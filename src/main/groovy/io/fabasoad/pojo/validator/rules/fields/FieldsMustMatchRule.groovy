package io.fabasoad.pojo.validator.rules.fields

import java.lang.reflect.Field
import java.util.regex.Pattern

/**
 * This rule checks that fields' names must match regex.
 *
 * Positive examples in case regex is "$(_.+)^":
 * <code>
 *  public class Pojo1 {
 *    private final byte _code;
 *    private int _count;
 *  }
 *
 *  public record Pojo2(String name) {}
 * </code>
 *
 * Negative examples in case regex is "$(_.+)^":
 * <code>
 *   public class Pojo {
 *     String name;
 *     public byte Code;
 *   }
 * </code>
 */
class FieldsMustMatchRule extends CustomFieldRule {

    FieldsMustMatchRule(regex) {
        super(
                { Field f -> (f.name =~ Pattern.compile(regex)).find() },
                { "Field '" + it.name + "' doesn't match '" + regex }
        )
    }
}
