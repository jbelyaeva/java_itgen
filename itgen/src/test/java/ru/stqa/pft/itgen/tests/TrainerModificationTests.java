package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.TrainerData;
import ru.stqa.pft.itgen.model.Trainers;
import ru.stqa.pft.itgen.model.WorkerData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
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
    Trainers before = app.db().trainers();
    for (TrainerData trainerData : before) {
      String id = trainerData.getId();
      if ((!id.equals("1")) && (!id.equals("2")) && (!id.equals("3")) && (!id.equals("4")) && (!id.equals("5"))
              && (!id.equals("6")) && (!id.equals("7")) && (!id.equals("8")) && (!id.equals("9")) && (!id.equals("10"))
              && (!id.equals("11")) && (!id.equals("12")) && (!id.equals("13")) && (!id.equals("14"))
              && (!id.equals("15")) && (!id.equals("16")) && (!id.equals("17")) && (!id.equals("18"))
              && (!id.equals("19")) && (!id.equals("20"))) {
        modifyTrainer = trainerData;
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
          modifyTrainer = trainerData;
          return;
        }
    }
  }

  @Test(dataProvider = "validWorkersTrainersFromJson")
  public void testTrainerModification(TrainerData trainer) {
    app.goTo().tasks();
    app.goTo().gotoTrainer();
    Trainers before = app.db().trainers();
    app.trainer().selectedTrainerById(modifyTrainer);
    app.trainer().modifyTrainer(trainer);
    Trainers after = app.db().trainers();
    assertThat(after.size(), equalTo(before.size()));
    TrainerData trainerAdd = trainer.withId(modifyTrainer.getId());
    assertThat(after, equalTo(before.without(modifyTrainer).withAdded(trainerAdd)));
    verifyTrainerListInUI();
  }
}
