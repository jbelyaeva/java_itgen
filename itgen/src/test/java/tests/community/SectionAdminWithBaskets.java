package tests.community;
/* Кейс: есть сообщество с подписчиком c правом на создание сообщества + право администрировать.
Перейти в раздел Администраторы
ОР - на против каждого администратора есть корзина */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SectionAdminWithBaskets extends TestBase {

  StudentService studentService = new StudentService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
    studentService.updateArrayField("student", "roles",
        new String[]{"child", "createCommunities"});
    data.community().set5_CommunityScratchWithPostAndComment_StudentSubscriberAndManager("student");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSectionAdminWithBaskets() {
    app.community().goToSubscribers();
    app.check().countElements(
        By.xpath("//div[@class='actions-buttons-wrap']//*[name()='svg' and @id-qa='trash']"), 2);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities().family().parent().student();
  }
}
