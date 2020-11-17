package data.model.schedule;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Comments extends ForwardingSet<CommentData> {
  private final Set<CommentData> delegate;

  public Comments(Comments comments) {
    this.delegate = new HashSet<CommentData>(comments.delegate);
  }

  public Comments() { // конструктор без параметров
    this.delegate = new HashSet<CommentData>();
  }

  public Comments(Collection<CommentData> comments) {
    this.delegate = new HashSet<CommentData>(comments);
  }

  public Comments withAdded(CommentData comment) {
    Comments comments = new Comments(this);
    comments.add(comment);
    return comments;
  }

  public Comments without(CommentData comment) {
    Comments comments = new Comments(this);
    comments.remove(comment);
    return comments;
  }

  @Override
  protected Set<CommentData> delegate() {
    return delegate;
  }
}
