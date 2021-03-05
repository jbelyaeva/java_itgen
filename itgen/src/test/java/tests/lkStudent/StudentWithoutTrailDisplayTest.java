package tests.lkStudent;
/*T-237
 * Авторизоваться под учеником в статусе Без пробного- открывается таб Проверка звука и видео.
 * "У ученика нет завершенных занятий", есть туториал "Добро пожаловать"
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentWithoutTrailDisplayTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set19_ChangeDefaultStudentInStart();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testStudentWithoutTrailDisplay() {
    app.lkStudent().goInAccountStudentAfterChanged();
    app.check().equalityOfTwoElements(app.lkStudent().findTutorials(), true);
    app.check().equalityOfTwoElements(app.lkStudent().openCheckConnection(), true);
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().btnLogo();
  }
}
