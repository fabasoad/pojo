package io.fabasoad.pojo.validator.rules.fields;

import io.fabasoad.pojo.validator.exceptions.ValidationException;
import java.lang.reflect.Field;
import java.util.function.Function;
import java.util.function.Predicate;

public class CustomFieldRule implements FieldRule {

  private final Predicate<Field> checker;
  private final Function<Field, String> errorMessageRetriever;

  public CustomFieldRule(
      final Predicate<Field> checker, final Function<Field, String> errorMessageRetriever) {
    this.checker = checker;
    this.errorMessageRetriever = errorMessageRetriever;
  }

  @Override
  public void accept(final Class<?> clazz) {
    for (Field field : clazz.getDeclaredFields()) {
      if (!checker.test(field)) {
        throw new ValidationException(errorMessageRetriever.apply(field));
      }
    }
  }
}
