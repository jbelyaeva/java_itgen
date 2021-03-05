package tests.lkStudent;
/*T-277
 * Авторизоваться под дефолтным учеником. Есть запись на разовое на новое направление , которое началось.
 * Нажать Перейти к занятию  - отображается лейбл Новое
 * Перейти в календарь расписания - отображается лейбл Новое
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentLabelNewSubject extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long alreadyRun = 7200000;//2ч
    TimeGeneral time = new TimeGeneral();
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set25_TodayStartSingleScheduleWithOneStudentOnNewSkill(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testStudentLabelNewSubject() {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().goToFeed();
    app.lkStudent().goOnLesson();
    app.check().findElement(By.xpath("//div[@class='lesson-label new-skill']"));
    app.lkStudent().goToFeed();
    app.lkStudent().btnAllSchedule();
    app.check().findElement(By.xpath("//span[@class='label new-subj-label']"));
    app.base().clickWithMoveToElementAndWait(1, By.xpath("//span[@class='label new-subj-label']"));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule();
  }
}
