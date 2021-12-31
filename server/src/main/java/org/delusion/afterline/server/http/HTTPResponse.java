package org.delusion.afterline.server.http;

import java.nio.charset.Charset;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPResponse {
    private static final String statusLineRegex = "^HTTP\\/(?<httpVersion>[1-9][0-9]*(\\.[1-9][0-9]*)?)\\ (?<statusCode>[1-5][0-9][0-9])\\ (?<statusMessage>.*)$";
    private static final String headerLineRegex = "^(?<headerField>[A-Za-z][A-Za-z\\-]*):(?<headerValue>.*)$";

    private String httpVersionStr;
    private HTTPStatusCode statusCode;
    private String statusMsg;

    private HTTPHeaders headers;

    private String content;

    public HTTPResponse() {
        httpVersionStr = "HTTP/1.1";
        statusCode = HTTPStatusCode.Ok;
        statusMsg = "OK";
        headers = new HTTPHeaders();
        content = "\n";
    }

    public HTTPResponse(String stringToParse) {
        String[] lines = stringToParse.split("\n");
        for (int i = 0 ; i < lines.length ; i++) lines[i] = lines[i].endsWith("\r") ? lines[i].substring(0, lines[i].length() - 1) : lines[i];
        Pattern statusLinePattern = Pattern.compile(statusLineRegex);
        Pattern headerLinePattern = Pattern.compile(headerLineRegex);

        String statusLine = lines[0];
        Matcher statusLineMatcher = statusLinePattern.matcher(statusLine);
        httpVersionStr = "HTTP/" + statusLineMatcher.group("httpVersion");
        statusCode = HTTPStatusCode.getCodeFromID(Integer.parseInt(statusLineMatcher.group("statusCode")));
        statusMsg = statusLineMatcher.group("statusMessage");

        headers = new HTTPHeaders();

        int curLine = 1;
        while(curLine < lines.length && !lines[curLine].equals("\n") && !lines[curLine].equals("\r\n")) {
            String l = lines[curLine];
            Matcher hm = headerLinePattern.matcher(l);
            String hf = hm.group("headerField");
            String hv = hm.group("headerValue");
            headers.set(hf, hv);

            curLine++;
        }

        curLine++;

        StringBuilder content_ = new StringBuilder();
        for (; curLine < lines.length ; curLine++) content_.append(lines[curLine]);
        content = content_.toString() + "\r\n";
    }

    public HTTPResponse setStatusCode(HTTPStatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public HTTPResponse setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
        return this;
    }

    public HTTPStatusCode getStatusCode() {
        return statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public HTTPHeaders getHeaders() {
        return headers;
    }

    public String getContent() {
        return content;
    }

    public HTTPResponse setContent(String content) {
        this.content = content;
        headers.set(HTTPHeaderField.ContentLength, Integer.toString(content.length()));
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String statusLine = httpVersionStr + " " + statusCode.code + " " + statusMsg + "\r\n";
        String headersText = headers.toString();
        sb.append(statusLine).append(headersText).append("\r\n").append(content).append("\r\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("TEST GENERATE RESPONSE");
        System.out.println("================================================");
        HTTPResponse resp = new HTTPResponse();
        resp.setStatusCode(HTTPStatusCode.NotFound);
        resp.setStatusMsg("Not Found");
        resp.setContent("{\"test\":43}");
        resp.getHeaders().set(HTTPHeaderField.ContentType,"text/json");
        System.out.println(resp);
        System.out.println("================================================");
    }

    public void setStatusCode(HTTPStatusCode statusCode, boolean useNameForMsg) {
        this.statusCode = statusCode;
        if (useNameForMsg) {
            statusMsg = statusCode.name();
        }
    }

    public HTTPResponse setHeader(HTTPHeaderField hf, String hv) {
        headers.set(hf, hv);
        return this;
    }

    public HTTPResponse setHeader(String hf, String hv) {
        headers.set(hf, hv);
        return this;
    }


    public static final HTTPResponse methodNotAllowed = new HTTPResponse().setStatusCode(HTTPStatusCode.MethodNotAllowed).setStatusMsg("Method Not Allowed");
    private static final String NOTFOUND_PAGE_DEFAULT = "<!DOCTYPE html><html><head><title>Page Not Found</title></head><body><h1>Page Not Found</h1></body></html>";
    public static final HTTPResponse notFound = new HTTPResponse().setStatusCode(HTTPStatusCode.NotFound).setStatusMsg("Not Found").setHeader(HTTPHeaderField.ContentType, "text/html").setContent(NOTFOUND_PAGE_DEFAULT);

    public static HTTPResponse createHTML(String pageText) {
        HTTPResponse httpResponse = new HTTPResponse().setStatusCode(HTTPStatusCode.Ok).setContent(pageText);
        httpResponse.getHeaders().set(HTTPHeaderField.ContentType, "text/html; charset=UTF-8");
        return httpResponse;
    }

    public static HTTPResponse redirectTo(String link) {
        HTTPResponse httpResponse = new HTTPResponse().setStatusCode(HTTPStatusCode.TemporaryRedirect).setStatusMsg("Temporary Redirect");
        httpResponse.getHeaders().set(HTTPHeaderField.Location, link);
        return httpResponse;
    }
}
