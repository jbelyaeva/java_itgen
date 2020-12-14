package tests.leads;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.lead.LeadData;
import data.model.lead.Leads;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Utm;
import data.provides.LocaleUtilsTestData;
import data.services.LeadService;
import java.util.Arrays;
import java.util.Collections;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LeadModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    LeadService leadService = new LeadService();
    LeadData lead =
        new LeadData()
            .withId("forLeadModify")
            .withFirstName("Лид")
            .withLastName("Лидов")
            .withRoles(Arrays.asList("child"))
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withLocate("ru")
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withStatus("new")
            .withUtm(new Utm().withSource("site").withMedium("manual"));
    leadService.create(lead);
  }

  @Test(dataProvider = "validModifyLeadsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testLeadModification(LeadData lead) {
    app.goTo().menuTasks();
    app.goTo().menuLeads();
    Leads before = app.db().leads();
    app.lead().selectLeadInListUIById("forLeadModify");
    app.lead().modify(lead);
    Leads after = app.db().leads();
    assertThat(after.size(), equalTo(before.size()));

    for (LeadData leadModify : before) { // найти в списке "до" родителя с таким id
      if (leadModify.getId().equals("forLeadModify")) {
        LeadData leadAdd = lead.withId(leadModify.getId());
        Leads before1 = before.without(leadModify).withAdded(leadAdd);
        assertThat(after, equalTo(before1));
        return;
      }
    }
    verifyLeadsListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    LeadService leadService = new LeadService();
    leadService.DeleteById("forLeadModify");
  }
}
