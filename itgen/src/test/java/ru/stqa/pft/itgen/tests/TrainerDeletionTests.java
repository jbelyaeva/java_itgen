package ru.stqa.pft.itgen.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.TrainerData;
import ru.stqa.pft.itgen.model.Trainers;
import ru.stqa.pft.itgen.model.WorkerData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TrainerDeletionTests extends TestBase {
  public TrainerData deletedTrainer;
  int a = 0;

  @BeforeMethod
  public void ensurePreconditions() {
    Trainers before = app.db().trainers();
    for (TrainerData trainerData : before) {
      String id = trainerData.getId();
      if ((!id.equals("1")) && (!id.equals("2")) && (!id.equals("3")) && (!id.equals("4")) && (!id.equals("5"))
              && (!id.equals("6")) && (!id.equals("7")) && (!id.equals("8")) && (!id.equals("9")) && (!id.equals("10"))
              && (!id.equals("11")) && (!id.equals("12")) && (!id.equals("13")) && (!id.equals("14"))
              && (!id.equals("15")) && (!id.equals("16")) && (!id.equals("17")) && (!id.equals("18"))
              && (!id.equals("19")) && (!id.equals("20"))) {
        deletedTrainer = trainerData;
        a = 1;
        break;
      }
    }
    if (a == 0) {
      app.goTo().tasks();
      app.goTo().gotoWorker();
      app.trainer().createFirstTrainer(new WorkerData().withFirstName("Маша").withLastName("Машина").withRole("trainer")
              .withPhone("8962988888888"));
      Trainers beforeNew = app.db().trainers();
      String url = app.trainer().getURL();
      String id = app.trainer().getId(url);
      //найти в списке ДО родителя с таким id
      for (TrainerData trainerData : beforeNew)
        if (trainerData.getId().equals(id)) {
          deletedTrainer = trainerData;
          return;
        }
    }
  }

  @Test
  public void testTrainerDeletion() {
    app.goTo().tasks();
    app.goTo().gotoTrainer();
    Trainers before = app.db().trainers();
    app.trainer().deletionTrainer(deletedTrainer);
    Trainers after = app.db().trainers();
    assertThat(after, equalTo(before.without(deletedTrainer)));
    verifyTrainerListInUI();
  }
}
