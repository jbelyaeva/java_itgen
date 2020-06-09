package ru.stqa.pft.itgen.tests.students;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;
import ru.stqa.pft.itgen.model.users.Contacts;
import ru.stqa.pft.itgen.model.users.Status;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.StudentService;
import ru.stqa.pft.itgen.tests.TestBase;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StudentFiltrPolTests extends TestBase {
  int pol = 1;

  @BeforeMethod
  public void ensurePreconditions() {

    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("studentFiltr").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student1 = new StudentData().withId("studentFiltrPol1").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("studentFiltr").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
    studentService.save(student1);
    StudentData student2 = new StudentData().withId("studentFiltrPol2").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(1)
            .withFamilyId("studentFiltr").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
    studentService.save(student2);
  }

  @Test
  public void testStudentFiltrPol() {
    app.goTo().menuStudents();
    app.student().openFiltr();
    app.student().changePol(pol);
    app.student().btnApply();
    Students list = app.dbstudents().studentFiltrPol(pol);
    List<StudentData> uiStudents = app.student().list();
    assertThat(new HashSet<Object>(uiStudents), equalTo(list
            .stream().map((s) -> new StudentData()
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
    studentService.findByIdAndDelete("studentFiltrPol1");
    studentService.findByIdAndDelete("studentFiltrPol2");
    familyService.findByIdAndDelete("studentFiltr");
  }
}
