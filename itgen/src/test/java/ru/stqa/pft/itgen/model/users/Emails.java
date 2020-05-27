package ru.stqa.pft.itgen.model.users;


public class Emails {
  private String address;
  private Boolean verified;

  public Emails() {
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Boolean getVerified() {
    return verified;
  }

  public void setVerified(Boolean verified) {
    this.verified = verified;
  }

  @Override
  public String toString() {
    return "Emails{" +
            "address='" + address + '\'' +
            ", verified=" + verified +
            '}';
  }
}