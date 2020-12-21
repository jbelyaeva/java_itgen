package data.precondition;

import app.appmanager.HelperBase;
import core.general.TimeGeneral;
import data.model.materials.MaterialData;

public class DefaultFamilyAndFinishedLessonWithProjects extends TranzactionManager {

  protected static final DataManager data = new DataManager();
  HelperBase base = new HelperBase();
  TimeGeneral time = new TimeGeneral();

  /**
   * В дефолтную семью добавлен ученик с завершенным вчера пробным занятием с историей проектов.
   */
  public void set1_LessonYesterdayFinishedWithProject_StudentAddInDefaultFamily(
      String period) {
    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "111",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-1460),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "learning"
        );

    trScheduleYesterday()
        .finishingFirstTrialLesson(period, "ScheduleYesterday", "14", "newStudent", "1");

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

    trMaterial()
        .materialsOnLesson(
            "01",
            "newStudent",
            "MaterialOnLessonFirst",
            true,
            "notStarted",
            "14",
            "ScheduleYesterday",
            time.dateYesterday(),
            "markHw",
            true,
            false,
            true,
            null,
            null);

    trMaterial()
        .materialsOnLesson(
            "02",
            "newStudent",
            "MaterialOnLessonSecond",
            false,
            "done",
            "14",
            "ScheduleYesterday",
            time.dateYesterday(),
            "changeStatus",
            true,
            null,
            null,
            "notStarted",
            "done");

    trFinishedLesson()
        .finishedChildLesson(
            "ScheduleYesterday1856921",
            "ScheduleYesterday",
            time.dateYesterday(),
            0,
            "14",
            "newStudent",
            "finished",
            0,
            3,
            "1",
            "ru",
            4,
            true,
            false,
            time.StimeYesterday(period),
            time.EtimeYesterday(period));

    trFinishedLesson()
        .finishedLessonWithOneStudent(
            "ScheduleYesterday18569",
            "ScheduleYesterday",
            time.dateYesterday(),
            0,
            "14",
            time.StimeYesterday(period),
            time.EtimeYesterday(period),
            time.StimeYesterday(period),
            time.EtimeYesterday(period),
            "newStudent",
            "finished",
            0,
            3,
            "1",
            "ru",
            4,
            true,
            false);

    MaterialData hwMaterial = data.materialService().findById("MaterialOnLessonFirst");
    MaterialData doneMaterial = data.materialService().findById("MaterialOnLessonSecond");
    String[] hwMaterials = {
        hwMaterial.getTitle(), hwMaterial.getType(), hwMaterial.getMaterialLink(), "notStarted"
    };
    String[] doneMaterials = {
        doneMaterial.getTitle(), doneMaterial.getType(), doneMaterial.getMaterialLink(), "done"
    };

    Integer[] grades = {3, 2, 4, 2, 4, 4};
    String[] text = {"Ученик очень старался", "Ученик очень старался", "Телепортация"};
    trMaterial()
        .addComment(
            "1",
            "14",
            "newStudent",
            "ScheduleYesterday",
            time.dateYesterday(),
            base.DateWithCorrectionDays(-1),
            hwMaterials,
            doneMaterials,
            "Проект Головоломка",
            time.EtimeYesterday(period),
            time.StimeYesterday(period),
            grades,
            "Проект Лаборатория",
            "1",
            "finished",
            text);
  }
}
