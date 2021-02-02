package tests.lkStudent;
/* T-261
 * Перейти в таб Тестирование. Таб Тестирование. У ученика нет тестов.
 * Отображается текст Нет пройденных и выданных тестов */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class StudentWithoutTestTypeform extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testStudentWithoutTestTypeform() {
    app.lkStudent().goToStudentProfileTabTests();
    app.check().textElement(app.lkStudent().getLabelNotTests(), "Нет пройденных и выданных тестов");
    app.lkStudent().goToFeed();
  }
}
