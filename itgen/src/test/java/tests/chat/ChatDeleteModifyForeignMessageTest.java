package tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.TrainerData;
import data.model.users.WorkerData;
import data.services.ChatMessageService;
import data.services.ChatRoomService;
import data.services.ChatSubscriptionService;
import data.services.TrainerService;
import data.services.WorkerService;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatDeleteModifyForeignMessageTest extends TestBase {

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
    app.trChat().saveChatMessage("deleteMessage", "RoomMessage", new Date(), messageOld, "23");
    app.trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "m", "RoomMessage", "23", 1, 0, "23", messageOld);
    app.trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "m", "RoomMessage", "-1", 1, 1, "23", messageOld);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDeleteModifyForeignMessage() {
    assertThat(app.chat().deleteForegnMessage("23"), equalTo(false));
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
  }
}
