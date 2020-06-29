package io.itgen.tests.screenShotPar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.appmanager.ApplicationManager;
import io.itgen.model.StudentData;
import io.itgen.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SshotAddNewStudentFormFirst extends TestBase {
  @DataProvider
  public Iterator<Object[]> StudentsFromJson() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/testdata/sshot_creation_student_par_form1.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      java.util.List<StudentData> students = gson.fromJson(json, new TypeToken<List<StudentData>>() {
      }.getType()); // List<StudentData>.class
      return students.stream().map((s) -> new Object[]{s}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "StudentsFromJson")
  public void testAddNewStudentFormFirst(StudentData student) throws AWTException, IOException {
    app.lkParent().createSShotFirstForm(student);

    String name = "Parent_AddNewStudentForm1_RU_Chrome";
    String[] locatorIgnor = {
            "//div[contains(@id,'MeteorToys')]"
    };

    ImageDiff diff = app.sshot().getImageDiff(ApplicationManager.properties.getProperty("expected")
            , ApplicationManager.properties.getProperty("actual")
            , ApplicationManager.properties.getProperty("markedImages")
            , name, locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);
    app.lkParent().btnLogo();
  }


}
