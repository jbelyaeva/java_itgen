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

  //сообщество, администратор - супер , ребенок подписан на сообщество
  public void set4_CommunityScratchWithPost_StudentSubscriber() {
    String title = "Scratch";
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666", "21"};
    Date[] dateSubsc = {new Date(), new Date()};
    String[] skills = {"1"};
    trCommunity()
        .newCommunity(
            "newCommunity",
            new Date(),
            "666",
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
            "newCommunity",
            "Ученик созванивается с преподавателем",
            "newCommunity",
            true,
            new Date(),
            idLikes,
            0,
            "666",
            idAttachments);
  }

  //Сообщество с постом и комментом к посту. Дефолтный ребенок c подпиской на сообщество,
  // имеет права на создание сообщества,и правом менеджера
  public void set5_CommunityScratchWithPostAndComment_StudentSubscriberAndManager() {
    String title = "Scratch";
    String text = "Новый пост, новый пост";
    String[] tags = {};
    String[] idManagers = {"666", "21"};
    String[] idSubscUser = {"666", "21"};
    Date[] dateSubsc = {new Date(), new Date()};
    String[] skills = {"1"};
    trCommunity()
        .newCommunity(
            "newCommunity",
            new Date(),
            "666",
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
            "666",
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

  //супером создано новое сообщестов без тегов
  public void set6_NewCommunity() {
    String title = "Scratch";
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};
    String[] skills = {"1"};
    trCommunity()
        .newCommunity(
            "newCommunity",
            new Date(),
            "666",
            "Сообщество по направлению Scratch. Лучшие проекты.",
            idManagers,
            idSubscUser,
            dateSubsc,
            1,
            title,
            tags,
            "ru",
            skills);
  }
}
