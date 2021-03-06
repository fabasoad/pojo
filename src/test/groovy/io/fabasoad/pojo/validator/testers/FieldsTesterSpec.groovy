package io.fabasoad.pojo.validator.testers

import io.fabasoad.pojo.validator.PojoValidatorBuilder
import io.fabasoad.pojo.validator.exceptions.ValidationException
import io.fabasoad.pojo.validator.rules.fields.CustomFieldRule
import io.fabasoad.pojo.validator.rules.fields.FieldsMustBeFinalRule
import io.fabasoad.pojo.validator.rules.fields.FieldsMustBePrivateRule
import io.fabasoad.pojo.validator.rules.fields.FieldsMustNotBePublicRule
import spock.lang.Specification

class FieldsTesterSpec extends Specification {

  static PACKAGE_NAME = "io.fabasoad.pojo.validator.fixture.fields"
  static customFieldRule = new CustomFieldRule(
      { "cause" == it.name },
      { "Field name is '" + it.name + "' but 'cause' is expected" })

  def "Fields must follow the rule :: positive"() {
    given:
    def builder = PojoValidatorBuilder.create(PACKAGE_NAME)

    when:
    def validator = builder.with(new FieldsTester(rule)).build()

    then:
    validator.validate({ expected.contains(it.simpleName) })

    where:
    rule                            | expected
    new FieldsMustBeFinalRule()     | ["A"]
    new FieldsMustBePrivateRule()   | ["A", "B"]
    new FieldsMustNotBePublicRule() | ["A", "B", "D", "E"]
    customFieldRule                 | ["E"]
  }

  def "Fields must follow the rule :: negative"() {
    given:
    def builder = PojoValidatorBuilder.create(PACKAGE_NAME)

    when:
    builder
        .with(new FieldsTester(rule))
        .build()
        .validate({ expected.contains(it.simpleName) })

    then:
    thrown(ValidationException)

    where:
    rule                            | expected
    new FieldsMustBeFinalRule()     | ["B", "C", "D", "E"]
    new FieldsMustBePrivateRule()   | ["C", "D", "E"]
    new FieldsMustNotBePublicRule() | ["C"]
    customFieldRule                 | ["A", "B", "C", "D"]
  }
}
