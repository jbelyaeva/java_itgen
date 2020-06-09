package ru.stqa.pft.itgen.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.TrainerData;
import ru.stqa.pft.itgen.model.Trainers;
import ru.stqa.pft.itgen.model.users.Contacts;
import ru.stqa.pft.itgen.model.users.Emails;
import ru.stqa.pft.itgen.services.TrainerService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TrainerDeletionTests extends TestBase {
  public TrainerData deletedTrainer;
  int a = 0;

  @BeforeMethod
  public void ensurePreconditions() {
    TrainerService trainerService = new TrainerService();
    deletedTrainer = new TrainerData().withId("trainerDelete").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("trainer", "employee"))
            .withCountry("AL").withTimeZone("Europe/Minsk")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L)).withGender(2)
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withEmails(Collections.singletonList(new Emails().withAddress("julja83@list.ru").withVerified(true)));
    trainerService.save(deletedTrainer);

  }

  @Test
  public void testTrainerDeletion() {
    app.goTo().menuSchedule();
    app.goTo().menuTrainers();
    Trainers before = app.db().trainers();
    app.trainer().delete(deletedTrainer);
    Trainers after = app.db().trainers();
    assertThat(after, equalTo(before.without(deletedTrainer)));
    verifyTrainerListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    TrainerService trainerService = new TrainerService();
    trainerService.findByIdAndDelete("trainerDelete");
  }
}
