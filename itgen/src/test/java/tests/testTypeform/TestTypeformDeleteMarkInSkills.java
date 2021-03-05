package tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.typeform.Tests;
import data.services.SkillsService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformDeleteMarkInSkills extends TestBase {
  private final SkillsService skillsService = new SkillsService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.tests().set1_1_NewTestForRusScratch();
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
    data.clean().tests();
    skillsService.deleteField(app.db().firstSkill().getId(), "testId");
  }
}
