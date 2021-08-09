package io.fabasoad.pojo.validator.exceptions;

public class AggregateException extends RuntimeException {

  private final Throwable[] causes;

  public AggregateException(Throwable... causes) {
    this.causes = causes;
  }

  public Throwable[] getCauses() {
    return causes;
  }
}
