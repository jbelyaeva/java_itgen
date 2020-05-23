package ru.stqa.pft.itgen.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ST;
import ru.stqa.pft.itgen.model.ScheduleData;
import ru.stqa.pft.itgen.model.Slots;
import ru.stqa.pft.itgen.model.Times;
import ru.stqa.pft.itgen.services.ScheduleService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class CreateSheduleTests {
  private static EntityManagerFactory entityManagerFactory;

  @BeforeMethod
  public static void setUpEntityManagerFactory() {
    entityManagerFactory = Persistence.createEntityManagerFactory("ogm-mongoDB");
  }

  @Test
  public void testCreateShedule() throws Exception {
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule = new ScheduleData()
            .withId("forScheduleCreation")
            .withVer(0)
            .withFromDate(1589396400.0)
            //       .withSlots(Collections.singletonList(new ScheduleData.Slots().withW(155899999.0).withId("1").withSt(new ScheduleData.Slots.ST().withE(111.111).withS(22.22))))
            //       .withSlots(Arrays.asList(new ScheduleData.Slots().withId("77").withW(1.0).withSt(new ScheduleData.Slots.ST().withE(111.111).withS(22.22))))
            //       .withSlots(Arrays.asList(new ScheduleData.Slots().withId("77").withW(1.0), new ScheduleData.Slots().withId("14").withW(5.0)))
            //       .withSlots(Arrays.asList(new ScheduleData.Slots().withSt(new ScheduleData.Slots.ST().withE(111.111).withS(22.22))))
            //       .withSlots(Arrays.asList(new Slots().withSt(new ST().withE(111.111).withS(22.22))))
            //       .withTimes(new ScheduleData.Times().withStart(1111111).withEnd(22222))
            .withSlots(Arrays.asList(new Slots().withSt(new ST(1.1, 2.2))))
            .withTimes(new Times(10, 20))
            .withSkypeId("1");
    //  .withSlots(new ScheduleData.Slots().withW(1589396400.0));
    // .withSlots(Collections.singletonList(new ScheduleData.Slots().withId("1")))
    scheduleService.create(schedule);
  }

  @AfterMethod
  public static void closeEntityManagerFactory() {
    entityManagerFactory.close();
  }
}
