package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.TrainerData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TrainerModificationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validWorkersTrainersFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/workers_trainers_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<TrainerData> trainers = gson.fromJson(json, new TypeToken<List<TrainerData>>(){}.getType()); // List<TrainerData>.class
      return trainers.stream().map((t) -> new Object[] {t}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validWorkersTrainersFromJson", enabled = false)
  public void testTrainerModification(TrainerData trainer) {
    app.goTo().gotoTasks();
    app.goTo().gotoTrainer();
    int before = app.getTrainerHelper().getTrainerCount();
    app.getTrainerHelper().selectedTrainer();  // выбирает 9-го по списку тренера (его надо предварительно создать!!!)
    app.getTrainerHelper().modifyTrainer();
    app.getTrainerHelper().modifiTrainerForm(trainer);
    app.getTrainerHelper().submitTrainerModify();
    app.goTo().gotoTrainer();
    int after = app.getTrainerHelper().getTrainerCount();
    Assert.assertEquals(after, before);
  }
}
