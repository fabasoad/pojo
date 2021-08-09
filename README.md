# POJO testing library

## Testing example

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
