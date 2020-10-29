package io.itgen.tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.RunTestAgain;
import io.itgen.model.chat.ChatMessages;
import io.itgen.model.chat.ChatRooms;
import io.itgen.model.chat.ChatSubscriptions;
import io.itgen.services.ChatMessageService;
import io.itgen.services.ChatRoomService;
import io.itgen.services.ChatSubscriptionService;
import io.itgen.tests.TestBase;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ChatSendFileNewDialogTest extends TestBase {
  ChatRoomService chatRoomService = new ChatRoomService();
  ChatMessageService chatMessageService = new ChatMessageService();
  ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();
  String fileName="file.jpg";

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSendFileNewDialog() throws InterruptedException, IOException {

    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions  beforeSubscription = app.dbchat().chatSubscriptions();

    String path = "/src/test/resources/testdata/file.jpg";
    app.chat().sendFile("Тренер",path);
    Thread.sleep(3000);//не успевает сохраниться информация о файле в бд
    Boolean getFile = app.chat().fileGetTrainer("trainer","111111", fileName);
    ChatMessages afterMessage = app.dbchat().chatMessages();
    ChatRooms afterRooms = app.dbchat().chatRooms();
    ChatSubscriptions afterSubscription = app.dbchat().chatSubscriptions();

    assertThat(afterMessage.size(), equalTo(beforeMessage.size() + 1));
    assertThat(afterRooms.size(), equalTo(beforeRooms.size() + 1));
    assertThat(afterSubscription.size(), equalTo(beforeSubscription.size() + 2));
    assertThat(getFile, equalTo(true));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
  }
}
