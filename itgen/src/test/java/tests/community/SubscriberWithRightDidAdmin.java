package tests.community;
/* Кейс: есть сообщество с подписчиком c правом.
1. Зайти в сообщество,
2. Перейти в таб Подписчики
3. Нажать на троеточие напротив подписчика с правом
4. Выбрать Сделать администратором
ОР - подписчик переместился в раздел Администраторы */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubscriberWithRightDidAdmin extends TestBase {

  StudentService studentService = new StudentService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
    studentService.updateArrayField("student", "roles",
        new String[]{"child", "createCommunities"});
    data.community().set4_CommunityScratchWithPost_StudentSubscriber("student");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSubscriberWithRightDidAdmin() {
    app.community().SubscriberDidAdmin();
    app.check().textElement(By.xpath("//div[@class='subscribers-panel-header']"),
        "Администраторы сообщества");
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().family().student().parent();
  }
}
