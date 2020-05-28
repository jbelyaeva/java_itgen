package ru.stqa.pft.itgen.model.users;

import dev.morphia.annotations.Embedded;
import ru.stqa.pft.itgen.model.Schedule.C;

import java.util.ArrayList;
import java.util.List;

public  class Services {
  private String bcrypt;
  @Embedded
  private List<LoginTokens> loginTokens = new ArrayList<LoginTokens>();

  public Services() {
  }

  public String getBcrypt() {
    return bcrypt;
  }

  public void setBcrypt(String bcrypt) {
    this.bcrypt = bcrypt;
  }

  public List<LoginTokens> getLoginTokens() {
    return loginTokens;
  }

  public void setLoginTokens(List<LoginTokens> loginTokens) {
    this.loginTokens = loginTokens;
  }


  @Override
  public String toString() {
    return "Services{" +
            "bcrypt='" + bcrypt + '\'' +
            ", loginTokens=" + loginTokens +
            '}';
  }
}