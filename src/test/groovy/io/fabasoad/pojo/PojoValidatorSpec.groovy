package io.fabasoad.pojo

import io.fabasoad.pojo.validator.fixture.getters.B
import io.fabasoad.pojo.validator.fixture.getters.A
import io.fabasoad.pojo.validator.PojoValidatorBuilder
import spock.lang.Specification

class PojoValidatorSpec extends Specification {

  def PACKAGE_NAME = "io.fabasoad.pojo.validator.fixture.getters"

  def "Get correct list of classes"() {
    given:
    def builder = PojoValidatorBuilder.create(PACKAGE_NAME)

    when:
    def validator = builder.build()
    def actual = new ArrayList<Class<?>>(validator.classes())
    def expected = [A, B]

    then:
    2 == validator.classes().size()
    0 == validator.testers().size()
    [] == ((expected - actual) + (actual - expected))
  }
}
