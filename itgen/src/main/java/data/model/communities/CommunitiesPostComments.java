package data.model.communities;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CommunitiesPostComments extends ForwardingSet<CommunitiesPostCommentData> {
  private final Set<CommunitiesPostCommentData> delegate;

  public CommunitiesPostComments(CommunitiesPostComments communitiesPosts) {
    this.delegate = new HashSet<CommunitiesPostCommentData>(communitiesPosts.delegate);
  }

  public CommunitiesPostComments() { // конструктор без параметров
    this.delegate = new HashSet<CommunitiesPostCommentData>();
  }

  public CommunitiesPostComments(Collection<CommunitiesPostCommentData> communitiesPostComments) {
    this.delegate = new HashSet<CommunitiesPostCommentData>(communitiesPostComments);
  }

  public CommunitiesPostComments withAdded(CommunitiesPostCommentData communitiesPostComment) {
    CommunitiesPostComments communitiesPostComments = new CommunitiesPostComments(this);
    communitiesPostComments.add(communitiesPostComment);
    return communitiesPostComments;
  }

  public CommunitiesPostComments without(CommunitiesPostCommentData communitiesPostComment) {
    CommunitiesPostComments communitiesPostComments = new CommunitiesPostComments(this);
    communitiesPostComments.remove(communitiesPostComment);
    return communitiesPostComments;
  }

  @Override
  protected Set<CommunitiesPostCommentData> delegate() {
    return delegate;
  }
}
