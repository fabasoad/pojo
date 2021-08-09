package io.fabasoad.pojo.validator.rules.fields;

import io.fabasoad.pojo.validator.exceptions.ValidationException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldsMustBeFinalRule implements FieldRule {

  @Override
  public void accept(final Class<?> clazz) {
    for (Field field : clazz.getDeclaredFields()) {
      if (!Modifier.isFinal(field.getModifiers())) {
        throw new ValidationException(String.format("Field '%s' is not final", field.getName()));
      }
    }
  }
}
