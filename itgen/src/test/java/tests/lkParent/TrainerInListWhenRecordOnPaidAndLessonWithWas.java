package tests.lkParent;
/* T-70 */
/*
 * В дефолтную семью добавлен ученик с завершенным вчера занятием c тренером Настей.
 * Перейти в запись на постоянку и убедиться, что тренер Настя есть в списке тренеров под чертой.
 * Перейти в запись на разовое и убедиться, что тренер Настя есть в списке тренеров под чертой.
 */

import app.testbase.TestBase;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerInListWhenRecordOnPaidAndLessonWithWas extends TestBase {

  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {

    app.trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "LkRecordOnRegularSchedule", "1");

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
                "trialFinished",
                "1",
                "1",
                1);
  }

  @Test
  public void testTrainerInListWhenRecordOnPaidAndLessonWithWas() {
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
