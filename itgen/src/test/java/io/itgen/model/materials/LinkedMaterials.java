package io.itgen.model.materials;

import java.util.Objects;

public class LinkedMaterials {

  private String id;
  private String lang;
  private String status;

  public LinkedMaterials() {
  }

  public LinkedMaterials withId(String id) {
    this.id = id;
    return this;
  }

  public LinkedMaterials withLang(String lang) {
    this.lang = lang;
    return this;
  }

  public LinkedMaterials withStatus(String status) {
    this.status = status;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getLang() {
    return lang;
  }

  public String getStatus() {
    return status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinkedMaterials that = (LinkedMaterials) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(lang, that.lang) &&
        Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lang, status);
  }

  @Override
  public String toString() {
    return "LinkedMaterials{" +
        "id='" + id + '\'' +
        ", lang='" + lang + '\'' +
        ", status='" + status + '\'' +
        '}';
  }
}
