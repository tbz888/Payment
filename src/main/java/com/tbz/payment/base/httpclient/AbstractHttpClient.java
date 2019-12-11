package com.tbz.payment.base.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * HTTP客户端的抽象类
 */
public abstract class AbstractHttpClient {

    private static Logger log = LoggerFactory.getLogger(AbstractHttpClient.class);

    protected RestTemplate rest;

    public AbstractHttpClient() {
        this.rest = new RestTemplate();
    }

    public void setRequestFactory(ClientHttpRequestFactory restFactory) {
        this.rest = new RestTemplate(restFactory);
    }

    /**
     * 调用GET接口
     *
     * @param rawUrl       路径
     * @param headers      请求头
     * @param queryString  输入参数
     * @param responseType 响应体类型
     * @return
     * @throws RestClientException
     */
    public <T> ResponseEntity<T> get(String rawUrl, MultiValueMap<String, String> headers, Map<String, String> queryString, Class<T> responseType)
            throws RestClientException {
        // 请求头
        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);
        // 请求参数
        StringBuilder paramUrl = new StringBuilder(rawUrl);
        if (!CollectionUtils.isEmpty(queryString)) {
            paramUrl.append("?");
            queryString.forEach((key, value) -> {
                paramUrl.append(key).append("=").append("{").append(key).append("}").append("&");
            });
        }
        String url = paramUrl.toString();
        try {
            ResponseEntity<T> response = null;
            if (CollectionUtils.isEmpty(queryString)) {
                response = rest.exchange(url, HttpMethod.GET, requestEntity, responseType);
            } else {
                response = rest.exchange(url, HttpMethod.GET, requestEntity, responseType, queryString);
            }
            return response;
        } catch (Exception e) {
            log.error("GET {} failed.", url, e);
            throw e;
        }
    }

    /**
     * 调用POST接口
     *
     * @param url          路径
     * @param headers      请求头
     * @param params       请求体
     * @param responseType 响应体类型
     * @return
     * @throws RestClientException
     */
    public <T, P> ResponseEntity<T> post(String url, MultiValueMap<String, String> headers, P params, Class<T> responseType)
            throws RestClientException {
        // 请求头和请求体
        HttpEntity<P> requestEntity = new HttpEntity<>(params, headers);
        try {
            ResponseEntity<T> response = rest.exchange(url, HttpMethod.POST, requestEntity, responseType);
            return response;
        } catch (Exception e) {
            log.error("POST {} failed.", url, e);
            throw e;
        }
    }

}
