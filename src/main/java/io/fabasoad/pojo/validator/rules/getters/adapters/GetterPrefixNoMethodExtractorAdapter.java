package io.fabasoad.pojo.validator.rules.getters.adapters;

import io.fabasoad.pojo.validator.exceptions.ValidationException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GetterPrefixNoMethodExtractorAdapter implements MethodExtractorAdapter {

  @Override
  public Method getMethod(Class<?> clazz, Field field) {
    try {
      return clazz.getMethod(field.getName());
    } catch (NoSuchMethodException e) {
      final String message = String.format("Method '%s' is not found.", field.getName());
      throw new ValidationException(message, e);
    }
  }
}
