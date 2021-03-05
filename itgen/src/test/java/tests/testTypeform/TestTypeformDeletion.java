package tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.typeform.TestData;
import data.model.typeform.Tests;
import data.services.TestService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformDeletion extends TestBase {

  private final TestService testService = new TestService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set1_1_NewTestForRusScratch();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformDeletion() {
    app.goTo().urlTests();
    Tests before = app.dbtest().tests();
    app.test().deleteTest();
    Tests after = app.dbtest().trySeveralTimeGetNewDateFromDB(before);
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().urlTests();
  }

  private void check(Tests after) {
    data.tests().set1_4_RemovedTestRusScratch();
    TestData testAdd = testService.findById("deleteTest");

    for (TestData testAfter : after) {
      if (testAfter.getId().equals("deleteTest")) {
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
