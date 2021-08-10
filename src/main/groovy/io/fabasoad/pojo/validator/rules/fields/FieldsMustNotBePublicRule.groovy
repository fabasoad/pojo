package io.fabasoad.pojo.validator.rules.fields

import java.lang.reflect.Field
import java.lang.reflect.Modifier

/**
 * This rule checks that fields must not have 'public' visibility.
 *
 * Positive examples:
 * <code>
 *  public class Pojo1 {
 *    final byte code;
 *    private final int count;
 *    protected String name;
 *  }
 *
 *  public record Pojo2(Throwable cause) {}
 * </code>
 *
 * Negative examples:
 * <code>
 *   public class Pojo {
 *     public byte code;
 *     public final int count;
 *   }
 * </code>
 */
class FieldsMustNotBePublicRule extends CustomFieldRule {

  FieldsMustNotBePublicRule() {
    super(
        { Field f -> !Modifier.isPublic(f.modifiers) },
        { "Field '" + it.name + "' is public" }
    )
  }
}
