package data.precondition;

import app.appmanager.HelperBase;

public class NewFamilyWithRegularLessons extends TransactionManager {

  protected static final DataManager data = new DataManager();
  HelperBase base = new HelperBase();

  /**
   * новая семья с учеником и одним родителем. Есть на сегодня постоянное занятие без учеников.
   * Тренер Бокша Настя
   */

  public void set1_FamilyAndRegularLesson(String period) {
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

    trScheduleToday().RegularScheduleWithoutStudents(period, "newSchedule", "14");
  }

  /**
   * новая семья с учеником и одним родителем. У ученика есть на сегодня постоянное. Тренер Бокша
   * Настя
   */

  public void set2_FamilyWithRegularLesson(String period) {
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

    trScheduleToday().RegularScheduleWithOneNewStudent(
        period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  /**
   * новая семья с учеником и одним родителем. Есть на завтра постоянное занятие без учеников.
   * Тренер Бокша Настя
   */
  public void set3_FamilyAndRegularLessonTomorrow(String period) {
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

    trScheduleTomorrow().RegularScheduleWithoutStudents(period, "newSchedule", "14");
  }
}
