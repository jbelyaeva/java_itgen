package io.itgen.appmanager;

import io.itgen.model.LeadData;
import io.itgen.model.Leads;
import io.itgen.model.StudentData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    WebDriverWait myWaitVar = new WebDriverWait(wd, 3);
    myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@id-qa,'signup')]")));
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

  public void firstPointWithScroll() {
    ((JavascriptExecutor) wd).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
    btnLogo();
  }

  public void recordOnRegular() {
    btnShowSchedule();
    btnRecordOnLesson();
    changeScrollTime();
    btnNext();
    selectCheckBox();
    btnRecord();
   }

  public void confirmRecordOnRegular() {
    btnShowSchedule();
    btnRecordOnLesson();
    changeScrollTime();
    btnNext();
    selectCheckBox();
   }

  public void btnRecordOnLesson() {
//    click(By.xpath("//div[contains(@class,'actions')]//button"));
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
   type(By.xpath("//input[2]"),"24:00");
    click(By.xpath("//div[@class='date-filter-container']"));
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

}
