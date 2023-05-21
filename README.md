# POJO testing library

[![Stand With Ukraine](https://raw.githubusercontent.com/vshymanskyy/StandWithUkraine/main/badges/StandWithUkraine.svg)](https://stand-with-ukraine.pp.ua)
![GitHub release](https://img.shields.io/github/v/release/fabasoad/pojo?include_prereleases)
![Unit Tests](https://github.com/fabasoad/pojo/workflows/Unit%20Tests/badge.svg)
![Security Tests](https://github.com/fabasoad/pojo/workflows/Security%20Tests/badge.svg)
![pre-commit](https://github.com/fabasoad/pojo/actions/workflows/pre-commit.yml/badge.svg)
[![Known Vulnerabilities](https://snyk.io/test/github/fabasoad/pojo/badge.svg)](https://snyk.io/test/github/fabasoad/pojo)

## Import

1. Add [GitHub Packages](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-to-github-packages)
   maven server to `pom.xml`
2. Import `pojo` dependency.

### Maven

```xml
<dependency>
  <groupId>io.fabasoad</groupId>
  <artifactId>pojo</artifactId>
  <version>0.2.1</version>
</dependency>
```

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
