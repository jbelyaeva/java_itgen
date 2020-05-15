package ru.stqa.pft.itgen.tests.screenSort;

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


public class SshotFamily extends TestBase {

  public WebDriver wd;
  private static String pathToFile = "C:/Devel/Projects/java_itgen/itgen/src/test/resources/testdata/ashot.txt";

  @BeforeMethod
  public void ensurePreconditions() {

    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("familyAshot").withTrialBonusOff(false).withTierId("txa")
            .withTierHistory(Collections.singletonList(new FamilyData.TierHistory().withTierHistory("")));
    familyService.create(family);

    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("studentAshot").withFirstName("Маша").withLastName("Машина")
            .withRoles(Collections.singletonList(new StudentData.Roles().withRoles("child")))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("familyAshot").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Collections.singletonList(new StudentData.Langs().withLangs("ru")))
            .withContacts(Collections.singletonList(new StudentData.Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new StudentData.Status().withState("noTrial"));
    studentService.create(student);
  }

  @Test
  public void testSshotFamilies() throws AWTException, IOException {
    String expected = "./src/test/testsScreenshot/expected/";
    String actual = "./src/test/testsScreenshot/actual/";
    String markedImages = "./src/test/testsScreenshot/markedImages/";
    String name = "families_RU_Chrome";
    String locatorFlag="//div[@class='header']";

    app.goTo().menuStudents();
    app.goTo().refresh();
    app.student().selectStudentInListUIById("studentAshot");
    app.family().btnFamily();

    ImageDiff diff = app.sshot().getImageDiff(expected, actual, markedImages, name,locatorFlag);

    Assert.assertEquals(diff.getDiffSize(), 0);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    FamilyService familyService = new FamilyService();
    FamilyData familyClean = familyService.findById("familyAshot");
    familyService.delete(familyClean);
    StudentService studentService = new StudentService();
    StudentData studentClean = studentService.findById("studentAshot");
    if (studentClean != null) {
      studentService.delete(studentClean);
    }

  }
}
