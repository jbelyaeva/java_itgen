package app.appmanager;

import static app.appmanager.ApplicationManager.properties;

import data.model.users.StudentData;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LKParentRecordHelper extends HelperBase {

  private final By btnRecordOnTrail = By.xpath("(//button[contains(@id-qa,'trial')])[2]");
  private final By btnLogo = By.xpath("//img[contains(@src,'logo')]");
  private final By btnSignUp = By.xpath("//button[contains(@id-qa,'signup')]");
  private final By selectLesson = By.className("list-of-times");
  private final By editNewStudent_firstName = By.xpath("//input[@id-qa='name']");
  private final By editNewStudent_lastName = By.xpath("//input[@id-qa='surname']");
  private final By editNewStudent_birthday = By.xpath("//input[@id-qa='birthday']");
  private final By editNewStudent_gender = By.xpath("//input[@id-qa='gender']/..");
  private final By editNewStudent_lang = By.xpath("//input[@id-qa='lang']/..");
  private final By editNewStudent_pcLevel = By.xpath("//input[@id-qa='pcLevel']/..");
  private final By btnNextOnForm = By.className("button-next");
  private final By btnAddNewStudent = By.xpath("//a[contains(@href,'addChild')]");
  private final By btnNextBadOnForm = By.className("button-next");
  private final By btnSkipHelper = By.xpath("//button[@title='Skip']");
  private final By btnTomorrowInPicker = By.xpath(
      "//div[@class='picker-item selected']/following-sibling::div");
  private final By btnTodayInPicker = By.xpath("//div[@class='picker-item selected']");
  private final By btnSingleSchedule = By.xpath("(//div[@class='info'])[2]");
  private final By btnRecordOnLesson = By.xpath("//button//span[text()='Записаться на занятие']");
  private final By selectCheckBoxForSignup = By.xpath("//label[contains(@for,'signup')]");
  private final By btnNext = By.xpath("//button[contains(@class,'next')]");
  private final By editLeftFiltrTime = By.xpath("//div[@class='times-filter']//input[1]");
  private final By editRightFiltrTime = By.xpath("(//div[@class='times-filter']//input)[2]");
  private final By emptyAreaFiltrTime = By.xpath("//div[@class='times-filter']");
  private final By btnRecord = By.xpath("//div[contains(@class,'actions')]//button");
  private final By btnShowScheduleChild = By.xpath("//button[@id-qa='show-schedule']");
  private final By selectFirstLessonInRegularForCancel = By.xpath(
      "(//label)[1]"); //+привязка к индексу
  private final By btnCancelInPopup = By.xpath("//div[contains(@class,'buttons-group')]");
  private final By selectLessonInSingleForCancel = By.xpath("//label");
  private final By btnDropdown = By.xpath("//button[@data-toggle='dropdown']");
  private final By btnCancelSchedule = By.xpath("//button[@id-qa='cancel']");
  private final By inputPassword = By.xpath("//input[@autocomplete='new-password']");
  private final By btnSaveInActivation = By.xpath("//button[1]");
  private final By fullAreaInCourseSelectionPage = By.xpath(
      "//div[@class='course-selection-page']");
  private final By btnShowHistorySecondChild = By.xpath("(//button[@id-qa='show-history'])[2]");
  private final By tabSchedule = By.xpath("//button[@id-qa='tab-schedule']");
  private final By btnInstall = By.xpath("//button[@id-qa='install']");
  private final By dropdownSkill = By.xpath("(//div[@class='gena-form-item']//div)[1]");
  private final By dropdownTrainerOnRegular = By.xpath("(//div[@class='gena-form-item'])[6]");
  private final By dropdownTrainerOnSingle = By.xpath("(//div[@class='gena-form-item'])[5]");
  private final By checkboxTrainer = By.xpath("//input[@id='with-trainer']/..");
  private final By trainerNastyaInList = By.xpath("//div[text()='Бокша Настя']");
  private final By btnAssignWorking = By.xpath("//div[contains(@class,'assign')]//button");
  private final By btnAssignInWindowWorkingOff = By.xpath("//button[contains(@class,'assign')]");
  private final By selectLessonForWorkingOff = By.xpath(
      "//input[@name='assign-working-off-group']");
  private final By btnReturnFromWorkingOff = By.xpath("//button[@id-qa='return']");
  private final By labelWithoutStudents = By.xpath("//span[@class='text-info']");

  public LKParentRecordHelper(WebDriver wd) {
    super(wd);
  }

  public By getTrainerNastyaInList() {
    return trainerNastyaInList;
  }

  public By getBtnAssignInWindowWorkingOff() {
    return btnAssignInWindowWorkingOff;
  }

  public By getLabelWithoutStudents() {
    return labelWithoutStudents;
  }

  public void btnRecordOnTrail() {
    if (!isElementPresent(btnRecordOnTrail)) {
      refresh(); // для докера
    }
    clickWithMoveToElementAndWait(5, btnRecordOnTrail);
    noErrorMessage();
  }

  public void recordOnTrail(int idSkill) {
    skipHelper();
    btnLogo();
    btnRecordOnTrail();
    btnSelectSkill(idSkill);
    selectLesson();
    btnSignUp();
  }

  public String goToPageInstall(int idSkill) throws InterruptedException {
    recordOnTrail(idSkill);
    return goToNewWindowAndGoToBack(btnInstall);
  }

  public void btnLogo() {
    click(btnLogo);
    noErrorMessage();
  }

  public void clickByTabSchedule() {
    click(tabSchedule);
    noErrorMessage();
  }

  private void btnSignUp() {
    clickWaitElementToBeClicable(5, btnSignUp);
    noErrorMessage();
  }

  public void selectLesson() {
    click(selectLesson);
    noErrorMessage();
  }

  public void btnSelectSkill(int idSkill) {
    click(By.xpath("//button[@id-qa='btn-" + idSkill + "']"));
    noErrorMessage();
  }

  public void create(StudentData student) {
    btnAddNewStudent();
    fillStudentForm(student);
    btnNextOnForm();
  }

  public void createNewStudent() {
    btnAddNewStudent();
    fillNewStudentForm();
    btnNextOnForm();
  }

  public void createSShotFirstForm(StudentData student) {
    btnAddNewStudent();
    fillStudentForm(student);
  }

  private ExpectedCondition<WebElement> expectVisible(String locator) {
    return ExpectedConditions.visibilityOfElementLocated(By.xpath(locator));
  }

  public void fillStudentForm(StudentData studentData) {
    type(editNewStudent_firstName, studentData.getFirstname());
    type(editNewStudent_lastName, studentData.getLastname());
    if (!DateISOToUsualDataString(studentData.getBirthday()).equals("01.01.2000")) {
      type(editNewStudent_birthday, DateISOToUsualDataString(studentData.getBirthday()));
    }
    WebDriverWait wait = new WebDriverWait(wd, 3);
    click(editNewStudent_gender);
    String gender = "//li[@data-value='" + studentData.getGender() + "']";
    wait.until(this.expectVisible(gender));
    click(By.xpath(gender));

    click(editNewStudent_lang);
    String lang = "//li[@data-value='" + studentData.getStudyLang() + "']";
    click(By.xpath(lang));

    // pclevel может быть пустым, т.к. тест параметризован, в тестовых данных встречается вариант с
    // pclaval=""
    if (!studentData.getPclevel().equals("")) {
      click(editNewStudent_pcLevel);
      String pcLevel = "//li[@data-value='" + studentData.getPclevel() + "']";
      wait.until(this.expectVisible(pcLevel));
      clickWithMoveToElementAndWait(3, By.xpath(pcLevel));
    }
  }

  public void fillNewStudentForm() {
    type(editNewStudent_firstName, "Олег");
    type(editNewStudent_lastName, "Олегов");
    type(editNewStudent_birthday, "01012011");
    click(editNewStudent_gender);
    String gender = "//li[@data-value='1']";
    click(By.xpath(gender));
    click(editNewStudent_lang);
    String lang = "//li[@data-value='ru']";
    click(By.xpath(lang));
    click(editNewStudent_pcLevel);
    String pcLevel = "//li[@data-value='expert']";
    clickWithMoveToElementAndWait(3, By.xpath(pcLevel));
  }

  private void btnNextOnForm() {
    click(btnNextOnForm);
    noErrorMessage();
  }

  private void btnAddNewStudent() {
    if (!isElementPresent(btnAddNewStudent)) {
      btnLogo();
    }
    click(btnAddNewStudent);
    noErrorMessage();
  }

  public void recordOnRegular() {
    skipHelper();
    btnLogo();
    btnShowSchedule();
    btnRecordOnLesson();
    btnTomorrowForRegular();
    changeScrollTime();
    btnNext();
    selectCheckBox();
    btnRecord();
  }

  public void skipHelper() {
    trySearchElementTwoTimesAndClickWithWaiteAndMove(5, btnSkipHelper);
  }

  private void btnTomorrowForRegular() {
    if (checkMatchTZServerUTC()) {
      if (isElementPresent(btnTomorrowInPicker)) {
        click(btnTomorrowInPicker);
      } else {
        click(btnTodayInPicker);
      }
      noErrorMessage();
    }
  }

  public void recordOnSingle() {
    btnShowSchedule();
    btnRecordOnLesson();
    btnSingleSchedule();
    // btnTomorrowForSingle();
    changeScrollTime();
    btnNext();
    selectCheckBox();
    btnRecord();
  }

  private void changeScrollTime() {
    type(editLeftFiltrTime, "00:00");
    type(editRightFiltrTime, "24:00");
    click(emptyAreaFiltrTime); // щелкнуть на пустое место, чтоб обновился скролл
    noErrorMessage();
  }

  private void btnTomorrowForSingle() {//НУЖЕН!!!
    // находим активный элемент и берем следующий сестринский вниз по дереву
    if (checkMatchTZServerUTC()) {
      click(btnTomorrowInPicker);
    }
  }

  public void GoToFiltrRecordSingle() {
    btnShowSchedule();
    btnRecordOnLesson();
    btnSingleSchedule();
    btnTomorrowForSingleSshot();
    changeScrollTime();
  }

  private void btnTomorrowForSingleSshot() {
    // находим активный элемент и берем следующий сестринский вниз по дереву
    click(btnTomorrowInPicker);
  }

  public void btnSingleSchedule() {
    click(btnSingleSchedule);
    noErrorMessage();
  }

  public void btnRecordOnLesson() {
    clickWaitElementToBeClicable(5, btnRecordOnLesson);
    noErrorMessage();
  }

  private void selectCheckBox() {
    click(selectCheckBoxForSignup);
    noErrorMessage();
  }

  private void btnNext() {
    click(btnNext);
    noErrorMessage();
  }

  public void btnRecord() {
    click(btnRecord);
    noErrorMessage();
  }

  public void btnShowSchedule() {
    clickWithMoveToElementAndWait(10, btnShowScheduleChild);
    noErrorMessage();
  }

  public void selfRegistration(StudentData student) throws IOException {
    if (!"".equals(properties.getProperty("selenium.server"))) {
      click(By.xpath("(//div[contains(@class,'locale-switcher')]//button)[1]"));
    }
    fillStudentForm(student);
    btnNextOnForm();
  }

  public void goHref() {
    wd.get(address() + "/registerFromLead?leadId=selfRegistration");
  }

  public void cancelLessonInSingleSchedule() {
    btnShowSchedule();
    btnCancelSchedule();
    btnDropdown();
    clickCheckBox();
    btnDropdown();
    btnCancel();
  }

  public void cancelSingleScheduleAndClickOnWorkingOffNow() {
    btnShowSchedule();
    btnCancelSchedule();
    btnDropdown();
    clickCheckBox();
    btnDropdown();
    btnWorkingOffNow();
  }

  public void cancelSingleScheduleAndClickOnWorkingOffLater() {
    btnShowSchedule();
    btnCancelSchedule();
    btnDropdown();
    clickCheckBox();
    btnDropdown();
    btnWorkingOffLater();
  }

  private void btnWorkingOffLater() {
    click(By.xpath(
        "//button[contains(@class,'btn-cancel-and-working-off')]/following-sibling::button"));
  }

  private void btnWorkingOffNow() {
    click(By.xpath("//button[contains(@class,'btn-cancel-and-working-off')]"));
  }

  public void cancelLessonsInRegularSchedule() {
    btnShowSchedule();
    btnCancelSchedule();
    btnDropdown();
    clickCheckBoxAll();
    btnDropdown();
    btnCancel();
  }

  public void cancelOneLessonInRegularSchedule() {
    btnShowSchedule();
    btnCancelSchedule();
    btnDropdown();
    clickCheckBoxOneLessonInRegular();
    btnDropdown();
    btnCancel();
  }

  private void clickCheckBoxOneLessonInRegular() {
    click(selectFirstLessonInRegularForCancel);
  }

  private void btnCancel() {
    click(btnCancelInPopup);
    noErrorMessage();
  }

  private void clickCheckBox() {
    click(selectLessonInSingleForCancel);
  }

  private void clickCheckBoxAll() {
    for (int i = 1; i < 5; i++) {
      click(By.xpath("(//label)[" + i + "]"));
      noErrorMessage();
    }
  }

  private void btnDropdown() {
    click(btnDropdown);
  }

  private void btnCancelSchedule() {
    click(btnCancelSchedule);
    noErrorMessage();
  }

  public void goHrefActiveLK(String token) {
    wd.get(address() + "/enrollAccount/" + token + "?locale=ru");
    noErrorMessage();
  }

  public void createBad(StudentData student) {
    btnAddNewStudent();
    fillStudentForm(student);
    btnNextBad();
    btnLogo();
  }

  private void btnNextBad() {
    clickWithMoveToElementAndWait(2, btnNextBadOnForm);
    thereAreErrorMessages();
  }

  public void inputPassword(String password) {
    clickWithMoveToElementAndWait(5, inputPassword);
    type(inputPassword, password);
    noErrorMessage();
  }

  public void btnSave() {
    click(btnSaveInActivation);
    noErrorMessage();
  }

  public void activation(String token) {
    goHrefActiveLK(token);
    inputPassword("111111");
    btnSave();
    noErrorMessage();
    refresh();
    logout();
  }

  public void clickByFullArea() {
    click(fullAreaInCourseSelectionPage);
  }

  public void clickByShowHistorySecondChild() {
    click(btnShowHistorySecondChild);
  }

  public void recordOnSingleOnSkill(String skill) {
    btnShowSchedule();
    btnRecordOnLesson();
    btnSingleSchedule();
    // btnTomorrowForSingle();
    selectSkill(skill);
    changeScrollTime();
    btnNext();
    selectCheckBox();
    btnRecord();
    noErrorMessage();
  }

  public void recordOnSingleOnNewSkillWithFailedTest(String skill) {
    btnShowSchedule();
    btnRecordOnLesson();
    btnSingleSchedule();
    // btnTomorrowForSingle();
    selectSkill(skill);
  }

  private void selectSkill(String skill) {
    click(dropdownSkill);
    click(By.xpath("//div[text()='" + skill + "']"));
  }

  public void trainerInListOnRegularRecord() {
    skipHelper();
    btnLogo();
    btnShowSchedule();
    btnRecordOnLesson();
    checkBoxTrainer();
    dropdownTrainerOnRegular();
  }

  private void dropdownTrainerOnRegular() {
    click(dropdownTrainerOnRegular);
  }

  private void dropdownTrainerOnSingle() {
    click(dropdownTrainerOnSingle);
  }

  private void checkBoxTrainer() {
    click(checkboxTrainer);
  }

  public void trainerInListOnSingleRecord() {
    btnSingleSchedule();
    dropdownTrainerOnSingle();
  }

  public void goInWorkingOff() {
    clickByShowHistorySecondChild();
    clickByTabSchedule();
    clickByAssignWorking();
  }

  private void clickByAssignWorking() {
    click(btnAssignWorking);
    noErrorMessage();
  }

  public void selectLessonForWorkingOff() {
    click(selectLessonForWorkingOff);
  }

  public void clickByRecordOnWorkingOff() {
    click(btnAssignInWindowWorkingOff);
    noErrorMessage();
  }

  public void clickByBack() {
    click(btnReturnFromWorkingOff);
  }

  public void recordOnWorkingOff() {
    goInWorkingOff();
    selectLessonForWorkingOff();
    clickByRecordOnWorkingOff();
  }

  public void recordOnSingleWithTestForSkill(String skill) {
    btnShowSchedule();
    btnRecordOnLesson();
    btnSingleSchedule();
    selectSkill(skill);
  }

  public void recordOnSingleOpenSkill() {
    btnShowSchedule();
    btnRecordOnLesson();
    btnSingleSchedule();
    click(dropdownSkill);
  }
}
