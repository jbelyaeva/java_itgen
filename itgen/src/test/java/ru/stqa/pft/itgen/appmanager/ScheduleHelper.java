package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import ru.stqa.pft.itgen.model.*;

public class ScheduleHelper extends HelperBase {

  public ScheduleHelper(WebDriver wd) {
    super(wd);
  }


  public void btnCreateSchedule() {
    click(By.xpath("//a[contains(@href,'/createSchoolSchedule')]"));
   }

  public void checkBoxConst() {
    click(By.xpath("//input[@type='checkbox']"));
  }

  public void selectTime(String time) {
    click(By.xpath("//div[contains(@class,'select-toggle-btn')]"));
    WebElement getTime = wd.findElement(By.xpath("//div[contains(text(),'"+time+"')]"));
    ((JavascriptExecutor) wd).executeScript("arguments[0].scrollIntoView();", getTime);
    getTime.click();
   }

  public void selectTrainer(String trainer) {
    click(By.xpath("//*[@id=\"__blaze-root\"]/div[1]/div[2]/main/div/div/div[2]/div[1]/div[4]/div/div"));
    WebElement getTrainer = wd.findElement(By.xpath("//div[contains(text(),'"+trainer+"')]"));
    ((JavascriptExecutor) wd).executeScript("arguments[0].scrollIntoView();", getTrainer);
    getTrainer.click();
  }

  public void selectScype() {
  }

  public void btnCreate() {
    click(By.xpath("//button[contains(@class,'btn-primary')]"));
  }

  public void createSchedule(String time, String trainer){
   btnCreateSchedule();
   checkBoxConst();
   selectTime(time);
   selectTrainer(trainer);
   selectScype();
   btnCreate();
  }
  public String getIdNewScheduleDB(Schedules before, Schedules after) {
    int a = 0;
    String getIdAfter = "";
    for (ScheduleData schedule : after) {
      getIdAfter = schedule.getId();
      for (ScheduleData schedule_before : before) {
        String getIdBefore = schedule_before.getId();
        if (getIdAfter.equals(getIdBefore)) {
          a = 1;
          break;
        } else {
          a = 2;
        }
      }
      if (a == 2) {
        break;
      }
    }
    return getIdAfter;
  }
}
