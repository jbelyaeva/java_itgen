package ru.stqa.pft.itgen.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.Families;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FamilyDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().families().size() == 0) {
      app.goTo().menuTasks();
      app.goTo().menuStudents();
      app.student().create(new StudentData().withFirstName("Маша").withLastName("Машина")
              .withBirthdayUi("01.01.1999").withPclevel("expert").withCountry("AL"));
    }
  }

  @Test
  public void testFamilyDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Families before = app.db().families();//для проверки, что семьи уменьшились на 1
    Students listBefore = app.db().students();//выбираем студента для удаления
    StudentData deletedStudent = listBefore.iterator().next();
    String url = app.family().delete(deletedStudent);//удаляем выбранного студента
    Families after = app.db().families();
    assertThat(after.size(), equalTo(before.size() - 1)); //семьи уменьшились на 1
    String idFamily = app.family().getId(url);
    Students users = app.db().familyComposition(idFamily);
    assertThat(users.size(), equalTo(0));
  }


  @AfterMethod(alwaysRun = true)
  public void cleanFamily() {
    if (app.db().students().size() > 0) {
      while (app.db().students().size() != 0) {
        app.goTo().menuStudents();
        app.student().select();
        app.student().btnFamily();
        app.family().bntDeleteFamily();
        app.family().alertDeleteFamily();
      }
    }
  }
}
