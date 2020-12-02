package app.appmanager;

import static app.appmanager.ApplicationManager.properties;

import data.model.users.StudentData;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LKParentHelper extends HelperBase {

  private final By btnRecordOnTrail = By.xpath("(//button[contains(@id-qa,'trial')])[2]");
  private final By btnLogo = By.xpath("//img[contains(@src,'logo')]");
  private final By btnSignUp = By.xpath("//button[contains(@id-qa,'signup')]");
  private final By selectLesson = By.className("list-of-times");
  private final By btnSelectScratch = By.xpath("//button[@id-qa='btn-1']");
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
  private final By selectedIcon = By.xpath("//span[@class='selected-icon']");
  private final By btnSingleSchedule = By.xpath("(//div[@class='info'])[2]");
  private final By btnRecordOnLesson = By.xpath("//div[@class='buttons']");
  private final By selectCheckBoxForSignup = By.xpath("//label[contains(@for,'signup')]");
  private final By btnNext = By.xpath("//button[contains(@class,'next')]");
  private final By editLeftFiltrTime = By.xpath("//div[@class='times-filter']//input[1]");
  private final By editRightFiltrTime = By.xpath("(//div[@class='times-filter']//input)[2]");
  private final By emptyAreaFiltrTime = By.xpath("//div[@class='times-filter']");
  private final By btnRecord = By.xpath("//div[contains(@class,'actions')]//button");
  private final By btnShowScheduleAlongChild = By.xpath("//button[@id-qa='show-schedule']");
  private final By selectFirstLessonInRegularForCancel = By.xpath(
      "(//label)[1]"); //+привязка к индексу
  private final By btnCancelInPopup = By.xpath("//div[contains(@class,'buttons-group')]");
  private final By selectLessonInSingleForCancel = By.xpath("//label");
  private final By btnDropdown = By.xpath("//button[@data-toggle='dropdown']");
  private final By btnCancelSchedule = By.xpath("//button[@id-qa='cancel']");
  private final By btnClickHistory = By.xpath("//div[contains(@class,'btn-toggle')]");
  private final By inputPassword = By.xpath("//input[@autocomplete='new-password']");
  private final By btnSaveInActivation = By.xpath("//button[1]");
  private final By fullAreaInCourseSelectionPage = By.xpath(
      "//div[@class='course-selection-page']");
  private final By btnPrepare = By.xpath("//button[@id-qa='prepare']");
  private final By btnSchedule = By.xpath("//div[@class='child-schedule-btn']");
  private final By btnShowHistorySecondChild = By.xpath("(//button[@id-qa='show-history'])[2]");
  private final By btnMainHelpCenter = By.xpath("//div[@class='help-center-btn']");
  private final By btnHelpCenterInMenu = By.xpath("//a[contains(@href,'itgen.io')]");
  private final By btnShowScheduleSecondChild = By.xpath(
      "(//div[@class='child-management'])[2]//button[@id-qa='show-schedule']");
  private final By btnTrialSecondChild = By.xpath("//button[@id-qa='trial'])[2]");
  private final By tabSchedule = By.xpath("//button[@id-qa='tab-schedule']");
  private final By labelSchedulePeriodLesson = By.xpath("//div[@class='lesson-content-item'][1]");
  private final By labelScheduleTrainerOnLesson = By.xpath(
      "//div[@class='lesson-content-item'][2]");
  private final By labelScheduleSkillOnLesson = By.xpath("(//div[@class='flex-content']//span)[1]");
  private final By scheduleLabelNewOnLesson = By.xpath(
      "(//div[@class='lesson-label new-skill'])[1]");
  private final By scheduleLabelPlannedOnLesson = By.xpath("//div[@class='lesson-label planned']");
  private final By tabSettings = By.xpath("//button[@id-qa='tab-settings']");
  private final By labelPersonalInformation = By.xpath("//div[@class='info']//h4");
  private final By labelLoginAndPassword = By.xpath("//div[@class='login-and-password']//h4");
  private final By labelHowToJoinInstruction = By.xpath(
      "//div[@class='how-to-join-instruction']//span");
  private final By labelFreeLessonsHeader = By.xpath("//div[@class='freeLessons-header']//h4");
  private final By btnShareHref = By.xpath("//div[@class='share-copyButton']");
  private final By btnFreeLessons = By.xpath("//div[@class='family-free-lessons']//button");
  private final By sectionSocialNetworks = By.xpath(" //div[@class='shareButtons']");
  private final By tabHistory = By.xpath("//button[@id-qa='tab-history']");
  private final By labelPeriodLessonInHistory = By.xpath("//div[@class='time']");
  private final By labelDayAndMonthLessonInHistory = By.xpath("//span[@class='day-of-month']");
  private final By labelProjectDoneInHistory = By.xpath(
      "//div[@class='material-project done']//span");
  private final By labelProjectNotStartedInHistory = By.xpath(
      "//div[@class='material-project not-started']//span");
  private final By labelGradeAboutLessonInHistory = By.xpath("//ul[@class='grade-texts']");
  private final By labelWaiteOnLessons = By.xpath("//h4");
  private final By labelRecomendationForFirstLesson = By.xpath("//div[@class='title']//span");
  private final By iconCalendar = By.xpath(
      "//a[contains(@href,'/newStudent')]//div[@class='child-schedule-btn']");
  private final By labelFutureLessonOnGeneralPage = By.xpath(
      "//span[@class='lesson-info gena-text-muted']");
  private final By btnHistoryBallans = By.xpath("//div[@class='gena-panel-btn']");
  private final By btnBlockHistoryBallans = By.xpath("//tbody");
  private final By btnInfoAboutReasonBallans = By.xpath("//tbody//td[2]");
  private final By btnTutorialByLkParent = By.xpath("//li[@id-qa='parent-cabinet']");
  private final By winTutorialByLkParent = By.xpath("//div[@class='tooltip-with-itgenik']");
  private final By btnTutorialBySwitchToStudent = By.xpath("//li[@id-qa='child-cabinet']");
  private final By winTutorialBySwitchToStudent = By.xpath(
      "//div[@class='base-tooltip-tutorials']");
  private final By btnFacebook = By.xpath("//button[@aria-label='facebook']");

  private ApplicationManager app;

  public LKParentHelper(WebDriver wd) {
    super(wd);
  }

  public By showHistorySecondChild() {
    return btnShowHistorySecondChild;
  }

  public By showScheduleSecondChild() {
    return btnShowScheduleSecondChild;
  }

  public By btnTrialSecondChild() {
    return btnTrialSecondChild;
  }

  public By tabSchedule() {
    return tabSchedule;
  }

  public By schedulePeriodLesson() {
    return labelSchedulePeriodLesson;
  }

  public By scheduleTrainerOnLesson() {
    return labelScheduleTrainerOnLesson;
  }

  public By scheduleSkillOnLesson() {
    return labelScheduleSkillOnLesson;
  }

  public By scheduleLabelNewOnLesson() {
    return scheduleLabelNewOnLesson;
  }

  public By scheduleLabelPlannedOnLesson() {
    return scheduleLabelPlannedOnLesson;
  }

  public By labelPersonalInformation() {
    return labelPersonalInformation;
  }

  public By tabSettings() {
    return tabSettings;
  }

  public By labelLoginAndPassword() {
    return labelLoginAndPassword;
  }

  public By labelHowToJoinInstruction() {
    return labelHowToJoinInstruction;
  }

  public By labelFreeLessonsHeader() {
    return labelFreeLessonsHeader;
  }

  public By buttonShareHref() {
    return btnShareHref;
  }

  public By sectionSocialNetworks() {
    return sectionSocialNetworks;
  }

  public By tabHistory() {
    return tabHistory;
  }

  public By labelPeriodLessonInHistory() {
    return labelPeriodLessonInHistory;
  }

  public By labelDayAndMonthLessonInHistory() {
    return labelDayAndMonthLessonInHistory;
  }

  public By labelProjectDoneInHistory() {
    return labelProjectDoneInHistory;
  }

  public By labelProjectNotStartedInHistory() {
    return labelProjectNotStartedInHistory;
  }

  public By labelGradeAboutLessonInHistory() {
    return labelGradeAboutLessonInHistory;
  }

  public By labelWaiteOnLessons() {
    return labelWaiteOnLessons;
  }

  public By labelRecomendationForFirstLesson() {
    return labelRecomendationForFirstLesson;
  }

  public By iconCalendar() {
    return iconCalendar;
  }

  public By labelFutureLessonOnGeneralPage() {
    return labelFutureLessonOnGeneralPage;
  }

  public By btnBlockHistoryBallans() {
    return btnBlockHistoryBallans;
  }

  public By btnInfoAboutReasonBallans() {
    return btnInfoAboutReasonBallans;
  }

  public By winTutorialByLkParent() {
    return winTutorialByLkParent;
  }

  public By winTutorialBySwitchToStudent() {
    return winTutorialBySwitchToStudent;
  }

  public By btnFreeLessons() {
    return btnFreeLessons;
  }

  public void btnRecordOnTrail() {
    if (!isElementPresent(btnRecordOnTrail)) {
      refresh(); // для докера
    }
    clickWithMoveToElementAndWait(5, btnRecordOnTrail);
    noErrorMessage();
  }

  public void RecordOnTrail() {
    skipHelper();
    btnLogo();
    btnRecordOnTrail();
    btnSelectScratch();
    selectLesson();
    btnSignUp();
  }

  public void btnLogo() {
    click(btnLogo);
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

  public void btnSelectScratch() {
    click(btnSelectScratch);
    noErrorMessage();
  }

  public void create(StudentData student) {
    btnAddNewStudent();
    fillStudentForm(student);
    btnNextOnForm();
  }

  public void createSShotFirstForm(StudentData student) {
    btnAddNewStudent();
    fillStudentForm(student);
  }

  public void createSShotSecondForm(StudentData student) {
    btnAddNewStudent();
    fillStudentForm(student);
    btnNextOnForm();
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
    btnLogo();
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

  private void btnTomorrowForSingle() {
    // находим активный элемент и берем следующий сестринский вниз по дереву
    if (checkMatchTZServerUTC()) {
      click(btnTomorrowInPicker);
    }
  }

  private void btnTomorrowForSingleSshot() {
    // находим активный элемент и берем следующий сестринский вниз по дереву
    click(btnTomorrowInPicker);
  }

  public void GoToFiltrRecordSingle() {
    btnShowSchedule();
    btnRecordOnLesson();
    btnSingleSchedule();
    btnTomorrowForSingleSshot();
    changeScrollTime();
  }

  public void changeStyleDayOfTheWeek() {
    WebElement elementPicker = wd.findElement(btnTodayInPicker);
    ((JavascriptExecutor) wd)
        .executeScript("arguments[0].setAttribute('class', 'picker-item')", elementPicker);
    WebElement elementSelectedIcon = wd.findElement(selectedIcon);
    ((JavascriptExecutor) wd).executeScript("arguments[0].remove();", elementSelectedIcon);
  }

  private void changeWeeksPaginator() {
    for (int i = 1; i < 8; i++) {
      if (isElementPresent(By.xpath("//div[contains(@class,'picker-item')][" + i + "]"))) {
        WebElement elementDayWeeks =
            wd.findElement(
                By.xpath("//div[contains(@class,'picker-item')][" + i + "]//div//span[1]"));
        WebElement elementData =
            wd.findElement(
                By.xpath("//div[contains(@class,'picker-item')][" + i + "]//div//span[2]"));
        ((JavascriptExecutor) wd).executeScript("arguments[0].remove();", elementDayWeeks);
        ((JavascriptExecutor) wd).executeScript("arguments[0].remove();", elementData);
      }
    }
  }

  public void btnSingleSchedule() {
    click(btnSingleSchedule);
    noErrorMessage();
  }

  public void confirmRecordOnRegular() {
    btnShowSchedule();
    btnRecordOnLesson();
    btnTomorrowForRegular();
    changeScrollTime();
    btnNext();
    selectCheckBox();
    changeStyleDayOfTheWeek();
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

  private void changeScrollTime() {
    type(editLeftFiltrTime, "00:00");
    type(editRightFiltrTime, "24:00");
    click(emptyAreaFiltrTime); // щелкнуть на пустое место, чтоб обновился скролл
    noErrorMessage();
  }

  public void btnRecord() {
    click(btnRecord);
    noErrorMessage();
  }

  public void btnShowSchedule() {
    clickWithMoveToElementAndWait(10, btnShowScheduleAlongChild);
    noErrorMessage();
  }

  public void selfRegistration(StudentData student) {
    fillStudentForm(student);
    btnNextOnForm();
  }

  public void goHref() {
    wd.get("localhost:3000/registerFromLead?leadId=selfRegistration");
  }

  public void GoToFiltrRecordRegular() {
    btnShowSchedule();
    btnRecordOnLesson();
    btnTomorrowForRegular();
    changeScrollTime();
    changeStyleDayOfTheWeek();
  }

  public void cancelLessonInSingleSchedule() {
    btnShowSchedule();
    btnCancelSchedule();
    btnDropdown();
    clickCheckBox();
    btnDropdown();
    btnCancel();
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

  public void btnClickHistory() {
    click(btnClickHistory);
    noErrorMessage();
  }

  public void goHrefActiveLK(String token) {
    wd.get("http://localhost:3000/enrollAccount/" + token + "?locale=ru");
    noErrorMessage();
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

  public void btnPrepare() {
    click(btnPrepare);
  }

  public void reset() {
    btnLogo();
    refresh();
    click(btnFreeLessons);
    btnLogo();
  }

  public void btnSchedule() {
    click(btnSchedule);
  }

  public void clickByNameStudent(String idStudent) {
    click(By.xpath("//a[contains(@href,'" + idStudent + "')][1]"));
  }

  public void clickByShowHistory() {
    click(btnShowHistorySecondChild);
  }

  public void clickByFreeLesson() {
    click(btnFreeLessons);
  }

  public String goToHelpCenter() throws InterruptedException {
    click(btnMainHelpCenter);
    return goToNewWindowAndGoToBack(btnHelpCenterInMenu);
  }

  public void goToBallansHistory() {
    click(btnHistoryBallans);
    noErrorMessage();
  }

  public void goToTutorialByLkParent() {
    click(btnMainHelpCenter);
    click(btnTutorialByLkParent);
  }

  public void goToTutorialBySwitchToStudent() {
    click(btnMainHelpCenter);
    click(btnTutorialBySwitchToStudent);
  }

  public String getRefCode()
      throws IOException, UnsupportedFlavorException {
    click(btnFreeLessons);
    click(btnShareHref);
    if (!"".equals(properties.getProperty("selenium.server"))) {
   /*   clickWaitElementToBeClicable(5, By.xpath("//div[@class='right']//div[@class='chat-button']"));
      deleteAlerts();
      clickWaitElementToBeClicable(5, By.xpath("//div[@class='avatar']"));
      clickWaitElementToBeClicable(5,
          By.xpath("//div[contains(@class,'chat-message')]//textarea[1]"));*/
      /*  wd.findElement(By.xpath("//div[contains(@class,'chat-message')]//textarea[1]")).click();
//          .sendKeys(Keys.CONTROL + "v");

      // в убунту в терминале вставить из буфера - это комбинация: control + shift + v,
      // может быть в этом дело

      // вариант 1. Его надо попробывать*/
      //   Actions a = new Actions(wd);
      //   a.sendKeys(Keys.chord(Keys.CONTROL, "v")).build().perform();

      // вариант 2. Более дубовый но он у нас работал на маке
      //  a.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("v").build().perform();
      //  a.keyUp(Keys.CONTROL).keyUp(Keys.SHIFT).build().perform();

      /**
       но самое ЗБС это как в доке на селеноид написно:

       1. Начните новый сеанс, например, с ID f2bcd32b-d932-4cdc-a639-687ab8e4f840.

       Чтобы получить значение буфера обмена, отправьте следующий HTTP-запрос:

       $ curl http://selenoid-host.example.com:4444/clipboard/f2bcd32b-d932-4cdc-a639-687ab8e4f840
       some-clipboard-value

       (some-clipboard-value -- это не команда, это переводится "какое-то значение буфера обмена")

       2. Чтобы обновить значение буфера обмена:

       $ curl -X POST --data 'some-clipboard-value' http://selenoid-host.example.com:4444/clipboard/f2bcd32b-d932-4cdc-a639-687ab8e4f840

       ну а дальше все сделать через твой суперметод который ниже...
       */

      //  clickWaitElementToBeClicable(5,
      //      By.xpath("//div[contains(@class,'chat-message')]//button[2]"));

      String id = String.valueOf(((RemoteWebDriver) wd).getSessionId());
      String clipboard = "";
      Runtime.getRuntime()
          .exec("curl -X POST --data 'clipboard' http://135.181.63.111:4444/clipboard/" + id);
      //wd.findElement(By.xpath("//div[@class='message multiline-text']")).getText();*/
      return clipboard;
    } else {
      return (String) Toolkit.getDefaultToolkit()
          .getSystemClipboard()
          .getData(DataFlavor.stringFlavor);
    }
  }

  public String goToFacebook() throws InterruptedException {
    click(btnFreeLessons);
    String url = goToNewWindowAndGoToBack(btnFacebook);
    String[] words = url.split("/");
    return words[2];
  }
}
