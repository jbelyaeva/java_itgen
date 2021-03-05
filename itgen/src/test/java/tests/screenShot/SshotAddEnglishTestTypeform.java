package tests.screenShot;

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.model.typeform.TestData;
import data.services.TestService;
import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotAddEnglishTestTypeform extends TestBase {

  private final TestService testService = new TestService();
  private final Date createAt = new Date();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set7_TestInProcess();
   /* skills = new String[]{"1"};
    app.trTest()
        .saveTest(
            "addEnglishTest",
            "Тест",
            "111111",
            "ru",
            "Test на переход на новое направление",
            5,
            5,
            10,
            skills,
            createAt,
            null);*/
  }

  @DataProvider
  public Iterator<Object[]> validAddTestFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader("src/test/resources/testdata/tests_whichAdd.json"))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<TestData> tests = gson.fromJson(json, new TypeToken<List<TestData>>() {
      }.getType());
      return tests.stream().map((p) -> new Object[]{p}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validAddTestFromJson")
  public void testSshotAddEnglishTestTypeform(TestData test)
      throws AWTException, IOException, InterruptedException {
    app.goTo().menuTests();
    app.sshot().changeTopBar();
    app.test().addEnglishTest(test);
    String name = "Admin_TypeformAddEnglishTest_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    Thread.sleep(3000);
    app.base().deleteAlerts();
    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                properties.getProperty("expected"),
                properties.getProperty("actual"),
                properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.goTo().menuSchedule();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
  }
}
