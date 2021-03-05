package data.services;

import data.dao.CommunitiesDao;
import data.dao.CommunitiesPostCommentDao;
import data.dao.CommunitiesPostDao;
import data.dao.CommunitiesTagDao;
import data.model.communities.CommunitiesPostCommentData;
import data.model.communities.CommunitiesPostData;
import data.model.communities.CommunityData;

public class CommunitiesService {

  private final CommunitiesDao communitiesDao = new CommunitiesDao();
  private final CommunitiesTagDao communitiesTagDao = new CommunitiesTagDao();
  private final CommunitiesPostDao communitiesPostDao = new CommunitiesPostDao();
  private final CommunitiesPostCommentDao communitiesPostCommentDao = new CommunitiesPostCommentDao();

  public CommunitiesService() {
  }

  public void saveCommunity(CommunityData community) {
    communitiesDao.save(community);
  }

  public CommunityData findByIdCommunity(String id) {
    return communitiesDao.findById(id);
  }

  public void dropCommunity() {
    communitiesDao.drop();
  }

  public void dropCommTag(){
    communitiesTagDao.drop();
  }

  public void saveCommunityPost(CommunitiesPostData communitiesPost) {
    communitiesPostDao.save(communitiesPost);
  }

  public CommunitiesPostData findByIdCommPost(String id) {
    return communitiesPostDao.findById(id);
  }

  public void dropCommPost(){
    communitiesPostDao.drop();
  }

  public void saveCommunityPostComment(CommunitiesPostCommentData  communitiesPostComment) {
    communitiesPostCommentDao.save(communitiesPostComment);
  }

  public void dropCommPostComment(){
    communitiesPostCommentDao.drop();
  }
}
