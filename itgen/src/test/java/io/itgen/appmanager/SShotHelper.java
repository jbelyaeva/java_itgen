package io.itgen.appmanager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.itgen.appmanager.ApplicationManager.properties;
import static io.itgen.tests.TestBase.etalon;

public class SShotHelper extends HelperBase {

  public SShotHelper(WebDriver wd) {
    super(wd);
  }

  public ImageDiff getImageDiff(String expected, String actual, String markedImages, String name, String[] locatorIgnor) throws AWTException, IOException {
    Robot bot = new Robot();
    bot.mouseMove(0, 0);
    //получаем
    if (locatorIgnor != null) {
      for (int i = 0; i <= locatorIgnor.length - 1; i++) {
        List<WebElement> elementsList = wd.findElements(By.xpath(locatorIgnor[i]));
        for (WebElement element : elementsList) {
          ((JavascriptExecutor) wd)
                  .executeScript("arguments[0].remove();", element);
        }
      }
    }

    //   Screenshot actualScreenshot = new AShot().takeScreenshot(wd);
    Screenshot actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(1.25f), 100))
            .takeScreenshot(wd);
    //взять скриншот после появления элемента с локатором
    //сохраняем
    etalon(expected, name, actualScreenshot);

    File actualFile = new File(actual + name + ".png");
    ImageIO.write(actualScreenshot.getImage(), "png", actualFile);

    //берем эталонный снимок
    Screenshot expectedScreenshot = new Screenshot(ImageIO.read(new File(expected + name + ".png")));

    //сравниваем
    ImageDiff diff = new ImageDiffer().makeDiff(expectedScreenshot, actualScreenshot);

    //результат
    int rez = diff.getDiffSize();
    if (rez != 0) {
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

  public void changeTopBar() {
    ((JavascriptExecutor) wd).executeScript("$('.top-bar-container').css('position', 'relative');");
  }

  public void changeTableInWindowSchedule() {
    String locatorHeader="(//div[@class='cell-heading cell-info'])[1]";
    String locatorBody="(//div[@class='create-child-schedule-group-list cell-info'])[1]";
    for (int i = 1; i < 8; i++) {
      if (isElementPresent(By.xpath(locatorHeader))) {
        WebElement elementHeader = wd.findElement(By.xpath(locatorHeader));
        WebElement elementBody = wd.findElement(By.xpath(locatorBody));
        ((JavascriptExecutor) wd).executeScript("arguments[0].setAttribute('class', 'cell-heading cell-default')", elementHeader);
        ((JavascriptExecutor) wd).executeScript("arguments[0].setAttribute('class', 'create-child-schedule-group-list cell-default')", elementBody);
      }
    }
  }
}
