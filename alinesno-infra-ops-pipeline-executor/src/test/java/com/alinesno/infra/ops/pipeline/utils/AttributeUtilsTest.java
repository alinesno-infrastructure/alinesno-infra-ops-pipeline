package com.alinesno.infra.ops.pipeline.utils;

import com.alinesno.infra.ops.pipeline.dto.ValueAttributeDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttributeUtilsTest {

    @Test
    @DisplayName("测试 convertAttributesToMap 方法")
    public void testConvertAttributesToMap() {
        // 准备测试数据
        ValueAttributeDto attribute1 = new ValueAttributeDto("key1", "value1");
        ValueAttributeDto attribute2 = new ValueAttributeDto("key2", "value2");
        List<ValueAttributeDto> attributes = Arrays.asList(attribute1, attribute2);

        // 调用被测试方法
        Map<String, Object> result = AttributeUtils.convertAttributesToMap(attributes);

        // 验证结果
        assertEquals(2, result.size());
        assertEquals("value1", result.get("key1"));
        assertEquals("value2", result.get("key2"));
    }

    @Test
    @DisplayName("测试 createAttributeMap 方法")
    public void testCreateAttributeMap() {
        // 准备测试数据
        String key = "testKey";
        String value = "testValue";

        // 调用被测试方法
        Map<String, Object> result = AttributeUtils.createAttributeMap(key, value);

        // 验证结果
        assertEquals(1, result.size());
        assertEquals("testValue", result.get("testKey"));
    }
}
