package ru.stqa.pft.itgen.model.users;

import java.util.Date;
import java.util.Objects;

public class LoginTokens {
  private Date when;
  private String hashedToken;

  public LoginTokens() {
  }

  public Date getWhen() {
    return when;
  }

  public void setWhen(Date when) {
    this.when = when;
  }

  public String getHashedToken() {
    return hashedToken;
  }

  public void setHashedToken(String hashedToken) {
    this.hashedToken = hashedToken;
  }

  @Override
  public String toString() {
    return "LoginTokens{" +
            "when=" + when +
            ", hashedToken='" + hashedToken + '\'' +
            '}';
  }
}
