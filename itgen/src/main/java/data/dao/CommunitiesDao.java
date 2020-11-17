package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.communities.CommunitiesPostCommentData;
import data.model.communities.CommunitiesPostData;
import data.model.communities.CommunitiesTagData;
import data.model.communities.CommunityData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class CommunitiesDao {

  public void saveCommunity(CommunityData community) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(community);
  }

  public CommunityData findByIdCommunity(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(CommunityData.class).field("id").equal(id).first();
  }

  public void dropCommunity() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CommunityData> query = datastore.createQuery(CommunityData.class);
    datastore.delete(query);
  }

  public void saveCommunityTag(CommunitiesTagData communitiesTag) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(communitiesTag);
  }

  public CommunitiesTagData findByIdCommTag(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(CommunitiesTagData.class).field("id").equal(id).first();
  }

  public void dropCommTag() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CommunitiesTagData> query = datastore.createQuery(CommunitiesTagData.class);
    datastore.delete(query);
  }

  public void saveCommunityPost(CommunitiesPostData communitiesPost) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(communitiesPost);
  }

  public CommunitiesPostData findByIdCommPost(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(CommunitiesPostData.class).field("id").equal(id).first();
  }

  public void dropCommPost() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CommunitiesPostData> query = datastore.createQuery(CommunitiesPostData.class);
    datastore.delete(query);
  }

  public void saveCommunityPostComment(CommunitiesPostCommentData communitiesPostComment) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(communitiesPostComment);
  }

  public CommunitiesPostCommentData findByIdCommPostComment(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(CommunitiesPostCommentData.class).field("id").equal(id).first();
  }

  public void dropCommPostComment() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CommunitiesPostCommentData> query = datastore
        .createQuery(CommunitiesPostCommentData.class);
    datastore.delete(query);
  }
}
