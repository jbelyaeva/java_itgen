package io.itgen.tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.RunTestAgain;
import io.itgen.model.TrainerData;
import io.itgen.model.WorkerData;
import io.itgen.services.ChatMessageService;
import io.itgen.services.ChatRoomService;
import io.itgen.services.ChatSubscriptionService;
import io.itgen.services.TrainerService;
import io.itgen.services.WorkerService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatRoleInterlocutorTest extends TestBase {

  TrainerService trainerService = new TrainerService();
  WorkerService workerService = new WorkerService();
  ChatRoomService chatRoomService = new ChatRoomService();
  ChatMessageService chatMessageService = new ChatMessageService();
  ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();
  String messageOld = "Привет";

  @BeforeMethod
  public void ensurePreconditions() {
    WorkerData superadmin = workerService.findById("666");
    TrainerData trainer = trainerService.findById("23");
    Object[] users = new Object[2];
    users[0] = superadmin;
    users[1] = trainer;

    app.trChat().saveChatRoom("RoomMessage", new Date(), "m", "23", users, "Admin_Trainer");
    app.trChat()
        .saveChatMessage("ForeignMessage", "RoomMessage", new Date(), messageOld, "23");
    app.trChat().saveChatSubscription(
        "subsc1",
        new Date(),
        "m",
        "RoomMessage",
        "23",
        1,
        false,
        "23",
        messageOld);
    app.trChat().saveChatSubscription(
        "subsc2",
        new Date(),
        "m",
        "RoomMessage",
        "-1",
        1,
        true,
        "23",
        messageOld);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatRoleInterlocutor() {
    app.chat().btnOpenChat();
    assertThat(app.chat().findRoleInterlocutor("23"),equalTo("Дефолтный Тренер (Тр.)"));
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
  }
}
