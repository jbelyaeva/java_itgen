package data.model.communities;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CommunitiesPosts extends ForwardingSet<CommunitiesPostData> {
  private final Set<CommunitiesPostData> delegate;

  public CommunitiesPosts(CommunitiesPosts communitiesPosts) {
    this.delegate = new HashSet<CommunitiesPostData>(communitiesPosts.delegate);
  }

  public CommunitiesPosts() { // конструктор без параметров
    this.delegate = new HashSet<CommunitiesPostData>();
  }

  public CommunitiesPosts(Collection<CommunitiesPostData> communitiesPosts) {
    this.delegate = new HashSet<CommunitiesPostData>(communitiesPosts);
  }

  public CommunitiesPosts withAdded(CommunitiesPostData communitiesPost) {
    CommunitiesPosts communitiesPosts = new CommunitiesPosts(this);
    communitiesPosts.add(communitiesPost);
    return communitiesPosts;
  }

  public CommunitiesPosts without(CommunitiesPostData communitiesPost) {
    CommunitiesPosts communitiesPosts = new CommunitiesPosts(this);
    communitiesPosts.remove(communitiesPost);
    return communitiesPosts;
  }

  @Override
  protected Set<CommunitiesPostData> delegate() {
    return delegate;
  }
}
