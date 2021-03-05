package tests.candidates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.candidate.Candidates;
import data.services.CandidateService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CandidateDeletionTest extends TestBase {

  CandidateService candidateService = new CandidateService();

  @BeforeMethod
  public void ensurePreconditions() {
  data.candidates().set1_newCandidate("trainer", "new");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCandidateDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuCandidates();
    Candidates before = app.dbcandidates().candidates();
    app.cantidate().delete("candidate");
    Candidates after = app.dbcandidates().candidates();
    assertThat(after.size(), equalTo(before.size() - 1));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    candidateService.drop();
  }
}
