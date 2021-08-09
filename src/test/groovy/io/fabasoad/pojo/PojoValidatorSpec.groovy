package io.fabasoad.pojo

import io.fabasoad.pojo.validator.PojoValidatorBuilder
import io.fabasoad.pojo.validator.fixture.fields.A
import io.fabasoad.pojo.validator.fixture.fields.B
import io.fabasoad.pojo.validator.fixture.fields.C
import io.fabasoad.pojo.validator.fixture.fields.D
import io.fabasoad.pojo.validator.fixture.fields.E
import spock.lang.Specification

class PojoValidatorSpec extends Specification {

  def "Get correct list of classes with default settings"() {
    given:
    def builder = PojoValidatorBuilder.create(packageName)

    when:
    def validator = builder.build()
    def actual = new ArrayList<Class<?>>(validator.classes())

    then:
    expected.size() == validator.classes().size()
    0 == validator.testers().size()
    [] == ((expected - actual) + (actual - expected))

    where:
    packageName                                 | expected
    "io.fabasoad.pojo.validator.fixture"        | []
    "io.fabasoad.pojo.validator.fixture.fields" | [A, B, C, D, E]
  }

  def "Get correct list of classes"() {
    given:
    def builder = PojoValidatorBuilder.create(packageName, recursive)

    when:
    def validator = builder.build()
    def actual = new ArrayList<Class<?>>(validator.classes())

    then:
    expected.size() == validator.classes().size()
    0 == validator.testers().size()
    [] == ((expected - actual) + (actual - expected))

    where:
    recursive | packageName                                 | expected
    true      | "io.fabasoad.pojo.validator.fixture"        | [io.fabasoad.pojo.validator.fixture.getters.A, io.fabasoad.pojo.validator.fixture.getters.B, A, B, C, D, E]
    false     | "io.fabasoad.pojo.validator.fixture.fields" | [A, B, C, D, E]
  }
}
