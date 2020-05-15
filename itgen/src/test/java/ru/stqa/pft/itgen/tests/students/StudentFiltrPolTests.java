package ru.stqa.pft.itgen.tests.students;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.StudentService;
import ru.stqa.pft.itgen.tests.TestBase;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StudentFiltrPolTests extends TestBase {
  int pol = 1;

  @BeforeMethod
  public void ensurePreconditions() {

    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("studentFiltr").withTrialBonusOff(false).withTierId("txa")
            .withTierHistory(Collections.singletonList(new FamilyData.TierHistory().withTierHistory("")));
    familyService.create(family);

    StudentService studentService = new StudentService();
    StudentData student1 = new StudentData().withId("studentFiltrPol1").withFirstName("Маша").withLastName("Машина")
            .withRoles(Collections.singletonList(new StudentData.Roles().withRoles("child")))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("studentFiltr").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Collections.singletonList(new StudentData.Langs().withLangs("ru")))
            .withContacts(Collections.singletonList(new StudentData.Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new StudentData.Status().withState("noTrial"));
    studentService.create(student1);
    StudentData student2 = new StudentData().withId("studentFiltrPol2").withFirstName("Маша").withLastName("Машина")
            .withRoles(Collections.singletonList(new StudentData.Roles().withRoles("child")))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(1)
            .withFamilyId("studentFiltr").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Collections.singletonList(new StudentData.Langs().withLangs("ru")))
            .withContacts(Collections.singletonList(new StudentData.Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new StudentData.Status().withState("noTrial"));
    studentService.create(student2);
  }

  @Test
  public void testStudentFiltrPol() {
    app.goTo().menuTasks();
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
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    FamilyService familyService = new FamilyService();
    FamilyData familyClean = familyService.findById("studentFiltr");
    familyService.delete(familyClean);
    StudentService studentService = new StudentService();
    StudentData studentClean1 = studentService.findById("studentFiltrPol1");
    studentService.delete(studentClean1);
    StudentData studentClean2 = studentService.findById("studentFiltrPol2");
    studentService.delete(studentClean2);

  }
}
