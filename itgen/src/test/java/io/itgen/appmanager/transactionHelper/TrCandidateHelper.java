package io.itgen.appmanager.transactionHelper;

import io.itgen.model.CandidateData;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Utm;
import io.itgen.services.CandidateService;
import java.util.Arrays;
import java.util.Date;

public class TrCandidateHelper {

  private final CandidateService candidateService = new CandidateService();

  public void saveCandidate(
      String id,
      String firstName, String lastName, String engFirstName, String engLastName,
      Date birthday,
      int gender,
      String status,
      String vacancy,
      String country, String city, String timezone, String locale,
      String note,
      String info,
      String referId,
      String resume,
      String testTask,
      String phone, String email, String telegram, String viber, String c2d, String skype,
      String whatsapp, String facebook, String vk, String ok, String instagram,
      String source,
      String medium,
      String campaing,
      String term,
      String content) {
    CandidateData candidate = new CandidateData()
        .withId(id)
        .withFirstname(firstName)
        .withLastname(lastName)
        .withEngFirstname(engFirstName)
        .withEngLastname(engLastName)
        .withStatus(status)
        .withBirthday(birthday)
        .withGender(gender)
        .withVacancyId(vacancy)
        .withCountry(country)
        .withCity(city)
        .withTimezone(timezone)
        .withLocale(locale)
        .withNote(note)
        .withInfo(info)
        .withReferId(referId)
        .withResume(resume)
        .withTestTask(testTask)
        .withContacts(
            Arrays.asList(new Contacts().withType("phone").withVal(phone).withPreferred(false),
                new Contacts().withType("email").withVal(email).withPreferred(false),
                new Contacts().withType("telegram").withVal(telegram).withPreferred(false),
                new Contacts().withType("viber").withVal(viber).withPreferred(false),
                new Contacts().withType("c2d").withVal(c2d).withPreferred(false),
                new Contacts().withType("skype").withVal(skype).withPreferred(false),
                new Contacts().withType("whatsapp").withVal(whatsapp).withPreferred(false),
                new Contacts().withType("facebook").withVal(facebook).withPreferred(false),
                new Contacts().withType("vk").withVal(vk).withPreferred(false),
                new Contacts().withType("ok").withVal(ok).withPreferred(false),
                new Contacts().withType("instagram").withVal(instagram).withPreferred(false)))
        .withUtm(new Utm().withSource(source)
            .withMedium(medium)
            .withCampaing(campaing)
            .withTerm(term)
            .withContent(content));
    candidateService.save(candidate);
  }

}