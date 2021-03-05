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
import org.testng.annotations.Test;

public class CandidateQACreationTest extends TestBase {

  CandidateService candidateService = new CandidateService();
  CandidateData candidateClean = null;

  @Test(dataProvider = "validCandidatesFromJson",
      dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testCandidateQACreation(CandidateData candidate) {
    app.goTo().menuCandidates();
    Candidates before = app.dbcandidates().candidates();
    app.cantidate().create(candidate, "qa");
    Candidates after = app.dbcandidates().trySeveralTimeGetNewDateFromDB(before);
    candidateClean = app.dbcandidates().lastCandidate();
    assertThat(after.size(), equalTo(before.size() + 1));
    check(after, candidateClean.getId(), candidate, candidateClean.getVacancyId());
  }

  private void check(Candidates after, String id, CandidateData candidate, String vacancy) {
    data.candidates().set2_newCandidateFromObject(vacancy, "new", candidate);

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
