package com.shilovi.drones.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j(topic = "UTIL")
@UtilityClass
public class JsonHelper {

    private final ObjectMapper MAPPER = new ObjectMapper();

    public JsonNode getNodeFromString(String node) {
        try {
            return MAPPER.readTree(node);
        } catch (JsonProcessingException e) {
            logger.error("Error mapping to json!", e);
            throw new RuntimeException("Error mapping to json!", e);
        }
    }

    public JsonNode getNodeFromObject(Object object) {
        return MAPPER.valueToTree(object);
    }

    public String getStringFromNode(JsonNode node) {
        try {
            return MAPPER.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            logger.error("Error mapping to String!", e);
            throw new RuntimeException("Error mapping to String!", e);
        }
    }

    public String getStringFromObject(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Error mapping to String!", e);
            throw new RuntimeException(e);
        }
    }

    public <T> T getObjectFromString(String node, Class<T> clazz) {
        try {
            return MAPPER.readValue(node, clazz);
        } catch (JsonProcessingException e) {
            logger.error("Error mapping to String={}", node, e);
            throw new RuntimeException("Error mapping to String!", e);
        }
    }

    public String getStringField(JsonNode node, String path) {
        return Optional.ofNullable(node.get(path)).map(JsonNode::asText).filter(e -> e.length() > 0).orElse(null);
    }

    public ObjectMapper getMapper() {
        return MAPPER;
    }
}