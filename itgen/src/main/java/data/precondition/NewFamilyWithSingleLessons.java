package data.precondition;

import app.appmanager.HelperBase;

public class NewFamilyWithSingleLessons extends TransactionManager {

  protected static final DataManager data = new DataManager();
  HelperBase base = new HelperBase();

  /**
   * новая семья с учеником и одним родителем. Есть на сегодня разовое занятие без учеников. Тренер
   * Бокша Настя
   */
  public void set1_FamilyAndSingleLesson(String period) {
    trFamily().newFamily("newFamily", false, "HseKLp6muYbZQ3cZA");
    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "newFamily",
            "Europe/Minsk",
            1,
            base.DateWithCorrectionDays(-2460),
            "ru",
            "ru",
            "78787878i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial"
        );
    trParent().newParent("newParent", "Родитель", "Новый", "BL", "Europe/Minsk",
        "ru", "newFamily", "+97895554433", "julja@tt.ru", true);

    trScheduleToday().SingleScheduleWithoutStudents(period, "newSchedule", "14");
  }

  /* новая семья с учеником и одним родителем. Есть на сегодня разовое занятие. Тренер Бокша Настя
   */
  public void set2_FamilyAndSingleLesson(String period, String idTrainer) {
    trFamily().newFamily("newFamily", false, "HseKLp6muYbZQ3cZA");
    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "newFamily",
            "Europe/Minsk",
            1,
            base.DateWithCorrectionDays(-2460),
            "ru",
            "ru",
            "78787878i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial"
        );
    trParent().newParent("newParent", "Родитель", "Новый", "BL", "Europe/Minsk",
        "ru", "newFamily", "+97895554433", "julja@tt.ru", true);

    trScheduleToday().SingleScheduleWithOneStudent(
        period, "newSchedule", idTrainer, "newStudent", "1", "ru");
  }

  /* новая семья с учеником и одним родителем. Есть на сегодня пробное разовое занятие. Тренер Бокша Настя
   */
  public void set3_FamilyWithTrialSingleLesson(String period) {
    trFamily().newFamily("newFamily", false, "HseKLp6muYbZQ3cZA");
    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "newFamily",
            "Europe/Minsk",
            1,
            base.DateWithCorrectionDays(-2460),
            "ru",
            "ru",
            "78787878i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial"
        );
    trParent().newParent("newParent", "Родитель", "Новый", "BL", "Europe/Minsk",
        "ru", "newFamily", "+97895554433", "julja@tt.ru", true);

    trScheduleToday().SingleScheduleWithOneStudentOnTrail(
        period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  /**
   * семья с учеником и одним родителем
   */
  public void set4_FamilyWithStudentAfterLessonAndParent() {
    trFamily().newFamily("newFamily", false, "HseKLp6muYbZQ3cZA");
    trStudent()
        .studentInFamilyAfterScratch(
            "student",
            "newFamily",
            "Олег",
            "Олегов",
            "beginner",
            "BY",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-2460),
            "ru",
            "ru",
            "+9875645311",
            "ru",
            new String[]{"1"},
            2,
            1,
            "trialFinished",
            new String[]{"1"},
            new String[]{"1"}
        );
    trParent().newParent(
        "newParent",
        "Родитель",
        "Новый",
        "BL",
        "Europe/Minsk",
        "ru",
        "newFamily",
        "+97895554433",
        "julja@tt.ru",
        true
    );
  }

  /**
   * новая семья с учеником и одним родителем. Есть на сегодня разовое занятие без учеников. Тренер
   * Бокша Настя
   */
  public void set5_FamilyAndRegularIFLesson(String period) {
    trFamily().newFamily("newFamily", false, "HseKLp6muYbZQ3cZA");
    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "newFamily",
            "Europe/Minsk",
            1,
            base.DateWithCorrectionDays(-2460),
            "ru",
            "ru",
            "78787878i",
            "ru",
            new String[]{"1"},
            2,
            "learning"
        );
    trParent().newParent("newParent", "Родитель", "Новый", "BL", "Europe/Minsk",
        "ru", "newFamily", "+97895554433", "julja@tt.ru", true);

    trScheduleToday().RegularIF1hScheduleWithoutStudents(period, "newSchedule", "14");
  }

  public void set6_FamilyAndTomorrowSingleLesson(String period) {
    trFamily().newFamily("newFamily", false, "HseKLp6muYbZQ3cZA");
    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "newFamily",
            "Europe/Minsk",
            1,
            base.DateWithCorrectionDays(-2460),
            "ru",
            "ru",
            "78787878i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial"
        );
    trParent().newParent("newParent", "Родитель", "Новый", "BL", "Europe/Minsk",
        "ru", "newFamily", "+97895554433", "julja@tt.ru", true);

    trScheduleTomorrow().SingleScheduleWithoutStudent(period, "newSchedule", "14");
  }


  public void set7_FamilyAndYesterdayWasTrialWasSkipSingleIFLesson() {
    String periodFirst = "10:00 - 12:00";
    String periodSecond = "18:00 - 20:00";
    trFamily().newFamily("newFamily", false, "HseKLp6muYbZQ3cZA");
    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "newFamily",
            "Europe/Minsk",
            1,
            base.DateWithCorrectionDays(-2460),
            "ru",
            "ru",
            "78787878i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial"
        );
    trParent().newParent("parent", "Родитель", "Новый", "BL", "Europe/Minsk",
        "ru", "newFamily", "+97895554433", "julja@tt.ru", true);

    trScheduleYesterday()
        .finishingFirstTrialLesson(
            periodFirst, "FinishedTrialSchedule", "14", "newStudent", "1");

    trScheduleYesterday()
        .finishedIFLesson(
            periodSecond,
            "FinishedSchedule",
            "14",
            "newStudent",
            "1",
            3,
            "skipped",
            true,
            "oneTime",
            true,
            "ru",
            60
        );
  }
}
