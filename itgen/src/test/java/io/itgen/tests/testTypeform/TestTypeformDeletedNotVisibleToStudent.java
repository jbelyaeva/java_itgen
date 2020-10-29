package io.itgen.tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.services.TestService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformDeletedNotVisibleToStudent extends TestBase {

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
            new Date());
  }

  @Test
  public void testTypeformDeletedNotVisibleToStudent() {
    app.goTo().urlTests();
    app.test().goToStudentProfileTabTests("21");

    // проверка, что кнопка есть и она не задизейблена
    assertThat(app.test().buttonGiveTestMissing(), equalTo(false));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
  }
}
