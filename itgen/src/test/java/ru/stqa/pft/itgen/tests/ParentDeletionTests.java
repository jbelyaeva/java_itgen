package ru.stqa.pft.itgen.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParentDeletionTests extends TestBase {

  private String url;


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().families().size() == 0) {
      app.goTo().tasks();
      app.goTo().students();
      app.family().create(new FamilyDataUI().withFirstnameStudent("Маша").withLastnameStudent("Машина")
              .withBirthdayUiStudent("01.01.1987").withPclevelStudent("expert").withCountryStudent("AL")
              .withFirstnameParent("Олег").withLastnameParent("Машин").withCountryParent("AL").withPhoneParent("010101010101"));
    }
  }

  @Test
  public void testParentDeletion() {
    app.goTo().tasks();
    app.goTo().students();
    Parents before = null;
    Parents after = null;
    String url = "";
    //находим студента c родителем, если такого нет, то создаем студенту без родителя родителя
    Students students = app.db().students();
    int a = 1;
    for (StudentData student : students) {
      String id = student.getFamilyId();
      if (app.db().familyСomposition(id).size() == 2) {
        before = app.db().parents();
        url = app.parent().delete();
        break;
      } else {
        a = a + 1;
      }
    }
    if (a > 0) {
      app.parent().create(new ParentData().withFirstName("Папа").withLastName("Папа").withPhone("000000000"));
      before = app.db().parents();
      url = app.parent().delete();
    }
    after = app.db().parents();


    assertThat(after.size(), equalTo(before.size() - 1));
    String idParent = app.parent().getId(url);
    ParentData deletedParent = before.iterator().next().withId(idParent);
    assertThat(after, equalTo(before.without(deletedParent)));
  }


}
