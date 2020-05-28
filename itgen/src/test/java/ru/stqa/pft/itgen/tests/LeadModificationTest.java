package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.LeadData;
import ru.stqa.pft.itgen.model.Leads;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.users.Contacts;
import ru.stqa.pft.itgen.model.users.Utm;
import ru.stqa.pft.itgen.services.LeadService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeadModificationTest extends TestBase{
  @DataProvider
  public Iterator<Object[]> validLeadsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/leads_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<LeadData> leads = gson.fromJson(json, new TypeToken<List<LeadData>>() {
      }.getType()); // List<StudentData>.class
      return leads.stream().map((s) -> new Object[]{s}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    LeadService leadService = new LeadService();
    LeadData lead = new LeadData().withId("forLeadModify").withFirstName("Лид").withLastName("Лидов")
            .withRoles(Arrays.asList("child"))
            .withCountry("AL").withTimeZone("Europe/Minsk").withLocate("ru")
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withStatus("new").withUtm(new Utm().withSource("site").withMedium("manual"));
    leadService.create(lead);
  }

  @Test(dataProvider = "validLeadsFromJson")
  public void testLeadModification(LeadData lead) {
    app.goTo().menuTasks();
    app.goTo().menuLeads();
    Leads before = app.db().leads();
    app.lead().selectLeadInListUIById("forLeadModify");
    app.lead().modify(lead);
    Leads after = app.db().leads();
    assertThat(after.size(), equalTo(before.size()));

    for (LeadData leadModify : before) { //найти в списке "до" родителя с таким id
      if (leadModify.getId().equals("forLeadModify")) {
        LeadData leadAdd = lead.withId(leadModify.getId());
        Leads before1=before.without(leadModify).withAdded(leadAdd);
        assertThat(after,equalTo(before1));
        return;
      }
    }
    verifyLeadsListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    LeadService leadService = new LeadService();
    leadService.findByIdAndDelete("forLeadModify");
    }

}
