package tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.chat.ChatMessages;
import data.model.chat.ChatRooms;
import data.model.chat.ChatSubscriptions;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ChatSendFileNewDialogTest extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSendFileNewDialog() throws InterruptedException, IOException {
    app.base().refresh();
    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions beforeSubscription = app.dbchat().chatSubscriptions();

    String path = "/src/test/resources/testdata/file.jpg";
    app.chat().sendFileByAdmin("Тренер", path);//аналогичный алгоритм
    Thread.sleep(5000); // не успевает сохраниться информация о файле в бд
    ChatMessages afterMessage = app.dbchat().chatMessages();
    ChatRooms afterRooms = app.dbchat().chatRooms();
    ChatSubscriptions afterSubscription = app.dbchat().chatSubscriptions();

    assertThat(afterMessage.size(), equalTo(beforeMessage.size() + 1));
    assertThat(afterRooms.size(), equalTo(beforeRooms.size() + 1));
    assertThat(afterSubscription.size(), equalTo(beforeSubscription.size() + 2));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat();
  }
}
