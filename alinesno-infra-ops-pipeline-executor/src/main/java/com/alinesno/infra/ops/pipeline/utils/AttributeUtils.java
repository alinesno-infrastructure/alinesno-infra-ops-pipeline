package com.alinesno.infra.ops.pipeline.utils;

import com.alinesno.infra.ops.pipeline.dto.ValueAttributeDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 属性工具类，用于处理属性相关操作。
 */
public class AttributeUtils {
    /**
     * 将ValueAttributeDto对象列表转换为Map<String, Object>。
     *
     * @param attributes ValueAttributeDto对象列表
     * @return 转换后的属性Map
     */
    public static Map<String, Object> convertAttributesToMap(List<ValueAttributeDto> attributes) {
        Map<String, Object> attributesMap = new HashMap<>();
        for (ValueAttributeDto attribute : attributes) {
            attributesMap.put(attribute.getKey(), attribute.getValue());
        }
        return attributesMap;
    }

    /**
     * 使用提供的键和值创建一个包含单个属性的Map<String, Object>。
     *
     * @param key   属性键
     * @param value 属性值
     * @return 创建的属性Map
     */
    public static Map<String, Object> createAttributeMap(String key, String value) {
        Map<String, Object> attributeMap = new HashMap<>();
        attributeMap.put(key, value);
        return attributeMap;
    }

    /**
     * 将Map<String, Object>转换为List<ValueAttributeDto>。
     *
     * @param attributeMap 属性Map
     * @return 转换后的ValueAttributeDto列表
     */
    public static List<ValueAttributeDto> convertMapToAttributes(Map<String, Object> attributeMap) {
        List<ValueAttributeDto> attributes = new ArrayList<>();
        for (Map.Entry<String, Object> entry : attributeMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            ValueAttributeDto attribute = new ValueAttributeDto(key, value+"");
            attributes.add(attribute);
        }
        return attributes;
    }
}
