package io.fabasoad.pojo.validator;

import io.fabasoad.pojo.reflections.Reflections;
import io.fabasoad.pojo.validator.rules.Rule;
import io.fabasoad.pojo.validator.testers.Tester;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PojoValidatorBuilder {

  private final List<Tester<? extends Rule>> testers = new ArrayList<>();
  private final Set<Class<?>> classes;

  private PojoValidatorBuilder(final Set<Class<?>> classes) {
    this.classes = classes;
  }

  public static PojoValidatorBuilder create(final String packageName) {
    return create(packageName, false);
  }

  public static PojoValidatorBuilder create(final String packageName, final boolean recursive) {
    final Reflections reflections = new Reflections();
    return new PojoValidatorBuilder(reflections.getClasses(packageName, recursive));
  }

  public <T extends Rule> PojoValidatorBuilder with(final Tester<T> tester) {
    testers.add(tester);
    return this;
  }

  public PojoValidator build() {
    return new PojoValidator(classes, testers);
  }
}
