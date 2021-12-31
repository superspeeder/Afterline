package org.delusion.afterline.server.http;

import java.util.HashMap;
import java.util.Map;

public class HTTPHeaders {
    private HashMap<String, String> rawHeaders = new HashMap<>();

    public HTTPHeaders set(String hf, String hv) {
        rawHeaders.put(hf, hv);
        return this;
    }

    public HTTPHeaders set(HTTPHeaderField hf, String hv) {
        rawHeaders.put(hf.text, hv);
        return this;
    }

    public String get(String hf) {
        return rawHeaders.get(hf);
    }

    public String get(HTTPHeaderField hf) {
        return rawHeaders.get(hf.text);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        rawHeaders.forEach((hf, hv) -> {
            sb.append(hf).append(": ").append(hv).append("\r\n");
        });
        return sb.toString();
    }

    public Map<String, String> getAll() {
        return rawHeaders;
    }
}
