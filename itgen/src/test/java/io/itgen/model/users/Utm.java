package io.itgen.model.users;

public class Utm {
  private String source;
  private String medium;
  private String campaing;
  private String term;
  private String content;

  public Utm() {
  }

  public Utm withSource(String source) {
    this.source = source;
    return this;
  }

  public Utm withMedium(String medium) {
    this.medium = medium;
    return this;
  }

  public Utm withCampaing(String campaing) {
    this.campaing = campaing;
    return this;
  }

  public Utm withTerm(String term) {
    this.term = term;
    return this;
  }

  public Utm withContent(String content) {
    this.content = content;
    return this;
  }

  public String getSource() {
    return source;
  }

  public String getMedium() {
    return medium;
  }

  public String getCampaing() {
    return campaing;
  }

  public String getTerm() {
    return term;
  }

  public String getContent() {
    return content;
  }

  @Override
  public String toString() {
    return "Utm{" +
        "source='" + source + '\'' +
        ", medium='" + medium + '\'' +
        ", campaing='" + campaing + '\'' +
        ", term='" + term + '\'' +
        ", content='" + content + '\'' +
        '}';
  }

}