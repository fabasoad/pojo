package io.fabasoad.pojo.validator.rules.fields

import java.lang.reflect.Field
import java.lang.reflect.Modifier

/**
 * This rule checks that fields must have 'private' visibility.
 *
 * Positive examples:
 * <code>
 *  public class Pojo1 {
 *    private final byte code;
 *    private int count;
 *  }
 *
 *  public record Pojo2(String name) {}
 * </code>
 *
 * Negative examples:
 * <code>
 *   public class Pojo {
 *     String name;
 *     public byte code;
 *     protected int count;
 *   }
 * </code>
 */
class FieldsMustBePrivateRule extends CustomFieldRule {

  FieldsMustBePrivateRule() {
    super(
        { Field f -> Modifier.isPrivate(f.modifiers) },
        { "Field '" + it.name + "' is not private" }
    )
  }
}
