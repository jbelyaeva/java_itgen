package ru.stqa.pft.itgen.tests;

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
      app.goTo().tasks();
      app.goTo().students();
      app.students().createStudent(new StudentData().withFirstName("Маша").withLastName("Машина")
              .withBirthdayUi("01.01.1999").withPclevel("expert").withCountry("AL"));
    }
  }

  @Test
  public void testFamilyDeletion() {
    app.goTo().tasks();
    app.goTo().students();
    Families before = app.db().families();//для проверки, что семьи уменьшились на 1
    Students listBefore = app.db().students();//выбираем студента для удаления
    StudentData deletedStudent = listBefore.iterator().next();
    String url = app.families().deletionFamily(deletedStudent);//удаляем выбранного студента
    Families after = app.db().families();
    assertThat(after.size(), equalTo(before.size() - 1)); //семьи уменьшились на 1
    // удалились все изеры с этой семьей
    String urlFamily = app.families().getIdFamily(url);
    Students users = app.db().family(urlFamily);
    assertThat(users.size(), equalTo(0));
  }

}
