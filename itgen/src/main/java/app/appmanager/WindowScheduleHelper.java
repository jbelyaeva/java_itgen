package app.appmanager;

import data.services.TrainerService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowScheduleHelper extends HelperBase {

  TrainerService trainerService = new TrainerService();
  public WindowScheduleHelper(WebDriver wd) {
    super(wd);
  }

  public void recordStudentOn2hRegular(String name) {
    btnRecordOnLesson();
    selectStudent(name);
    selectRegular();
    selectLesson();
    btnRecord();
    noErrorMessage();
  }

  private void btnRecord() {
    click(By.xpath("(//div[@class='schedule-menu']//button)[1]"));
  }

  private void selectLesson() {
    click(By.xpath("//div[@class='group']//div[@class='trainer-name']"));
  }

  private void selectRegular() {
    clickWithMoveToElementAndWait(10, By.xpath("(//div[@class='lesson-types']//button)[1]"));
  }

  private void btnRecordOnLesson() {
    click(By.xpath("//a[@href='/createSchedule']"));
  }

  private void selectStudent(String name) {
    type(By.xpath("//div[@class='child-search dropdown']//input"), name);
    if (isElementPresent(By.xpath("//span[contains(@class,'result')]"))) {
      click(By.xpath("//span[contains(@class,'result')]"));
    } else {
      wd.findElement(By.xpath("//div[@class='child-search dropdown']//input")).clear();
      type(By.xpath("//div[@class='child-search dropdown']//input"), name);
      click(By.xpath("//span[contains(@class,'result')]"));
    }
  }

  public void waitVisibleSchedule(String idTrainer) {
    WebDriverWait wait = new WebDriverWait(wd, 10);
    wait.until(
        ExpectedConditions.visibilityOf(
            wd.findElement(By.xpath("//div[@data-trainer-id='" + idTrainer + "']"))));
  }

  public void recordStudentOnRegularFirst1h(String name, String id) {
    btnRecordOnLesson();
    selectStudent(name);
    selectRegular();
    selectDuration();
    selectLesson1h(id);
    btnRecord();
    noErrorMessage();
  }

  private void selectLesson1h(String id) {
    click(
        By.xpath(
            "(//span[contains(text(),'" + trainerService.findById(id).getLastName() + "')])[1]"));
  }

  private void selectDuration() {
    click(By.xpath("(//div[contains(@class,'select')])[2]"));
    click(By.xpath("//div[contains(@class,'dropdown')]//li[2]"));
  }

  public void recordStudentOnRegularSecond1h(String name, String id) {
    btnRecordOnLesson();
    selectStudent(name);
    selectRegular();
    selectDuration();
    selectLesson2h(id);
    btnRecord();
    noErrorMessage();
  }

  private void selectLesson2h(String id) {
    click(
        By.xpath(
            "(//span[contains(text(),'" + trainerService.findById(id).getLastName() + "')])[2]"));
  }

  public void recordStudentOn2hSingle(String name) {
    btnRecordOnLesson();
    selectStudent(name);
    selectSingle();
    selectLesson();
    btnRecord();
    noErrorMessage();
  }

  private void selectSingle() {
    clickWithMoveToElementAndWait(10, By.xpath("(//div[@class='lesson-types']//button)[2]"));
  }

  public void recordStudentOnSingleFirst1h(String name, String id) {
    btnRecordOnLesson();
    selectStudent(name);
    selectSingle();
    selectDuration();
    selectLesson1h(id);
    btnRecord();
    noErrorMessage();
  }

  public void recordStudentOnSingleSecond1h(String name, String id) {
    btnRecordOnLesson();
    selectStudent(name);
    selectSingle();
    selectDuration();
    selectLesson2h(id);
    btnRecord();
    noErrorMessage();
  }

  public void recordStudentOnTrial(String name, String id) {
    btnRecordOnLesson();
    selectStudent(name);
    selectLesson();
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
