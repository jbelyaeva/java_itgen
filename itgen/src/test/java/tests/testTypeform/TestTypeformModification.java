package tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.typeform.TestData;
import data.model.typeform.Tests;
import data.provides.LocaleUtilsTestData;
import data.services.TestService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformModification extends TestBase {

  private final TestService testService = new TestService();
  TestData testClean = null;

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set1_1_NewTestForRusScratch();
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
    data.tests().set1_4_RuTestRusPC(testClean.getId(), test);
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
    data.clean().tests();
  }
}
