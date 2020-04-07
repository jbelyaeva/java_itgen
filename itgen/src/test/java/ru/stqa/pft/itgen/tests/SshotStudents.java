package ru.stqa.pft.itgen.tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class SshotStudents  extends TestBase {
  public WebDriver wd;

  @Test
  public void testSshotStudents() throws AWTException, IOException {

   String expected="./src/test/testsScreenshot/expected/";
   String actual="./src/test/testsScreenshot/actual/";
   String markedImages="./src/test/testsScreenshot/markedImages/";
   String name="students_RU_Chrome";

    app.getNavigationHelper().gotoTasks();
    app.getNavigationHelper().gotoStudents();

   //������ ������ � ������� ����� ���� ������
    Robot bot = new Robot();
    bot.mouseMove(0, 0);
    //��������
    Screenshot actualScreenshot=app.getScreenShert("//body//th[1]");//����� �������� ����� ��������� �������� � ���������
    //���������
 //   File expectedFile = new File(expected+name+".png"); //����������� ����� �������� ���������� ������
  //  ImageIO.write(actualScreenshot.getImage(), "png", expectedFile);//����������� ����� �������� ���������� ������
    File actualFile = new File(actual+name+".png");
    ImageIO.write(actualScreenshot.getImage(), "png", actualFile);

    //����� ��������� ������
    Screenshot expectedScreenshot = new Screenshot(ImageIO.read(new File(expected+name+".png")));

    //����������
    ImageDiff diff = new ImageDiffer().makeDiff(expectedScreenshot, actualScreenshot);

    //���������
    int rez=diff.getDiffSize();
    if (rez!=0){
      File diffFile = new File(markedImages+name+".png");
      ImageIO.write(diff.getMarkedImage(), "png", diffFile);
    }
    Assert.assertEquals(diff.getDiffSize(), 0);

    //������� ��������� � Allure

    /* AllureAttachments.attachScreen(expectedFile.getAbsolutePath(), "Expected: "+name);
    AllureAttachments.attachScreen(actualFile.getAbsolutePath(), "Actual: "+name);
    AllureAttachments.attachScreen(diffFile.getAbsolutePath(), "Differ: "+name);
*/
    }
}
