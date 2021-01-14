package tests.schedule;
/* T-170 Есть новая семья с ребенком. Есть разовое пробное расписание у ученика на сегодня 21:00. Удалить с
 * пробного ученика
 */
import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveStudentOnTrialSingleLessonTests extends TestBase {

  String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithSingleLessons().set3_FamilyWithTrialSingleLesson(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRemoveStudentOnTrialSingleLesson() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().removeStudent("newSchedule");
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set18_SingleScheduleWithoutStudent(period, "14");
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check()
        .textElement(app.lkParentRecord().getLabelWithoutStudents(), "Нет записанных учеников");
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().student().taskAndSchedule().family().parent();
  }
}
