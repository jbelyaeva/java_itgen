package io.itgen.appmanager.transactionHelper;

import io.itgen.model.WorkerData;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Emails;
import io.itgen.services.WorkerService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class TrWorkerHelper {

  private final WorkerService workerService = new WorkerService();

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
                Collections.singletonList(
                    new Emails().withAddress(email).withVerified(true)));
    workerService.save(worker);
  }


}
