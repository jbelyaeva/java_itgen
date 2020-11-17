package tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import data.model.typeform.TestData;
import data.model.typeform.Tests;
import data.services.TestService;
import app.testbase.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformDeletion extends TestBase {

  private final TestService testService = new TestService();
  private final Date createAt = new Date();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[] {"1"};
    app.trTest()
        .saveTest(
            "deleteTest",
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
    testService.deleteField("deleteTest", "removedAt");
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
    app.trTest()
        .saveTest(
            "deleteTest",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            createAt,
            new Date());

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
    testService.drop();
  }
}
