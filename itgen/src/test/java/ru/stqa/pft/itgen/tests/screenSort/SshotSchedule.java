package ru.stqa.pft.itgen.tests.screenSort;
/**
 * Скриншот страницы с расписанием. База изначально должна быть пустая. Тест создает расписание, делает снимок,
 * сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка запускаем конфигурацию запуска
 * со свойством -Detalon=true.
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.tests.TestBase;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;

import static ru.stqa.pft.itgen.appmanager.ApplicationManager.propertiesAshot;

public class SshotSchedule extends TestBase {


  @Test
  public void testSshotSchedule() throws AWTException, IOException {
    String name = "schedule_RU_Chrome";
    String locatorIgnor = "";
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    app.schedule().createRegularSchedule();

    ImageDiff diff = app.sshot().getImageDiff(propertiesAshot.getProperty("expected")
            , propertiesAshot.getProperty("actual")
            , propertiesAshot.getProperty("markedImages")
            , name, locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);
  }

  /* @AfterMethod(alwaysRun = true)
    public void clean() {
     String idSchedule=app.schedule().selectScheduleGetId();
     ScheduleService scheduleService = new ScheduleService();
     ScheduleData scheduleClean = scheduleService.findById(idSchedule);
     if (scheduleClean != null) {
       scheduleService.delete(scheduleClean);
     }
   }*/
}

