package io.itgen.tests.task;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.model.tasks.TaskData;
import io.itgen.model.tasks.Tasks;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TaskManualAddNew extends TestBase {

  TaskService taskService = new TaskService();
  TaskData taskClean = null;

  @DataProvider
  public Iterator<Object[]> validTaskFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/task_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<TaskData> tasks =
          gson.fromJson(json, new TypeToken<List<TaskData>>() {
          }.getType());
      return tasks.stream()
          .map((s) -> new Object[]{s})
          .collect(Collectors.toList())
          .iterator();
    }
  }


  @Test(dataProvider = "validTaskFromJson")
  public void testAddNewTask(TaskData task) {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().addNewTask(task);
    Tasks after = app.dbtasks().tasks();
    taskClean = app.dbtasks().lastTask();
    assertThat(after.size(), equalTo(before.size() + 1));
    check(after, task);
    app.goTo().menuSchedule();
  }

  private void check(Tasks after, TaskData task) {

    app.trTask()
        .newManualTask(
            taskClean.getId(),
            task.getCreator(),
            task.getAssignee(),
            task.getText(),
            task.getPriority(),
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            task.getLinkUser());

    TaskData taskAdd = taskService.findById(taskClean.getId());

    for (TaskData taskAfter : after) {
      if (taskAfter.getId().equals(taskClean.getId())) {
        assertThat(after, equalTo(after.without(taskAfter).withAdded(taskAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    taskService.DeleteById(taskClean.getId());
  }
}
