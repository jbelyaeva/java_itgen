package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.StudentData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ParentModificationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validParentsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/parents_modification.json")))) {
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
  public void testParentModification(ParentData parent) {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().selectedStudent();
    app.getStudentHelper().selectedFamily();
    app.getStudentHelper().selectedParent();
    app.getStudentHelper().modifyParent();
    app.getStudentHelper().ModifyParentForm(parent);
    app.getStudentHelper().submitParentModify();
  }
}
