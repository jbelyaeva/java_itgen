package ru.stqa.pft.itgen.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Families extends ForwardingSet<FamilyData> {
  private Set<FamilyData> delegate;

  public Families(Families families) {
    this.delegate = new HashSet<FamilyData>(families.delegate);
  }

  public Families() { //конструктор без параметров
    this.delegate = new HashSet<FamilyData>();
  }

  public Families(Collection<FamilyData> families) {
    this.delegate = new HashSet<FamilyData>(families);
  }

  @Override
  protected Set<FamilyData> delegate() {
    return delegate;
  }
}
