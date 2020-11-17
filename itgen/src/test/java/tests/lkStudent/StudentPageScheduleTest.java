package tests.lkStudent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.services.ScheduleService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentPageScheduleTest extends TestBase {

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
            "Schedule",
            "23",
            "21",
            "1",
            "ru");
  }
  /*тест проверяет, что кнопка Все расписание нажимается и ведет на расписание*/
  @Test(retryAnalyzer = RunTestAgain.class)
  public void testStudentPageSchedule() {
    app.student().refresh();
    app.student().goToFeed();
    assertThat(app.student().goOnSchedule(), equalTo(true));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.drop();
  }
}
