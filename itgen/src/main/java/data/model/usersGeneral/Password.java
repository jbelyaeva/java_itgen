package data.model.usersGeneral;

import dev.morphia.annotations.Embedded;

public class Password {
  @Embedded public Reset reset;
  String bcrypt;

  public Password() {}

  public Password withReset(Reset reset) {
    this.reset = reset;
    return this;
  }

  public Password withBcrypt(String bcrypt) {
    this.bcrypt = bcrypt;
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
