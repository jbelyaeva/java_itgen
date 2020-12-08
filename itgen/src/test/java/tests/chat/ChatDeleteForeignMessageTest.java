package tests.chat;

import core.general.RunTestAgain;
import data.model.users.TrainerData;
import data.model.users.WorkerData;
import data.services.ChatMessageService;
import data.services.ChatRoomService;
import data.services.ChatSubscriptionService;
import data.services.TrainerService;
import data.services.WorkerService;
import app.testbase.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatDeleteForeignMessageTest extends TestBase {

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

    app.trChat().saveChatRoom("RoomModifyMessage", new Date(), "m", "666", users, "Admin_Trainer");
    app.trChat()
        .saveChatMessage("modifyMessage", "RoomModifyMessage", new Date(), messageOld, "666");
    app.trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "m", "RoomModifyMessage", "23", 1, 1, "666", messageOld);
    app.trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "m", "RoomModifyMessage", "-1", 1, 0, "666", messageOld);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDeleteForeignMessage() {
    app.chat().deleteMessage("23");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
  }
}
