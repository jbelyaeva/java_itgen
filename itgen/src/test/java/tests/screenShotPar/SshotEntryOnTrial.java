package tests.screenShotPar;
/* Скриншот страницы семьи. База изначально должна быть пустая. Тест создает семью, делает снимок,
  сравнивает его с эталонным.
*/

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import core.general.RunTestAgain;
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

public class SshotEntryOnTrial extends TestBase {
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик
  // и разовое расписание на завтра в 18.00, на которое нужно записать добавленного ученика на
  // пробное
  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily().set6_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testEntryOnTrial() throws AWTException, IOException {
    app.lkParent().recordOnTrail(1);

    String name = "Parent_EntryOnTrial_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='lesson']//span[1]"));
    locatorIgnor.add(By.xpath("//div[@class='day']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));

    app.sshot().changeTopBarInLKParent();

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    app.lkParent().btnLogo();
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
