package data.model.communities;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Communities extends ForwardingSet<CommunityData> {
  private final Set<CommunityData> delegate;

  public Communities(Communities communities) {
    this.delegate = new HashSet<CommunityData>(communities.delegate);
  }

  public Communities() { // конструктор без параметров
    this.delegate = new HashSet<CommunityData>();
  }

  public Communities(Collection<CommunityData> communities) {
    this.delegate = new HashSet<CommunityData>(communities);
  }

  public Communities withAdded(CommunityData community) {
    Communities communities = new Communities(this);
    communities.add(community);
    return communities;
  }

  public Communities without(CommunityData community) {
    Communities communities = new Communities(this);
    communities.remove(community);
    return communities;
  }

  @Override
  protected Set<CommunityData> delegate() {
    return delegate;
  }
}
