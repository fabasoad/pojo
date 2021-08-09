package io.fabasoad.pojo.validator.rules.getters.adapters;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface MethodExtractorAdapter {
  Method getMethod(Class<?> clazz, Field field);
}
