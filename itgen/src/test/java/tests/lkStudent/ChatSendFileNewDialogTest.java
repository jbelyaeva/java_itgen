package tests.lkStudent;
/*T-254
 * Дефолтный студент записан на пробное на сегодня. Оно началось. Начать диалог с тренером,
 * отправив ему изображение
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.chat.ChatMessages;
import data.model.chat.ChatRooms;
import data.model.chat.ChatSubscriptions;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatSendFileNewDialogTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long alreadyRun = 7200000;//2ч
    TimeGeneral time = new TimeGeneral();
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set26_TodayStartSingleScheduleWithOneStudentOnTrial(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSendFileNewDialog() throws InterruptedException, IOException {

    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions beforeSubscription = app.dbchat().chatSubscriptions();

    String path = "/src/test/resources/testdata/file.jpg";
    String fileName = "file.jpg";
    app.chat().sendFileByStudent("Тренер", path);
    Thread.sleep(4000); // не успевает сохраниться информация о файле в бд
    Boolean getFile = app.chat().fileGetTrainerFromStudent("trainer", "111111", fileName, "21");
    ChatMessages afterMessage = app.dbchat().chatMessages();
    ChatRooms afterRooms = app.dbchat().chatRooms();
    ChatSubscriptions afterSubscription = app.dbchat().chatSubscriptions();

    app.check().equalityOfTwoElements(afterMessage.size(), beforeMessage.size() + 1);
    app.check().equalityOfTwoElements(afterRooms.size(), beforeRooms.size() + 1);
    app.check().equalityOfTwoElements(afterSubscription.size(), beforeSubscription.size() + 2);
    app.check().equalityOfTwoElements(getFile, true);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().chat().taskAndSchedule();
  }
}
