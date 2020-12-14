package tests.students;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.family.FamilyData;
import data.model.users.StudentData;
import data.model.users.Students;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Status;
import data.provides.LocaleUtilsTestData;
import data.services.FamilyService;
import data.services.StudentService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    FamilyService familyService = new FamilyService();
    FamilyData family =
        new FamilyData().withId("studentModify").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student =
        new StudentData()
            .withId("studentModify")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert")
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withGender(2)
            .withFamilyId("studentModify")
            .withStudyLang("ru")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2)
            .withStatus(new Status().withState("noTrial"));
    studentService.save(student);
  }

  @Test(dataProvider = "validModifyStudentsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testStudentModification(StudentData student) {
    app.goTo().menuStudents();
    Students before = app.dbstudents().students();
    app.student().selectStudentInListUIById("studentModify");
    app.student().modify(student);
    Students after = app.dbstudents().students();
    assertThat(after.size(), equalTo(before.size()));

    for (StudentData studentModify : before) { // найти в списке "до" родителя с таким id
      if (studentModify.getId().equals("studentModify")) {
        StudentData studentAdd = student.withId(studentModify.getId());
        assertThat(after, equalTo(before.without(studentModify).withAdded(studentAdd)));
        return;
      }
    }

    verifyStudentsListInUI();
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    StudentService studentService = new StudentService();
    studentService.DeleteById("studentModify");
    FamilyService familyService = new FamilyService();
    familyService.DeleteById("studentModify");
  }
}
