package ru.stqa.pft.itgen.tests.schedule;
//автотест проверяет подвижку разового расписания

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ScheduleData;
import ru.stqa.pft.itgen.model.Schedules;
import ru.stqa.pft.itgen.services.ScheduleService;
import ru.stqa.pft.itgen.tests.TestBase;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleRegularCancelTests extends TestBase {

  String idSchedule;

  @BeforeMethod
  public void ensurePreconditions() {
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule = new ScheduleData()
            .withId("forScheduleCreation")
            .withVer(0)
            .withFromDate(1589396400.0) //надо дату сегодняшнюю
            //     .withSlots(Collections.singletonList(new ScheduleData.Slots().withW(155899999.0).withId("1").withSt(new ScheduleData.Slots.ST().withE(111.111).withS(22.22))))
        //          .withSlots(Arrays.asList(new ScheduleData.Slots().withId("77").withW(1.0).withSt(new ScheduleData.Slots.ST().withE(111.111).withS(22.22))))
         //         .withSlots(Arrays.asList(new ScheduleData.Slots().withId("77").withW(1.0), new ScheduleData.Slots().withId("14").withW(5.0)))
          //        .withSlots(Arrays.asList(new ScheduleData.Slots().withSt(new ScheduleData.Slots.ST())))
            //       .withSlots(Arrays.asList(new ScheduleData.Slots().withSt(new ScheduleData.Slots.ST().withE(111.111).withS(22.22))))
            //       .withTimes(new ScheduleData.Times().withStart(1111111).withEnd(22222))
            .withSkypeId("1");
    //  .withSlots(new ScheduleData.Slots().withW(1589396400.0));
    // .withSlots(Collections.singletonList(new ScheduleData.Slots().withId("1")))
    scheduleService.create(schedule);

  }
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
