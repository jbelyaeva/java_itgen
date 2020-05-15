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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StudentDeletionTests extends TestBase {

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
  public void testStudentDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Students before = app.dbstudents().students();
    app.student().selectStudentInListUIById("studentDelete");
    app.student().delete();
    Students after = app.dbstudents().students();
    assertThat(after.size(), equalTo(before.size() - 1));

    for (StudentData student : before) { //найти в списке "до" родителя с таким id
      if (student.getId().equals("studentDelete")) {
        assertThat(after, equalTo(before.without(student)));
        return;
      }
    }
    verifyStudentsListInUI();
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
