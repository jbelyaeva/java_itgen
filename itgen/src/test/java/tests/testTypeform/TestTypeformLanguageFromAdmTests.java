package tests.testTypeform;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.SetUtils;
import java.util.Set;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformLanguageFromAdmTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set10_TwoTestsInAdminDifferentLang();
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
    data.clean().tests();
  }
}
