package org.delusion.afterline.server.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPRequest {
    public enum Method {
        GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH
    }

    private static final String requestLineRegex = "(?<method>[a-zA-Z]+)\\ (?<path>\\/([^\\\\\\:\\ ]+)*)\\ HTTP\\/(?<httpVersion>[1-9][0-9]*(\\.[1-9][0-9]*)?)\r?\n?";
    private static final String headerLineRegex = "^(?<headerField>[A-Za-z][A-Za-z\\-]*):(?<headerValue>.*)$";

    private String httpVersionStr;
    private HTTPHeaders headers;
    private String path;
    private Method method;
    private String content;

    public HTTPRequest(Method method) {
        this.method = method;
        path = "/";
        headers = new HTTPHeaders();
        httpVersionStr = "HTTP/1.1";
    }

    public HTTPRequest(String stringToParse) {
        if (stringToParse.isEmpty()) {
            System.out.println("WARN: cannot parse a blank request");
            throw new RuntimeException("Bad HTTP Request");
        } else {
            System.out.println("Parsing " + stringToParse);
            String[] lines = stringToParse.split("\n");
            for (int i = 0; i < lines.length; i++)
                lines[i] = lines[i].endsWith("\r") ? lines[i].substring(0, lines[i].length() - 1) : lines[i];

            Pattern requestLinePattern = Pattern.compile(requestLineRegex);
            Pattern headerLinePattern = Pattern.compile(headerLineRegex);

            String requestLine = lines[0];
            Matcher requestLineMatcher = requestLinePattern.matcher(requestLine);
            if (requestLineMatcher.matches()) {
                System.out.println("matches");
            }

            String vs = requestLineMatcher.group("httpVersion");
            httpVersionStr = "HTTP/" + vs;
            String methodStr = requestLineMatcher.group("method");
            method = Method.valueOf(methodStr.toUpperCase());
            path = requestLineMatcher.group("path");
            System.out.println("REQUEST PATH: " + path);

            headers = new HTTPHeaders();

            int curLine = 1;
            while (curLine < lines.length && !lines[curLine].equals("\n") && !lines[curLine].equals("\r\n") && !lines[curLine].isEmpty()) {
                String l = lines[curLine];
                Matcher hm = headerLinePattern.matcher(l);
                hm.matches();
                String hf = hm.group("headerField");
                String hv = hm.group("headerValue").strip();
                headers.set(hf, hv);

                curLine++;
            }

            curLine++;

            StringBuilder content_ = new StringBuilder();
            for (; curLine < lines.length; curLine++) content_.append(lines[curLine]);
            content = content_.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String statusLine = method.toString() + " " + path + " " + httpVersionStr + "\r\n";
        String headersText = headers.toString();
        sb.append(statusLine).append(headersText).append("\r\n").append(content).append("\r\n");
        return sb.toString();
    }


    public HTTPHeaders getHeaders() {
        return headers;
    }

    public String getPath() {
        return path;
    }

    public HTTPRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public HTTPRequest setMethod(Method method) {
        this.method = method;
        return this;
    }

    public String getContent() {
        return content;
    }

    public HTTPRequest setContent(String content) {
        this.content = content;
        return this;
    }

    /*public static void main(String[] args) {
        String testRequest = """
GET /http/http_message_examples.htm HTTP/1.1
Host: www.tutorialspoint.com
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:95.0) Gecko/20100101 Firefox/95.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,**;q=0.8
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate, br
Connection: keep-alive
Upgrade-Insecure-Requests: 1
Sec-Fetch-Dest: document
Sec-Fetch-Mode: navigate
Sec-Fetch-Site: cross-site
Cache-Control: max-age=0
""";

        HTTPRequest req = new HTTPRequest(testRequest);
        System.out.println("Method = " + req.getMethod());
        System.out.println("Path = " + req.getPath());
        System.out.println("Headers:");
        req.getHeaders().getAll().forEach((hf, hv) -> System.out.println("  " + hf + " = `" + hv + "`"));
        System.out.println("Content:");
        System.out.println(req.content);
    }*/
}
