package io.fabasoad.pojo.validator.testers

import io.fabasoad.pojo.validator.PojoValidatorBuilder
import io.fabasoad.pojo.validator.exceptions.ValidationException
import io.fabasoad.pojo.validator.rules.getters.GetterPrefix
import io.fabasoad.pojo.validator.rules.getters.GettersMustExistRule
import spock.lang.Specification

class GettersTesterSpec extends Specification {

  def PACKAGE_NAME = "io.fabasoad.pojo.validator.fixture.getters"

  def "Getters must exist with default setting"() {
    given:
    def builder = PojoValidatorBuilder.create(PACKAGE_NAME)

    when:
    def validator = builder.with(new GettersTester(new GettersMustExistRule())).build()

    then:
    validator.validate()
  }

  def "Getters must exist with any setting :: positive"() {
    given:
    def builder = PojoValidatorBuilder.create(PACKAGE_NAME)

    when:
    def validator = builder.with(new GettersTester(new GettersMustExistRule(prefix))).build()

    then:
    validator.validate(filter)

    where:
    prefix           | filter
    GetterPrefix.ANY | (c) -> true
    GetterPrefix.NO  | (c) -> "A" == c.getSimpleName()
    GetterPrefix.YES | (c) -> "B" == c.getSimpleName()
  }

  def "Getters must exist with any setting :: negative"() {
    given:
    def builder = PojoValidatorBuilder.create(PACKAGE_NAME)

    when:
    builder.with(new GettersTester(rule)).build().validate(filter)

    then:
    thrown(ValidationException)

    where:
    rule                                       | filter
    new GettersMustExistRule(GetterPrefix.NO)  | (c) -> "B" == c.getSimpleName()
    new GettersMustExistRule(GetterPrefix.YES) | (c) -> "A" == c.getSimpleName()
  }
}
