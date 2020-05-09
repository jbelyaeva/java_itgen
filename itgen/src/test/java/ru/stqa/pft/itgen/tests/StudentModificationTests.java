package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.StudentService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class StudentModificationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validStudentsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/students_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students = gson.fromJson(json, new TypeToken<List<StudentData>>() {
      }.getType()); // List<StudentData>.class
      return students.stream().map((s) -> new Object[]{s}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().students().size() == 0) {
      FamilyService familyService = new FamilyService();
      FamilyData family = new FamilyData().withId("222222").withTrialBonusOff(false).withTierId("txa")
              .withTierHistory(Collections.singletonList(new FamilyData.TierHistory().withTierHistory("")));
      familyService.create(family);

      StudentService studentService = new StudentService();
      StudentData student = new StudentData().withId("1111111").withFirstName("Маша").withLastName("Машина")
              .withRoles(Collections.singletonList(new StudentData.Roles().withRoles("child")))
              .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
              .withFamilyId("222222").withStudyLang("ru").withLocate("ru")
              .withBirthday(new Date(1977-10-12)) // придумать конвертор DATE в ISODATE
              .withLangs(Collections.singletonList(new StudentData.Langs().withLangs("ru")))
              .withContacts(Collections.singletonList(new StudentData.Contacts().withType("phone").withVal("1234567899")))
              .withDuration(2).withStatus(new StudentData.Status().withState("noTrial"));
      studentService.create(student);
    }
  }

  @Test(dataProvider = "validStudentsFromJson")
  public void testStudentModification(StudentData student) {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Students before = app.db().students();
    StudentData modifyStudent = before.iterator().next();
    app.student().selectStudentInStudentListUI(modifyStudent);
    app.student().modify(student);
    Students after = app.db().students();
    assertThat(after.size(), equalTo(before.size()));
    StudentData studentAdd = student.withId(modifyStudent.getId());
    assertThat(after, equalTo(before.without(modifyStudent).withAdded(studentAdd)));
    verifyStudentsListInUI();
  }
}