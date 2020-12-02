package tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.LocaleUtilsTestData;
import core.general.RunTestAgain;
import data.model.typeform.TestData;
import data.model.typeform.Tests;
import data.services.TestService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformModification extends TestBase {

  private final TestService testService = new TestService();
  TestData testClean = null;
  private final Date createAt = new Date();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[]{"1"};
    app.trTest()
        .saveTest(
            "addEnglishTest",
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
  }

  @Test(dataProvider = "validDataModifyTestFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testTypeformModification(TestData test) throws InterruptedException {
    app.base().waitVisibleElement(5, By.xpath("//h2"));
    app.goTo().menuTests();
    Tests before = app.dbtest().tests();
    app.test().modifyTest(test);
    Thread.sleep(5000);
    Tests after = app.dbtest().trySeveralTimeGetNewDateFromDB(before);
    testClean = app.dbtest().lastTest();
    assertThat(after.size(), equalTo(before.size()));
    check(after, test);
    app.goTo().menuTasks();
  }

  private void check(Tests after, TestData test) {
    skills = new String[]{"26"};
    app.trTest()
        .saveTest(
            testClean.getId(),
            test.getTitle(),
            test.getRootFormId(),
            "ru",
            test.getDescription(),
            test.getMinScore(),
            test.getMaxScore(),
            test.getTimeForPassing(),
            skills,
            createAt,
            null);

    TestData testAdd = testService.findById(testClean.getId());

    for (TestData testAfter : after) {
      if (testAfter.getId().equals(testClean.getId())) {
        assertThat(after, equalTo(after.without(testAfter).withAdded(testAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
  }
}
