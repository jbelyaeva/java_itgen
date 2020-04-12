package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.*;
import ru.stqa.pft.itgen.model.ParentData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ParentCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validParentsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/parents_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ParentData> parents = gson.fromJson(json, new TypeToken<List<ParentData>>(){}.getType()); // List<ParentData>.class
      return parents.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validParentsFromJson")
  public void testParentCreation(ParentData parent) {
    app.getNavigationHelper().gotoTasks();
    app.getNavigationHelper().gotoStudents();
    app.students().selectedStudent();
    app.students().selectedFamily();
    app.students().addParentInFamily();
    app.students().fillParentForm(parent);
    app.students().submitParentCreation();
  }
}