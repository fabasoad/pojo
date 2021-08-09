package io.fabasoad.pojo.validator.rules.fields;

import io.fabasoad.pojo.validator.exceptions.ValidationException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldsMustBePrivateRule implements FieldRule {

  @Override
  public void accept(final Class<?> clazz) {
    for (Field field : clazz.getDeclaredFields()) {
      if (!Modifier.isPrivate(field.getModifiers())) {
        throw new ValidationException(String.format("Field '%s' is not private", field.getName()));
      }
    }
  }
}
