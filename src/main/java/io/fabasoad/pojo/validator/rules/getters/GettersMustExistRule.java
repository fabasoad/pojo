package io.fabasoad.pojo.validator.rules.getters;

import io.fabasoad.pojo.validator.exceptions.ValidationException;
import io.fabasoad.pojo.validator.rules.getters.adapters.GetterPrefixAnyMethodExtractorAdapter;
import io.fabasoad.pojo.validator.rules.getters.adapters.GetterPrefixYesMethodExtractorAdapter;
import io.fabasoad.pojo.validator.rules.getters.adapters.GetterPrefixNoMethodExtractorAdapter;
import io.fabasoad.pojo.validator.rules.getters.adapters.MethodExtractorAdapter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GettersMustExistRule implements GetterRule {

  private final MethodExtractorAdapter methodExtractorAdapter;

  public GettersMustExistRule() {
    this(GetterPrefix.ANY);
  }

  public GettersMustExistRule(final GetterPrefix prefix) {
    methodExtractorAdapter = switch (prefix) {
      case ANY -> new GetterPrefixAnyMethodExtractorAdapter();
      case YES -> new GetterPrefixYesMethodExtractorAdapter();
      case NO -> new GetterPrefixNoMethodExtractorAdapter();
    };
  }

  @Override
  public void accept(final Class<?> clazz) {
    for (final Field field : clazz.getDeclaredFields()) {
      final Method method = methodExtractorAdapter.getMethod(clazz, field);

      if (method.getReturnType() != field.getType()) {
        throw new ValidationException(String.format(
            "Expected return type of '%s' method is %s but %s is found",
            method.getName(), field.getType().getName(), method.getReturnType().getName()));
      }
    }
  }
}
