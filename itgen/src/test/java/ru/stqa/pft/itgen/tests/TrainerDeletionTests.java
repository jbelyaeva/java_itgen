package ru.stqa.pft.itgen.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.TrainerData;
import ru.stqa.pft.itgen.model.Trainers;
import ru.stqa.pft.itgen.model.WorkerData;
import ru.stqa.pft.itgen.model.Workers;
import ru.stqa.pft.itgen.services.TrainerService;
import ru.stqa.pft.itgen.services.WorkerService;

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
      TrainerData trainer = new TrainerData().withId("trainerDelete").withFirstName("Маша").withLastName("Машина")
              .withRoles(Collections.singletonList(new TrainerData.Roles().withRoles("trainer")))
              .withCountry("AL").withTimeZone("Europe/Minsk")
              .withLocate("ru")
              .withBirthday(new Date(1556726891000L)).withGender(2)
              .withLangs(Collections.singletonList(new TrainerData.Langs().withLangs("ru")))
              .withContacts(Collections.singletonList(new TrainerData.Contacts().withType("phone").withVal("1234567899")))
              .withEmails(Collections.singletonList(new TrainerData.Emails().withAddress("julja83@list.ru").withVerified(true)));
      trainerService.create(trainer);

  }

  @Test
  public void testTrainerDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuTrainers();
    Trainers before = app.db().trainers();
    deletedTrainer = app.trainer().findTrainer("trainerDelete");
    app.trainer().delete(deletedTrainer);
    Trainers after = app.db().trainers();
    assertThat(after, equalTo(before.without(deletedTrainer)));
    verifyTrainerListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    TrainerService trainerService = new TrainerService();
    TrainerData trainerClean = trainerService.findById("trainerDelete");
    if (trainerClean != null) {
      trainerService.delete(trainerClean);}
  }
}
