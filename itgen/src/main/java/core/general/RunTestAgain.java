package core.general;

import app.testbase.TestBase;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RunTestAgain extends TestBase implements IRetryAnalyzer {

  private int nowCount = 0;
  private final int maxCount = 1;

  @Override
  public boolean retry(ITestResult iTestResult) {
    if (!iTestResult.isSuccess()) {
      if (nowCount < maxCount) {
        nowCount++;
        iTestResult.setStatus(ITestResult.SUCCESS);
        app.base().refresh();
        return true; // пока истина перезапускаем
      }
    } else {
      iTestResult.setStatus(ITestResult.FAILURE);
    }
    System.out.println("ТЕСТ ПРОВАЛЕН ДВАЖДЫ!!! ");
    nowCount = 0;
    return false;
  }
}
// (retryAnalyzer = RunTestAgain.class)
