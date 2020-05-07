package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParentModificationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validParentsFromJson() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/testdata/parents_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ParentData> parents = gson.fromJson(json, new TypeToken<List<ParentData>>() {
      }.getType()); // List<ParentData>.class
      return parents.stream().map((p) -> new Object[]{p}).collect(Collectors.toList()).iterator();
    }
  }

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

  @Test(dataProvider = "validParentsFromJson")
  public void testParentModification(ParentData parent) {
    Parents before = null;
    String url = "";
    boolean a = true;

    app.goTo().tasks();
    app.goTo().students();
     //находим студента c родителем, если такого нет, то создаем студенту без родителя родителя
    Students students = app.db().students();
    for (StudentData student : students) { //проходим по всем студентам
      String idFamily = student.getFamilyId();// у всех по порядку берем FamilyID
      if (app.db().familyСomposition(idFamily).size() == 2) { //если в семье 2 человека
        app.goTo().students();// переходим в студенты
        app.student().selectStudentInStudentListUI(student);//выбираем этого студента в списке
        before = app.db().parents();// запоминаем список родителей До
        app.student().bntFamily();
        url = app.parent().modify(parent);//модифицируем родителя
        a = false;
        break;
      }
    }
    if (a) {  //если у всех студентов нет родителя, то добавляем родителя к первому студенту
      url = app.parent().createWithUrl(new ParentData().withFirstName("Папа").withLastName("Папа").withPhone("000000000"));
      before = app.db().parents();// берем список родителй ДО
      app.parent().modifyNewParent(parent);//модифицируем
    }
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size()));
    String idParent = app.parent().getId(url); //id модифиц родителя
    for (ParentData parentModify : before) { //найти в списке ДО родителя с таким id
      if (parentModify.getId().equals(idParent)) {
        ParentData parentAdd = parent.withId(parentModify.getId());
        assertThat(after, equalTo(before.without(parentModify).withAdded(parentAdd)));
        return;
      }
    }


  }


}
