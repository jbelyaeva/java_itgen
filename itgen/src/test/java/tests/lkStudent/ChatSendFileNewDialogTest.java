package tests.lkStudent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.chat.ChatMessages;
import data.model.chat.ChatRooms;
import data.model.chat.ChatSubscriptions;
import data.services.ChatMessageService;
import data.services.ChatRoomService;
import data.services.ChatSubscriptionService;
import data.services.ScheduleService;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatSendFileNewDialogTest extends TestBase {

  ChatRoomService chatRoomService = new ChatRoomService();
  ChatMessageService chatMessageService = new ChatMessageService();
  ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();
  String fileName = "file.jpg";
  ScheduleService scheduleService = new ScheduleService();
  private final TimeGeneral time = new TimeGeneral();
  private String period = "";
  private final long alreadyRun = 7200000; // 2 часа идет занятие

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow() - alreadyRun);
    app.trScheduleToday()
        .StartSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun,
            period,
            "FileForTrainer",
            "23",
            "21",
            "1",
            "ru");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSendFileNewDialog() throws InterruptedException, IOException {

    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions beforeSubscription = app.dbchat().chatSubscriptions();

    String path = "/src/test/resources/testdata/file.jpg";
    app.chat().sendFileByStudent("Тренер", path);
    Thread.sleep(4000); // не успевает сохраниться информация о файле в бд
    Boolean getFile = app.chat().fileGetTrainerFromStudent("trainer", "111111", fileName, "21");
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
    scheduleService.drop();
  }
}
