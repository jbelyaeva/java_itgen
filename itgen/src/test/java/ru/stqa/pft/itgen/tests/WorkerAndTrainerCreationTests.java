package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.TrainerData;
import ru.stqa.pft.itgen.model.WorkerData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class WorkerAndTrainerCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validWorkersFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/workers_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<WorkerData> workers = gson.fromJson(json, new TypeToken<List<WorkerData>>(){}.getType()); // List<WorkerData>.class
      return workers.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validWorkersAdminsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/workers_admins_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<WorkerData> workers = gson.fromJson(json, new TypeToken<List<WorkerData>>(){}.getType()); // List<WorkerData>.class
      return workers.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validWorkersTrainersFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/workers_trainers_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<WorkerData> workers = gson.fromJson(json, new TypeToken<List<WorkerData>>(){}.getType()); // List<TrainerData>.class
      return workers.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validWorkersFromJson", enabled = false)
  public void testWorkerCreation(WorkerData worker) {
    app.getNavigationHelper().gotoWorker();
    int before = app.getWorkerHelper().getWorkerCount();
    app.getWorkerHelper().addWorker();
    app.getWorkerHelper().fillWorkerForm(worker);
    app.getWorkerHelper().submitWorkerCreation();
    app.getNavigationHelper().gotoWorker();
    int after = app.getWorkerHelper().getWorkerCount();
    Assert.assertEquals(after, before +1);
  }

  @Test (dataProvider = "validWorkersAdminsFromJson", enabled = false)
  public void testWorkerAdminCreation(WorkerData worker) {
    app.getNavigationHelper().gotoWorker();
    int before = app.getWorkerHelper().getWorkerCount();
    app.getWorkerHelper().addWorker();
    app.getWorkerHelper().fillWorkerForm(worker);
    app.getWorkerHelper().submitWorkerCreation();
    app.getNavigationHelper().gotoWorker();
    int after = app.getWorkerHelper().getWorkerCount();
    Assert.assertEquals(after, before +1);
  }

  @Test (dataProvider = "validWorkersTrainersFromJson")
  public void testWorkerTrainerCreation(WorkerData worker) {
    app.getNavigationHelper().gotoTrainer();
    int before = app.getTrainerHelper().getTrainerCount();
    app.getNavigationHelper().gotoWorker();
    app.getWorkerHelper().addWorker();
    app.getWorkerHelper().fillWorkerForm(worker);
    app.getWorkerHelper().submitWorkerCreation();
    app.getNavigationHelper().gotoTrainer();
    int after = app.getTrainerHelper().getTrainerCount();
    Assert.assertEquals(after, before +1);
  }
}