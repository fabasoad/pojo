package io.fabasoad.pojo.validator.rules.getters.adapters;

import io.fabasoad.pojo.validator.exceptions.AggregateException;
import io.fabasoad.pojo.validator.exceptions.ValidationException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class GetterPrefixAutoMethodExtractorAdapter implements MethodExtractorAdapter {

  @Override
  public Method getMethod(Class<?> clazz, Field field) {
    MethodExtractorAdapter methodExtractorAdapter = new GetterPrefixYesMethodExtractorAdapter();
    try {
      return methodExtractorAdapter.getMethod(clazz, field);
    } catch (ValidationException e1) {
      methodExtractorAdapter = new GetterPrefixNoMethodExtractorAdapter();
      try {
        return methodExtractorAdapter.getMethod(clazz, field);
      } catch (ValidationException e2) {
        final String message = Objects.equals(e1.getMessage(), e2.getMessage())
          ? e1.getMessage()
          : String.format("%s %s", e1.getMessage(), e2.getMessage());
        throw new ValidationException(message, new AggregateException(e1, e2));
      }
    }
  }
}
