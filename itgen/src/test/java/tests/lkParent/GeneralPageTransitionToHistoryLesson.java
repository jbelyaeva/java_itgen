package tests.lkParent;
/* T-12 */
/*
 * В дефолтную семью добавлен ученик с завершенным вчера занятием с историей проектов.
 * Перейти в таб История и проверить, что в нем отобраэается завершенное занятие
 */

import static core.general.DateFormat.formatDD;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.materials.MaterialData;
import data.services.CommentService;
import data.services.FinishedChildLessonService;
import data.services.FinishedLessonService;
import data.services.MaterialBranchService;
import data.services.MaterialChildsService;
import data.services.MaterialService;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GeneralPageTransitionToHistoryLesson extends TestBase {

  private final TimeGeneral time = new TimeGeneral();
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  TaskService taskService = new TaskService();
  CommentService commentService = new CommentService();
  MaterialChildsService materialChildsService = new MaterialChildsService();
  MaterialBranchService materialBranchService = new MaterialBranchService();
  FinishedChildLessonService finishedChildLessonService = new FinishedChildLessonService();
  FinishedLessonService finishedLessonService = new FinishedLessonService();
  MaterialService materialService = new MaterialService();
  String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "111",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-1460),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "learning"
        );

    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(period, "ScheduleYesterday", "14", "newStudent", "1");

    app.trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");

    app.trMaterial()
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

    app.trMaterial()
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

    app.trMaterial()
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

    app.trMaterial()
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

    app.trFinishedLesson()
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

    app.trFinishedLesson()
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
    app.trMaterial()
        .addComment(
            "1",
            "14",
            "newStudent",
            "ScheduleYesterday",
            time.dateYesterday(),
            app.base().DateWithCorrectionDays(-1),
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

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testGeneralPageTransitionToHistoryLesson() {
    app.lkParent().reset();
    app.lkParent().clickByShowHistory();

    app.check().findElement(app.lkParent().getTabHistory());
    app.check().textElement(app.lkParent().getLabelPeriodLessonInHistory(), period);
    String day = formatDD(app.base().DateWithCorrectionDays(-1));
    app.check().textElement(app.lkParent().getLabelDayAndMonthLessonInHistory(), day);
    app.check().textElement(app.lkParent().getLabelProjectDoneInHistory(), "Лабиринт");
    app.check().textElement(app.lkParent().getLabelProjectNotStartedInHistory(), "Жуки");
    app.check().findElement(app.lkParent().getLabelGradeAboutLessonInHistory());
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    studentService.DeleteById("newStudent");
    scheduleService.drop();
    scheduleService.DeleteById("ScheduleYesterday");
    materialBranchService.drop();
    materialChildsService.drop();
    materialService.drop();
    finishedChildLessonService.drop();
    finishedLessonService.drop();
    taskService.drop();
    commentService.drop();
  }
}
