package data.model.schedule;

import java.util.Objects;

public class HwMaterials {
  String id;
  String title;
  String type;
  String materialLink;
  String status;

  public HwMaterials() {}

  public HwMaterials withId(String id) {
    this.id = id;
    return this;
  }

  public HwMaterials withTitle(String title) {
    this.title = title;
    return this;
  }

  public HwMaterials withType(String type) {
    this.type = type;
    return this;
  }

  public HwMaterials withMaterialLink(String materialLink) {
    this.materialLink = materialLink;
    return this;
  }

  public HwMaterials withStatus(String status) {
    this.status = status;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }

  public String getMaterialLink() {
    return materialLink;
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
    HwMaterials that = (HwMaterials) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(title, that.title) &&
        Objects.equals(type, that.type) &&
        Objects.equals(materialLink, that.materialLink) &&
        Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, type, materialLink, status);
  }

  @Override
  public String toString() {
    return "HwMaterials{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", type='" + type + '\'' +
        ", materialLink='" + materialLink + '\'' +
        ", status='" + status + '\'' +
        '}';
  }
}
