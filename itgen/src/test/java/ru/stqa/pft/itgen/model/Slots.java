package ru.stqa.pft.itgen.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Slots {
  private Double w;
  private String id;
  @Type(type = "class")
  private static List<C> c = new ArrayList<C>();
  @Embedded
  private ST st;


  public Double getW() {
    return w;
  }

  public void setW(Double w) {
    this.w = w;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<C> getC() {
    return c;
  }

  public void setC(List<C> c) {
    this.c = c;
  }

  public ST getSt() {
    return st;
  }

  public Slots withSt(ST st) {
    this.st = st;
    return this;
  }

  public Slots withW(Double w) {
    this.w = w;
    return this;
  }

  @Override
  public String toString() {
    return "Slots{" +
            "w=" + w +
            ", id='" + id + '\'' +
            ", c=" + c +
            ", st=" + st +
            '}';
  }
}
