package tests.community;
/* Кейс: есть сообщество с подписчиком c правом. Зайти в сообщество, перейти в табе Подписчики -
проверить, что напротив подписчика есть кнопка троеточие */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.CommunitiesService;
import data.services.FamilyService;
import data.services.StudentService;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CommunityWithSubscriberWithRight extends TestBase {

  CommunitiesService communitiesService = new CommunitiesService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  String title = "Scratch";

  @BeforeMethod
  public void ensurePreconditions() {
    app.trFamily().newFamily("newStudent", false, "EuxjfWpTMbxtGMb2j");

    app.trStudent()
        .NewStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "recordStudent",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "noTrial");
    studentService.updateField("newStudent", "roles", new String[]{"child", "createCommunities"});

    String[] tags = {};
    String[] idManagers = {"666"};
    String[] idSubscUser = {"666", "newStudent"};
    Date[] dateSubsc = {new Date(), new Date()};
    String[] skills = {"1"};
    app.trCommunity()
        .newCommunity(
            "newCommunity",
            new Date(),
            "666",
            "Сообщество по направлению Scratch. Лучшие проекты.",
            idManagers,
            idSubscUser,
            dateSubsc,
            2,
            title,
            tags,
            "ru",
            skills);

  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCommunityWithSubscriberWithRight() {
    app.community().goToSubscribers();
    app.check().findElement(By.xpath("//div[@class='user-item']//button"));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    communitiesService.dropCommunity();
    studentService.DeleteById("newStudent");
    familyService.DeleteById("newStudent");
  }
}
