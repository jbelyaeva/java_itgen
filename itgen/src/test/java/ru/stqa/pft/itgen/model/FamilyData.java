package ru.stqa.pft.itgen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "families")
public class FamilyData {
  @Id
  @Column(name = "_id")
  private String id;

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
