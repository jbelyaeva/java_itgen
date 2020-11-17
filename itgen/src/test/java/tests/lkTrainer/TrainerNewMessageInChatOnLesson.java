package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.services.ChatMessageService;
import data.services.ChatRoomService;
import data.services.ChatSubscriptionService;
import data.services.CommentService;
import data.services.FamilyService;
import data.services.FinishedChildLessonService;
import data.services.FinishedLessonService;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import data.services.TrainerService;
import app.testbase.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerNewMessageInChatOnLesson extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  TaskService taskService = new TaskService();
  CommentService commentService = new CommentService();
  TrainerService trainerService = new TrainerService();
  private final TimeGeneral time = new TimeGeneral();
  ChatRoomService chatRoomService = new ChatRoomService();
  ChatMessageService chatMessageService = new ChatMessageService();
  ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();
  FinishedChildLessonService finishedChildLessonService = new FinishedChildLessonService();
  FinishedLessonService finishedLessonService = new FinishedLessonService();
  private String period = "";
  private final long alreadyRun = 7200000; // 2 часа идет занятие
  String messageOld = "Привет";

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow() - alreadyRun);
    app.trScheduleToday()
        .StartSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun, period, "Chat", "23", "Chat", "1", "ru");

    app.trFamily().newFamily("Chat", false, "RHCtjnpq5oTfhKPQs");

    app.trStudent()
        .NewStudent(
            "Chat",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Chat",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2);

    Object[] users = new Object[2];
    users[0] = studentService.findById("Chat");
    users[1] = trainerService.findById("23");

    app.trChat().saveChatRoom("RoomMessage", new Date(), "d", "Chat", users, "Student_Trainer");
    app.trChat().saveChatMessage("MessageOnLesson", "RoomMessage", new Date(), messageOld, "Chat");
    app.trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "d", "RoomMessage", "Chat", 1, false, "Chat", messageOld);
    app.trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "d", "RoomMessage", "23", 1, true, "Chat", messageOld);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerNewMessageInChatOnLesson() {
    app.trainer().maxBrowser();
    app.trainer().goToLesson("Chat");
    assertThat(app.chat().indicatorNewMessageOnLessonByTrainer(), equalTo(true));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("Chat");
    studentService.DeleteById("Chat");
    familyService.DeleteById("Chat");
    finishedChildLessonService.drop();
    finishedLessonService.drop();
    taskService.drop();
    commentService.drop();
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
  }
}
