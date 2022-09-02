package io.fabasoad.pojo.validator.rules.getters;

import io.fabasoad.pojo.validator.exceptions.ValidationException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GettersMustExistRule implements GetterRule {

  private final GetterPrefix prefix;

  public GettersMustExistRule() {
    this(GetterPrefix.auto());
  }

  public GettersMustExistRule(final GetterPrefix prefix) {
    this.prefix = prefix;
  }

  @Override
  public void accept(final Class<?> clazz) {
    for (final Field field : clazz.getDeclaredFields()) {
      final Method method = prefix.getMethod(clazz, field);

      if (method.getReturnType() != field.getType()) {
        throw new ValidationException(
            String.format(
                "Expected return type of '%s' method is %s but %s is found",
                method.getName(), field.getType().getName(), method.getReturnType().getName()));
      }
    }
  }
}
