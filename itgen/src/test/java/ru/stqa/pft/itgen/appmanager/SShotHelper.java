package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.pft.itgen.model.WorkerData;
import ru.stqa.pft.itgen.services.WorkerService;
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
import java.util.ArrayList;
import java.util.List;

import static ru.stqa.pft.itgen.tests.TestBase.etalon;

public class SShotHelper extends HelperBase {

  public SShotHelper(WebDriver wd) {
    super(wd);
  }

  public ImageDiff getImageDiff(String expected, String actual, String markedImages, String name, String lokatorFlag) throws AWTException, IOException {
    Robot bot = new Robot();
    bot.mouseMove(0, 0);
    //получаем
    Screenshot actualScreenshot = getScreenShert(lokatorFlag);//взять скрkиншот после появления элемента с локатором
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
    String Path = "C:/Devel/Projects/java_itgen/itgen/src/test/testsScreenshot/markedImages/" + ResourseName + ".png";
    byte[] file = Files.readAllBytes(Paths.get(Path));
    return file;
  }

  public Screenshot getScreenShert(String locator) {
    Screenshot actualScreenshot;
    // return actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(wd);
    WebDriverWait wait = new WebDriverWait(wd, 5);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    return new AShot().addIgnoredElement(By.xpath("//tbody")).takeScreenshot(wd);
  }
}
