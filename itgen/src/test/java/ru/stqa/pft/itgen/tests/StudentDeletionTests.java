package ru.stqa.pft.itgen.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StudentDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().students().size() == 0) {
      app.goTo().tasks();
      app.goTo().students();
      app.students().createStudent(new StudentData().withFirstName("Маша").withLastName("Машина")
              .withBirthdayUi("01.01.1999").withPclevel("expert").withCountry("AL"));
    }
  }

  @Test
  public void testStudentDeletion() {
    app.goTo().tasks();
    app.goTo().students();
    Students before = app.db().students();
    StudentData deletedStudent = before.iterator().next(); //выбираем студента для удаления
    app.students().deletetionStudent(deletedStudent);//удаляем выбранного студента
    Students after = app.db().students();
    assertThat(after, equalTo(before.without(deletedStudent)));
    verifyStudentsListInUI();
  }
}
