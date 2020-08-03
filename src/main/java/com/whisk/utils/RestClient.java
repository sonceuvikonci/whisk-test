package com.whisk.utils;

import com.whisk.base.api.APIRequest;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class RestClient {

    private static RestTemplate restTemplate;

    public static ResponseEntity sendRequest(APIRequest request) throws  NoSuchMethodError {
        Reporter.log("\n--- Send POST request URL: " + request.getUrl());

        if (restTemplate == null)
        restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        request.getHeaders().forEach(headers::add);

        HttpEntity<String> entity;

        if (request.getBody() != null) {
            entity = new HttpEntity(request.getBody(), headers);
        } else {
            entity = new HttpEntity(headers);
        }

        ResponseEntity<String> response;

        HttpMethod method;
        switch (request.getType()) {
            case GET:
                method = HttpMethod.GET;
                break;
            case POST:
                method = HttpMethod.POST;
                break;
            case DELETE:
                method = HttpMethod.DELETE;
                break;
            case PUT:
                method = HttpMethod.PUT;
                break;
            default:
                throw new NoSuchMethodError();
        }

        try {
            response = restTemplate.exchange(request.getUrl(), method, entity, String.class);
        } catch (HttpStatusCodeException e) {
            response = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }

        return response;
    }
}

