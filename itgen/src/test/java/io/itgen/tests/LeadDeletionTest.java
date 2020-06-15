package io.itgen.tests;

import io.itgen.model.LeadData;
import io.itgen.model.Leads;
import io.itgen.services.LeadService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.itgen.model.users.Contacts;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeadDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    LeadService leadService = new LeadService();
    LeadData lead = new LeadData().withId("forLeadDeletion").withFirstName("Лид").withLastName("Лидов")
            .withRoles(Collections.singletonList("child"))
            .withCountry("AL").withTimeZone("Europe/Minsk").withLocate("ru")
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withStatus("new");
    leadService.create(lead);
  }

  @Test
  public void testLeadDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuLeads();
    Leads before = app.db().leads();
    app.lead().selectLeadInListUIById("forLeadDeletion");
    app.lead().delete();
    Leads after = app.db().leads();
    assertThat(after.size(), equalTo(before.size() - 1));

    for (LeadData lead : before) { //найти в списке "до" родителя с таким id
      if (lead.getId().equals("forLeadDeletion")) {
        Leads before1 = before.without(lead);
        assertThat(after, equalTo(before1));
        return;
      }
    }
    verifyLeadsListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    LeadService leadService = new LeadService();
    leadService.findByIdAndDelete("forLeadDeletion");
  }

}
