# POJO testing library

[![Stand With Ukraine](https://raw.githubusercontent.com/vshymanskyy/StandWithUkraine/main/badges/StandWithUkraine.svg)](https://stand-with-ukraine.pp.ua)
![GitHub release](https://img.shields.io/github/v/release/fabasoad/pojo?include_prereleases)
![unit-tests](https://github.com/fabasoad/pojo/actions/workflows/unit-tests.yml/badge.svg)
![security](https://github.com/fabasoad/pojo/actions/workflows/security.yml/badge.svg)
![linting](https://github.com/fabasoad/pojo/actions/workflows/linting.yml/badge.svg)

## Import

1. Add [GitHub Packages](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-to-github-packages)
   maven server to `pom.xml`
2. Import `pojo` dependency.

### Maven

See [example](https://github.com/fabasoad/pojo/packages/931918) on how to use it
with maven.

## Examples

### Java

```java
public class PojoTest {
  // The package to test
  private static final String PACKAGE_NAME = "io.fabasoad.pojo";

  @Test
  public void testPojoStructureAndBehavior() {
    final PojoValidator validator = PojoValidatorBuilder.create(PACKAGE_NAME)
        .with(new GettersTester(new GettersMustExistRule()))
        .with(new FieldsTester(
            new FieldsMustBeFinalRule(),
            new FieldsMustBePrivateRule()))
        .build();

    validator.validate();
  }
}
```

### Groovy

```groovy
class PojoSpec extends Specification {
  // The package to test
  def PACKAGE_NAME = "io.fabasoad.pojo";

  def "Getters and fields must follow the rules"() {
    given:
    def builder = PojoValidatorBuilder.create(PACKAGE_NAME)

    when:
    def validator = builder
        .with(new GettersTester(new GettersMustExistRule()))
        .with(new FieldsTester(
            new FieldsMustBeFinalRule(),
            new FieldsMustBePrivateRule()))
        .build()

    then:
    validator.validate()
  }
}
```

## Contributions

![Alt](https://repobeats.axiom.co/api/embed/7a892bda2ac0a8dbfc492b849fb9030a6ed37ea4.svg "Repobeats analytics image")
