package ru.stqa.pft.itgen.tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.StudentService;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;


public class SshotStudents  extends TestBase {
  public WebDriver wd;
  @BeforeMethod
  public void ensurePreconditions() {

    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("studentDelete").withTrialBonusOff(false).withTierId("txa")
            .withTierHistory(Collections.singletonList(new FamilyData.TierHistory().withTierHistory("")));
    familyService.create(family);

    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("studentDelete").withFirstName("Маша").withLastName("Машина")
            .withRoles(Collections.singletonList(new StudentData.Roles().withRoles("child")))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("studentDelete").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Collections.singletonList(new StudentData.Langs().withLangs("ru")))
            .withContacts(Collections.singletonList(new StudentData.Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new StudentData.Status().withState("noTrial"));
    studentService.create(student);
  }
  @Test
  public void testSshotStudents() throws AWTException, IOException {

   String expected="./src/test/testsScreenshot/expected/";
   String actual="./src/test/testsScreenshot/actual/";
   String markedImages="./src/test/testsScreenshot/markedImages/";
   String name="students_RU_Chrome";

    app.goTo().menuTasks();
    app.goTo().menuStudents();

   //уводим курсор в верхний левый угол экрана
    Robot bot = new Robot();
    bot.mouseMove(0, 0);
    //получаем
    Screenshot actualScreenshot=app.getScreenShert("//body//th[1]");//взять скриншот после появления элемента с локатором
    //сохраняем
    etalon( expected,name,actualScreenshot);

    File actualFile = new File(actual+name+".png");
    ImageIO.write(actualScreenshot.getImage(), "png", actualFile);

    //берем эталонный снимок
    Screenshot expectedScreenshot = new Screenshot(ImageIO.read(new File(expected+name+".png")));

    //сравниваем
    ImageDiff diff = new ImageDiffer().makeDiff(expectedScreenshot, actualScreenshot);

    //результат
    int rez=diff.getDiffSize();
    if (rez!=0){
      File diffFile = new File(markedImages+name+".png");
      ImageIO.write(diff.getMarkedImage(), "png", diffFile);
    }
    Assert.assertEquals(diff.getDiffSize(), 0);

    //добавим результат в Allure

    /* AllureAttachments.attachScreen(expectedFile.getAbsolutePath(), "Expected: "+name);
    AllureAttachments.attachScreen(actualFile.getAbsolutePath(), "Actual: "+name);
    AllureAttachments.attachScreen(diffFile.getAbsolutePath(), "Differ: "+name);
*/
    }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    FamilyService familyService = new FamilyService();
    FamilyData familyClean = familyService.findById("studentDelete");
    familyService.delete(familyClean);
    StudentService studentService = new StudentService();
    StudentData studentClean = studentService.findById("studentDelete");
    if (studentClean != null) {
      studentService.delete(studentClean);
    }
  }
}
