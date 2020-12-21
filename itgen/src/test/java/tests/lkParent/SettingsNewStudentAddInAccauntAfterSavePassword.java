package tests.lkParent;
/* T-119 */
/* создать нового ребенка в дефолтной семье без пароля.
 * Перейти в настройки у этого ребенка. Создать логин-пароль.
 * Проверить, что в быстрых переходах появился новый ребенок
 * Инсерт в базу не подойдет, т.к ученик тогда сразу попадает в быстрый переход, поэтому
 * в этом тесте захардкоджен конкретный ученик
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SettingsNewStudentAddInAccauntAfterSavePassword extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSettingsNewStudentAddInAccauntAfterSavePassword() {
    app.lkParent().reset();
    app.lkParent().clickByHeader();
    app.check().notFindElement(By.xpath("//span[text()='Олегов Олег']"));
    app.base().refresh();
    app.lkParent().createNewStudent();
    app.lkParent().saveLoginAndPassword("loginNew");
    app.lkParent().clickByHeader();
    app.check().findElement(By.xpath("//span[text()='Олегов Олег']"));
    app.base().refresh();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    String id = app.dbstudents().lastStudent().getId();
    if (!id.equals("21")) {
      data.studentService().DeleteById(app.dbstudents().lastStudent().getId());
    }
    //удалить нового ребенка из быстрых переходов
    app.lkParent().clickByHeader();
    app.lkParent().clickByOleg();
    app.base().login("parent", "111111");
  }
}
