package tests.leads;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.lead.LeadData;
import data.model.lead.Leads;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LeadDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.leads().set1_leadParent("lead");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testLeadDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuLeads();
    Leads before = app.db().leads();
    app.lead().selectLeadInListUIById("lead");
    app.lead().delete();
    Leads after = app.db().leads();
    assertThat(after.size(), equalTo(before.size() - 1));

    for (LeadData lead : before) { // найти в списке "до" родителя с таким id
      if (lead.getId().equals("lead")) {
        Leads before1 = before.without(lead);
        assertThat(after, equalTo(before1));
        return;
      }
    }
    verifyLeadsListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().leads();
  }
}
