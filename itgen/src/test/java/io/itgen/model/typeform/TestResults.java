package io.itgen.model.typeform;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TestResults extends ForwardingSet<TestResultsData> {
  private Set<TestResultsData> delegate;

  public TestResults(TestResults testResults) {
    this.delegate = new HashSet<TestResultsData>(testResults.delegate);
  }

  public TestResults() { //конструктор без параметров
    this.delegate = new HashSet<TestResultsData>();
  }

  public TestResults(Collection<TestResultsData> testResults) {
    this.delegate = new HashSet<TestResultsData>(testResults);
  }

  public TestResults withAdded(TestResultsData testResult) {
    TestResults testResults = new TestResults(this);
    testResults.add(testResult);
    return testResults;
  }

  public TestResults without(TestResultsData testResult) {
    TestResults testResults = new TestResults(this);
    testResults.remove(testResult);
    return testResults;
  }

  @Override
  protected Set<TestResultsData> delegate() {
    return delegate;
  }
}
