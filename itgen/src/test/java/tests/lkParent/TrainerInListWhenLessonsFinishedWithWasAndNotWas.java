package tests.lkParent;
/* T-70 */
/*
 * В дефолтную семью добавлен ученик с завершенным вчера занятием c тренером Настей.
 * Перейти в запись на постоянку и убедиться, что тренер Настя есть в списке тренеров под чертой.
 * Перейти в запись на разовое и убедиться, что тренер Настя есть в списке тренеров под чертой.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerInListWhenLessonsFinishedWithWasAndNotWas extends TestBase {

  StudentService studentService = new StudentService();

  @BeforeMethod
  public void ensurePreconditions() {
    String periodFirst = "16:00 - 18:00";
    String periodSecond = "19:00 - 21:00";

    app.trScheduleYesterday()
        .finishingFirstTrialLesson(
            periodFirst, "FinishedFirstSchedule", "14", "LkRecordOnRegularSchedule", "1");

    app.trScheduleYesterday()
        .finishedLesson(
            periodSecond,
            "FinishedSecondSchedule",
            "14",
            "LkRecordOnRegularSchedule",
            "1",
            1,
            "skipped",
            false,
            true,
            false,
            "ru",
            120);

    app.trStudent()
        .studentAddDefaultFamilyAfterLesson
            ("LkRecordOnRegularSchedule",
                "Маша",
                "Машина",
                "expert",
                "BL",
                "Europe/Minsk",
                2,
                app.base().DateWithCorrectionDays(-3650),
                "ru",
                "ru",
                "12345678i",
                "ru",
                "1",
                2,
                1,
                "learning",
                "1",
                "1",
                1);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerInListWhenLessonsFinishedWithWasAndNotWas() {
    app.lkParent().trainerInListOnRegularRecord();
    app.check().findElement(app.lkParent().getTrainerNastyaInList());
    app.lkParent().trainerInListOnSingleRecord();
    app.check().findElement(app.lkParent().getTrainerNastyaInList());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("LkRecordOnRegularSchedule");
    app.postClean().dropTaskAndSchedule();
  }
}
