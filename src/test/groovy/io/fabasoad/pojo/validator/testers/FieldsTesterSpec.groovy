package io.fabasoad.pojo.validator.testers

import io.fabasoad.pojo.validator.PojoValidatorBuilder
import io.fabasoad.pojo.validator.exceptions.ValidationException
import io.fabasoad.pojo.validator.rules.fields.FieldsMustBeFinalRule
import io.fabasoad.pojo.validator.rules.fields.FieldsMustBePrivateRule
import io.fabasoad.pojo.validator.rules.fields.FieldsMustNotBePublicRule
import spock.lang.Specification

class FieldsTesterSpec extends Specification {

  def PACKAGE_NAME = "io.fabasoad.pojo.validator.fixture.fields"

  def "Fields must follow the rule :: positive"() {
    given:
    def builder = PojoValidatorBuilder.create(PACKAGE_NAME)

    when:
    def validator = builder.with(new FieldsTester(rule)).build()

    then:
    validator.validate((c) -> expected.contains(c.getSimpleName()))

    where:
    rule                            | expected
    new FieldsMustBeFinalRule()     | ["A"]
    new FieldsMustBePrivateRule()   | ["A", "B"]
    new FieldsMustNotBePublicRule() | ["A", "B", "D", "E"]
  }

  def "Fields must follow the rule :: negative"() {
    given:
    def builder = PojoValidatorBuilder.create(PACKAGE_NAME)

    when:
    def validator = builder.with(new FieldsTester(rule)).build()
    builder
        .with(new FieldsTester(new FieldsMustBeFinalRule()))
        .build()
        .validate((c) -> expected.contains(c.getSimpleName()))

    then:
    thrown(ValidationException)

    where:
    rule                            | expected
    new FieldsMustBeFinalRule()     | ["B", "C", "D", "E"]
    new FieldsMustBePrivateRule()   | ["C", "D", "E"]
    new FieldsMustNotBePublicRule() | ["C"]
  }
}
