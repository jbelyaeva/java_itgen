package tests.skills;
/*T-134
 * Создать в админке направление новое направление с обоими форматами oбучения и проверить по бд
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.skills.SkillsData;
import data.provides.LocaleUtilsTestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SkillCreationWithIF extends TestBase {

  private SkillsData skillNew = null;

  @Test(dataProvider = "validDataCreateSkillFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testSkillCreationWithIF(SkillsData skill) {
    app.goTo().menuSkills();
    app.skill().goToFormWithCrateSkill();
    app.skill().fillFormSkillWithIF(skill);
    app.skill().btnCreateSkill();
    skillNew = app.db().lastSkill();
    //проверка, что в бд у нового направления проставлены оба формата
    app.check()
        .equalityOfTwoElements(skillNew.getLessonsFormats().toArray(new Integer[0]),
            new Integer[]{1, 0});
    app.goTo().menuSchedule();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().skill(skillNew.getId());
  }
}
