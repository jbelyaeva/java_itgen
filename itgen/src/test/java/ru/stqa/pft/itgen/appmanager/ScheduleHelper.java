package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.pft.itgen.model.ScheduleData;
import ru.stqa.pft.itgen.model.Schedules;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    WebElement dynamicElement = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'block')]")));
    dynamicElement.click();
  }

  private void selectAssign() {
    click(By.xpath("//a[contains(@class,'assign')]"));
  }

  public void writeNote(String note) {
    type(By.name("block-desc"), note);
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
    String idSchedule = getId(getURL());
    return idSchedule;
  }

  private void selectCancel() {
    WebElement dynamicElement = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'cancel')]")));
    dynamicElement.click();

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

  public void createSingleSchedule() {
    btnCreateSchedule();
    checkBoxConst();
    selectTime();
    selectTrainer();
    selectScype();
    btnCreate();
    selectSchedule();
    refresh();
  }

  public void createRegularSchedule() {
    btnCreateSchedule();
    selectTime();
    selectTrainer();
    selectScype();
    btnCreate();
    refresh();
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

  public void showElement() {
    WebElement dynamicElement1 = (new WebDriverWait(wd, 40))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'create')]")));
  }

  public void move(String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectMove();
    fillFormMove();
    btnMove();
    refresh();
  }

  public void block(String id, String note) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectBlock();
    writeNote(note);
    btnBlock();
    refresh();
  }

  public void blockAll(String id, String note) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectBlock();
    selectAllScheduleBlock();
    writeNote(note);
    btnBlock();
    refresh();
  }

  private void selectAllScheduleBlock() {
    WebElement dynamicElement = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='1']")));
    Actions actions = new Actions(wd);
    actions.moveToElement(dynamicElement).build().perform();
    dynamicElement.click();
  }

  public void cancel(String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectCancel();
    btnCancel();
    refresh();
  }

  public void assignTrainer(String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectAssign();
    selectNewTrainer();
    btnAssign();
    refresh();
  }

  public void assignTrainerAll(String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectAssign();
    selectOnAllSchedule();
    selectNewTrainer();
    btnAssign();
    refresh();
  }

  private void btnMove() {
    click(By.xpath(" //button[contains(@class,'accept')]"));
  }

  private void btnBlock() {
    WebElement dynamicElement = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'block')]")));
    dynamicElement.click();
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

  public void selectScheduleInListUIById(String id) {
    //находим пагинатор
    String next = wd.findElement(By.xpath("//button[contains(@class,'next')]")).getAttribute("class");
    //есть ли на первой странице наш студент
    List<WebElement> list = wd.findElements(By.xpath("//a[contains(@href,'" + id + "')]"));
    if (list.size() > 0) {
      wd.findElement(By.xpath("//a[contains(@href,'" + id + "')]")).click();
    } else {
      //если студентк не на первой странице, надо нажать пагинатор, пока не найдем
      while (!next.equals("disabled")) {
        List<WebElement> list_pagin = wd.findElements(By.cssSelector("a[href='/lesson/" + id + "'"));
        if (list_pagin.size() > 0) {
          wd.findElement(By.cssSelector("a[href='/lesson/" + id + "'")).click();
          break;
        } else {
          wd.findElement(By.xpath("//button[contains(@class,'next')]")).click();
        }
      }
    }
  }

  private void fillFormMove() {
    //выбор текущей даты в календаре
    WebElement dynamicElement = (new WebDriverWait(wd, 5))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'picker')]")));
    dynamicElement.click();
    //решение на периодическое не открытие календаря
    if (isElementPresent(By.xpath("//td[contains(@class,'active')]"))) {
      click(By.xpath(" //td[contains(@class,'active')]"));
    } else {
      dynamicElement.click();
      click(By.xpath(" //td[contains(@class,'active')]"));
    }
    //выбор времени (крайнее на сегодня)
    WebElement Selectbox_times = wd.findElement(By.xpath("//select[contains(@id,'tp')]"));
    Select select1 = new Select(Selectbox_times);
    select1.selectByVisibleText("23:00 - 01:00");
    //выбор тренера
    WebElement dynamicElementTrainer = (new WebDriverWait(wd, 5))
            .until(ExpectedConditions.elementToBeClickable(By.id("trainer")));
    dynamicElementTrainer.click();
    click(By.xpath("//select[@id='trainer']//option[@value='12']"));
    //выбор скайпа
    WebElement dynamicElementSkype = (new WebDriverWait(wd, 5))
            .until(ExpectedConditions.elementToBeClickable(By.id("skype")));
    dynamicElementSkype.click();
    click(By.xpath("//select[@id='skype']//option[@value='17']"));
  }

  public void recordStudentOnRegular2h(String name, String id) {
    selectScheduleInListUIById(id);
    bntRecordSrudent();
    selectStudent(name);
    selectResult();
    selectNo();
    btnRecord();
  }

  private void btnRecord() {
    click(By.xpath("//button[contains(@class,'create')]"));
  }

  private void selectNo() {
    click(By.xpath("//button[contains(@class,'change')][2]"));
  }

  private void selectResult() {
    click(By.xpath("//span[contains(@class,'result')]"));
  }

  private void selectStudent(String name) {
    wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    type(By.id("child-name"), name);

  }

  private void bntRecordSrudent() {
    click(By.xpath("//button[contains(@class,'create')]"));
  }

  public void recordStudentOnRegularFirst1h(String name, String id) {
    selectScheduleInListUIById(id);
    bntRecordSrudent();
    selectStudent(name);
    selectResult();
    selectNo();
    selectFirst();
    btnRecord();
  }

  private void selectFirst() {
    click(By.xpath("//button[contains(@data-itemid,'1')]"));
  }

  public void recordStudentOnRegularSecond1h(String name, String id) {
    selectScheduleInListUIById(id);
    bntRecordSrudent();
    selectStudent(name);
    selectResult();
    selectNo();
    selectSecond();
    btnRecord();
  }

  private void selectSecond() {
    click(By.xpath("//button[contains(@data-itemid,'1')]"));
  }
}
