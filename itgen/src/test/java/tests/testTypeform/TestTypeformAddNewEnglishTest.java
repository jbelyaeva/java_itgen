package tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.typeform.TestData;
import data.model.typeform.Tests;
import data.provides.LocaleUtilsTestData;
import data.services.TestService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformAddNewEnglishTest extends TestBase {

  private final TestService testService = new TestService();
  TestData testClean = null;

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set1_1_NewTestForRusScratch();
  }

  @Test(dataProvider = "validAddTestFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testTypeformAddNewEnglishTest(TestData test) {
    app.goTo().menuTests();
    Tests before = app.dbtest().tests();
    app.test().addEnglishTest(test);
    app.trainer().refresh();
    Tests after = app.dbtest().trySeveralTimeGetNewDateFromDB(before);
    testClean = app.dbtest().lastTest();
    assertThat(after.size(), equalTo(before.size() + 1));
    check(after, test);
  }

  private void check(Tests after, TestData test) {
    data.tests().set1_2_EnTestAddToRusScratch(testClean.getId(), test);
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
