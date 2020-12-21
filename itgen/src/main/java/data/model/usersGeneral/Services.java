package data.model.usersGeneral;

import dev.morphia.annotations.Embedded;
import java.util.ArrayList;
import java.util.List;

public class Services {

  @Embedded
  private Password password;
  private String bcrypt;
  @Embedded
  private List<LoginTokens> loginTokens = new ArrayList<LoginTokens>();

  public Services() {
  }

  public Services withBcrypt(String bcrypt) {
    this.bcrypt = bcrypt;
    return this;
  }

  public Services withPassword(Password password) {
    this.password = password;
    return this;
  }

  public Services withLoginTokens(List<LoginTokens> loginTokens) {
    this.loginTokens = loginTokens;
    return this;
  }

  public String getBcrypt() {
    return bcrypt;
  }

  public List<LoginTokens> getLoginTokens() {
    return loginTokens;
  }

  public Password getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "Services{"
        + "password="
        + password
        + ", bcrypt='"
        + bcrypt
        + '\''
        + ", loginTokens="
        + loginTokens
        + '}';
  }
}
