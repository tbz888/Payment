package com.tbz.payment.base.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class JsonUtilsTest {
    @Before
    public void testBefore() {
        System.getProperties().put("root.dir", "/Users/tbz/Payment");
    }

    @Test
    public void testToString() throws IOException {
        Man man = new Man();
        man.id = 1;
        man.tasks = Lists.newArrayList("type", "program");
        String str = JsonUtils.toString(man);
        Assert.assertTrue(str.contains("type"));
        Assert.assertTrue(str.contains("program"));
        Assert.assertTrue(str.contains("tasks"));
        Assert.assertTrue(str.contains("id"));
    }

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

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Man {
        @JsonProperty
        public int id;

        @JsonProperty
        public List<String> tasks;
    }
}
