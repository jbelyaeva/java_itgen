package data.precondition.transactionHelper;

import data.model.users.ParentData;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Emails;
import data.services.ParentService;
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
