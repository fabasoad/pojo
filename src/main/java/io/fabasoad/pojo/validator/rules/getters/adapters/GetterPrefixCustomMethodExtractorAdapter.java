package io.fabasoad.pojo.validator.rules.getters.adapters;

import io.fabasoad.pojo.validator.exceptions.ValidationException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

public record GetterPrefixCustomMethodExtractorAdapter(String prefix)
    implements MethodExtractorAdapter {

  private String buildMethodName(final Field field) {
    final String fieldName = field.getName();
    if (prefix == null || prefix.isEmpty()) {
      return fieldName;
    }
    return prefix + fieldName.substring(0, 1).toUpperCase(Locale.ROOT) + fieldName.substring(1);
  }

  @Override
  public Method getMethod(final Class<?> clazz, final Field field) {
    final String methodName = buildMethodName(field);
    try {
      return clazz.getMethod(methodName);
    } catch (NoSuchMethodException e) {
      final String message = String.format("Method '%s' is not found.", methodName);
      throw new ValidationException(message, e);
    }
  }
}
