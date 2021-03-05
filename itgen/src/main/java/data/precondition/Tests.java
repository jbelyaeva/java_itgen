package data.precondition;

import app.appmanager.HelperBase;
import data.model.typeform.TestData;
import data.services.TestResultsService;
import data.services.TestService;
import java.util.Date;

public class Tests extends TransactionManager {

  protected static final DataManager data = new DataManager();
  HelperBase base = new HelperBase();
  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();

  //В админке тестов
  public void set1_NewTestForRusMinecraft() {
    String[] skills = new String[]{"21"};
    trTest()
        .saveTest(
            "newTest",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            new Date(),
            null);
    data.testService().deleteField("deleteTest", "removedAt");
  }

  public void set1_1_NewTestForRusScratch() {
    String[] skills = new String[]{"1"};
    trTest()
        .saveTest(
            "newTest",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            new Date(),
            null);
    data.testService().deleteField("deleteTest", "removedAt");
  }

  public void set1_2_EnTestAddToRusScratch(String id, TestData test) {
    String[] skills = new String[]{"1"};
    trTest()
        .saveTest(
            id,
            test.getTitle(),
            test.getRootFormId(),
            "en",
            test.getDescription(),
            5,
            5,
            10,
            skills,
            new Date(),
            null);
  }

  public void set1_3_RuTestRusPython(String id, TestData test) {
    String[] skills = new String[]{"2"};
    trTest()
        .saveTest(
            id,
            test.getTitle(),
            test.getRootFormId(),
            "ru",
            test.getDescription(),
            test.getMinScore(),
            test.getMaxScore(),
            test.getTimeForPassing(),
            skills,
            new Date(),
            null);
  }

  public void set1_4_RuTestRusPC(String id, TestData test) {
    String[] skills = new String[]{"26"};
    trTest()
        .saveTest(
            id,
            test.getTitle(),
            test.getRootFormId(),
            "ru",
            test.getDescription(),
            test.getMinScore(),
            test.getMaxScore(),
            test.getTimeForPassing(),
            skills,
            new Date(),
            null);
  }

  public void set1_4_RemovedTestRusScratch() {
    String[] skills = new String[]{"1"};
    trTest()
        .saveTest(
            "newTest",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            new Date(),
            new Date());
  }

  /**
   * Новый ученик добавлен в дефолтную семью, прошел положительно тест на майнкрафт. Тест есть в
   * админке направлений
   */
  public void set2_NewStudentInDefaultFamilyPassedTestOnMinecraft() {
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

    String[] skills = new String[]{"21"};
    trTest()
        .saveTest(
            "test",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            new Date(),
            null);
    data.testService().deleteField("deleteTest", "removedAt");

    trTest()
        .saveResultTest(
            "TestPass", "newStudent", "test", "Тест", "111111", skills, "ru", 5, 5, new Date(), "",
            true);
  }

  /**
   * Новый ученик добавлен в дефолтную семью, завалил тест на майнкрафт. Тест есть в админке
   * направлений
   */
  public void set3_NewStudentInDefaultFamilyFailedTestOnMinecraft() {
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

    String[] skills = new String[]{"21"};
    trTest()
        .saveTest(
            "test",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            new Date(),
            null);
    data.testService().deleteField("deleteTest", "removedAt");

    trTest()
        .saveResultTest(
            "TestNotPass",
            "newStudent",
            "NotPass",
            "test",
            "111111",
            skills,
            "ru",
            5,
            5,
            new Date(),
            "",
            false);
  }

  /**
   * Новый ученик добавлен в дефолтную семью, прошел вчера побное по Скрейч, прошел положительно
   * тест на майнкрафт. Тест есть в админке направлений.
   */
  public void set4_NewStudentInDefaultFamily_AfterTrialByScratch_PassedTestOnMinecraft(
      String period) {
    trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "newStudent", "1");

    trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-2700),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            1,
            "learning",
            new String[]{"1"},
            new String[]{"1"},
            new int[]{1, 120});

    String[] skills = new String[]{"21"};
    trTest()
        .saveTest(
            "test",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            new Date(),
            null);
    data.testService().deleteField("deleteTest", "removedAt");

    trTest()
        .saveResultTest(
            "TestPass",
            "newStudent",
            "Pass",
            "test",
            "111111",
            skills,
            "ru",
            5,
            5,
            new Date(),
            "",
            true);
  }

  /**
   * Новый ученик добавлен в дефолтную семью, прошел вчера побное по Скрейч, прошел отрицательно
   * тест на майнкрафт. Тест есть в админке направлений.
   */
  public void set5_NewStudentInDefaultFamily_AfterTrialByScratch_FailedTestOnMinecraft(
      String period) {
    trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "newStudent", "1");

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
            "learning",
            new String[]{"1"},
            new String[]{"1"},
            new int[]{1, 120});

    String[] skills = new String[]{"21"};
    trTest()
        .saveTest(
            "test",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            new Date(),
            null);
    data.testService().deleteField("deleteTest", "removedAt");

    trTest()
        .saveResultTest(
            "TestFailed",
            "newStudent",
            "Faild",
            "test",
            "111111",
            skills,
            "ru",
            5,
            5,
            new Date(),
            "",
            false);
  }

  /**
   * Новый ученик добавлен в дефолтную семью, прошел вчера побное по Майнкрафт, прошел положительно
   * тест на майнкрафт. Тест есть в админке направлений.
   */
  public void set6_NewStudentInDefaultFamily_AfterTrialByMinecraft_PassedTestOnMinecraft(
      String period) {
    trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "newStudent", "21");

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
            new String[]{"21"},
            2,
            1,
            "finishedTrial",
            new String[]{"1"},
            new String[]{"1"},
            new int[]{1, 120});

    String[] skills = new String[]{"21"};
    trTest()
        .saveTest(
            "test",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            new Date(),
            null);
    data.testService().deleteField("deleteTest", "removedAt");

    trTest()
        .saveResultTest(
            "TestPass",
            "newStudent",
            "Pass",
            "test",
            "111111",
            skills,
            "ru",
            5,
            5,
            base.DateWithCorrectionDays(-1),
            "",
            true);
  }

  //тест есть в админке и выдан дефолтному ученику
  public void set7_TestInProcess() {
    TestService testService = new TestService();
    Date createAt = new Date();
    String[] skills = new String[]{"1"};
    trTest()
        .saveTest(
            "test",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            createAt,
            null);

    testService.deleteField("InProcess", "removedAt");

    trTest()
        .saveResultTestInProcess(
            "InProcess", "21", "InProcess", "Тест", "111111", skills, "ru", 5, 5, createAt, "");
  }

  //тест есть в админке и выдан дефолтному ученику, ученик прошел его успешно
  public void set8_TestPassed() {
    Date createAt = new Date();
    String[] skills = new String[]{"1"};
    trTest()
        .saveTest(
            "test",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            createAt,
            null);

    testService.deleteField("Pass", "removedAt");

    trTest()
        .saveResultTest(
            "TestPass", "21", "test", "Тест", "111111", skills, "ru",
            5, 5, createAt, "", true);
  }

  //тест есть в админке и выдан дефолтному ученику, ученик прошел его успешно
  public void set8_1_TestFailed() {
    String[] skills = new String[]{"1"};
    trTest()
        .saveTest(
            "NotPass",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            new Date(),
            null);

    testService.deleteField("NotPass", "removedAt");

    trTest()
        .saveResultTest(
            "TestNotPass",
            "21",
            "NotPass",
            "Тест",
            "111111",
            skills,
            "ru",
            5,
            5,
            new Date(),
            "",
            false);
  }

  //У дефолтного ребенка два теста: пройден успешно и не успешно
  public void set9_TestSetDefaultStudent() {
    String[] skills = new String[]{"1"};
    Date createAt = new Date();
    trTest()
        .saveTest(
            "Pass",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            createAt,
            null);

    testService.deleteField("Pass", "removedAt");

    trTest()
        .saveResultTest(
            "TestPass", "21", "Pass", "Тест", "111111", skills, "ru", 5, 5, createAt, "", true);

    trTest()
        .saveTest(
            "NotPass",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            createAt,
            null);

    testService.deleteField("NotPass", "removedAt");

    trTest()
        .saveResultTest(
            "TestNotPass",
            "21",
            "NotPass",
            "Тест",
            "111111",
            skills,
            "ru",
            5,
            5,
            createAt,
            "",
            false);
    testResultsService.updateField("TestNotPass", "entityTestId", "99999");
  }

  //У дефолтного ребенка два теста: пройден успешно и не успешно
  public void set10_TwoTestsInAdminDifferentLang() {
    trTest()
        .saveTest(
            "RussianTest",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            new String[]{"1"},
            new Date(),
            null);
    trTest()
        .saveTest(
            "EnglishTest",
            "ТестИнг",
            "222222",
            "en",
            "Test на переход на новое направление",
            5,
            5,
            10,
            new String[]{"1"},
            new Date(),
            null);
  }
}
