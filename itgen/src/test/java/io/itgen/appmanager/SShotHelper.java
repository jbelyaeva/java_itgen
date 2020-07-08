package io.itgen.appmanager;

import static io.itgen.appmanager.ApplicationManager.properties;
import static io.itgen.tests.TestBase.etalon;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class SShotHelper extends HelperBase {

  public SShotHelper(WebDriver wd) {
    super(wd);
  }

  public ImageDiff getImageDiff(
      String expected, String actual, String markedImages, String name, Set<By> locatorIgnor)
      throws AWTException, IOException {
    Robot bot = new Robot();
    bot.mouseMove(0, 0);

    Screenshot actualScreenshot =
        new AShot()
            .coordsProvider(new WebDriverCoordsProvider())
            .ignoredElements(locatorIgnor)
            .shootingStrategy(
                ShootingStrategies.viewportPasting(ShootingStrategies.scaling(1.25f), 100))
            .takeScreenshot(wd);
    Set<Coords> ignoredCoords = actualScreenshot.getIgnoredAreas();

    // взять скриншот после появления элемента с локатором
    etalon(expected, name, actualScreenshot);

    File actualFile = new File(actual + name + ".png");
    ImageIO.write(actualScreenshot.getImage(), "png", actualFile);

    // берем эталонный снимок
    Screenshot expectedScreenshot =
        new Screenshot(ImageIO.read(new File(expected + name + ".png")));
    expectedScreenshot.setIgnoredAreas(ignoredCoords);

    // сравниваем
    ImageDiff diff = new ImageDiffer().makeDiff(expectedScreenshot, actualScreenshot);

    // результат
    if (diff.getDiffSize() != 0) {
      File diffFile = new File(markedImages + name + ".png");
      ImageIO.write(diff.getMarkedImage(), "png", diffFile);
      getScreenShot(name);
    }
    return diff;
  }

  @Attachment()
  public static byte[] getScreenShot(String ResourseName) throws IOException {
    String Path = properties.getProperty("markedImages") + ResourseName + ".png";
    byte[] file = Files.readAllBytes(Paths.get(Path));
    return file;
  }

  // открепляем топ-бар
  public void changeTopBar() {
    ((JavascriptExecutor) wd).executeScript("$('.top-bar-container').css('position', 'relative');");
  }

  // приводим таблицу с доступными занятиями к одному стилю, т.к. стиль меняется динамически,
  // скриншоты падают
  public void changeTableInWindowSchedule() {
    By locatorHeading = By.xpath("(//div[@class='cell-heading cell-info'])[1]");
    By locatorGroupList =
        By.xpath("(//div[@class='create-child-schedule-group-list cell-info'])[1]");
    for (int i = 1; i < 8; i++) {
      if (isElementPresent(locatorHeading)) {
        WebElement elementHeading = wd.findElement(locatorHeading);
        WebElement elementGroupList = wd.findElement(locatorGroupList);
        ((JavascriptExecutor) wd)
            .executeScript(
                "arguments[0].setAttribute('class', 'cell-heading cell-default')", elementHeading);
        ((JavascriptExecutor) wd)
            .executeScript(
                "arguments[0].setAttribute('class', 'create-child-schedule-group-list cell-default')",
                elementGroupList);
      }
    }
  }
}
