package tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import data.model.typeform.Tests;
import data.services.SkillsService;
import data.services.TestService;
import app.testbase.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformDeleteMarkInSkills extends TestBase {

  private final TestService testService = new TestService();
  private final SkillsService skillsService = new SkillsService();
  private final Date createAt = new Date();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[] {"1"};
    app.trTest()
        .saveTest(
            "deleteTest",
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
    testService.deleteField("deleteTest", "removedAt");

    skillsService.updateField(app.db().firstSkill().getId(), "testId", "deleteTest");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTypeformDeleteMarkInSkills() {
    app.goTo().urlTests();
    Tests before = app.dbtest().tests();
    app.test().deleteTestConnectionWithSkills();
    Tests after = app.dbtest().tests();
    assertThat(after.size(), equalTo(before.size()));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
    skillsService.deleteField(app.db().firstSkill().getId(), "testId");
  }
}
