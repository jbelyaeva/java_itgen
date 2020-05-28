package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.LeadData;
import ru.stqa.pft.itgen.model.Leads;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.users.Contacts;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.LeadService;
import ru.stqa.pft.itgen.services.StudentService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LeadDeletionTest extends TestBase{

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
        Leads before1=before.without(lead);
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
