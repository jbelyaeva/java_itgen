package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WindowScheduleHalper extends HelperBase{
  public WindowScheduleHalper (WebDriver wd) {
    super(wd);
  }

  public void recordStudentOn2h(String name, String id) {
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
    click(By.xpath("//button[contains(@class,'permanent')]"));
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

  public void recordStudentOnFirst1h(String name, String id) {
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

  public void recordStudentOnSecond1h(String name, String id) {
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
}
