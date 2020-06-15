package io.itgen.model.users;


public class Emails {
  private String address;
  private Boolean verified;

  public Emails() {
  }

  public String getAddress() {
    return address;
  }

  public Emails withAddress(String address) {
    this.address = address;
    return this;
  }

  public Boolean getVerified() {
    return verified;
  }

  public Emails withVerified(Boolean verified) {
    this.verified = verified;
    return this;
  }

  @Override
  public String toString() {
    return "Emails{" +
            "address='" + address + '\'' +
            ", verified=" + verified +
            '}';
  }
}