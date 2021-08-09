package io.fabasoad.pojo.validator;

import io.fabasoad.pojo.validator.rules.Rule;
import io.fabasoad.pojo.validator.testers.Tester;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public record PojoValidator(Set<Class<?>> classes, List<Tester<? extends Rule>> testers) {

  public void validate(final Predicate<Class<?>> filter) {
    classes.stream().filter(filter).forEach(c -> testers.forEach(t -> t.accept(c)));
  }

  public void validate() {
    validate(ignored -> true);
  }
}
