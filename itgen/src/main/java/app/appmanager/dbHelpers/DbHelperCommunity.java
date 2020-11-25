package app.appmanager.dbHelpers;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.communities.Communities;
import data.model.communities.CommunitiesPostCommentData;
import data.model.communities.CommunitiesPostComments;
import data.model.communities.CommunitiesPostData;
import data.model.communities.CommunitiesPosts;
import data.model.communities.CommunitiesTagData;
import data.model.communities.CommunityData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DbHelperCommunity {

  Datastore datastore = morphiaSessionFactoryUtil();

  public Communities communities() {
    Query<CommunityData> q = datastore.createQuery(CommunityData.class);
    return new Communities(q.find().toList());
  }

  public CommunitiesPosts posts() {
    Query<CommunitiesPostData> q = datastore.createQuery(CommunitiesPostData.class);
    return new CommunitiesPosts(q.find().toList());
  }

  public CommunitiesPostComments comments() {
    Query<CommunitiesPostCommentData> q = datastore.createQuery(CommunitiesPostCommentData.class);
    return new CommunitiesPostComments(q.find().toList());
  }

  public CommunityData lastCommunity() {
    Query<CommunityData> q = datastore.createQuery(CommunityData.class);
    long count = q.count();
    List<CommunityData> communities = q.find().toList();
    return communities.get(Math.toIntExact(count - 1));
  }

  public CommunitiesPostData lastPost() {
    Query<CommunitiesPostData> q = datastore.createQuery(CommunitiesPostData.class);
    long count = q.count();
    List<CommunitiesPostData> posts = q.find().toList();
    return posts.get(Math.toIntExact(count - 1));
  }

  public String[] tags() {
    Set<String> tagsIds = new HashSet<>();
    Query<CommunitiesTagData> q = datastore.createQuery(CommunitiesTagData.class);
    List<CommunitiesTagData> tags = q.find().toList();
    for (CommunitiesTagData tag : tags) {
      tagsIds.add(tag.getId());
    }
    return (tagsIds.stream().toArray(n -> new String[n]));
  }
}
