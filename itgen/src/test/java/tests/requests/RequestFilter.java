package tests.requests;
/* Т-326
 * есть заявки:
 * 1. постоянка, Scratch, 2ч
 * 2. разовое Python 2ч , гендер 2
 * 3. разовое Python 1ч , гендер 1
 * 4. Пробное агл Scratch 2ч
 * 5. Разовое на Скретч 2ч к Дефолтному тренеру
 * Отфильтровать:
 * направление скретч
 * направление Все
 * язык обучения русский
 * язык обучения все
 * тренера, на которого оставлена заявка на скретч - отображается только заявка по сертч
 * в Тренер выбрать все - отображаются все заявки
 * В Тип занятия выбрать разовое
 * В Тип занятиявыбрать все - отображаются все
 * В Длительность выбрать 2ч
 * В Длительность выбрать все - отображаются все
 * В Пол тренера выбрать мужской пол
 * В Пол тренера выбрать все - отображаются все */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestFilter extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.studentService().updateField("student", "username", "user");
    data.newFamily().set2_EngFamilyWithStudentAndParent();
    data.requests().set3_requestOnScratch2hRegular();
    data.requests().set7_requestOnPython2hSingle(2);
    data.requests().set8_requestOnPython1hSingle(1);
    data.requests().set9_requestOnScratch1hTrial();
    data.requests().set6_requestOnScratch2hSingle("23", "newStudent");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestFilterSkillScratch() throws InterruptedException {
    app.goTo().menuRequests();
    app.base().refresh();
    app.request().btnFilterClose();
    app.request().btnDropdownSkill();
    app.request().selectScratch();
    app.request().btnAcceptInFilter();
    Thread.sleep(3000);
    String[] etalonStudent = new String[]{"Машина Маша", "Олегов Олег", "Машина Маша"};
    String[] etalonTypeLesson = new String[]{"Пробное", "Постоянное", "Разовое"};
    app.check().equalityOfTwoElements(etalonStudent, app.request().getListStudents());
    app.check().equalityOfTwoElements(etalonTypeLesson, app.request().getListTypeLesson());
    app.request().btnFilterClose();
    app.request().btnDropdownSkill();
    app.request().selectScratch();
    app.request().btnAcceptInFilter();
    etalonStudent = new String[]{"Машина Маша", "Олегов Олег", "Машина Маша", "Олегов Олег",
        "Олегов Олег"};
    etalonTypeLesson = new String[]{"Пробное", "Постоянное", "Разовое", "Разовое", "Разовое"};
    app.check().equalityOfTwoElements(etalonStudent, app.request().getListStudents());
    app.check().equalityOfTwoElements(etalonTypeLesson, app.request().getListTypeLesson());
    app.goTo().menuTasks();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestFilterLang() throws InterruptedException {
    app.goTo().menuRequests();
    app.base().refresh();
    app.request().btnFilterClose();
    app.request().btnDropdownLang();
    app.request().selectEng();
    app.request().btnAcceptInFilter();
    Thread.sleep(3000);
    String[] etalonStudent = new String[]{"Машина Маша", "Машина Маша",};
    String[] etalonTypeLesson = new String[]{"Пробное", "Разовое"};
    app.check().equalityOfTwoElements(etalonStudent, app.request().getListStudents());
    app.check().equalityOfTwoElements(etalonTypeLesson, app.request().getListTypeLesson());
    app.request().btnFilterClose();
    app.request().btnDropdownLang();
    app.request().selectAllLang();
    app.request().btnAcceptInFilter();
    etalonStudent = new String[]{"Машина Маша", "Олегов Олег", "Машина Маша", "Олегов Олег",
        "Олегов Олег"};
    etalonTypeLesson = new String[]{"Пробное", "Постоянное", "Разовое", "Разовое", "Разовое"};
    app.check().equalityOfTwoElements(etalonStudent, app.request().getListStudents());
    app.check().equalityOfTwoElements(etalonTypeLesson, app.request().getListTypeLesson());
    app.goTo().menuTasks();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestFilterTrainer() throws InterruptedException {
    app.goTo().menuRequests();
    app.base().refresh();
    app.request().btnFilterClose();
    app.request().btnDropdownTrainer();
    app.request().selectTrainer("23");
    app.request().btnAcceptInFilter();
    Thread.sleep(3000);
    String[] etalonStudent = new String[]{"Машина Маша"};
    String[] etalonTypeLesson = new String[]{"Разовое"};
    app.check().equalityOfTwoElements(etalonStudent, app.request().getListStudents());
    app.check().equalityOfTwoElements(etalonTypeLesson, app.request().getListTypeLesson());
    app.request().btnFilterClose();
    app.request().btnDropdownTrainer();
    app.request().selectAllTrainers();
    app.request().btnAcceptInFilter();
    etalonStudent = new String[]{"Машина Маша", "Олегов Олег", "Машина Маша", "Олегов Олег",
        "Олегов Олег"};
    etalonTypeLesson = new String[]{"Пробное", "Постоянное", "Разовое", "Разовое", "Разовое"};
    app.check().equalityOfTwoElements(etalonStudent, app.request().getListStudents());
    app.check().equalityOfTwoElements(etalonTypeLesson, app.request().getListTypeLesson());
    app.goTo().menuTasks();
  }

  @Test(retryAnalyzer = RunTestAgain.class, enabled = false)
  public void testRequestFilterTypeLesson() throws InterruptedException {
    app.goTo().menuRequests();
    app.base().refresh();
    app.request().btnFilterClose();
    app.request().btnDropdownTypeLesson();
    app.request().selectTrial();
    app.request().btnAcceptInFilter();
    Thread.sleep(3000);
    String[] etalonStudent = new String[]{"Машина Маша"};
    String[] etalonTypeLesson = new String[]{"Пробное"};
    app.check().equalityOfTwoElements(etalonStudent, app.request().getListStudents());
    app.check().equalityOfTwoElements(etalonTypeLesson, app.request().getListTypeLesson());
    app.request().btnFilterClose();
    app.request().btnDropdownTrainer();
    app.request().selectAllTypes();
    app.request().btnAcceptInFilter();
    etalonStudent = new String[]{"Машина Маша", "Олегов Олег", "Машина Маша", "Олегов Олег",
        "Олегов Олег"};
    etalonTypeLesson = new String[]{"Пробное", "Постоянное", "Разовое", "Разовое", "Разовое"};
    app.check().equalityOfTwoElements(etalonStudent, app.request().getListStudents());
    app.check().equalityOfTwoElements(etalonTypeLesson, app.request().getListTypeLesson());
    app.goTo().menuTasks();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestFilterTypeDuration() throws InterruptedException {
    app.goTo().menuRequests();
    app.base().refresh();
    app.request().btnFilterClose();
    app.request().btnDropdownDuration();
    app.request().select1h();
    app.request().btnAcceptInFilter();
    String[] etalonStudent = new String[]{"Олегов Олег"};
    String[] etalonTypeLesson = new String[]{"Разовое"};
    app.check().equalityOfTwoElements(etalonStudent, app.request().getListStudents());
    app.check().equalityOfTwoElements(etalonTypeLesson, app.request().getListTypeLesson());
    app.request().btnFilterClose();
    app.request().btnDropdownDuration();
    app.request().selectAllDuration();
    app.request().btnAcceptInFilter();
    etalonStudent = new String[]{"Машина Маша", "Олегов Олег", "Машина Маша", "Олегов Олег",
        "Олегов Олег"};
    etalonTypeLesson = new String[]{"Пробное", "Постоянное", "Разовое", "Разовое", "Разовое"};
    app.check().equalityOfTwoElements(etalonStudent, app.request().getListStudents());
    app.check().equalityOfTwoElements(etalonTypeLesson, app.request().getListTypeLesson());
    app.goTo().menuTasks();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestFilterGender() throws InterruptedException {
    app.goTo().menuRequests();
    app.base().refresh();
    app.request().btnFilterClose();
    app.request().btnDropdownGender();
    app.request().selectMale();
    app.request().btnAcceptInFilter();
    app.base().waitElementWithText(4, By.xpath("//p[@class='text']"), "Найдено: 1");
    String[] etalonStudent = new String[]{"Олегов Олег"};
    String[] etalonTypeLesson = new String[]{"Разовое"};
    app.check().equalityOfTwoElements(etalonStudent, app.request().getListStudents());
    app.check().equalityOfTwoElements(etalonTypeLesson, app.request().getListTypeLesson());
    app.request().btnFilterClose();
    app.request().btnDropdownGender();
    app.request().selectAllGenders();
    app.request().btnAcceptInFilter();
    etalonStudent = new String[]{"Машина Маша", "Олегов Олег", "Машина Маша", "Олегов Олег",
        "Олегов Олег"};
    etalonTypeLesson = new String[]{"Пробное", "Постоянное", "Разовое", "Разовое", "Разовое"};
    app.check().equalityOfTwoElements(etalonStudent, app.request().getListStudents());
    app.check().equalityOfTwoElements(etalonTypeLesson, app.request().getListTypeLesson());
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student().family().parent().requests();
    data.studentService().updateField("student", "username", "userName");
  }
}
