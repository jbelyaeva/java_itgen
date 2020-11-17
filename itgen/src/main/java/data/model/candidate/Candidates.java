package data.model.candidate;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Candidates extends ForwardingSet<CandidateData> {

  private final Set<CandidateData> delegate;

  public Candidates(Candidates candidates) {
    this.delegate = new HashSet<CandidateData>(candidates.delegate);
  }

  public Candidates() { // конструктор без параметров
    this.delegate = new HashSet<CandidateData>();
  }

  public Candidates(Collection<CandidateData> candidates) {
    this.delegate = new HashSet<CandidateData>(candidates);
  }

  public Candidates withAdded(CandidateData candidate) {
    Candidates candidates = new Candidates(this);
    candidates.add(candidate);
    return candidates;
  }

  public Candidates without(CandidateData candidate) {
    Candidates candidates = new Candidates(this);
    candidates.remove(candidate);
    return candidates;
  }

  @Override
  protected Set<CandidateData> delegate() {
    return delegate;
  }
}
