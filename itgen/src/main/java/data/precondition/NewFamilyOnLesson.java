package data.precondition;

import static app.appmanager.HelperBase.DateWithCorrectionDays;

import app.appmanager.HelperBase;
import core.general.TimeGeneral;

public class NewFamilyOnLesson extends TransactionManager {

  protected static final DataManager data = new DataManager();
  HelperBase base = new HelperBase();
  TimeGeneral time = new TimeGeneral();

  //новая семья с учеником. Ученик на уроке. Есть материалы, которые можно выдать
  public void set1_StudentOnLessonWithMaterials(String idSchedule) {
    long alreadyRun = 7200000;
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    trScheduleToday()
        .StartSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun,
            period,
            idSchedule,
            "23",
            "student",
            "1",
            "ru");

    trFamily().newFamily("family", false, "txa");

    trStudent()
        .newStudent(
            "student",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "family",
            "Europe/Minsk",
            2,
            DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial");

    trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");

    trMaterial()
        .publishedMaterial(
            "MaterialOnLessonFirst",
            "14",
            "Жуки",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");

    trMaterial()
        .publishedMaterial(
            "MaterialOnLessonSecond",
            "14",
            "Лабиринт",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");
  }

  //новая семья с учеником. Ученик на уроке. Есть материалы, которые можно выдать
  public void set2_StudentOnLesson() {
    long alreadyRun = 7200000;
    String period = time.getPeriod(time.getTimeNow() - alreadyRun);
    trScheduleToday()
        .StartSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun,
            period,
            "newSchedule",
            "23",
            "student",
            "1",
            "ru");

    trFamily().newFamily("family", false, "txa");

    trStudent()
        .newStudent(
            "student",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "family",
            "Europe/Minsk",
            2,
            DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial");
  }

  //новая семья с учеником. сейчас начнется урок. Тренер дефолтный
  public void set3_StudentBeforeLesson() {
    String period = time.getPeriod(time.getTimeNow());
    trScheduleToday()
        .SingleScheduleWithOneStudentOnTrail(
            period, "schedule", "23", "student", "1", "ru");

    trFamily().newFamily("family", false, "txc");

    trStudent()
        .newStudent(
            "student",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "family",
            "Europe/Minsk",
            2,
            DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial");
  }
}
