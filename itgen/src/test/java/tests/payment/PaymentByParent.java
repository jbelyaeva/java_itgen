package tests.payment;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.materials.MaterialData;
import data.services.MaterialService;
import data.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaymentByParent extends TestBase {

  StudentService studentService = new StudentService();
  private final TimeGeneral time = new TimeGeneral();
  MaterialService materialService = new MaterialService();

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    app.trScheduleYesterday()
        .finishingFirstTrialLesson(period, "ScheduleYesterday", "14", "paymentByParent", "1");

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
            "paymentByParent",
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
            "paymentByParent",
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
            "paymentByParent",
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
            "paymentByParent",
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
            "paymentByParent",
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

    app.trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "paymentByParent",
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
            new String[]{"1"},
            2,
            1,
            "trialFinished",
            new String[]{"1"},
            new String[]{"1"},
            new int[]{1, 120});
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testPaymentByParent() {
    app.lkParent().reset();
    app.payment().goToShopByParent();
    app.payment().paymentByParent();
    //  assertThat(app.student().findPictureSuccessPay(), equalTo(true)); разкомментить, когда будут
    // права
    // добавить проверку на пополнение баланса, когда будут права, а затем вернуть баланс в 0
    //  app.payment().goToBack("paymentAdmin");
    app.payment().goToFamily("111");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().payment().material().finishedLesson();
    studentService.DeleteById("paymentByParent");
  }
}
