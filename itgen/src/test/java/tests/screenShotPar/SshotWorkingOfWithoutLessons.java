package tests.screenShotPar;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

/**
 * T-97 К дефолтной семье добавлен ученик старше 7 лет. Вчера было завершено пробное, а затем так же
 * * вчера им было пропущено занятие. Зайти в окно оработки и убедиться, что 1 .Отображается чп
 * ученика и направление 2."В течении недели нет свободных групп. Пожалуйста, свяжитесь с
 * Айтигеником!" если нет групп.
 */
public class SshotWorkingOfWithoutLessons extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String periodFirst = "10:00 - 12:00";
    String periodSecond = "18:00 - 20:00";
    data.schedules().set28_YesterdayStudentFinishedTrialLesson(periodFirst, "student");
    data.schedules().set41_YesterdayFinishedSingleLesson(periodSecond, "14", "skipped");
    data.newFamily().set1_FamilyWithStudentAndParent();
  }

  @Test()
  public void testSshotWorkingOfWithoutLessons() throws AWTException, IOException {
    app.lkParent().goInWorkingOff();

    String name = "Parent_WorkingOffWithoutLessons_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='calendar']"));
    locatorIgnor.add(By.xpath("//span[@class='tip-text']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.lkParent().closeWinWorkingOffWithoutLesson();
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
