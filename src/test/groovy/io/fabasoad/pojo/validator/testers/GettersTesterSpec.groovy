package io.fabasoad.pojo.validator.testers

import io.fabasoad.pojo.validator.PojoValidatorBuilder
import io.fabasoad.pojo.validator.exceptions.ValidationException
import io.fabasoad.pojo.validator.rules.getters.GetterPrefix
import io.fabasoad.pojo.validator.rules.getters.GettersMustExistRule
import spock.lang.Specification

class GettersTesterSpec extends Specification {

  static PACKAGE_NAME = "io.fabasoad.pojo.validator.fixture.getters"

  def "Getters must exist with default setting"() {
    given:
    def builder = PojoValidatorBuilder.create(PACKAGE_NAME + ".auto")

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
    prefix                        | filter
    GetterPrefix.auto()           | { ["A", "B"].contains it.simpleName }
    GetterPrefix.without()        | { "A" == it.simpleName }
    GetterPrefix.with()           | { "B" == it.simpleName }
    GetterPrefix.with("retrieve") | { "C" == it.simpleName }
  }

  def "Getters must exist with any setting :: negative"() {
    given:
    def builder = PojoValidatorBuilder.create(PACKAGE_NAME)

    when:
    builder.with(new GettersTester(rule)).build().validate(filter)

    then:
    thrown(ValidationException)

    where:
    rule                                                   | filter
    new GettersMustExistRule(GetterPrefix.without())       | { "B" == it.simpleName }
    new GettersMustExistRule(GetterPrefix.with())          | { "A" == it.simpleName }
    new GettersMustExistRule(GetterPrefix.with("unknown")) | { ["A", "B", "C"].contains it.simpleName }
  }
}
