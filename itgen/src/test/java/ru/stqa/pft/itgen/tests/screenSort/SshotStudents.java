package ru.stqa.pft.itgen.tests.screenSort;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.StudentService;
import ru.stqa.pft.itgen.tests.TestBase;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class SshotStudents extends TestBase {
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

    String expected = "./src/test/testsScreenshot/expected/";
    String actual = "./src/test/testsScreenshot/actual/";
    String markedImages = "./src/test/testsScreenshot/markedImages/";
    String name = "students_RU_Chrome";
    String locatorFlag="//body//th[1]";
   // String locatorIgnor="//a[contains(@href, '/tasks')]";
    Set<By> bySet = new HashSet<>();
    bySet.add(By.xpath("//a[contains(@href, '/tasks')]"));
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    String locatorIgnor="";
    ImageDiff diff = app.sshot().getImageDiff(expected, actual, markedImages, name,locatorFlag, locatorIgnor);

    //передать имя файла для прикладывания в отчет
    Assert.assertEquals(diff.getDiffSize(), 0);
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
