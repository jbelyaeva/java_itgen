package io.itgen.appmanager.tranzactionHelper;

import io.itgen.model.LeadData;
import io.itgen.model.StudentData;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.FinishedLessonsCountBySkill;
import io.itgen.model.users.Status;
import io.itgen.model.users.Utm;
import io.itgen.services.LeadService;
import io.itgen.services.StudentService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class TrLeadHelper {

  private final LeadService leadService = new LeadService();

  public void leadParent(
      String idLead,
      String name,
      String surname,
      String role,
      String country,
      String tz,
      String locale,
      String phone,
      String email,
      String status,
      String source,
      String medium) {
    LeadData lead =
        new LeadData()
            .withId(idLead)
            .withFirstName(name)
            .withLastName(surname)
            .withRoles(Arrays.asList(role))
            .withCountry(country)
            .withTimeZone(tz)
            .withLocate(locale)
            .withContacts(
                Arrays.asList(
                    new Contacts().withType("phone").withVal(phone),
                    new Contacts().withType("email").withVal(email)))
            .withStatus(status)
            .withUtm(new Utm().withSource(source).withMedium(medium));
    leadService.save(lead);
  }
}
