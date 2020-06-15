package io.itgen.tests.students;

import io.itgen.model.users.Contacts;
import io.itgen.model.users.Status;
import io.itgen.services.FamilyService;
import io.itgen.services.StudentService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.itgen.model.FamilyData;
import io.itgen.model.StudentData;
import io.itgen.model.Students;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StudentDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("studentDelete").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("studentDelete").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("studentDelete").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
    studentService.save(student);
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

    for (StudentData student : before) {
      if (student.getId().equals("studentDelete")) {
        assertThat(after, equalTo(before.without(student)));
        return;
      }
    }
    verifyStudentsListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("studentDelete");
    FamilyService familyService = new FamilyService();
    familyService.findByIdAndDelete("studentDelete");
  }
}
