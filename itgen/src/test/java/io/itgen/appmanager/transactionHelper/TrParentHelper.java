package io.itgen.appmanager.transactionHelper;

import io.itgen.model.ParentData;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Emails;
import io.itgen.services.ParentService;
import java.util.Arrays;
import java.util.Collections;

public class TrParentHelper {

  private final ParentService parentService = new ParentService();

  public void newParent(
      String idParent,
      String name,
      String surname,
      String country,
      String tz,
      String locate,
      String idFamily,
      String phone,
      String email,
      Boolean verified) {

    ParentData parent =
        new ParentData()
            .withId(idParent)
            .withFirstName(name)
            .withLastName(surname)
            .withRoles(Arrays.asList("parent"))
            .withCountry(country)
            .withTimeZone(tz)
            .withFamilyId(idFamily)
            .withLocate(locate)
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal(phone)))
            .withEmails(
                Collections.singletonList(new Emails().withAddress(email).withVerified(verified)));

    parentService.save(parent);
  }
}
