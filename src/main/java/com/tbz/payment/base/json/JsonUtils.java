package com.tbz.payment.base.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * JSON序列化与反序列化的工具类
 */
public class JsonUtils {

    private static Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true); // 序列化成对象时忽略key的大小写
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 反序列忽略未知属性
    }

    /**
     * String转换成POJO
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T toObject(String json, Class<T> type) throws IOException {
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            log.error("String to Object failed.", e);
            throw e;
        }
    }

    /**
     * String转换成树形结构的JsonNode
     *
     * @param json
     * @return
     * @throws IOException
     */
    public static JsonNode toJsonNode(String json) throws IOException {
        try {
            return objectMapper.readTree(json);
        } catch (IOException e) {
            log.error("String to JsonNode failed.", e);
            throw e;
        }
    }

    /**
     * JsonNode/POJO转换成String
     *
     * @param json
     * @return
     * @throws IOException
     */
    public static String toString(Object json) throws IOException {
        try {
            return objectMapper.writeValueAsString(json);
        } catch (IOException e) {
            log.error("Object to String failed.", e);
            throw e;
        }
    }
}

