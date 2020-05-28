package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.TrainerData;
import ru.stqa.pft.itgen.model.Trainers;
import ru.stqa.pft.itgen.model.users.Contacts;
import ru.stqa.pft.itgen.model.users.Emails;
import ru.stqa.pft.itgen.services.TrainerService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TrainerModificationTests extends TestBase {
  public TrainerData modifyTrainer;
  int a = 0;

  @DataProvider
  public Iterator<Object[]> validWorkersTrainersFromJson() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/testdata/workers_trainers_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<TrainerData> trainers = gson.fromJson(json, new TypeToken<List<TrainerData>>() {
      }.getType()); // List<TrainerData>.class
      return trainers.stream().map((t) -> new Object[]{t}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    TrainerService trainerService = new TrainerService();
    modifyTrainer = new TrainerData().withId("trainerModify").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("trainer", "employee"))
            .withCountry("AL").withTimeZone("Europe/Minsk")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withStartWorkAt(new Date())
            .withCreatedAt(new Date())
            .withGender(2)
            .withMaxSlots(4)
            .withPayBase(5)
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withEmails(Collections.singletonList(new Emails().withAddress("julja83@list.ru").withVerified(true)));
    trainerService.save(modifyTrainer);
  }

  @Test(dataProvider = "validWorkersTrainersFromJson")
  public void testTrainerModification(TrainerData trainer) {
    app.goTo().menuTasks();
    app.goTo().menuTrainers();
    Trainers before = app.db().trainers();
    app.trainer().selectTrainerById(modifyTrainer);
    app.trainer().modify(trainer);
    Trainers after = app.db().trainers();
    assertThat(after.size(), equalTo(before.size()));
    TrainerData trainerAdd = trainer.withId(modifyTrainer.getId());
    assertThat(after, equalTo(before.without(modifyTrainer).withAdded(trainerAdd)));
    verifyTrainerListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    TrainerService trainerService = new TrainerService();
    trainerService.findByIdAndDelete("trainerModify");
    }
}
