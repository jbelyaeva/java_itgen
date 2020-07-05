package io.itgen.tests.screenShot;
/**
 * Скриншот страницы с расписанием. База изначально должна быть пустая. Тест создает расписание, делает снимок,
 * сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка запускаем конфигурацию запуска
 * со свойством -Detalon=true.
 */

import io.itgen.general.TimeGeneral;
import io.itgen.model.ScheduleData;
import io.itgen.model.schedule.C;
import io.itgen.model.schedule.ST;
import io.itgen.model.schedule.Slots;
import io.itgen.model.schedule.Times;
import io.itgen.services.ScheduleService;
import io.itgen.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static io.itgen.appmanager.ApplicationManager.properties;

public class SshotMainSchedule extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule = new ScheduleData()
            .withId("scheduleSingleBlock")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.date())
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);

  }


  @Test
  public void testSshotMainSchedule() throws AWTException, IOException {
    String name = "Admin_MainSchedule_RU_Chrome";
    String[] locatorIgnor = {
            "//h4"
    };

    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    ImageDiff diff = app.sshot().getImageDiff(properties.getProperty("expected")
            , properties.getProperty("actual")
            , properties.getProperty("markedImages")
            , name, locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("scheduleSingleBlock");
  }
}

