package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
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

public class FamilyDeletionTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions (){
    if (app.db().families().size()==0) {
      app.goTo().gotoTasks();
      app.goTo().gotoStudents();
      app.students().createStudent(new StudentData().withFirstName("Маша").withLastName("Машина").withBirthdayUi("01.01.2009")
      .withPclevel("expert").withCountry("AL"));
    }
  }

  @Test
  public void testFamilyDeletion() {
    app.goTo().gotoTasks();
    app.goTo().gotoStudents();
    Families before = app.db().families();//для проверки, что семьи уменьшились на 1
    Students listBefore=app.db().students();//выбираем студента для удаления
    StudentData deletedStudent = listBefore.iterator().next();
    String url=app.families().deletionFamily(deletedStudent);//удаляем выбранного студента
    Families after = app.db().families();
    Assert.assertEquals(after.size(), before.size() - 1); //семьи уменьшились на 1
   // удалились все изеры с этой семьей
    String urlFamily = app.families().getIdFamily(url);
    Students users = app.db().family(urlFamily);
    Assert.assertEquals(users.size(), 0);
  }

}
