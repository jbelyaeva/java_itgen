package tests.lkStudent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentWithoutTrailDisplayTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.trStudent()
        .changeDefaultStudent(
            "21",
            "Ребенок",
            "Дефолтный",
            new String[]{"child"},
            "beginner",
            "BY",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "ru",
            "1",
            "+9875645311",
            2,
            "noTrial",
            0,
            null,
            null,
            0);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testStudentWithoutTrailDisplay() {
    app.student().goInAccountStudentAfterChanged();
    assertThat(app.student().findTutorials(), equalTo(true));
    assertThat(app.student().openCheckConnection(), equalTo(true));
    app.student().btnCloseTutorial();
  }
}
