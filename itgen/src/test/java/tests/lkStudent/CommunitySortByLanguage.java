package tests.lkStudent;
/* T-301
 * Есть 3 сообщества, c русским, анг
 * Зайти под учеником в сообщества
 * и проверить, фильтр с языком
 * 1. По дефолту отображаются направления на языке ученика
 * 2. выбрать все
 * 3. выбрать рус
 * 4. выбрать анг*/

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CommunitySortByLanguage extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.clean().communities();
    data.community().set12_CommunityDifferentLang();
    data.defFamily().set21_DefaultStudent_finishLessonBy1Skil();
    app.base().refresh();
    app.base().goByHref(app.base().address() + "/login");
    app.lkStudent()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCommunitySortByLanguage() throws InterruptedException {
    app.lkStudent().btnCloseTutorial();
    app.lkStudent().btnCommunities();
    String[] etalon = {"Python", null, null};
    String[] communities = app.lkStudent().getCommunities();
    app.check().equalityOfTwoElements(communities, etalon);

    app.lkStudent().selectAllLanguage();
    String[] etalonFirst = {"Scratch", "Minecraft", "Python"};
    String[] communitiesFirst = app.lkStudent().getCommunities();
    app.check().equalityOfTwoElements(communitiesFirst, etalonFirst);

    app.lkStudent().selectRusLanguage();
    String[] etalonSecond = {"Python", null, null};
    Thread.sleep(2000);
    String[] communitiesSecond = app.lkStudent().getCommunities();
    app.check().equalityOfTwoElements(communitiesSecond, etalonSecond);

    app.lkStudent().selectEnLanguage();
    String[] etalonThird = {"Scratch", null, null};
    String[] communitiesThird = app.lkStudent().getCommunities();
    app.check().equalityOfTwoElements(communitiesThird, etalonThird);
    app.lkStudent().goToFeed();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().communities();
  }
}
