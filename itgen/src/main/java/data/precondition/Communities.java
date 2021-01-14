package data.precondition;

import java.util.Date;

public class Communities extends TransactionManager {

  protected static final DataManager data = new DataManager();

  //новое сообщестов (в ensurePreconditions()) без тегов, дефолтный студент без подписки.
  //Администратор, подписчик - супер
  public void set1_NewCommunityScratchWithoutTagsWithPostWithoutLikes(String idCreator,
      String title, String text) {
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};
    String[] skills = {"1"};
    trCommunity().newCommunity(
        "newCommunity",
        new Date(),
        idCreator,
        "Сообщество по направлению Scratch. Лучшие проекты.",
        idManagers,
        idSubscUser,
        dateSubsc,
        1,
        title,
        tags,
        "ru",
        skills);

    String[] idLikes = {};
    String[] idAttachments = {};
    trCommunity()
        .newCommunityPost(
            "newPost",
            text,
            "newCommunity",
            true,
            new Date(),
            idLikes,
            0,
            idCreator,
            idAttachments);
  }

  //дефолтный студент поставил лайк пд постом (set1)
  public void set2_PostWithLike(String idCreator, String text) {
    String[] idLikes = {"21"};
    String[] idAttachments = {};
    trCommunity()
        .newCommunityPost(
            "newPost",
            text,
            "newCommunity",
            true,
            new Date(),
            idLikes,
            1,
            idCreator,
            idAttachments);
  }

  //сообщество с потосм и комментом к посту от деф ребенка
  public void set3_CommunityWithPostAndComment(String idCreator, String text, String title) {
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666", "21"};
    Date[] dateSubsc = {new Date(), new Date()};
    String[] skills = {"1"};
    trCommunity()
        .newCommunity(
            "newCommunity",
            new Date(),
            idCreator,
            "Сообщество по направлению Scratch. Лучшие проекты.",
            idManagers,
            idSubscUser,
            dateSubsc,
            2,
            title,
            tags,
            "ru",
            skills);

    String[] idLikes = {};
    String[] idAttachments = {};
    trCommunity()
        .newCommunityPost(
            "newPost",
            "Ученик созванивается с преподавателем",
            "newCommunity",
            true,
            new Date(),
            idLikes,
            0,
            idCreator,
            idAttachments);

    String[] idPostCommentLikes = {};
    String[] idPostCommentAttachments = {};
    trCommunity()
        .newCommunityPostComment(
            "newComment",
            text,
            "newPost",
            "newCommunity",
            false,
            new Date(),
            new Date(),
            idPostCommentLikes,
            0,
            "21",
            idPostCommentAttachments);
  }
}
