package io.itgen.model.users;

import dev.morphia.annotations.Property;

public class Reset {
  @Property("token")
  public String token;

  public Reset() {}

  public String getToken() {
    return token;
  }

  public Reset withToken(String token) {
    this.token = token;
    return this;
  }

  @Override
  public String toString() {
    return "Reset{" + "token='" + token + '\'' + '}';
  }
}
