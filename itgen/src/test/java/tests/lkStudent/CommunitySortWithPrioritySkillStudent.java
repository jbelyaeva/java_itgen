package tests.lkStudent;
/* T-462
 * Есть 3 сообщества, сначала создан Питон, потом Скпейч, потом Майнкрафт.
 * Дефолтный ученик прошел пробное по Скрейч
 * Зайти под учеником в сообщества
 * и проверить, что сообщества отсортированы от новых к старым, но Скрейч будет первым*/

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CommunitySortWithPrioritySkillStudent extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.clean().communities();
    data.community().set11_CommunityDifferentSkill();
    data.defFamily().set21_DefaultStudent_finishLessonBy1Skil();
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCommunitySortWithPrioritySkillStudent() {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().btnCommunities();
    String[] etalon = {"Scratch", "Minecraft", "Python"};
    String[] communities = app.lkStudent().getCommunities();
    app.check().equalityOfTwoElements(communities, etalon);
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
  }
}
