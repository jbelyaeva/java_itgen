package tests.skills;
/*T-133
 * В админке в форме создания нового направления оба формата указаны по дефолту
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class SkillVisibleCreationFormWithBothFormat extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSkillVisibleCreationFormWithBothFormat() {
    app.goTo().menuSkills();
    app.skill().goToFormWithCrateSkill();
    String text = app.skill().getVisualFormat();
    app.goTo().menuSchedule();

    app.check().equalityOfTwoElements(text, "Индивидуальный, Индивидуально-групповой");
  }
}
