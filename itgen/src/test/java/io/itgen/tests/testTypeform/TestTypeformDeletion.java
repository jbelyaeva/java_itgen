package io.itgen.tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.typeform.TestData;
import io.itgen.model.typeform.Tests;
import io.itgen.services.TestService;
import io.itgen.tests.TestBase;
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
    skills = new String[]{"1"};
    app.trTest().saveTest("deleteTest", "Тест", "111111", "ru",
        "Test на переход на новое направление", 5, 5, 10, skills, createAt,
        null);
    testService.deleteField("deleteTest", "removedAt");
  }

  @Test
  public void testTypeformDeletion() {
    app.goTo().menuSchedule();
    app.goTo().menuTests();
    Tests before = app.dbtest().tests();
    app.test().deleteTest();
    Tests after = app.dbtest().tests();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuStudents();
  }

  private void check(Tests after) {
    app.trTest()
        .saveTest(
            "deleteTest", "Тест", "111111", "ru",
            "Test на переход на новое направление", 5,
            5, 10, skills, createAt, new Date());

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
