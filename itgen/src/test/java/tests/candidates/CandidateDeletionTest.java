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

    app.trCandidate()
        .saveCandidate(
            "CandidateDelete",
            "Света",
            "Светина",
            "Sveta",
            "Svetina",
            app.base().DateWithCorrectionDays(-7300),
            1,
            "new",
            "trainer",
            "AM",
            "City",
            "Europe/Monaco",
            "ru",
            "Хороший кандидат, плохой кандидат",
            "Очень много говорит",
            "14",
            "http://www.yandex.ru",
            "http://www.yandex.ru",
            "1111111111",
            "mail@mail.com",
            "+56756756756",
            "+79896667845",
            "111111111111111",
            "+5674545453",
            "+9998764534",
            "+00078566664",
            "+890000066432",
            "0000000000000",
            "010101010101",
            "cat",
            "key",
            null,
            "good",
            "mother");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCandidateDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuCandidates();
    Candidates before = app.dbcandidates().candidates();
    app.cantidate().delete("CandidateDelete");
    Candidates after = app.dbcandidates().candidates();
    assertThat(after.size(), equalTo(before.size() - 1));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    candidateService.drop();
  }
}
