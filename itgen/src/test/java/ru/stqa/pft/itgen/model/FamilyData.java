package ru.stqa.pft.itgen.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "families")
public class FamilyData {
  @Expose
  @Id
  @Column(name="_id")
 // @OneToOne(mappedBy = "users")
  private String id;

  public FamilyData withId(String id) {
    this.id = id;
    return this;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FamilyData that = (FamilyData) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "FamilyData{" +
            "id='" + id + '\'' +
            '}';
  }
}
