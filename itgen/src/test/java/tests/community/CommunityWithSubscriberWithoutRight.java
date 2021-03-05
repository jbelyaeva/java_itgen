package tests.community;
/* Кейс: есть сообщество с подписчиком без права. Зайти в сообщество, перейти в табе Подписчики -
проверить, что напротив подписчика нет кнопки троеточие */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.StudentService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CommunityWithSubscriberWithoutRight extends TestBase {

  StudentService studentService = new StudentService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
    studentService.updateArrayField("student", "roles", new String[]{"child"});
    data.community().set13_CommunityWithSubscriber("student");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCommunityWithSubscriberWithoutRight() {
    app.community().goToSubscribers();
    app.check().notFindElement(By.xpath("//div[@class='user-item']//button"));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
  }
}
