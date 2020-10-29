package io.itgen.tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.ParentData;
import io.itgen.model.WorkerData;
import io.itgen.services.ChatMessageService;
import io.itgen.services.ChatRoomService;
import io.itgen.services.ChatSubscriptionService;
import io.itgen.services.ParentService;
import io.itgen.services.WorkerService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*автосообщение родителю при саморегистрации нельзя удалить под админом*/

public class ChatDeleteModifyAutoMessageTest extends TestBase {

  ParentService parentService = new ParentService();
  WorkerService workerService = new WorkerService();
  ChatRoomService chatRoomService = new ChatRoomService();
  ChatMessageService chatMessageService = new ChatMessageService();
  ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();
  String messageOld = "Добрый день! Чем могу помочь? Если у вас появятся вопросы...";

  @BeforeMethod
  public void ensurePreconditions() {
    WorkerData superadmin = workerService.findById("666");
    ParentData parent = parentService.findById("22");
    Object[] users = new Object[2];
    users[0] = superadmin;
    users[1] = parent;

    app.trChat().saveChatRoom("RoomMessage", new Date(), "m", "-1", users, "Admin_Parent");
    app.trChat()
        .saveChatMessage("deleteMessage", "RoomMessage", new Date(), messageOld, "-1");
    app.trChat().saveChatSubscription(
        "subsc1",
        new Date(),
        "m",
        "RoomMessage",
        "22",
        1,
        true,
        "-1",
        messageOld);
    app.trChat().saveChatSubscription(
        "subsc2",
        new Date(),
        "m",
        "RoomMessage",
        "-1",
        1,
        false,
        "-1",
        messageOld);
  }

  @Test
  public void testChatDeleteModifyAutoMessage() {
    app.chat().btnOpenChat();
    assertThat(app.chat().deleteAutoMessage("22"), equalTo(false));
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
  }
}
