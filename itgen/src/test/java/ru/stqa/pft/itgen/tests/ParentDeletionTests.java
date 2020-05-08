package ru.stqa.pft.itgen.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParentDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().families().size() == 0) {
      app.goTo().menuTasks();
      app.goTo().menuStudents();
      app.family().create(new FamilyDataUI().withFirstnameStudent("Маша").withLastnameStudent("Машина")
              .withBirthdayUiStudent("01.01.1987").withPclevelStudent("expert").withCountryStudent("AL")
              .withFirstnameParent("Олег").withLastnameParent("Машин").withCountryParent("AL").withPhoneParent("010101010101"));
    }
  }

  @Test
  public void testParentDeletion() {
    Parents before = null;
    String url = "";
    boolean a = true;

    app.goTo().menuTasks();
    app.goTo().menuStudents();
    //находим студента c родителем, если такого нет, то создаем студента без родителя
    Students students = app.db().students();
    for (StudentData student : students) { //проходим по всем студентам
      String idFamily = student.getFamilyId(); // у всех по порядку берем FamilyID
      if (app.db().familyComposition(idFamily).size() == 2) { //если в семье 2 человека
        app.goTo().menuStudents(); // переходим в студенты
        app.student().selectStudentInStudentListUI(student); //выбираем этого студента в списке
        before = app.db().parents(); // запоминаем список родителей "до"
        app.student().btnFamily();
        url = app.parent().delete(); //удаляем родителя
        a = false;
        break;
      }
    }
    if (a) {  //если у всех студентов нет родителя, то добавляем родителя к первому студенту
      app.parent().create(new ParentData().withFirstName("Папа").withLastName("Папа").withPhone("000000000"));
      before = app.db().parents(); // берем список родителй "до"
      app.student().btnFamily();
      url = app.parent().delete(); //удаляем, и запоминаем URL удаленного родителя
    }
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size() - 1));

    String idParent = app.parent().getId(url); //id удаленного родителя
    for (ParentData parent : before) { //найти в списке "до" родителя с таким id
      if (parent.getId().equals(idParent)) {
        assertThat(after, equalTo(before.without(parent))); //список "после" и "до" без этого родителя
        return;
      }
    }
  }


}
