package io.itgen.appmanager;

import io.itgen.model.LeadData;
import io.itgen.model.Leads;
import io.itgen.model.StudentData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    WebDriverWait myWaitVar2 = new WebDriverWait(wd, 3);
    myWaitVar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@id-qa,'signup')]")));
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

  public void fillStudentForm1(StudentData studentData) {
    type(By.xpath("//input[@id-qa='name']"), studentData.getFirstname());
    type(By.xpath("//input[@id-qa='surname']"), studentData.getLastname());
    type(By.xpath("//input[@id-qa='birthday']"), studentData.getBirthdayUi());
    click(By.xpath("//input[@id-qa='gender']/.."));
    WebDriverWait myWaitVar = new WebDriverWait(wd, 3);
    myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-value='" + studentData.getGender() + "']")));
    click(By.xpath("//li[@data-value='" + studentData.getGender() + "']"));
    click(By.xpath("//input[@id-qa='lang']/.."));
    WebDriverWait myWaitVar1 = new WebDriverWait(wd, 3);
    myWaitVar1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-value='" + studentData.getStudyLang() + "']")));
    click(By.xpath("//li[@data-value='" + studentData.getStudyLang() + "']"));
    if (!studentData.getPclevel().equals("")) {
      click(By.xpath("//input[@id-qa='pcLevel']/.."));
      WebDriverWait myWaitVar2 = new WebDriverWait(wd, 3);
      myWaitVar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-value='" + studentData.getPclevel() + "']")));
      click(By.xpath("//li[@data-value='" + studentData.getPclevel() + "']"));
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
}
