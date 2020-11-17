package tests.testTypeform;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import core.general.RunTestAgain;
import core.general.SetUtils;
import data.services.TestService;
import app.testbase.TestBase;
import java.util.Date;
import java.util.Set;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformLanguageFromAdmTests extends TestBase {

  private final TestService testService = new TestService();
  private final Date createAt = new Date();
  private String[] skills = {"1"};

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[] {"1"};
    app.trTest()
        .saveTest(
            "RussianTest",
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
    app.trTest()
        .saveTest(
            "EnglishTest",
            "ТестИнг",
            "222222",
            "en",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            createAt,
            null);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformLanguageFromAdmTests() {
    app.test().goToStudentProfileTabTests("21");

    // проверка, что можно выбрать только тесты на тех языках, что есть в админке
    Set<String> langs = app.test().getLanguagesInDropdown();
    Set<String> etalonSet = app.test().getEtalonSetInDropdown("Английский", "Русский");
    assertThat(SetUtils.equals(langs, etalonSet), is(true));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
  }
}
