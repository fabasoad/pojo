package io.fabasoad.pojo.validator.testers;

import io.fabasoad.pojo.validator.rules.Rule;
import io.fabasoad.pojo.validator.rules.getters.GetterRule;
import java.util.function.Consumer;

public interface Tester<T extends Rule> extends Consumer<Class<?>> {

  T[] rules();

  @Override
  default void accept(final Class<?> clazz) {
    for (final T rule : rules()) {
      rule.accept(clazz);
    }
  }
}
