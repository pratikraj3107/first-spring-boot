package com.pratik.first.spring.boot.repository;

import java.util.List;
import java.util.Map;

public interface CustomRepository {
    <T> List<T> getEntityWithCustomCriteria(Map<String, Object> fieldMap, Class<T> classType);
}
