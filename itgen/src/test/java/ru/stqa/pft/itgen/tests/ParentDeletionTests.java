package ru.stqa.pft.itgen.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyDataUI;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.Parents;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParentDeletionTests extends TestBase {

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

  @Test(enabled = false)
  public void testParentDeletion() {
    app.goTo().tasks();
    app.goTo().students();
    Parents before = app.db().parents();
    String url = app.parent().delete();
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size() - 1));
    String idParent = app.parent().getId(url);
    ParentData deletedParent = before.iterator().next().withId(idParent);
    assertThat(after, equalTo(before.without(deletedParent)));
  }


}
