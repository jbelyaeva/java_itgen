package tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import data.model.users.TrainerData;
import data.model.users.WorkerData;
import data.model.chat.ChatMessages;
import data.model.chat.ChatRooms;
import data.model.chat.ChatSubscriptions;
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

public class ChatDeleteMessageTest extends TestBase {

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
  public void testChatDeleteMessage() {
    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions beforeSubscription = app.dbchat().chatSubscriptions();

    app.chat().deleteMessage("23");
    Boolean messageGetTrainer = app.chat().getByTrainerDeletedMessageFromAdmin("trainer", "111111");

    ChatMessages afterMessage = app.dbchat().chatMessages();
    ChatRooms afterRooms = app.dbchat().chatRooms();
    ChatSubscriptions afterSubscription = app.dbchat().chatSubscriptions();

    assertThat(afterMessage.size(), equalTo(beforeMessage.size()));
    assertThat(afterRooms.size(), equalTo(beforeRooms.size()));
    assertThat(afterSubscription.size(), equalTo(beforeSubscription.size()));
    assertThat(messageGetTrainer, equalTo(false));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
  }
}
