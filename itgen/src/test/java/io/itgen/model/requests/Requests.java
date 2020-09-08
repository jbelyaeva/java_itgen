package io.itgen.model.requests;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Requests extends ForwardingSet<RequestData> {
  private Set<RequestData> delegate;

  public Requests(Requests requests) {
    this.delegate = new HashSet<RequestData>(requests.delegate);
  }

  public Requests() { //конструктор без параметров
    this.delegate = new HashSet<RequestData>();
  }

  public Requests(Collection<RequestData> requests) {
    this.delegate = new HashSet<RequestData>(requests);
  }

  public Requests withAdded(RequestData request) {
    Requests requests = new Requests(this);
    requests.add(request);
    return requests;
  }

  public Requests without(RequestData request) {
    Requests requests = new Requests(this);
    requests.remove(request);
    return requests;
  }

  @Override
  protected Set<RequestData> delegate() {
    return delegate;
  }
}

