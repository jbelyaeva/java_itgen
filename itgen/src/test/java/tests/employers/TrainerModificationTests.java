package tests.employers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.TrainerData;
import data.model.users.Trainers;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Emails;
import data.provides.LocaleUtilsTestData;
import data.services.TrainerService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerModificationTests extends TestBase {

  public TrainerData modifyTrainer;

  @BeforeMethod
  public void ensurePreconditions() {
    TrainerService trainerService = new TrainerService();
    modifyTrainer =
        new TrainerData()
            .withId("trainerModify")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withEngFirstName("Masha")
            .withEngLastName("Mashina")
            .withRoles(Arrays.asList("trainer", "employee"))
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withStartWorkAt(new Date())
            .withCreatedAt(new Date())
            .withGender(2)
            .withMaxSlots(4)
            .withPayBase(2.5)
            .withLangs(Arrays.asList("ru"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withEmails(
                Collections.singletonList(
                    new Emails().withAddress("julja83@list.ru").withVerified(true)))
            .withWorkloadLevel("easy");
    trainerService.save(modifyTrainer);
  }

  @Test(dataProvider = "validWorkersTrainersFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testTrainerModification(TrainerData trainer) {
    app.goTo().menuTrainers();
    Trainers before = app.db().trainers();
    app.trainer().goUrlTrainer("trainerModify");
    app.trainer().modify(trainer);
    Trainers after = app.db().trainers();
    assertThat(after.size(), equalTo(before.size()));
    TrainerData trainerAdd = trainer.withId(modifyTrainer.getId());
    assertThat(after, equalTo(before.without(modifyTrainer).withAdded(trainerAdd)));
    verifyTrainerListInUI();
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    TrainerService trainerService = new TrainerService();
    trainerService.DeleteById("trainerModify");
  }
}
