package data.model.communities;

import com.google.gson.annotations.Expose;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.Objects;

@Entity("communities_tags")
public class CommunitiesTagData {
  @Expose
  @Id
  @Property("_id")
  private String id;

  @Expose
  @Property("title")
  private String title;

  public CommunitiesTagData() {}

  public CommunitiesTagData withId(String id) {
    this.id = id;
    return this;
  }

  public CommunitiesTagData withTitle(String title) {
    this.title = title;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommunitiesTagData that = (CommunitiesTagData) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(title, that.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title);
  }

  @Override
  public String toString() {
    return "CommunitiesTagData{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        '}';
  }
}
