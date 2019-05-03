package com.qpf.spring.cloud.demo.zuul.fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommonResponse implements ClientHttpResponse {
    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    @Override
    public InputStream getBody() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("status", 200);
        params.put("message", "无法连接,请检查你的网络");
        return new ByteArrayInputStream(new ObjectMapper().writeValueAsString(params).getBytes("UTF-8"));
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.OK;
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return HttpStatus.OK.value();
    }

    @Override
    public String getStatusText() throws IOException {
        return HttpStatus.OK.getReasonPhrase();
    }

    @Override
    public void close() {

    }
}
