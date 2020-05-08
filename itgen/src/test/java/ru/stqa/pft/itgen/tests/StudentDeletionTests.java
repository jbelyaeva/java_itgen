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
      app.goTo().menuTasks();
      app.goTo().menuStudents();
      app.student().create(new StudentData().withFirstName("Маша").withLastName("Машина")
              .withBirthdayUi("01.01.1999").withPclevel("expert").withCountry("AL"));
    }
  }

  @Test
  public void testStudentDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    app.student().addStudentInFamily(); //добавляем еще одного студента, чтоб при удалении одного из студентов одной семьи не появлялись фантомные записи в таблице family
    Students before = app.db().students();
    String url = app.student().delete();
    Students after = app.db().students();
    String idDeletedStudent = app.student().getId(url);
    for (StudentData studentDeleted : before) { //найти в списке "до" родителя с таким id
      if (studentDeleted.getId().equals(idDeletedStudent)) {
        assertThat(after, equalTo(before.without(studentDeleted))); //список "после" и "до"без этого родителя
        return;
      }
    }
    verifyStudentsListInUI();
  }
}
