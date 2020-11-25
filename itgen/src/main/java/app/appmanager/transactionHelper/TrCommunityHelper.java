package app.appmanager.transactionHelper;

import app.appmanager.dbHelpers.DbHelper;
import data.model.communities.CommunitiesPostCommentData;
import data.model.communities.CommunitiesPostData;
import data.model.communities.CommunityData;
import data.model.communities.Managers;
import data.model.communities.Subscribers;
import data.services.CommunitiesService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TrCommunityHelper {

  private final CommunitiesService communitiesService = new CommunitiesService();
  private final DbHelper dbHelper = new DbHelper();

  public void newCommunity(
      String idCommunity,
      Date createAt,
      String idCreator,
      String desc,
      String[] idManagers,
      String[] idSubscribers,
      Date[] dateSubscribers,
      int subsCount,
      String title,
      String[] idTags,
      String lang,
      String[] skills) {
    CommunityData community =
        new CommunityData()
            .withId(idCommunity)
            .withCreateAt(createAt)
            .withCreatorId(idCreator)
            .withDescription(desc)
            .withSubscribersCount(subsCount)
            .withTitle(title)
            .withLang(lang)
            .withTagIds(Arrays.asList(idTags))
            .withSkills(Arrays.asList(skills))
            .withManagers(getManagers(idManagers))
            .withSubscribers(getSubscribers(idSubscribers, dateSubscribers));
    communitiesService.saveCommunity(community);
  }

  private List<Managers> getManagers(String[] idManagers) {
    List<Managers> mas = new ArrayList<Managers>();
    for (String idManager : idManagers) {
      mas.add(new Managers()
          .withId(idManager)
          .withRoles(dbHelper.roles(idManager)));
    }
    return mas;
  }

  private List<Subscribers> getSubscribers(String[] idSubscribers, Date[] dateSubscribers) {
    List<Subscribers> mas = new ArrayList<Subscribers>();
    for (int i = 0; i < idSubscribers.length; i++) {
      mas.add(new Subscribers()
          .withId(idSubscribers[i])
          .withSubAt(dateSubscribers[i]));
    }
    return mas;
  }


  public void newCommunityPost(
      String idPost,
      String text,
      String idCommunities,
      boolean fromCommunity,
      Date createAt,
      String[] idLikes,
      int likesCount,
      String idCreator,
      String[] idAttachments) {
    CommunitiesPostData communityPost =
        new CommunitiesPostData()
            .withId(idPost)
            .withText(text)
            .withCommunityId(idCommunities)
            .withFromCommunity(fromCommunity)
            .withCreateAt(createAt)
            .withLikes(Arrays.asList(idLikes))
            .withLikesCount(likesCount)
            .withAttachments(Arrays.asList(idAttachments))
            .withCreatorId(idCreator);
    communitiesService.saveCommunityPost(communityPost);
  }

  public void newCommunityPostComment(
      String idComment,
      String text,
      String idPost,
      String idCommunity,
      boolean fromCommunity,
      Date createAt,
      Date updatedAt,
      String[] idLikes,
      int likesCount,
      String idCreator,
      String[] idAttachments) {
    CommunitiesPostCommentData communityPostComment =
        new CommunitiesPostCommentData()
            .withId(idComment)
            .withText(text)
            .withPostId(idPost)
            .withCommunityId(idCommunity)
            .withFromCommunity(fromCommunity)
            .withCreateAt(createAt)
            .withUpdatedAt(updatedAt)
            .withLikes(Arrays.asList(idLikes))
            .withLikesCount(likesCount)
            .withAttachments(Arrays.asList(idAttachments))
            .withCreatorId(idCreator);
    communitiesService.saveCommunityPostComment(communityPostComment);
  }
}
