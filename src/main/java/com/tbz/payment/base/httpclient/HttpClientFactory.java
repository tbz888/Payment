package com.tbz.payment.base.httpclient;

public class HttpClientFactory {

    private static AbstractHttpClient client = new DefaultHttpClient();

    private HttpClientFactory() {
    }

    public static AbstractHttpClient getInstance() {
        return client;
    }
}
