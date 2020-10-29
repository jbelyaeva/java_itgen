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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestTypeformAddNewEnglishTest extends TestBase {

  private final TestService testService = new TestService();
  private final Date createAt = new Date();
  TestData testClean = null;
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[] {"1"};
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
            null);
  }

  @DataProvider
  public Iterator<Object[]> validAddTestFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/tests_whichAdd.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<TestData> tests = gson.fromJson(json, new TypeToken<List<TestData>>() {}.getType());
      return tests.stream().map((p) -> new Object[] {p}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validAddTestFromJson", retryAnalyzer = RunTestAgain.class)
  public void testTypeformAddNewEnglishTest(TestData test) {
    app.goTo().urlTests();
    Tests before = app.dbtest().tests();
    app.test().addEnglishTest(test);
    Tests after = app.dbtest().trySeveralTimeGetNewDateFromDB(before);
    testClean = app.dbtest().lastTest();
    assertThat(after.size(), equalTo(before.size() + 1));
    check(after, test);
  }

  private void check(Tests after, TestData test) {
    app.trTest()
        .saveTest(
            testClean.getId(),
            test.getTitle(),
            test.getRootFormId(),
            "en",
            test.getDescription(),
            5,
            5,
            10,
            skills,
            createAt,
            null);

    TestData testAdd = testService.findById(testClean.getId());

    for (TestData testAfter : after) {
      if (testAfter.getId().equals(testClean.getId())) {
        assertThat(after, equalTo(after.without(testAfter).withAdded(testAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
  }
}
