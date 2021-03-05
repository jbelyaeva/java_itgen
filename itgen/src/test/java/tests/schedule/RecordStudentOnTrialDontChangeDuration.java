package tests.schedule;
/* T-443
 * Есть новая семья с ребенком c пробным 2 ч.
 * Перейти в занятие и убедиться, что "часы" задизейблены
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordStudentOnTrialDontChangeDuration extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithSingleLessons().set3_FamilyWithTrialSingleLesson(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRecordStudentOnTrialDontChangeDuration() {
    app.goTo().menuSchedule();
    app.schedule().tryChangeDurationInUsualTrial("newSchedule");
    app.check().findElement(By.xpath("//span[@class='duration glyphicon glyphicon-time']"));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().taskAndSchedule().family().parent();
  }
}
