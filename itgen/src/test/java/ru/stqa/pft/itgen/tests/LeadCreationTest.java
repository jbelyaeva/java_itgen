package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.*;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.LeadService;
import ru.stqa.pft.itgen.services.StudentService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeadCreationTest extends TestBase{
String id;
  @DataProvider
  public Iterator<Object[]> validLeadsFromJson() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/testdata/leads_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<LeadData> parents = gson.fromJson(json, new TypeToken<List<LeadData>>() {
      }.getType()); // List<ParentData>.class
      return parents.stream().map((p) -> new Object[]{p}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validLeadsFromJson")
  public void testLeadCreation(LeadData lead) {
    app.goTo().menuTasks();
    app.goTo().menuLeads();
    Leads before = app.db().leads();
    app.lead().btnCreateLead();
    app.lead().fillLeadForm(lead);
    app.lead().btnAddLead();
    Leads after = app.db().leads();
    assertThat(after.size(), equalTo(before.size() + 1));
    id = app.lead().getIdNewLeadDB(before, after);
    LeadData leadAdd = lead.withId(id);
    assertThat(after, equalTo(before.withAdded(leadAdd)));
    verifyLeadsListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    LeadService leadService = new LeadService();
    LeadData leadClean = leadService.findById(id);
    if (leadClean != null) {
      leadService.delete(leadClean);
    }
  }

}
