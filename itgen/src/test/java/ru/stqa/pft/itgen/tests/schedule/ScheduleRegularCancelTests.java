package ru.stqa.pft.itgen.tests.schedule;
//автотест проверяет подвижку разового расписания

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ScheduleData;
import ru.stqa.pft.itgen.model.Schedules;
import ru.stqa.pft.itgen.services.ScheduleService;
import ru.stqa.pft.itgen.tests.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleRegularCancelTests extends TestBase {
  String idSchedule;

  @Test
  public void testScheduleRegularCancel() {
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    app.schedule().createRegularSchedule(); //заменить транзакцией в предусловии
    Schedules before = app.db().schedules();
    idSchedule = app.schedule().cancel();
    Schedules after = app.db().schedules();
    assertThat(after.size(), equalTo(before.size()));
    //проверка, что у занятия статус изменился
  }

 @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData scheduleClean = scheduleService.findById(idSchedule);
    if (scheduleClean != null) {
      scheduleService.delete(scheduleClean);
    }
  }
}
