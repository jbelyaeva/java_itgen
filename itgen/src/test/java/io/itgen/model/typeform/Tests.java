package io.itgen.model.typeform;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Tests extends ForwardingSet<TestData> {
  private final Set<TestData> delegate;

  public Tests(Tests tests) {
    this.delegate = new HashSet<TestData>(tests.delegate);
  }

  public Tests() { // конструктор без параметров
    this.delegate = new HashSet<TestData>();
  }

  public Tests(Collection<TestData> tests) {
    this.delegate = new HashSet<TestData>(tests);
  }

  public Tests withAdded(TestData test) {
    Tests tests = new Tests(this);
    tests.add(test);
    return tests;
  }

  public Tests without(TestData test) {
    Tests tests = new Tests(this);
    tests.remove(test);
    return tests;
  }

  @Override
  protected Set<TestData> delegate() {
    return delegate;
  }
}
