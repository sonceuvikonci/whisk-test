package com.whisk.base.api;

import com.whisk.utils.Constants;
import com.whisk.utils.RestClient;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class APIRequest {

    private final APIRequestTypes type;
    private String url;
    private final Map<String, String> headers;
    private String body;

    APIRequest(APIRequestTypes type, String url) {
        this.type = type;
        this.url = url;
        headers = new LinkedHashMap<>();
        headers.put("user-agent", Constants.USERAGENT);
    }

    public APIRequest setBody(String body) {
        this.body = body;
        return this;
    }

    public APIRequest setURL(String url) {
        this.url = url;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public APIRequest addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public APIRequestTypes getType() {
        return type;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public ResponseEntity send() {
        return RestClient.sendRequest(this);
    }

    public static APIRequest createGetRequest(String url) {
        return new APIRequest(APIRequestTypes.GET, url);
    }

    public static APIRequest createPostRequest(String url) {
        return new APIRequest(APIRequestTypes.POST, url);
    }

    public static APIRequest createDeleteRequest(String url) {
        return new APIRequest(APIRequestTypes.DELETE, url);
    }

    public static APIRequest createPutRequest(String url) {
        return new APIRequest(APIRequestTypes.PUT, url);
    }
}
