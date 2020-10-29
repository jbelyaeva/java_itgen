package io.itgen.model.users;

import dev.morphia.annotations.Embedded;

public class Password {
  @Embedded public Reset reset;

  public Password() {}

  public Password withReset(Reset reset) {
    this.reset = reset;
    return this;
  }

  public Reset getReset() {
    return reset;
  }

  @Override
  public String toString() {
    return "Password{" + "reset=" + reset + '}';
  }
}
