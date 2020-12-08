package tests.students;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import data.model.family.FamilyData;
import data.model.users.StudentData;
import data.model.users.Students;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Status;
import data.services.FamilyService;
import data.services.StudentService;
import app.testbase.TestBase;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentFiltrPolTests extends TestBase {
  int pol = 1;

  @BeforeMethod
  public void ensurePreconditions() {

    FamilyService familyService = new FamilyService();
    FamilyData family =
        new FamilyData().withId("studentFiltr").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student1 =
        new StudentData()
            .withId("studentFiltrPol1")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert")
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withGender(2)
            .withFamilyId("studentFiltr")
            .withStudyLang("ru")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2)
            .withStatus(new Status().withState("noTrial"));
    studentService.save(student1);
    StudentData student2 =
        new StudentData()
            .withId("studentFiltrPol2")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert")
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withGender(1)
            .withFamilyId("studentFiltr")
            .withStudyLang("ru")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2)
            .withStatus(new Status().withState("noTrial"));
    studentService.save(student2);
  }

  @Test(enabled = false)
  public void testStudentFiltrPol() {
    app.goTo().menuStudents();
    app.student().openFiltr();
    app.student().changePol(pol);
    app.student().btnApply();
    Students list = app.dbstudents().studentFiltrPol(pol);
    List<StudentData> uiStudents = app.student().list();
    assertThat(
        new HashSet<Object>(uiStudents),
        equalTo(
            list.stream()
                .map(
                    (s) ->
                        new StudentData()
                            .withId(s.getId())
                            .withFirstName(s.getFirstname())
                            .withLastName(s.getLastname()))
                .collect(Collectors.toSet())));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    StudentService studentService = new StudentService();
    FamilyService familyService = new FamilyService();
    studentService.DeleteById("studentFiltrPol1");
    studentService.DeleteById("studentFiltrPol2");
    familyService.DeleteById("studentFiltr");
  }
}
