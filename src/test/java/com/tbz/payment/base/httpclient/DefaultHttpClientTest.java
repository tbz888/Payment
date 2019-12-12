package com.tbz.payment.base.httpclient;

import com.tbz.payment.TestEnvironment;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class DefaultHttpClientTest extends TestEnvironment {

    @Test
    public void testGet() {
        AbstractHttpClient rest = HttpClientFactory.getInstance();
        Assert.assertNotNull(rest);
        String url = "http://www.baidu.com/s";
        Map<String, String> queryString = new HashMap<>();
        queryString.put("wd", "tbz");
        ResponseEntity<String> response = rest.get(url, null, queryString, String.class);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getBody().contains("<em>TBZ</em>"));
    }

    @Test
    public void testPost() {
        // TODO
    }
}
