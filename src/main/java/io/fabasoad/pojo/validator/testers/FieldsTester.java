package io.fabasoad.pojo.validator.testers;

import io.fabasoad.pojo.validator.rules.fields.FieldRule;

public record FieldsTester(FieldRule... rules) implements Tester<FieldRule> {}
