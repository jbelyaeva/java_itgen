package tests.leads;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.lead.LeadData;
import data.model.lead.Leads;
import data.provides.LocaleUtilsTestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LeadCreationTest extends TestBase {

  LeadData leadClean = null;

  @Test(dataProvider = "validLeadsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testLeadCreation(LeadData lead) {
    app.goTo().menuTrainers();
    app.goTo().menuLeads();
    Leads before = app.db().leads();
    app.lead().create(lead);
    Leads after = app.db().leads();
    leadClean = app.lead().getNewLeadDB(before, after);
    LeadData leadAdd = lead.withId(leadClean.getId());
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(leadAdd)));
    verifyLeadsListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().finishedLesson().leads();
  }
}
