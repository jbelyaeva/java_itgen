package tests.lkStudent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.chat.ChatMessages;
import data.model.chat.ChatRooms;
import data.model.chat.ChatSubscriptions;
import data.model.users.StudentData;
import data.model.users.TrainerData;
import data.services.ChatMessageService;
import data.services.ChatRoomService;
import data.services.ChatSubscriptionService;
import data.services.StudentService;
import data.services.TrainerService;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatModifyMessageTest extends TestBase {

  TrainerService trainerService = new TrainerService();
  StudentService studentService = new StudentService();
  ChatRoomService chatRoomService = new ChatRoomService();
  ChatMessageService chatMessageService = new ChatMessageService();
  ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();
  String messageOld = "Привет";

  @BeforeMethod
  public void ensurePreconditions() {
    StudentData student = studentService.findById("21");
    TrainerData trainer = trainerService.findById("23");
    Object[] users = new Object[2];
    users[0] = student;
    users[1] = trainer;

    app.trChat().saveChatRoom("RoomMessage", new Date(), "d", "23", users, "Student_Trainer");
    app.trChat().saveChatMessage("MessageOnLesson", "RoomMessage", new Date(), messageOld, "21");
    app.trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "d", "RoomMessage", "21", 1, false, "21", messageOld);
    app.trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "d", "RoomMessage", "23", 1, true, "21", messageOld);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatModifyMessage() {
    String messageNew = "Пока";
    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions beforeSubscription = app.dbchat().chatSubscriptions();

    app.chat().modifyMessageByStudent(messageNew);
    String messageGetTrainer = app.chat().getByTrainerMessageFromStudent("trainer", "111111");

    ChatMessages afterMessage = app.dbchat().chatMessages();
    ChatRooms afterRooms = app.dbchat().chatRooms();
    ChatSubscriptions afterSubscription = app.dbchat().chatSubscriptions();

    assertThat(afterMessage.size(), equalTo(beforeMessage.size()));
    assertThat(afterRooms.size(), equalTo(beforeRooms.size()));
    assertThat(afterSubscription.size(), equalTo(beforeSubscription.size()));
    assertThat(messageNew, equalTo(messageGetTrainer));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
  }
}
