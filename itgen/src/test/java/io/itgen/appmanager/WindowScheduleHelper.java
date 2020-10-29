package io.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowScheduleHelper extends HelperBase {
  public WindowScheduleHelper(WebDriver wd) {
    super(wd);
  }

  public void recordStudentOn2hRegular(String name, String id) {
    btnRecordOnLesson();
    selectStudent(name);
    waitVisibleSchedule(id);
    selectRegular();
    selectLesson(id);
    btnRecord();
    noErrorMessage();
  }

  private void btnRecord() {
    click(By.xpath("//button[contains(@class,'create')]"));
  }

  private void selectLesson(String id) {
    click(By.xpath("//div[@data-trainer-id='" + id + "']"));
  }

  private void selectRegular() {
    clickWithMoveToElementAndWait(10, By.xpath("//button[contains(@class,'permanent')]"));
  }

  private void btnRecordOnLesson() {
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

  public void waitVisibleSchedule(String idTrainer) {
    WebDriverWait wait = new WebDriverWait(wd, 10);
    wait.until(
        ExpectedConditions.visibilityOf(
            wd.findElement(By.xpath("//div[@data-trainer-id='" + idTrainer + "']"))));
  }

  public void recordStudentOnRegularFirst1h(String name, String id) throws InterruptedException {
    btnRecordOnLesson();
    selectStudent(name);
    Thread.sleep(3000);
    selectRegular();
    selectDuration();
    selectLesson1h(id);
    btnRecord();
    noErrorMessage();
  }

  private void selectLesson1h(String id) {
    click(
        By.xpath("//div[@data-trainer-id='" + id + "' and contains (@data-lesson-duration, '1')]"));
  }

  private void selectDuration() {
    click(By.xpath("//select[@id='create-schedule-duration']//option[@value='4']"));
  }

  public void recordStudentOnRegularSecond1h(String name, String id) throws InterruptedException {
    btnRecordOnLesson();
    selectStudent(name);
    waitVisibleSchedule(id);
    Thread.sleep(3000);
    selectRegular();
    selectDuration();
    selectLesson2h(id);
    btnRecord();
    noErrorMessage();
  }

  private void selectLesson2h(String id) {
    click(
        By.xpath("//div[@data-trainer-id='" + id + "' and contains (@data-lesson-duration, '2')]"));
  }

  public void recordStudentOn2hSingle(String name, String id) {
    btnRecordOnLesson();
    selectStudent(name);
    waitVisibleSchedule(id);
    selectSingle();
    selectLesson(id);
    btnRecord();
    noErrorMessage();
  }

  private void selectSingle() {
    clickWithMoveToElementAndWait(5, By.xpath("//button[contains(@class,'onetime')]"));
  }

  public void recordStudentOnSingleFirst1h(String name, String id) throws InterruptedException {
    btnRecordOnLesson();
    selectStudent(name);
    Thread.sleep(3000);
    selectSingle();
    selectDuration();
    selectLesson1h(id);
    btnRecord();
    noErrorMessage();
  }

  public void recordStudentOnSingleSecond1h(String name, String id) throws InterruptedException {
    btnRecordOnLesson();
    selectStudent(name);
    Thread.sleep(3000);
    selectSingle();
    selectDuration();
    selectLesson2h(id);
    btnRecord();
    noErrorMessage();
  }

  public void recordStudentOnTrial(String name, String id) {
    btnRecordOnLesson();
    selectStudent(name);
    waitVisibleSchedule(id);
    selectLesson(id);
    btnRecord();
    noErrorMessage();
  }

  public void makeRequestOnTrial2hScratch(String name) {
    btnRecordOnLesson();
    selectStudent(name);
    btnRequest();
    noErrorMessage();
  }

  private void btnRequest() {
    click(By.xpath("//button[contains(@class,'create-request')]"));
  }

  public void selectStudentForSshot(String name) {
    btnRecordOnLesson();
    selectStudent(name);
  }
}
