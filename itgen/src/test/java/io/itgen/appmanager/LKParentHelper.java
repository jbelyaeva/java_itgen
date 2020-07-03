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

  public LKParentHelper(WebDriver wd) {
    super(wd);
  }

  public void btnRecordOnTrail() {
    click(By.xpath("(//button[contains(@id-qa,'trial')])[2]"));
    noErrorMessage();
  }

  public void RecordOnTrail() {
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
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@id-qa,'signup')]")));
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

    //pclevel может быть пустым, т.к. тест параметризован, в тестовых данных встречается вариант с pclaval=""
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
    btnShowSchedule();
    btnRecordOnLesson();
    changeScrollTime();
    btnNext();
    selectCheckBox();
    btnRecord();
  }

  public void recordOnSingle() {
    btnShowSchedule();
    btnRecordOnLesson();
    btnSingleSchedule();
    changeScrollTime();
    btnNext();
    selectCheckBox();
    btnRecord();
  }

  public void GoToFiltrRecordSingle() {
    btnShowSchedule();
    btnRecordOnLesson();
    btnSingleSchedule();
    changeWeeksPaginator();
    changeStyleDayOfTheWeek();
  }

  public void changeStyleDayOfTheWeek() {
    By locator = By.xpath("//div[@class='picker-item selected']");
    WebElement element = wd.findElement(locator);
    ((JavascriptExecutor) wd).executeScript("arguments[0].setAttribute('class', 'picker-item')", element);
  }

  private void changeWeeksPaginator() {
    for (int i = 1; i < 8; i++) {
      if (isElementPresent(By.xpath("//div[contains(@class,'picker-item')][" + i + "]"))) {
        WebElement elementDayWeeks = wd.findElement(By.xpath("//div[contains(@class,'picker-item')][" + i + "]//div//span[1]"));
        WebElement elementData = wd.findElement(By.xpath("//div[contains(@class,'picker-item')][" + i + "]//div//span[2]"));
        ((JavascriptExecutor) wd).executeScript("arguments[0].remove();", elementDayWeeks);
        ((JavascriptExecutor) wd).executeScript("arguments[0].remove();", elementData);
      }
    }
  }

  public void btnSingleSchedule() {
    click(By.xpath("//div[contains(@class,'switcher')]//button[2]"));
    noErrorMessage();
  }

  public void confirmRecordOnRegular() {
    btnShowSchedule();
    btnRecordOnLesson();
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
    type(By.xpath("//div[@class='times-filter']//input[2]"), "24:00");
    click(By.xpath("//div[@class='times-filter']")); //щелкнуть на пустое место, чтоб обновился скролл
    noErrorMessage();
  }

  public void btnRecord() {
    click(By.xpath("//div[contains(@class,'actions')]//button"));
    noErrorMessage();
  }

  public void btnShowSchedule() {
    WebElement dynamicElement = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id-qa='show-schedule']")));
    dynamicElement.click();
    noErrorMessage();
  }

  public void btnSetPassword() {
    WebElement dynamicElement = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='instructions']//button]")));
    Actions actions = new Actions(wd);
    actions.moveToElement(dynamicElement).build().perform();
    dynamicElement.click();
    noErrorMessage();
  }

  public void waitForLoad() {
    new WebDriverWait(wd, 10)
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//img[contains(@src,'logo')]")));
  }

  public void GoToFiltrRecordRegular() {
    btnShowSchedule();
    btnRecordOnLesson();
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

  private void btnCancel() {
    click(By.xpath("//div[contains(@class,'buttons-group')]"));
    noErrorMessage();
  }

  private void clickCheckBox() {
    click(By.xpath("//label"));
  }

  private void btnDropdown() {
    click(By.xpath("//button[@data-toggle='dropdown']"));
  }

  private void btnCancelSchedule() {
    click(By.xpath("//button[@id-qa='cancel']"));
    noErrorMessage();
  }
}
