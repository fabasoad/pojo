package io.fabasoad.pojo.validator.rules.fields

import java.lang.reflect.Field
import java.lang.reflect.Modifier

/**
 * This rule checks that fields must have 'final' keyword.
 *
 * Positive examples:
 * <code>
 *  class Pojo1 {
 *    final byte code;
 *    private final int count;
 *  }
 *
 *  public record Pojo2(Throwable cause) {}
 * </code>
 *
 * Negative examples:
 * <code>
 *   public class Pojo {
 *     String name;
 *     private byte code;
 *     protected int count;
 *   }
 * </code>
 */
class FieldsMustBeFinalRule extends CustomFieldRule {

  FieldsMustBeFinalRule() {
    super(
        { Field f -> Modifier.isFinal(f.modifiers) },
        { "Field '" + it.name + "' is not final" })
  }
}
