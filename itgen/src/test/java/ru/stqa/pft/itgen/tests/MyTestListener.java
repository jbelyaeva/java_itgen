package ru.stqa.pft.itgen.tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.stqa.pft.itgen.appmanager.ApplicationManager;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyTestListener implements ITestListener {
  String resourceName="";
  @Override
  public void onTestStart(ITestResult result) {

  }

  @Override
  public void onTestSuccess(ITestResult result) {

  }

  @Override
  public void onTestFailure(ITestResult result) {
    ApplicationManager app = (ApplicationManager) result.getTestContext().getAttribute("app");
    saveScreenshot(app.takeScreenshot());
    try {
      getScreenShot();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
 // }

  @Attachment(value = "Page screenshot", type = "image/png")
  public byte[] saveScreenshot(byte[] screenShot) {
    return screenShot;
  }

  //прикладывание аттача для скриншот тестирования
 @Attachment(value = "Page screenshot", type = "image/png")
  public static byte[] getScreenShot () throws IOException {
  return Files.readAllBytes(Paths.get("C:/Devel/Projects/java_itgen/itgen/src/test/testsScreenshot/markedImages/students_RU_Chrome.png"));
  }

  @Override
  public void onTestSkipped(ITestResult result) {

  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

  }

  @Override
  public void onTestFailedWithTimeout(ITestResult result) {

  }

  @Override
  public void onStart(ITestContext context) {

  }

  @Override
  public void onFinish(ITestContext context) {

  }
}
