package tests.screenShotPar;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.model.users.StudentData;
import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotAddNewStudentFormFirst extends TestBase {

  @DataProvider
  public Iterator<Object[]> StudentsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(
                new File("src/test/resources/testdata/sshot_creation_student_par_form1.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      java.util.List<StudentData> students =
          gson.fromJson(json, new TypeToken<List<StudentData>>() {}.getType());
      return students.stream().map((s) -> new Object[] {s}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "StudentsFromJson")
  public void testAddNewStudentFormFirst(StudentData student) throws AWTException, IOException {
    app.lkParentRecord().createSShotFirstForm(student);

    String name = "Parent_AddNewStudentForm1_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);

    app.lkParent().btnLogo();
    if (diff.getDiffSize() > 500) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }
}
