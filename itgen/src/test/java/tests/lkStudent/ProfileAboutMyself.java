package tests.lkStudent;
/* T-279
Кейс: открыть профайл через быстрые переходы, заполнить информацию о себе. прверить ui  и бд */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class ProfileAboutMyself extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testProfileAboutMyself() {
    String text = "Я увлекаюсь играми";
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().goInProfile();
    app.lkStudent().editInformationAboutMyself(text);
    app.check().textElement(app.lkStudent().getEditInformationAboutMyself(), text);
    app.check().equalityOfTwoElements(data.studentService().findById("21").getAbout(), text);
  }
}
