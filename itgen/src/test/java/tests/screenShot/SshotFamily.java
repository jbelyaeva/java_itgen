package tests.screenShot;
/* Скриншот страницы семьи. База изначально должна быть пустая. Тест создает семью, делает снимок,
  сравнивает его с эталонным.
*/

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotFamily extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        data.familyService().updateFieldBoolean("111", "isTrialBonusOff", false);
    }

    @Test(retryAnalyzer = RunTestAgain.class)
    public void testSshotFamilies() throws AWTException, IOException, InterruptedException {
        String name = "Admin_Family_RU_Chrome";
        Set<By> locatorIgnor = new HashSet<>();
        locatorIgnor.add(By.xpath("//span[@class='user-time']"));

        app.goTo().menuStudents();
        app.student().selectStudentInListUIById("21");
        app.family().btnFamily();
        app.sshot().changeTopBar();
        Thread.sleep(4000);
        ImageDiff diff =
                app.sshot()
                        .getImageDiff(
                                properties.getProperty("expected"),
                                properties.getProperty("actual"),
                                properties.getProperty("markedImages"),
                                name,
                                locatorIgnor,
                                1.25f);

        if (diff.getDiffSize() > 100) { // погрешность
            Assert.assertEquals(diff.getDiffSize(), 0);
        }
    }
}
