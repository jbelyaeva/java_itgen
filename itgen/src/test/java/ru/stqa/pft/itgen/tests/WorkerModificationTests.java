package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.WorkerData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class WorkerModificationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validWorkersFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/workers_modification.json")))) {
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

  @Test (dataProvider = "validWorkersFromJson")
  public void testWorkerModification(WorkerData worker) {
    app.goTo().gotoTasks();
    app.goTo().gotoWorker();
    int before = app.getWorkerHelper().getWorkerCount();
    app.getWorkerHelper().selectedWorker();
    app.getWorkerHelper().modifyWorker();
    app.getWorkerHelper().modifiWorkerForm(worker);
    app.getWorkerHelper().submitWorkerModify();
    app.goTo().gotoWorker();
    int after = app.getWorkerHelper().getWorkerCount();
    Assert.assertEquals(after, before);
  }
}
