package ru.stqa.pft.itgen.model;

import javax.persistence.Embeddable;

@Embeddable
public class Times {
  public Integer start;
  public Integer end;

  public Times() {
  }

  public Times(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public Integer getStart() {
    return start;
  }

  public void setStart(Integer start) {
    this.start = start;
  }

  public Integer getEnd() {
    return end;
  }

  public void setEnd(Integer end) {
    this.end = end;
  }

  @Override
  public String toString() {
    return "Times{" +
            "start=" + start +
            ", end=" + end +
            '}';
  }
}
