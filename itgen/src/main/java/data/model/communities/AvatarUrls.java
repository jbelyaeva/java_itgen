package data.model.communities;

import java.util.Objects;

public class AvatarUrls {

  public String small;
  public String original;

  public AvatarUrls() {
  }

  public AvatarUrls withSmall(String small) {
    this.small = small;
    return this;
  }

  public AvatarUrls withOriginal(String original) {
    this.original = original;
    return this;
  }

  public String getSmall() {
    return small;
  }

  public String getOriginal() {
    return original;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AvatarUrls that = (AvatarUrls) o;
    return Objects.equals(small, that.small) &&
        Objects.equals(original, that.original);
  }

  @Override
  public int hashCode() {
    return Objects.hash(small, original);
  }

  @Override
  public String toString() {
    return "AvatarUrls{" +
        "small='" + small + '\'' +
        ", original='" + original + '\'' +
        '}';
  }
}
