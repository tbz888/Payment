package com.tbz.payment.base.serialization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.tbz.payment.TestEnvironment;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class JsonUtilsTest extends TestEnvironment {

    @Test
    public void testToObject() throws IOException {
        String json = "{\"ID\":1,\"tAsKs\":[\"type\",\"program\"]}";
        Man m = JsonUtils.toObject(json, Man.class);
        Assert.assertTrue(m.tasks.indexOf("program") > -1);
        Assert.assertTrue(m.tasks.indexOf("type") > -1);
        Assert.assertEquals(1, m.id);
    }

    @Test
    public void testToJsonNode() throws IOException {
        String json = "{\"id\":1,\"tasks\":[\"type\",\"program\"]}";
        JsonNode j = JsonUtils.toJsonNode(json);
        Assert.assertEquals(j.get("id").asInt(), 1);
        Assert.assertEquals(j.get("tasks").get(0).asText(), "type");
        Assert.assertEquals(j.get("tasks").get(1).asText(), "program");
    }

    @Test
    public void testToString() throws IOException {
        String json = "{\"id\":1,\"tasks\":[\"type\",\"program\"]}";
        JsonNode k = JsonUtils.toJsonNode(json);
        Assert.assertTrue(JsonUtils.toString(k).contains("program"));
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Man {
        @JsonProperty
        public int id;

        @JsonProperty
        public List<String> tasks;
    }
}
