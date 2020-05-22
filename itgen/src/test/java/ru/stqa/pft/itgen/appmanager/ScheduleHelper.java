package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

  private void selectMove() {
    click(By.xpath("//a[contains(@class,'change')]"));
  }

  private void selectBlock() {
    click(By.xpath("//a[contains(@class,'block')]"));
  }

  private void selectAssign() {
    click(By.xpath("//a[contains(@class,'assign')]"));
  }

  public void writeNote() {
   type(By.name("block-desc"),"заблокировать расписание");
  }

  private void selectOnAllSchedule() {
    WebElement dynamicElement = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='1']")));
    dynamicElement.click();
   }


  public void selectTime() {
   // click(By.xpath("//div[contains(@class,'select-toggle-btn')]"));
  //  dropDownList(By.xpath("//div[@class='dropdown-select-item']"),"22:00-00:00");
  }

  private void selectSchedule() {
    click(By.xpath("//div[@class='panel-body'][1]"));
  }

  public String selectScheduleGetId() {
    click(By.xpath("//div[@class='panel-body'][1]"));
    String idSchedule=getId(getURL());
    return idSchedule;
  }

  private void selectCancel() {
    click(By.xpath("//a[contains(@class,'cancel')]"));
  }

  public void selectTrainer() {
  }

  public void selectScype() {
  }

  public void btnCreate() {
    click(By.xpath("//button[contains(@class,'btn-primary')]"));
  }

  public void bntPoints() {
    click(By.xpath("//button[@id='dropdownMenuActions']"));
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

  public String move() {
 //будем знать id
 // нужно выбрать именно наше расписание путем анализа атрибута в dome
    selectSchedule(); //в локаторе поменять выбор по id а не первое в списке
    bntPoints();
    selectMove();
    fillFormMove();
    String idSchedule=getId(getURL());
    btnMove();
    return idSchedule;
  }

  public String block() {
    //будем знать id
    // нужно выбрать именно наше расписание путем анализа атрибута в dome
    selectSchedule(); //в локаторе поменять выбор по id а не первое в списке
    String idSchedule=getId(getURL());
    bntPoints();
    selectBlock();
    writeNote();
    btnBlock();
    return idSchedule;
  }
  public String blockAll() {
    //будем знать id
    // нужно выбрать именно наше расписание путем анализа атрибута в dome
    selectSchedule(); //в локаторе поменять выбор по id а не первое в списке
    String idSchedule=getId(getURL());
    bntPoints();
    selectBlock();
    selectAllScheduleBlock();
    writeNote();
    btnBlock();
    return idSchedule;
  }

  private void selectAllScheduleBlock() {
    WebElement dynamicElement = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='1']")));
    dynamicElement.click();
  }

  public String cancel() {
    //будем знать id
    // нужно выбрать именно наше расписание путем анализа атрибута в dome
    selectSchedule(); //в локаторе поменять выбор по id а не первое в списке
    bntPoints();
    selectCancel();
    String idSchedule=getId(getURL());
    btnCancel();
    return idSchedule;
  }

  public String assignTrainer() {
    //будем знать id
    // нужно выбрать именно наше расписание путем анализа атрибута в dome
    selectSchedule(); //в локаторе поменять выбор по id а не первое в списке
    bntPoints();
    selectAssign();
    String idSchedule=getId(getURL());
    selectNewTrainer();
    btnAssign();
    return idSchedule;
  }
  public String assignTrainerAll() {
    //будем знать id
    // нужно выбрать именно наше расписание путем анализа атрибута в dome
    selectSchedule(); //в локаторе поменять выбор по id а не первое в списке
    bntPoints();
    selectAssign();
    String idSchedule=getId(getURL());
    selectOnAllSchedule();
    selectNewTrainer();
    btnAssign();
    return idSchedule;
  }

  private void btnMove() {
    click(By.xpath(" //button[contains(@class,'accept')]"));
  }

  private void btnBlock() {
    click(By.xpath(" //button[contains(@class,'block')]"));
  }

  private void btnAssign() {
    click(By.xpath(" //button[contains(@class,'assign')]"));
  }

  private void btnCancel() {
    click(By.xpath(" //button[contains(@class,'cancel')]"));
  }

  private void selectNewTrainer() {
    WebElement dynamicElement1 = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.id("trainer")));
    dynamicElement1.click();
    click(By.xpath("//select[@id='trainer']//option[@value='18']"));
  }

  private void fillFormMove() {
    //выбор текущей даты в календаре
    WebElement dynamicElement = (new WebDriverWait(wd, 40))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'picker')]")));
    dynamicElement.click();
    click(By.xpath(" //td[contains(@class,'active')]"));
    //выбор времени (крайнее на сегодня)
    WebElement Selectbox_times = wd.findElement(By.xpath("//select[contains(@id,'tp')]"));
    Select select1 = new Select(Selectbox_times);
    select1.selectByVisibleText("23:00 - 01:00");
   //выбор тренера
    WebElement dynamicElement1 = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.id("trainer")));
    dynamicElement1.click();
    click(By.xpath("//select[@id='trainer']//option[@value='12']"));

    //выбор скайпа
    WebElement dynamicElement2 = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.id("skype")));
    dynamicElement2.click();
    click(By.xpath("//select[@id='skype']//option[@value='17']"));
  }
}
