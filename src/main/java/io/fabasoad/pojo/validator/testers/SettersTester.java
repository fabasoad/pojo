package io.fabasoad.pojo.validator.testers;

import io.fabasoad.pojo.validator.rules.setters.SetterRule;

public record SettersTester(SetterRule... rules) implements Tester<SetterRule> {
}
