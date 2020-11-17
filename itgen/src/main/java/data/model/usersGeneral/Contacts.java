package data.model.usersGeneral;

public class Contacts {
  private String type;
  private String val;
  private Boolean preferred;

  public Contacts() {}

  public Contacts withType(String type) {
    this.type = type;
    return this;
  }

  public Contacts withVal(String val) {
    this.val = val;
    return this;
  }

  public Contacts withPreferred(Boolean preferred) {
    this.preferred = preferred;
    return this;
  }

  public String getType() {
    return type;
  }

  public String getVal() {
    return val;
  }

  public Boolean getPreferred() {
    return preferred;
  }

  @Override
  public String toString() {
    return "Contacts{"
        + "type='"
        + type
        + '\''
        + ", val='"
        + val
        + '\''
        + ", preferred='"
        + preferred
        + '\''
        + '}';
  }
}
