package io.fabasoad.pojo.validator.testers;

import io.fabasoad.pojo.validator.rules.getters.GetterRule;

public record GettersTester(GetterRule... rules) implements Tester<GetterRule> {
}
