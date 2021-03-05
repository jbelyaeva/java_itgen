package data.precondition;

import static app.appmanager.HelperBase.DateWithCorrectionDays;

import core.general.TimeGeneral;
import java.util.Date;

public class Communities extends TransactionManager {

  protected static final DataManager data = new DataManager();
  private TimeGeneral time = new TimeGeneral();

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
  public void set3_CommunityWithPostAndCommentFromStudent(String idCreator, String text,
      String title) {
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
  public void set4_CommunityScratchWithPost_StudentSubscriber(String idStudent) {
    String title = "Scratch";
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666", idStudent};
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
  public void set5_CommunityScratchWithPostAndComment_StudentSubscriberAndManager(
      String idStudent) {
    String title = "Scratch";
    String text = "Новый пост, новый пост";
    String[] tags = {};
    String[] idManagers = {"666", idStudent};
    String[] idSubscUser = {"666", idStudent};
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

  // пост
  public void set6_NewPost(String idPost, String text) {
    String[] idLikes = {};
    String[] idAttachments = {};
    trCommunity()
        .newCommunityPost(
            idPost,
            text,
            "newCommunity",
            true,
            new Date(),
            idLikes,
            0,
            "666",
            idAttachments);
  }

  //сообщество, администратор - супер , дефолтный ребенок подписан на сообщество + новый ребенок подписан
  //на это сообщество (c ачивками и прогресс баром)
  public void set7_CommunityScratchWithPost_DefaultStudentSubscriber_SecondStudentSubscriber() {
    String title = "Scratch";
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666", "21", "student"};
    Date[] dateSubsc = {new Date(), new Date(), new Date()};
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

    data.newFamilyWithSingleLessons().set4_FamilyWithStudentAfterLessonAndParent();
    trAchievement().newAchievement(
        "student_0",
        "student",
        0,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
    data.newFamilyWithSingleLessons().set4_FamilyWithStudentAfterLessonAndParent();
    trAchievement().newAchievement(
        "student_1",
        "student",
        1,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
    data.newFamilyWithSingleLessons().set4_FamilyWithStudentAfterLessonAndParent();
    trAchievement().newAchievement(
        "student_2",
        "student",
        2,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );

    trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");

    trMaterial()
        .publishedMaterial(
            "MaterialOnLesson",
            "14",
            "Лабиринт",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");

    trMaterial()
        .materialsOnLesson(
            "02",
            "student",
            "MaterialOnLesson",
            false,
            "done",
            "14",
            "ScheduleYesterday",
            time.dateYesterday(),
            "changeStatus",
            true,
            null,
            null,
            "notStarted",
            "done");
  }

  //сообщество, администратор - супер , дефолтный ребенок подписан на сообщество + новый ребенок подписан
  //на это сообщество (4 ачивки))
  public void set8_CommunityScratchWithPost_DefaultStudentSubscriber_SecondStudentSubscriberWith4Achiv() {
    String title = "Scratch";
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666", "21", "student"};
    Date[] dateSubsc = {new Date(), new Date(), new Date()};
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

    data.newFamilyWithSingleLessons().set4_FamilyWithStudentAfterLessonAndParent();
    trAchievement().newAchievement(
        "student_0",
        "student",
        0,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
    trAchievement().newAchievement(
        "student_1",
        "student",
        1,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
    trAchievement().newAchievement(
        "student_2",
        "student",
        2,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
    trAchievement().newAchievement(
        "student_3",
        "student",
        3,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
  }

  //сообщество, администратор - супер , дефолтный ребенок подписан на сообщество + новый ребенок подписан
  //на это сообщество (4 ачивки, четвертая не выполнена))
  public void set9_CommunityScratchWithPost_DefaultStudentSubscriber_SecondStudentSubscriberWith4Achiv() {
    String title = "Scratch";
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666", "21", "student"};
    Date[] dateSubsc = {new Date(), new Date(), new Date()};
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

    data.newFamilyWithSingleLessons().set4_FamilyWithStudentAfterLessonAndParent();
    trAchievement().newAchievement(
        "student_0",
        "student",
        0,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
    trAchievement().newAchievement(
        "student_1",
        "student",
        1,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
    trAchievement().newAchievement(
        "student_2",
        "student",
        2,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
    trAchievement().newAchievement(
        "student_3",
        "student",
        3,
        false,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
  }

  //сообщество с постом и комментом к посту от админа
  public void set10_CommunityWithPostAndCommentFromAdmin(String text, String title) {
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
            "666",
            idPostCommentAttachments);
  }

  //три сообщество по трем направлениям (первый питон, второй скреч, третий майнкафт - разная дата создания)
  public void set11_CommunityDifferentSkill() {
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};

    trCommunity()
        .newCommunity(
            "newCommunity1",
            DateWithCorrectionDays(-5),
            "666",
            "Сообщество по направлению Python. Лучшие проекты.",
            idManagers,
            idSubscUser,
            dateSubsc,
            2,
            "Python",
            tags,
            "ru",
            new String[]{"2"});

    trCommunity()
        .newCommunity(
            "newCommunity2",
            DateWithCorrectionDays(-3),
            "666",
            "Сообщество по направлению Scratch. Лучшие проекты.",
            idManagers,
            idSubscUser,
            dateSubsc,
            2,
            "Scratch",
            tags,
            "ru",
            new String[]{"1"});

    trCommunity()
        .newCommunity(
            "newCommunity3",
            DateWithCorrectionDays(-1),
            "666",
            "Сообщество по направлению Minecraft. Лучшие проекты.",
            idManagers,
            idSubscUser,
            dateSubsc,
            2,
            "Minecraft",
            tags,
            "ru",
            new String[]{"21"});
  }

  public void set12_CommunityDifferentLang() {
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};

    trCommunity()
        .newCommunity(
            "newCommunity1",
            DateWithCorrectionDays(-5),
            "666",
            "Сообщество по направлению Python. Лучшие проекты.",
            idManagers,
            idSubscUser,
            dateSubsc,
            2,
            "Python",
            tags,
            "ru",
            new String[]{"2"});

    trCommunity()
        .newCommunity(
            "newCommunity2",
            DateWithCorrectionDays(-3),
            "666",
            "Сообщество по направлению Scratch. Лучшие проекты.",
            idManagers,
            idSubscUser,
            dateSubsc,
            2,
            "Scratch",
            tags,
            "en",
            new String[]{"1"});

    trCommunity()
        .newCommunity(
            "newCommunity3",
            DateWithCorrectionDays(-1),
            "666",
            "Сообщество по направлению Minecraft. Лучшие проекты.",
            idManagers,
            idSubscUser,
            dateSubsc,
            2,
            "Minecraft",
            tags,
            "es",
            new String[]{"21"});
  }

  public void set13_CommunityWithSubscriber(String idSubscriber) {
    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666", idSubscriber};
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
            "Scratch",
            tags,
            "ru",
            skills);
  }

  public void set14_CommunityWithTags(String[] tags, String desc, String title) {
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666"};
    Date[] dateSubsc = {new Date()};
    String[] skills = {"1"};
    trCommunity()
        .newCommunity(
            "newCommunity",
            new Date(),
            "666",
            desc,
            idManagers,
            idSubscUser,
            dateSubsc,
            1,
            title,
            tags,
            "ru",
            skills);
  }

  public void set14_CommunityWithPost() {
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
            "Scratch",
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
  }
}
