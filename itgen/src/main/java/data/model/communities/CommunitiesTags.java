package data.model.communities;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CommunitiesTags extends ForwardingSet<CommunitiesTagData> {
  private final Set<CommunitiesTagData> delegate;

  public CommunitiesTags(CommunitiesTags communitiesTags) {
    this.delegate = new HashSet<CommunitiesTagData>(communitiesTags.delegate);
  }

  public CommunitiesTags() { // конструктор без параметров
    this.delegate = new HashSet<CommunitiesTagData>();
  }

  public CommunitiesTags(Collection<CommunitiesTagData> communitiesTags) {
    this.delegate = new HashSet<CommunitiesTagData>(communitiesTags);
  }

  public CommunitiesTags withAdded(CommunitiesTagData communitiesTag) {
    CommunitiesTags communitiesTags = new CommunitiesTags(this);
    communitiesTags.add(communitiesTag);
    return communitiesTags;
  }

  public CommunitiesTags without(CommunitiesTagData communitiesTag) {
    CommunitiesTags communitiesTags = new CommunitiesTags(this);
    communitiesTags.remove(communitiesTag);
    return communitiesTags;
  }

  @Override
  protected Set<CommunitiesTagData> delegate() {
    return delegate;
  }
}
