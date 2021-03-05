package tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformDeletedNotVisibleToStudent extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set1_4_RemovedTestRusScratch();
  }

  @Test
  public void testTypeformDeletedNotVisibleToStudent() {
    app.goTo().menuTests();
    app.test().goToStudentProfileTabTests("21");
    // проверка, что кнопка есть и она не задизейблена
    assertThat(app.test().buttonGiveTestMissing(), equalTo(false));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().tests();
  }
}
