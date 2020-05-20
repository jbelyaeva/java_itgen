package ru.stqa.pft.itgen.tests.screenSort;
/* Скриншот страницы с учениками. База изначально должна быть пустая. Тест создает ученика, делает снимок,
   сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка запускаем конфигурацию запуска
   со свойством -Detalon=true.
 */

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.StudentService;
import ru.stqa.pft.itgen.tests.TestBase;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import java.awt.*;
import java.io.IOException;
import java.util.*;

import static ru.stqa.pft.itgen.appmanager.ApplicationManager.propertiesAshot;

public class SshotStudents extends TestBase  {

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
    String name = "students_RU_Chrome";
    String locatorIgnor="";
    app.goTo().menuTasks();
    app.goTo().menuStudents();

    ImageDiff diff = app.sshot().getImageDiff(propertiesAshot.getProperty("expected")
                                            , propertiesAshot.getProperty("actual")
                                            , propertiesAshot.getProperty("markedImages")
                                            , name,locatorIgnor);
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
