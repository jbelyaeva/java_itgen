package tests.screenShotStudent;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import core.general.TimeGeneral;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
/*Начальные данные: дефолтный ребенок
1. прошедший вчера пробное с двумя проектами
2. у него два теста (пройденный и заваленый)
3. занятие сегодня уже началось
Серия скриншотов на ученика имеет особенность. В первом тесте мы создаем тестовые данные, пробегаем по
вкладкам и делаем скриншоты, в последнем тесте чистим данные в бд. Причина: нужно менять данные в дефолтном
ученике после выполнения фикстуры -> при обновлении документа происходит выход из учетки и надо заново логиниться.*/

public class Sshot1Feed extends TestBase {

  private final TimeGeneral time = new TimeGeneral();

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.finishedLessonWithProject().set9_LessonYesterdayFinishedWithProject(period);
    data.defFamily().set21_DefaultStudent_finishLessonBy1Skil();
    long alreadyRun = 7200000; // 2 часа идет занятие
    String periodLesson = time.getPeriod(time.getTimeNow() - alreadyRun);
    data.schedules().set26_TodayStartSingleScheduleWithOneStudentOnTrial(periodLesson);
  }

  @Test
  public void testSshot1Feed() throws AWTException, IOException {
    String name = "Student_FeedPage_RU_Chrome";
    app.lkStudent().skipHelper();
    app.lkStudent().goToFeed();
    app.sshot().changeTopBarInLKParent();

    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//span[@class='date']"));
    locatorIgnor.add(By.xpath("//div[@class='date today']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));

    String[] deleteElements = {"//span[@class='date']", "//div[@class='date today']"};
    app.sshot().deleteElements(deleteElements);

    ImageDiff diff =
        app.sshot()
            .getImageDiffWithoutScroll(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }
}
