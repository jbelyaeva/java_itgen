package io.itgen.tests.screenShotPar;
/* Скриншот страницы семьи. База изначально должна быть пустая. Тест создает семью, делает снимок,
  сравнивает его с эталонным.
*/

import io.itgen.appmanager.ApplicationManager;
import io.itgen.model.FamilyData;
import io.itgen.services.FamilyService;
import io.itgen.tests.TestBase;
import org.aspectj.lang.annotation.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;

public class SshotMain extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // контролируем состояние,что семье положен бонус 24ч и нельзя пополнить баланс
    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("111").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);
  }

  @Test // упадет, если заускать через shift все тесты в подпапке.
  public void testSshotMain() throws AWTException, IOException {
    String name = "Parent_Main_RU_Chrome";
    String[] locatorIgnor = {"//div[contains(@id,'MeteorToys')]"};

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor);

    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }
}
