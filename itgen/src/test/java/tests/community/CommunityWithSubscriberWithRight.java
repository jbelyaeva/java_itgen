package tests.community;
/* Кейс: есть сообщество с подписчиком c правом. Зайти в сообщество, перейти в табе Подписчики -
проверить, что напротив подписчика есть кнопка троеточие */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CommunityWithSubscriberWithRight extends TestBase {

  StudentService studentService = new StudentService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
    studentService.updateArrayField("student", "roles",
        new String[]{"child", "createCommunities"});
    data.community().set4_CommunityScratchWithPost_StudentSubscriber("student");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCommunityWithSubscriberWithRight() {
    app.community().goToSubscribers();
    app.check().findElement(By.xpath("//div[@class='user-item']//button"));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities().student().family().parent();
  }
}
