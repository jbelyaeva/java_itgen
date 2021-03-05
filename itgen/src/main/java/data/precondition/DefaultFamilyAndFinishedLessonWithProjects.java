package data.precondition;

import static app.appmanager.HelperBase.DateWithCorrectionDays;

import app.appmanager.HelperBase;
import core.general.TimeGeneral;
import data.model.materials.MaterialData;
import data.services.MaterialService;

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
        .studentAddDefaultFamilyAfterLesson(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            1,
            "trialFinished",
            new String[]{"1"},
            new String[]{"1"},
            new int[]{1, 120}
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

  /* Дефолтый ученик c данными необходимыми для отображения прогресс бара. 1проект по скрейч.*/
  public void set5_ProgressBarFOrDefaultStudent_1ProjectScratch() {

    trStudent()
        .changeDefaultStudent_finishLessonBy1Skill(
            new String[]{"child"},
            "beginner",
            "BY",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "ru",
            new String[]{"1"},
            "+9875645311",
            2,
            "trialFinished",
            1,
            new String[]{"1"},
            new String[]{"1"},
            1,
            120);

    trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");

    trMaterial()
        .publishedMaterial(
            "MaterialOnLesson",
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
            "02",
            "21",
            "MaterialOnLesson",
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

    trAchievement().newAchievement(
        "21_0",
        "21",
        0,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
  }

  /* Дефолтый ученик c данными необходимыми для отображения прогресс бара. 1проект по скрейч.*/
  public void set6_ProgressBarFOrDefaultStudent_2ProjecsScratchMinecraft() {

    trStudent()
        .changeDefaultStudent_finishLessonBy2Skills(
            new String[]{"child"},
            "beginner",
            "BY",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "ru",
            new String[]{"1", "21"},
            "+9875645311",
            2,
            "trialFinished",
            2,
            new String[]{"1", "21"},
            new String[]{"1", "21"},
            1,
            120);

    trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");
    trMaterial().newMaterialBranch("2", "MaterialMinecraft", "Minecraft");

    trMaterial()
        .publishedMaterial(
            "MaterialOnLesson",
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
        .publishedMaterial(
            "MaterialMinecraft",
            "14",
            "Minecraft",
            "published",
            "21",
            "MaterialMinecraft",
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
            "02",
            "21",
            "MaterialOnLesson",
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

    trMaterial()
        .materialsOnLesson(
            "01",
            "21",
            "MaterialMinecraft",
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

    trAchievement().newAchievement(
        "21_0",
        "21",
        0,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );

    trAchievement().newAchievement(
        "21_1",
        "21",
        1,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
  }

  /* Дефолтый ученик c данными необходимыми для отображения прогресс бара. 3 проекта на разные напр.*/
  public void set7_ProgressBarFOrDefaultStudent_3ProjecsScratchMinecraftPython() {

    trStudent()
        .changeDefaultStudent_finishLessonBy2Skills(//для прогресс-бара это неважно
            new String[]{"child"},
            "beginner",
            "BY",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "ru",
            new String[]{"1", "21", "2"},
            "+9875645311",
            2,
            "trialFinished",
            3,
            new String[]{"1", "21", "2"},
            new String[]{"1", "21", "2"},
            1,
            120);

    trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");
    trMaterial().newMaterialBranch("2", "MaterialMinecraft", "Minecraft");
    trMaterial().newMaterialBranch("3", "MaterialPython", "Python");

    trMaterial()
        .publishedMaterial(
            "MaterialOnLesson",
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
        .publishedMaterial(
            "MaterialMinecraft",
            "14",
            "Minecraft",
            "published",
            "21",
            "MaterialMinecraft",
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
            "MaterialPython",
            "14",
            "Python",
            "published",
            "2",
            "MaterialPython",
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
            "02",
            "21",
            "MaterialOnLesson",
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

    trMaterial()
        .materialsOnLesson(
            "01",
            "21",
            "MaterialMinecraft",
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

    trMaterial()
        .materialsOnLesson(
            "03",
            "21",
            "MaterialPython",
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

    trAchievement().newAchievement(
        "21_0",
        "21",
        0,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );

    trAchievement().newAchievement(
        "21_1",
        "21",
        1,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1)
    );
  }

  /***********************/
  /*У дефолтного ученика есть вчера завершенное занятие с Был (без материалов, без дз) по scratch.
   */
  public void set8_YesterdaScratchWasWithoutProjects_StudentDefault() {
    String period = "18:00 - 20:00";
    trScheduleYesterday()
        .finishingFirstTrialLesson(period, "ScheduleYesterday", "14", "21", "1");

    trFinishedLesson()
        .finishedChildLesson(
            "ScheduleYesterday1856921",
            "ScheduleYesterday",
            time.dateYesterday(),
            0,
            "14",
            "21",
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
            "21",
            "finished",
            0,
            3,
            "1",
            "ru",
            4,
            true,
            false);

    trStudent()
        .changeDefaultStudent_finishLessonBy1Skill(//для прогресс-бара это неважно
            new String[]{"child"},
            "beginner",
            "BY",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "ru",
            new String[]{"1"},
            "+9875645311",
            2,
            "trialFinished",
            1,
            new String[]{"1"},
            new String[]{"1"},
            1,
            120);
  }

  //дефолтный ученик прошел всера пробное по скреч (Бокша) с Был с проектами

  /**
   * В дефолтную семью добавлен ученик с завершенным вчера пробным занятием с историей проектов.
   */
  public void set9_LessonYesterdayFinishedWithProject(String period) {
    MaterialService materialService = new MaterialService();

    trScheduleYesterday()
        .finishingFirstTrialLesson(period, "ScheduleYesterday", "14", "21", "1");

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
            "21",
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
            "21",
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
            "21",
            "finished",
            0,
            3,
            "1",
            "ru",
            4,
            true,
            false,
            time.Stime(period),
            time.Etime(period));

    trFinishedLesson()
        .finishedLessonWithOneStudent(
            "ScheduleYesterday18569",
            "ScheduleYesterday",
            time.dateYesterday(),
            0,
            "14",
            time.Stime(period),
            time.Etime(period),
            time.Stime(period),
            time.Etime(period),
            "21",
            "finished",
            0,
            3,
            "1",
            "ru",
            4,
            true,
            false);

    MaterialData hwMaterial = materialService.findById("MaterialOnLessonFirst");
    MaterialData doneMaterial = materialService.findById("MaterialOnLessonSecond");
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
            "21",
            "ScheduleYesterday",
            time.dateYesterday(),
            DateWithCorrectionDays(-1),
            hwMaterials,
            doneMaterials,
            "Проект Головоломка",
            time.Etime(period),
            time.Stime(period),
            grades,
            "Проект Лаборатория",
            "1",
            "finished",
            text);
  }
}
