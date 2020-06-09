package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowScheduleHelper extends HelperBase{
  public WindowScheduleHelper(WebDriver wd) {
    super(wd);
  }

  public void recordStudentOn2hRegular(String name, String id) {
    bntRecordOnLesson();
    selectStudent(name);
    selectRegular();
    selectLesson(id);
    btnRecord();
    noErrorMessage();
  }

  private void btnRecord() {
    click(By.xpath("//button[contains(@class,'create')]"));
  }

  private void selectLesson(String id) {
   click(By.xpath("//div[@data-trainer-id='"+id+"']"));
  }

  private void selectRegular() {
    WebElement dynamicElement = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'permanent')]")));
    Actions actions = new Actions(wd);
    actions.moveToElement(dynamicElement).build().perform();
    dynamicElement.click();
  //  click(By.xpath("//button[contains(@class,'permanent')]"));
  }

  private void bntRecordOnLesson() {
    click(By.xpath("//a[@href='/createSchedule']"));
  }

  private void selectStudent(String name) {

    type(By.id("child-name"), name);
    if (isElementPresent(By.xpath("//span[contains(@class,'result')]"))) {
      click(By.xpath("//span[contains(@class,'result')]"));
    } else {
      wd.findElement(By.id("child-name")).clear();
      type(By.id("child-name"), name);
      click(By.xpath("//span[contains(@class,'result')]"));
    }
  }

  public void recordStudentOnRegularFirst1h(String name, String id) {
    bntRecordOnLesson();
    selectStudent(name);
    selectRegular();
    selectDuration();
    selectLesson1h(id);
    btnRecord();
    noErrorMessage();
  }

  private void selectLesson1h(String id) {
    click(By.xpath("//div[@data-trainer-id='"+id+"' and contains (@data-lesson-duration, '1')]"));
  }

  private void selectDuration() {
   click(By.xpath("//select[@id='create-schedule-duration']//option[@value='4']"));
  }

  public void recordStudentOnRegularSecond1h(String name, String id) {
    bntRecordOnLesson();
    selectStudent(name);
    selectRegular();
    selectDuration();
    selectLesson2h(id);
    btnRecord();
    noErrorMessage();
  }

  private void selectLesson2h(String id) {
    click(By.xpath("//div[@data-trainer-id='"+id+"' and contains (@data-lesson-duration, '2')]"));
  }

  public void recordStudentOn2hSingle(String name, String id) {
    bntRecordOnLesson();
    selectStudent(name);
    selectSingle();
    selectLesson(id);
    btnRecord();
    noErrorMessage();
  }

  private void selectSingle() {
      click(By.xpath("//button[contains(@class,'onetime')]"));
  }

  public void recordStudentOnSingleFirst1h(String name, String id) {
    bntRecordOnLesson();
    selectStudent(name);
    selectSingle();
    selectDuration();
    selectLesson1h(id);
    btnRecord();
    noErrorMessage();
  }

  public void recordStudentOnSingleSecond1h(String name, String id) {
    bntRecordOnLesson();
    selectStudent(name);
    selectSingle();
    selectDuration();
    selectLesson2h(id);
    btnRecord();
    noErrorMessage();
  }

  public void recordStudentOnTrial(String name, String id) {
    bntRecordOnLesson();
    selectStudent(name);
    selectLesson(id);
    btnRecord();
    noErrorMessage();
  }

}
