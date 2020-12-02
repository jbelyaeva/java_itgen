package tests.family;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.LocaleUtilsTestData;
import core.general.RunTestAgain;
import data.model.family.Families;
import data.model.family.FamilyDataUI;
import data.model.users.StudentData;
import data.model.users.Students;
import data.services.FamilyService;
import data.services.StudentService;
import data.services.TaskService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class FamilyCreationTests extends TestBase {

  String idFamily;

  @Test(dataProvider = "validFamiliesFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testFamilyCreation(FamilyDataUI family) {
    app.base().waitVisibleElement(5, By.xpath("//h2"));
    app.goTo().urlStudents();
    Families before = app.db().families();
    app.family().create(family);
    Families after = app.db().families();
    assertThat(after.size(), equalTo(before.size() + 1));
    String url = app.family().getURL();
    idFamily = app.family().getId(url);
    Students users = app.db().familyComposition(idFamily);
    assertThat(users.size(), equalTo(2));
  }

  @AfterMethod(alwaysRun = true)
  public void cleanFamily() {
    FamilyService familyService = new FamilyService();
    familyService.DeleteById(idFamily);
    Students students =
        app.db()
            .familyComposition(
                idFamily); // в данном случае в список Students попадут ученики и родители
    StudentService studentService = new StudentService();
    TaskService taskService = new TaskService();
    for (StudentData studentClean : students) {
      studentService.DeleteById(studentClean);
      taskService.DeleteById(studentClean);
    }
  }
}
