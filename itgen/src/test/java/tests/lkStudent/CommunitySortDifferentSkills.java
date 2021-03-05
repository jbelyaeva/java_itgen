package tests.lkStudent;
/* T-300
 * Есть 3 сообщества, сначала создан Питон, потом Скпейч, потом Майнкрафт. Зайти под учеником в сообества
 * и проверить, что сообщества отсортированы от новых к старым*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CommunitySortDifferentSkills extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.clean().communities();
    data.community().set11_CommunityDifferentSkill();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCommunitySortDifferentSkills() {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().btnCommunities();
    String[] etalon = {"Minecraft", "Scratch", "Python"};
    String[] communities = app.lkStudent().getCommunities();
    app.check().equalityOfTwoElements(communities, etalon);
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
  }
}
