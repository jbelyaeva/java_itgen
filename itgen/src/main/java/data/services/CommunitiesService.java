package data.services;

import data.dao.CommunitiesDao;
import data.model.communities.CommunitiesPostCommentData;
import data.model.communities.CommunitiesPostData;
import data.model.communities.CommunitiesTagData;
import data.model.communities.CommunityData;

public class CommunitiesService {

  private final CommunitiesDao communitiesDao = new CommunitiesDao();

  public CommunitiesService() {}

  public void saveCommunity(CommunityData community) {
    communitiesDao.saveCommunity(community);
  }

  public CommunityData findByIdCommunity(String id) {
    return communitiesDao.findByIdCommunity(id);
  }

  public void dropCommunity(){
    communitiesDao.dropCommunity();
  }

  public void saveCommunityTag(CommunitiesTagData communitiesTag) {
    communitiesDao.saveCommunityTag(communitiesTag);
  }

  public CommunitiesTagData findByIdCommTag(String id) {
    return communitiesDao.findByIdCommTag(id);
  }

  public void dropCommTag(){
    communitiesDao.dropCommTag();
  }

  public void saveCommunityPost(CommunitiesPostData communitiesPost) {
    communitiesDao.saveCommunityPost(communitiesPost);
  }

  public CommunitiesPostData findByIdCommPost(String id) {
    return communitiesDao.findByIdCommPost(id);
  }

  public void dropCommPost(){
    communitiesDao.dropCommPost();
  }

  public void saveCommunityPostComment(CommunitiesPostCommentData  communitiesPostComment) {
    communitiesDao.saveCommunityPostComment(communitiesPostComment);
  }

  public CommunitiesPostCommentData findByIdCommPostComment(String id) {
    return communitiesDao.findByIdCommPostComment(id);
  }

  public void dropCommPostComment(){
    communitiesDao.dropCommPostComment();
  }
}
