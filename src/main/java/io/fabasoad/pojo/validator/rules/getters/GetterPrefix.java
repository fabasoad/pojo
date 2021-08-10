package io.fabasoad.pojo.validator.rules.getters;

import io.fabasoad.pojo.validator.rules.getters.adapters.GetterPrefixAutoMethodExtractorAdapter;
import io.fabasoad.pojo.validator.rules.getters.adapters.GetterPrefixCustomMethodExtractorAdapter;
import io.fabasoad.pojo.validator.rules.getters.adapters.GetterPrefixNoMethodExtractorAdapter;
import io.fabasoad.pojo.validator.rules.getters.adapters.GetterPrefixYesMethodExtractorAdapter;
import io.fabasoad.pojo.validator.rules.getters.adapters.MethodExtractorAdapter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GetterPrefix {

  private final MethodExtractorAdapter methodExtractorAdapter;

  private GetterPrefix(final MethodExtractorAdapter methodExtractorAdapter) {
    this.methodExtractorAdapter = methodExtractorAdapter;
  }

  public static GetterPrefix with(final String prefix) {
    return new GetterPrefix(new GetterPrefixCustomMethodExtractorAdapter(prefix));
  }

  public static GetterPrefix with() {
    return new GetterPrefix(new GetterPrefixYesMethodExtractorAdapter());
  }

  public static GetterPrefix auto() {
    return new GetterPrefix(new GetterPrefixAutoMethodExtractorAdapter());
  }

  public static GetterPrefix without() {
    return new GetterPrefix(new GetterPrefixNoMethodExtractorAdapter());
  }

  public Method getMethod(final Class<?> clazz, final Field field) {
    return methodExtractorAdapter.getMethod(clazz, field);
  }
}
