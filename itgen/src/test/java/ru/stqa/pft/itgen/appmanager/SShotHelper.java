package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static ru.stqa.pft.itgen.tests.TestBase.etalon;

public class SShotHelper extends HelperBase {

  public SShotHelper(WebDriver wd) {
    super(wd);
  }

  public ImageDiff getImageDiff(String expected, String actual, String markedImages, String name, String locatorIgnor) throws AWTException, IOException {
    Robot bot = new Robot();
    bot.mouseMove(0, 0);
    //получаем
    if (locatorIgnor != "") {
      List<WebElement> elementsList = wd.findElements(By.xpath(locatorIgnor));
      for (WebElement element : elementsList) {
        ((JavascriptExecutor) wd)
                .executeScript("arguments[0].remove();", element);
      }
    }
    Screenshot actualScreenshot = new AShot().takeScreenshot(wd);
    ;//взять скрkиншот после появления элемента с локатором
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
    String Path = "./src/test/testsScreenshot/markedImages/" + ResourseName + ".png";
    byte[] file = Files.readAllBytes(Paths.get(Path));
    return file;
  }

}
