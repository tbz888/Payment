package com.tbz.payment.base.httpclient;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class DefaultHttpClient extends AbstractHttpClient {

    private ClientHttpRequestFactory defaultFactory;

    // 连接超时
    private static final int CONNECT_TIMEOUT_MILLIS = 10 * 1000;

    // 读取超时
    private static final int READ_TIMEOUT_MILLIS = 300 * 1000;

    public DefaultHttpClient() {
        defaultFactory = new SimpleClientHttpRequestFactory();
        ((SimpleClientHttpRequestFactory) defaultFactory).setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
        ((SimpleClientHttpRequestFactory) defaultFactory).setReadTimeout(READ_TIMEOUT_MILLIS);
        super.setRequestFactory(defaultFactory);
    }

}
