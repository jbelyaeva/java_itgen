package tests.candidates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.candidate.CandidateData;
import data.model.candidate.Candidates;
import data.provides.LocaleUtilsTestData;
import data.services.CandidateService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CandidateModificationTest extends TestBase {

  CandidateService candidateService = new CandidateService();
  CandidateData candidateClean = null;

  @BeforeMethod
  public void ensurePreconditions() {
    data.candidates().set1_newCandidate("trainer", "new");
  }

  @Test(dataProvider = "validModifyCandidatesFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testCandidateModification(CandidateData candidate) throws InterruptedException {
    app.goTo().menuTasks();
    app.goTo().menuCandidates();
    Candidates before = app.dbcandidates().candidates();
    app.cantidate().modify("candidate", candidate);
    Thread.sleep(500); // необходимо, чтоб кандидат прописался в бд
    Candidates after = app.dbcandidates().candidates();
    candidateClean = app.dbcandidates().lastCandidate();
    assertThat(after.size(), equalTo(before.size()));
    check(after, candidateClean.getId(), candidate, candidateClean.getVacancyId());
    app.goTo().menuCandidates();
  }

  private void check(Candidates after, String id, CandidateData candidate, String vacancy) {
    data.candidates().set2_newCandidateFromObject("trainer", "new", candidate);

    CandidateData candidateAdd = candidateService.findById(candidateClean.getId());

    for (CandidateData candidateAfter : after) {
      if (candidateAfter.getId().equals(id)) {
        assertThat(after, equalTo(after.without(candidateAfter).withAdded(candidateAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    candidateService.drop();
  }
}
