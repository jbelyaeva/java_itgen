package tests.candidates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.LocaleUtilsTestData;
import core.general.RunTestAgain;
import data.model.candidate.CandidateData;
import data.model.candidate.Candidates;
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
    app.trCandidate()
        .saveCandidate(
            id,
            candidate.getFirstName(),
            candidate.getLastName(),
            candidate.getEngFirstName(),
            candidate.getEngLastName(),
            candidate.getBirthday(),
            candidate.getGender(),
            "new",
            vacancy,
            candidate.getCountry(),
            candidate.getCity(),
            candidate.getTimezone(),
            candidate.getLocale(),
            candidate.getNote(),
            candidate.getInfo(),
            candidate.getReferId(),
            candidate.getResume(),
            candidate.getTestTask(),
            candidate.getContacts().get(0).getVal(),
            candidate.getContacts().get(1).getVal(),
            candidate.getContacts().get(2).getVal(),
            candidate.getContacts().get(3).getVal(),
            candidate.getContacts().get(4).getVal(),
            candidate.getContacts().get(5).getVal(),
            candidate.getContacts().get(6).getVal(),
            candidate.getContacts().get(7).getVal(),
            candidate.getContacts().get(8).getVal(),
            candidate.getContacts().get(9).getVal(),
            candidate.getContacts().get(10).getVal(),
            candidate.getUtm().getSource(),
            candidate.getUtm().getMedium(),
            candidate.getUtm().getCampaing(),
            candidate.getUtm().getTerm(),
            candidate.getUtm().getContent());

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