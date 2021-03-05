package data.precondition;

import static app.appmanager.HelperBase.DateWithCorrectionDays;

import data.model.candidate.CandidateData;

public class Candidates extends TransactionManager {

  protected static final DataManager data = new DataManager();

  public void set1_newCandidate(String vacancy, String status) {
    trCandidate()
        .saveCandidate(
            "candidate",
            "Света",
            "Светина",
            "Sveta",
            "Svetina",
            DateWithCorrectionDays(-7300),
            1,
            status,
            vacancy,
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

  public void set2_newCandidateFromObject(String vacancy, String status, CandidateData candidate) {
    trCandidate()
        .saveCandidate(
            "candidate",
            candidate.getFirstName(),
            candidate.getLastName(),
            candidate.getEngFirstName(),
            candidate.getEngLastName(),
            candidate.getBirthday(),
            candidate.getGender(),
            status,
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
  }
}
