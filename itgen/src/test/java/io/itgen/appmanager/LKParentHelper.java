package io.itgen.appmanager;

import io.itgen.model.StudentData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LKParentHelper extends HelperBase {

  private ApplicationManager app;

  public LKParentHelper(WebDriver wd) {
    super(wd);
  }

  public void btnRecordOnTrail() {
    if (isElementPresent(By.xpath("(//button[contains(@id-qa,'trial')])[2]"))) refresh();//для докера
    moveToElementWithWait(5, By.xpath("(//button[contains(@id-qa,'trial')])[2]"));
    noErrorMessage();
  }

  public void RecordOnTrail() throws InterruptedException {
    if (isElementPresent(By.xpath("//button[@title='Skip']"))) {
      moveToElementWithWait(5, By.xpath("//button[@title='Skip']"));
    }
    Thread.sleep(3000);
    btnRecordOnTrail();
    btnSelectScratch();
    selectLesson();
    btnSignUp();
  }

  public void btnLogo() {
    click(By.xpath("//img[contains(@src,'logo')]"));
    noErrorMessage();
  }

  private void btnSignUp() {
    WebDriverWait wait = new WebDriverWait(wd, 3);
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//button[contains(@id-qa,'signup')]")));
    click(By.xpath("//button[contains(@id-qa,'signup')]"));
    noErrorMessage();
  }

  public void selectLesson() {
    click(By.className("list-of-times"));
    noErrorMessage();
  }

  public void btnSelectScratch() {
    click(By.xpath("//button[@id-qa='btn-1']"));
    noErrorMessage();
  }

  public void create(StudentData student) {
    btnAddNewStudent();
    fillStudentForm1(student);
    btnNextFirst();
    btnNextSecond();
  }

  public void createSShotFirstForm(StudentData student) {
    btnAddNewStudent();
    fillStudentForm1(student);
  }

  public void createSShotSecondForm(StudentData student) {
    btnAddNewStudent();
    fillStudentForm1(student);
    btnNextFirst();
  }

  private void btnNextSecond() {
    click(By.xpath("//button[contains(@class,'button-next')]"));
  }

  private ExpectedCondition<WebElement> expectVisible(String locator) {
    return ExpectedConditions.visibilityOfElementLocated(By.xpath(locator));
  }

  public void fillStudentForm1(StudentData studentData) {
    type(By.xpath("//input[@id-qa='name']"), studentData.getFirstname());
    type(By.xpath("//input[@id-qa='surname']"), studentData.getLastname());
    type(By.xpath("//input[@id-qa='birthday']"), studentData.getBirthdayUi());

    WebDriverWait wait = new WebDriverWait(wd, 3);

    click(By.xpath("//input[@id-qa='gender']/.."));
    String gender = "//li[@data-value='" + studentData.getGender() + "']";
    wait.until(this.expectVisible(gender));
    click(By.xpath(gender));

    click(By.xpath("//input[@id-qa='lang']/.."));
    String lang = "//li[@data-value='" + studentData.getStudyLang() + "']";
    wait.until(this.expectVisible(lang));
    click(By.xpath(lang));

    // pclevel может быть пустым, т.к. тест параметризован, в тестовых данных встречается вариант с
    // pclaval=""
    if (!studentData.getPclevel().equals("")) {
      click(By.xpath("//input[@id-qa='pcLevel']/.."));
      String pcLevel = "//li[@data-value='" + studentData.getPclevel() + "']";
      wait.until(this.expectVisible(pcLevel));
      click(By.xpath(pcLevel));
    }
  }

  private void btnNextFirst() {
    click(By.className("button-next"));
  }

  private void btnAddNewStudent() {
    if (!isElementPresent(By.xpath("//a[contains(@href,'addChild')]"))) btnLogo();

    click(By.xpath("//a[contains(@href,'addChild')]"));
    noErrorMessage();
  }

  public void createBad(StudentData student) {
    btnAddNewStudent();
    fillStudentForm1(student);
    btnNextBad();
    btnLogo();
  }

  private void btnNextBad() {
    click(By.className("button-next"));
    thereAreErrorMessages();
  }

  public void recordOnRegular() {
    skipHalper();
    btnLogo();
    btnShowSchedule();
    btnRecordOnLesson();
    btnTomorrowForRegular();
    changeScrollTime();
    btnNext();
    selectCheckBox();
    btnRecord();
  }

  private void btnTomorrowForRegular() {
    // находим активный элемент и берем следующий сестринский вниз по дереву
    String locator = "//div[@class='picker-item selected']/following-sibling::div";
    if (isElementPresent(By.xpath(locator))) {
      click(By.xpath(locator));
    } else {
      click(By.xpath("//div[@class='picker-item selected']"));
    }
    noErrorMessage();
  }

  public void recordOnSingle() {
    btnShowSchedule();
    btnRecordOnLesson();
    btnSingleSchedule();
    btnTomorrowForSingle();
    changeScrollTime();
    btnNext();
    selectCheckBox();
    btnRecord();
  }

  private void btnTomorrowForSingle() {
    // находим активный элемент и берем следующий сестринский вниз по дереву
    click(By.xpath("//div[@class='picker-item selected']/following-sibling::div"));
  }

  public void GoToFiltrRecordSingle() {
    btnShowSchedule();
    btnRecordOnLesson();
    btnSingleSchedule();
    btnTomorrowForSingle();
    changeScrollTime();
  }

  public void changeStyleDayOfTheWeek() {
    By locatorPicker = By.xpath("//div[@class='picker-item selected']");
    WebElement elementPicker = wd.findElement(locatorPicker);
    ((JavascriptExecutor) wd)
        .executeScript("arguments[0].setAttribute('class', 'picker-item')", elementPicker);
    By locatorSelectedIcon = By.xpath("//span[@class='selected-icon']");
    WebElement elementSelectedIcon = wd.findElement(locatorSelectedIcon);
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
    click(By.xpath("(//div[@class='info'])[2]"));
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
    click(By.xpath("//div[@class='buttons']"));
    noErrorMessage();
  }

  private void selectCheckBox() {
    click(By.xpath("//label[contains(@for,'signup')]"));
    noErrorMessage();
  }

  private void btnNext() {
    click(By.xpath("//button[contains(@class,'next')]"));
    noErrorMessage();
  }

  private void changeScrollTime() {
    type(By.xpath("//div[@class='times-filter']//input[1]"), "00:00");
    type(By.xpath("//div[@class='times-filter']//input[2]"), "24:00");
    click(
        By.xpath(
            "//div[@class='times-filter']")); // щелкнуть на пустое место, чтоб обновился скролл
    noErrorMessage();
  }

  public void btnRecord() {
    click(By.xpath("//div[contains(@class,'actions')]//button"));
    noErrorMessage();
  }

  public void btnShowSchedule() {
    WebElement dynamicElement =
        (new WebDriverWait(wd, 10))
            .until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id-qa='show-schedule']")));
    dynamicElement.click();
    noErrorMessage();
  }

  public void btnSetPassword() {
    WebElement dynamicElement =
        (new WebDriverWait(wd, 10))
            .until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='instructions']//button]")));
    Actions actions = new Actions(wd);
    actions.moveToElement(dynamicElement).build().perform();
    dynamicElement.click();
    noErrorMessage();
  }

  public void waitForLoadLogo() {
    new WebDriverWait(wd, 10)
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//img[contains(@src,'logo')]")));
  }

  public void selfRegistration(StudentData student) {
    fillStudentForm1(student);
    btnNextFirst();
    btnNextSecond();
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
    click(By.xpath("(//label)[1]"));
  }

  private void btnCancel() {
    click(By.xpath("//div[contains(@class,'buttons-group')]"));
    noErrorMessage();
  }

  private void clickCheckBox() {
    click(By.xpath("//label"));
  }

  private void clickCheckBoxAll() {
    for (int i = 1; i < 5; i++) {
      click(By.xpath("(//label)[" + i + "]"));
      noErrorMessage();
    }
  }

  private void btnDropdown() {
    click(By.xpath("//button[@data-toggle='dropdown']"));
  }

  private void btnCancelSchedule() {
    click(By.xpath("//button[@id-qa='cancel']"));
    noErrorMessage();
  }

  public void btnClickHistory() {
    click(By.xpath("//div[contains(@class,'btn-toggle')]"));
    noErrorMessage();
  }

  public void goHrefActiveLK(String token) {
    wd.get("http://localhost:3000/enrollAccount/" + token + "?locale=ru");
    noErrorMessage();
  }

  public void inputPassword(String password) {
    type(By.xpath("//input[@autocomplete='new-password']"), password);
    noErrorMessage();
  }

  public void btnSave() {
    click(By.xpath("//button[1]"));
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
    click(By.xpath("//div[@class='course-selection-page']"));
  }

  public void skipHalper() {
    if (isElementPresent(By.xpath("//button[@title='Skip']"))) {
      moveToElementWithWait(5, By.xpath("//button[@title='Skip']"));
    }
  }
}
