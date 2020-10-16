package io.itgen.tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.general.RunTestAgain;
import io.itgen.model.typeform.TestData;
import io.itgen.model.typeform.Tests;
import io.itgen.services.TestService;
import io.itgen.tests.TestBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestTypeformCreate extends TestBase {

  private final TestService testService = new TestService();


  @DataProvider
  public Iterator<Object[]> validTestsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/tests_creation.json")))) {
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

  @Test(dataProvider = "validTestsFromJson", retryAnalyzer = RunTestAgain.class)
  public void testTestTypeformCreate(TestData test) throws InterruptedException {
    Thread.sleep(5000);
    app.test().waiteVisibleElement(5, By.xpath("//h2"));
    app.goTo().urlTests();
    Tests before = app.dbtest().tests();
    app.test().createTest(test);
    Tests after = app.dbtest().tests();
    assertThat(after.size(), equalTo(before.size() + 1));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
  }
}
