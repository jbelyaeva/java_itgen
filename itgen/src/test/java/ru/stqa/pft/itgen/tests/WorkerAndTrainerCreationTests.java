package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.TrainerData;
import ru.stqa.pft.itgen.model.Trainers;
import ru.stqa.pft.itgen.model.WorkerData;
import ru.stqa.pft.itgen.model.Workers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkerAndTrainerCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validWorkersFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/workers_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<WorkerData> workers = gson.fromJson(json, new TypeToken<List<WorkerData>>() {
      }.getType()); // List<WorkerData>.class
      return workers.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validWorkersAdminsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/workers_admins_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<WorkerData> workers = gson.fromJson(json, new TypeToken<List<WorkerData>>() {
      }.getType()); // List<WorkerData>.class
      return workers.stream().map((w) -> new Object[]{w}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validWorkersTrainersFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/workers_trainers_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<TrainerData> trainers = gson.fromJson(json, new TypeToken<List<TrainerData>>() {
      }.getType()); // List<TrainerData>.class
      return trainers.stream().map((w) -> new Object[]{w}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validWorkersFromJson")
  public void testWorkerCreation(WorkerData worker) {
    app.goTo().tasks();
    app.goTo().worker();
    Workers before = app.db().workers();
    String url = app.workers().createWorker(worker);
    String idWorker = app.workers().getId(url);
    Workers after = app.db().workers();
    assertThat(after.size(), equalTo(before.size() + 1));
    WorkerData workerAdd = worker.withId(idWorker);
    assertThat(after, equalTo(before.withAdded(workerAdd)));
    verifyWorkerListInUI();
  }


  @Test(dataProvider = "validWorkersAdminsFromJson", enabled = false)
  public void testWorkerAdminCreation(WorkerData worker) {
    app.goTo().tasks();
    app.goTo().worker();
    int before = app.workers().getWorkerCount();
    app.workers().createWorker(worker);
    int after = app.workers().getWorkerCount();
    Assert.assertEquals(after, before + 1);
  }

  @Test(dataProvider = "validWorkersTrainersFromJson")
  public void testWorkerTrainerCreation(TrainerData trainer) {
    app.goTo().tasks();
    app.goTo().worker();
    Trainers before = app.db().trainers();
    String url = app.trainer().create(trainer);
    String idTrainer = app.trainer().getId(url);
    Trainers after = app.db().trainers();
    assertThat(after.size(), equalTo(before.size() + 1));
    TrainerData trainerAdd = trainer.withId(idTrainer);
    assertThat(after, equalTo(before.withAdded(trainerAdd)));
    verifyTrainerListInUI();
  }
}