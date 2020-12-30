package tests.lkParent;
/*T-30
 * Ребенок добавлен в дефолтную семью. Вчера он прошел пробное по Scratch. В админке
 * направлений указано, что на майнкрафт нужен тест. Тест есть в админке направлений. Ребенок
 * проходит по возрасту на направление. Перейти в запись на разовое и выбрать майнкрафт. Убедиться,
 * что появилась надпись "Для записи на занятие..." и недизейбленная кнопка Продолжить
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecordOnSingleWithTest extends TestBase {
    private final String period = "18:00 - 20:00";

    @BeforeMethod
    public void ensurePreconditions() {
        data.defFamily()
            .set10_LessonYesterdayFinished_SingleLessonTomorrowTrialWithStudent_StudentAddInDefaultFamily(
                period);
        data.skills()
            .set2_MinecraftWithTest(data.skillsService().findBySkillId("21", "ru").getId(), "test");
        data.tests().set1_NewTestForRusMinecraft();
    }

    @Test(retryAnalyzer = RunTestAgain.class)
    public void testRecordOnSingleWithTest() {
        app.lkParent().reset();
        app.lkParentRecord().recordOnSingleWithTestForSkill("Minecraft");
        app.check().textElement(By.xpath("//div[@class='need-pass-the-test']//div"),
            "Для записи на занятия по направлению Minecraft ученику необходимо пройти тест");
        app.check()
            .textElement(By.xpath("//div[@class='need-pass-the-test']//button"), "Продолжить");
        app.check().onNotDisabled(By.xpath("//div[@class='need-pass-the-test']//button"));
        // проверить, что при нажатии на кнопку прйти тест, перекидывает в окно выдачи теста родителем - нет прав!
    }

    @AfterMethod(alwaysRun = true)
    public void clean() {
        data.postClean().student().tests().taskAndSchedule();
    }
}
