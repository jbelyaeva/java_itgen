package io.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.model.LeadData;
import io.itgen.model.Leads;
import io.itgen.services.LeadService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.itgen.services.TaskService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeadCreationTest extends TestBase {
  LeadService leadService = new LeadService();
  TaskService taskService = new TaskService();
  LeadData leadClean = null;

  @DataProvider
  public Iterator<Object[]> validLeadsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/leads_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<LeadData> leads = gson.fromJson(json, new TypeToken<List<LeadData>>() {}.getType());
      return leads.stream().map((p) -> new Object[] {p}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validLeadsFromJson")
  public void testLeadCreation(LeadData lead) {
    app.goTo().menuTasks();
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
    leadService.DeleteById(leadClean);
    taskService.findByIdAndDeleteTask(leadClean);
  }
}
