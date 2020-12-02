package tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.LocaleUtilsTestData;
import core.general.RunTestAgain;
import data.model.typeform.TestData;
import data.model.typeform.Tests;
import data.services.TestService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestTypeformCreate extends TestBase {

  private final TestService testService = new TestService();

  @Test(dataProvider = "validTestsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testTestTypeformCreate(TestData test) throws InterruptedException {
    Thread.sleep(5000);
    app.test().waitVisibleElement(5, By.xpath("//h2"));
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
