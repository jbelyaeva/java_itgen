package tests.employers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import data.model.users.TrainerData;
import data.model.users.Trainers;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Emails;
import data.services.TrainerService;
import app.testbase.TestBase;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerDeletionTests extends TestBase {
  public TrainerData deletedTrainer;

  @BeforeMethod
  public void ensurePreconditions() {
    TrainerService trainerService = new TrainerService();
    deletedTrainer =
        new TrainerData()
            .withId("trainerDelete")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("trainer", "employee"))
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withGender(2)
            .withLangs(Arrays.asList("ru"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withEmails(
                Collections.singletonList(
                    new Emails().withAddress("julja83@list.ru").withVerified(true)));
    trainerService.save(deletedTrainer);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerDeletion() {
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
    trainerService.DeleteById("trainerDelete");
  }
}
