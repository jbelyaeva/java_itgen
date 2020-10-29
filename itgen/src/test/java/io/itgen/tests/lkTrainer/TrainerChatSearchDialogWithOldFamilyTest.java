package io.itgen.tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.TimeGeneral;
import io.itgen.model.ParentData;
import io.itgen.model.StudentData;
import io.itgen.model.TrainerData;
import io.itgen.services.ChatMessageService;
import io.itgen.services.ChatRoomService;
import io.itgen.services.ChatSubscriptionService;
import io.itgen.services.CommentService;
import io.itgen.services.FamilyService;
import io.itgen.services.FinishedChildLessonService;
import io.itgen.services.FinishedLessonService;
import io.itgen.services.ParentService;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.services.TrainerService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerChatSearchDialogWithOldFamilyTest extends TestBase {
  StudentService studentService = new StudentService();
  ParentService parentService = new ParentService();
  TrainerService trainerService = new TrainerService();
  ChatRoomService chatRoomService = new ChatRoomService();
  ChatMessageService chatMessageService = new ChatMessageService();
  ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();
  String messageOld = "Привет";

  @BeforeMethod
  public void ensurePreconditions() {
    StudentData student = studentService.findById("21");
    ParentData parent = parentService.findById("22");
    TrainerData trainer = trainerService.findById("23");
    Object[] usersDialog1 = new Object[2];
    usersDialog1[0] = student;
    usersDialog1[1] = trainer;

    app.trChat().saveChatRoom("RoomStudent", new Date(), "d", "21", usersDialog1, "Student_Trainer");
    app.trChat()
        .saveChatMessage("MessageOnLesson", "RoomStudent", new Date(), messageOld, "21");
    app.trChat().saveChatSubscription(
        "subsc1",
        new Date(),
        "d",
        "RoomStudent",
        "21",
        1,
        false,
        "21",
        messageOld);
    app.trChat().saveChatSubscription(
        "subsc2",
        new Date(),
        "d",
        "RoomStudent",
        "23",
        1,
        true,
        "21",
        messageOld);

    Object[] usersDialog2 = new Object[2];
    usersDialog2[0] = parent;
    usersDialog2[1] = trainer;

    app.trChat().saveChatRoom("RoomParent", new Date(), "d", "22", usersDialog2, "Parent_Trainer");
    app.trChat()
        .saveChatMessage("MessageParent", "RoomParent", new Date(), messageOld, "21");
    app.trChat().saveChatSubscription(
        "subsc1Par",
        new Date(),
        "d",
        "RoomParent",
        "22",
        1,
        false,
        "22",
        messageOld);
    app.trChat().saveChatSubscription(
        "subsc2Par",
        new Date(),
        "d",
        "RoomParent",
        "23",
        1,
        true,
        "22",
        messageOld);
  }

  @Test
  public void testTrainerChatSearchDialogWithOldStudent() {
    app.chat().btnOpenChat();
    assertThat(app.chat().searchPerson("ребенок"),equalTo(true));
    app.chat().btnCloseChat();
  }

  @Test
  public void testTrainerChatSearchDialogWithOldParent() {
    app.chat().btnOpenChat();
    assertThat(app.chat().searchPerson("родитель"),equalTo(true));
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
  }

}
