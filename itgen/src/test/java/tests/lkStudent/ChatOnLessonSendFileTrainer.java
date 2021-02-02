package tests.lkStudent;
/*T-80
 *Авторизоваться под дефолтным учеником. Есть запись на пробное, которое началось.
 * Нажать Перейти к занятию
 * Написать в чате (который на занятии) сообщение тренеру, прикрепив файл
 * Проверить:
 * 1.сообщение отображается в этом чате
 * 2.и в обычном чате
 * 3.в обычном чате под тренером
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatOnLessonSendFileTrainer extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long alreadyRun = 7200000;//2ч
    TimeGeneral time = new TimeGeneral();
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set26_TodayStartSingleScheduleWithOneStudentOnTrial(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatOnLessonSendFileTrainer() throws IOException, InterruptedException {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().goOnLesson();
    app.lkStudent().btnCloseTutorialTips();
    String path = "/src/test/resources/testdata/file.jpg";
    String fileName = "file.jpg";
    app.chat().sendFileByStudentInChatOnLesson("Тренер", path);
    //1
    app.check().assertTrue(app.chat().getNameFileInChatOnLesson(fileName));
    app.chat().btnOpenChat();
    app.chat().chooseFirstWithUser();
    //2
    app.check().assertTrue(app.chat().getNameFile(fileName));
    Boolean getFile = app.chat().fileGetTrainerFromStudent("trainer", "111111", fileName, "21");
    //3
    app.check().equalityOfTwoElements(getFile, true);
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().chat();
  }
}
