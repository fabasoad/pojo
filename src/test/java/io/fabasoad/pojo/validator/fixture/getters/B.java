package io.fabasoad.pojo.validator.fixture.getters;

/** Field: private, not final. Getter: public, final, "get" prefix */
public class B {
  private String name;
  private boolean active;
  private boolean isValid;

  public String getName() {
    return name;
  }

  public boolean isActive() {
    return active;
  }

  public boolean isValid() {
    return isValid;
  }
}
