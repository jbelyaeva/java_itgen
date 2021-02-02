package app.appmanager.transactionHelper;

import data.model.users.TrainerData;
import data.model.users.WorkerData;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Emails;
import data.services.TrainerService;
import data.services.WorkerService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class TrWorkerHelper {

  private final WorkerService workerService = new WorkerService();
  private final TrainerService trainerService = new TrainerService();

  public void saveNewWorker(
      String idWorker,
      String firstName,
      String lastName,
      String role,
      String country,
      String tz,
      String locate,
      String lang,
      String phone,
      String email) {
    WorkerData worker =
        new WorkerData()
            .withId(idWorker)
            .withFirstName(firstName)
            .withLastName(lastName)
            .withRoles(Arrays.asList(role))
            .withCountry(country)
            .withTimeZone(tz)
            .withLocate(locate)
            .withGender(2)
            .withStartDay(new Date())
            .withCreatedAt(new Date())
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList(lang))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal(phone)))
            .withEmails(
                Collections.singletonList(new Emails().withAddress(email).withVerified(true)));
    workerService.save(worker);
  }

  public void saveNewTrainer(
      String idTraier,
      String firstName,
      String lastName,
      String role,
      String country,
      String tz,
      String locate,
      String lang,
      String phone,
      String email,
      String skill
  ) {
    TrainerData trainer =
        new TrainerData()
            .withId(idTraier)
            .withFirstName(firstName)
            .withLastName(lastName)
            .withRoles(Arrays.asList(role))
            .withCountry(country)
            .withTimeZone(tz)
            .withLocate(locate)
            .withGender(2)
            .withStartWorkAt(new Date())
            .withCreatedAt(new Date())
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList(lang))
            .withSkills(Arrays.asList(skill))
            .withMaxSlots(6)
            .withPayBase(2.5)
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal(phone)))
            .withEmails(
                Collections.singletonList(new Emails().withAddress(email).withVerified(true)));
    trainerService.save(trainer);
  }
}
