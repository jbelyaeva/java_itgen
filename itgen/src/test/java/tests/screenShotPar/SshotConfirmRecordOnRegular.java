package tests.screenShotPar;
/* Скриншот страницы семьи. База изначально должна быть пустая. Тест создает семью, делает снимок,
  сравнивает его с эталонным.
*/

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

public class SshotConfirmRecordOnRegular extends TestBase {
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00 и постоянное расписание на завтра в 18.00, на которое нужно записать ученика
  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily()
        .set9_LessonYesterdayFinished_RegularLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(
            period);
  }

  @Test
  public void testConfirmRecordOnRegular() throws AWTException, IOException {
    app.lkParent().confirmRecordOnRegular();

    String name = "Parent_ConfirmRecordOnRegular_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//p[@class='user']"));
    locatorIgnor.add(By.xpath("//div[@class='DayPickerInput']//input"));
    locatorIgnor.add(By.xpath("//span[@class='selected-icon']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));
    locatorIgnor.add(By.xpath("//div[@class='permanent']"));

    String[] deleteElements = {"//strong"};

    app.sshot().deleteElements(deleteElements);

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    app.lkParent().btnRecord();
    app.lkParent().btnLogo();

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
