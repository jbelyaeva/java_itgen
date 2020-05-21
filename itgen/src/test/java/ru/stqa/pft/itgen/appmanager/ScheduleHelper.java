package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
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

  public void selectTime() {
   // click(By.xpath("//div[contains(@class,'select-toggle-btn')]"));
  //  dropDownList(By.xpath("//div[@class='dropdown-select-item']"),"22:00-00:00");
  }

  public void selectTrainer() {
  }

  public void selectScype() {
  }

  public void btnCreate() {
    click(By.xpath("//button[contains(@class,'btn-primary')]"));
  }

  public void createSingleSchedule(){
   btnCreateSchedule();
   checkBoxConst();
   selectTime();
   selectTrainer();
   selectScype();
   btnCreate();
  }

  public void createRegularSchedule(){
    btnCreateSchedule();
    selectTime();
    selectTrainer();
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
