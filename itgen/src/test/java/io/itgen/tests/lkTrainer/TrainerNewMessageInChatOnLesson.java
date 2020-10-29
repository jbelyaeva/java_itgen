package io.itgen.tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.RunTestAgain;
import io.itgen.general.TimeGeneral;
import io.itgen.model.StudentData;
import io.itgen.model.TrainerData;
import io.itgen.services.ChatMessageService;
import io.itgen.services.ChatRoomService;
import io.itgen.services.ChatSubscriptionService;
import io.itgen.services.CommentService;
import io.itgen.services.FamilyService;
import io.itgen.services.FinishedChildLessonService;
import io.itgen.services.FinishedLessonService;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.services.TrainerService;
import io.itgen.tests.TestBase;
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
            (double) alreadyRun,
            period,
            "Chat",
            "23",
            "Chat",
            "1",
            "ru");

    app.trFamily().newFamily("Chat", false, "RHCtjnpq5oTfhKPQs");

    app.trStudent()
        .newStudent(
            "Chat",
            "Маша",
            "Машина",
            "expert",
            "AL",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "Chat");

    StudentData student = studentService.findById("Chat");
    TrainerData trainer = trainerService.findById("23");
    Object[] users = new Object[2];
    users[0] = student;
    users[1] = trainer;

    app.trChat().saveChatRoom("RoomMessage", new Date(), "d", "Chat", users, "Student_Trainer");
    app.trChat()
        .saveChatMessage("MessageOnLesson", "RoomMessage", new Date(), messageOld, "Chat");
    app.trChat().saveChatSubscription(
        "subsc1",
        new Date(),
        "d",
        "RoomMessage",
        "Chat",
        1,
        false,
        "Chat",
        messageOld);
    app.trChat().saveChatSubscription(
        "subsc2",
        new Date(),
        "d",
        "RoomMessage",
        "23",
        1,
        true,
        "Chat",
        messageOld);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerNewMessageInChatOnLesson() {
    app.trainer().maxBrowser();
    app.trainer().goInLesson("Chat");
    assertThat(app.chat().indicatorNewMessageOnLessonByTrainer(),equalTo(true));
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
