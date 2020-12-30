package data.precondition;

import app.appmanager.HelperBase;
import core.general.TimeGeneral;
import data.model.materials.MaterialData;

public class DefaultFamilyAndFinishedLessonWithProjects extends TransactionManager {

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
            new String[]{"1"},
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
            "ScheduleYesterday18569newStudent",
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
    String[] text = {"Ученик очень старался",
        "Аня пришла сегодня с  домашним проектом, хоть и с ошибками,"
            + " но радует, что уже делала, разбиралась :) Разобрали ошибки и приступили к новой задачке на "
            + "закрепление тем, с которыми Аня уже работала. Не отвлекалась, работала с интересом сегодня, "
            + "молодец :)", "Телепортация"};
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

    trMaterial()
        .addCommentWithNoteUser(
            "fromAdmin",
            "666",
            "newStudent",
            base.DateWithCorrectionDays(-2),
            "Заметка заметка заметка"
        );
  }
  /*****************/
  /**
   * В дефолтную семью добавлен ученик  записан на сегодня на пробное по скрейч и на разовое на
   * майнкрафт
   */

  public void set2_TrialAndSingleLessonsStarted() {
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
            base.DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "withTrial"
        );
    data.studentService().deleteField("newStudent", "services");
    long alreadyRunOld = 21600000;
    String period1 = time.getPeriod(time.getTimeNow() - alreadyRunOld);
    trScheduleToday()
        .StartSingleScheduleWithOneStudentOnNewSkill(
            (double) alreadyRunOld,
            period1,
            "old",
            "14",
            "newStudent",
            "1",
            "ru");

    long alreadyRun = 7200000;
    String period2 = time.getPeriod(time.getTimeNow() - alreadyRun);
    trScheduleToday()
        .StartSingleScheduleWithOneStudentOnNewSkill(
            (double) alreadyRun,
            period2,
            "new",
            "14",
            "newStudent",
            "21",
            "ru");
  }

  /***********************/
  /* В дефолтную семью добавлен ученик с завершенным вчера пробным занятием с историей проектов по скрейч.Есть заметка от админа.
  Есть вчера завершенное занятие с Сорвано (без материалов) по майнкрафт, но с отметкой о дз.
   */
  public void set3_YesterdayScratchFinishedMinecraftDisrupt_StudentAddDefaultFamily() {
    String period = "06:00 - 08:00";
    set1_LessonYesterdayFinishedWithProject_StudentAddInDefaultFamily(period);
    trStudent()
        .studentAddDefaultFamilyAfterScratchMinecraft(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "111",
            1,
            base.DateWithCorrectionDays(-3650),
            "ru",
            "Europe/Minsk",
            "1234567",
            "ru",
            new String[]{"1", "21"},
            2,
            2,
            "learning",
            new String[]{"1", "21"},
            new String[]{"1", "21"}
        );
    String period2 = "12:00 - 14:00";
    trScheduleYesterday()
        .finishedLesson(
            period2,
            "old",
            "14",
            "newStudent",
            "21",
            3,
            "abort",
            true,
            "oneTime",
            false,
            "ru",
            120
        );
    trFinishedLesson()
        .finishedChildLesson(
            "old18569newStudent",
            "old",
            time.dateYesterday(),
            0,
            "14",
            "newStudent",
            "abort",
            0,
            3,
            "21",
            "ru",
            0,
            false,
            false,
            time.StimeYesterday(period2),
            time.EtimeYesterday(period2));

    trFinishedLesson()
        .finishedLessonWithOneStudent(
            "old18569",
            "old",
            time.dateYesterday(),
            0,
            "14",
            time.StimeYesterday(period2),
            time.EtimeYesterday(period2),
            time.StimeYesterday(period2),
            time.EtimeYesterday(period2),
            "newStudent",
            "finished",
            0,
            3,
            "21",
            "ru",
            0,
            false,
            false);

    String text = "Аня пришла сегодня с  домашним проектом, хоть и с ошибками,"
        + " но радует, что уже делала, разбиралась :) Разобрали ошибки и приступили к новой задачке на "
        + "закрепление тем, с которыми Аня уже работала. Не отвлекалась, работала с интересом сегодня, "
        + "молодец :)";
    trMaterial()
        .addCommentWithoutMaterialsWithHW(
            "2",
            "14",
            "newStudent",
            "old",
            time.dateYesterday(),
            base.DateWithCorrectionDays(-1),
            time.EtimeYesterday(period2),
            time.StimeYesterday(period2),
            "Проект Лаборатория",
            "21",
            "abort",
            text);
  }

  /***********************/
  /* В дефолтную семью добавлен ученик с пропущенным вчера пробным занятием по скрейч.
  Есть вчера завершенное занятие с Сорвано (без материалов, без дз) по майнкрафт.
   */
  public void set4_YesterdayScratchSkippedMinecraftDisrupt_StudentAddDefaultFamily() {
    String period = "06:00 - 08:00";
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
            base.DateWithCorrectionDays(-2956),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial");

    trScheduleYesterday()
        .finishedLesson(
            period,
            "old",
            "14",
            "newStudent",
            "1",
            3,
            "skipped",
            false,
            "trial",
            false,
            "ru",
            120
        );
    trFinishedLesson()
        .finishedChildLesson(
            "old18569newStudent",
            "old",
            time.dateYesterday(),
            0,
            "14",
            "newStudent",
            "skipped",
            0,
            3,
            "1",
            "ru",
            0,
            true,
            false,
            time.StimeYesterday(period),
            time.EtimeYesterday(period));

    trFinishedLesson()
        .finishedLessonWithOneStudent(
            "old18569",
            "old",
            time.dateYesterday(),
            0,
            "14",
            time.StimeYesterday(period),
            time.EtimeYesterday(period),
            time.StimeYesterday(period),
            time.EtimeYesterday(period),
            "newStudent",
            "skipped",
            0,
            3,
            "1",
            "ru",
            0,
            true,
            false);

    String period2 = "12:00 - 14:00";
    trScheduleYesterday()
        .finishedLesson(
            period2,
            "new",
            "14",
            "newStudent",
            "21",
            3,
            "abort",
            true,
            "oneTime",
            false,
            "ru",
            120
        );
    trFinishedLesson()
        .finishedChildLesson(
            "new18569newStudent",
            "new",
            time.dateYesterday(),
            0,
            "14",
            "newStudent",
            "abort",
            0,
            3,
            "21",
            "ru",
            0,
            false,
            false,
            time.StimeYesterday(period2),
            time.EtimeYesterday(period2));

    trFinishedLesson()
        .finishedLessonWithOneStudent(
            "new18569",
            "new",
            time.dateYesterday(),
            0,
            "14",
            time.StimeYesterday(period2),
            time.EtimeYesterday(period2),
            time.StimeYesterday(period2),
            time.EtimeYesterday(period2),
            "newStudent",
            "abort",
            0,
            3,
            "21",
            "ru",
            0,
            false,
            false);

    String text = "Аня пришла сегодня с  домашним проектом, хоть и с ошибками,"
        + " но радует, что уже делала, разбиралась :) Разобрали ошибки и приступили к новой задачке на "
        + "закрепление тем, с которыми Аня уже работала. Не отвлекалась, работала с интересом сегодня, "
        + "молодец :)";
    trMaterial()
        .addCommentWithoutMaterialsAndHW(
            "2",
            "14",
            "newStudent",
            "old",
            time.dateYesterday(),
            base.DateWithCorrectionDays(-1),
            time.EtimeYesterday(period2),
            time.StimeYesterday(period2),
            "21",
            "abort",
            text);
  }
}
