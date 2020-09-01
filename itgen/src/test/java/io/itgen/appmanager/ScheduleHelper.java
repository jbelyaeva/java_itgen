package io.itgen.appmanager;

import static org.testng.AssertJUnit.assertTrue;

import io.itgen.model.ScheduleData;
import io.itgen.model.Schedules;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScheduleHelper extends HelperBase {

  public ScheduleHelper(WebDriver wd) {
    super(wd);
  }

  public void btnCreateSchedule() {
    WebElement dynamicElement =
        (new WebDriverWait(wd, 10))
            .until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,'/createSchoolSchedule')]")));
    dynamicElement.click();
  }

  public void checkBoxConst() {
    click(By.xpath("//input[@type='checkbox']"));
  }

  private void selectMove() {
    click(By.xpath("//a[contains(@class,'change')]"));
  }

  private void selectBlock() {
    moveToElementWithWait(10, "//a[contains(@class,'block')]");
  }

  private void selectAssign() {
    click(By.xpath("//a[contains(@class,'assign')]"));
  }

  public void writeNote(String note) {
    // решение на нестабильный ввод примечания
    WebElement dynamicElement =
        (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.name("block-desc")));
    wd.findElement(By.name("block-desc")).clear();
    type(By.name("block-desc"), note);
  }

  private void selectOnAllSchedule() {
    // решение на периодическое не нажимание радиобаттон
    moveToElementWithWait(10, "//input[@value='1']");
  }

  public void selectTime() {}

  private void selectSchedule() {
    click(By.xpath("//div[@class='panel-body'][1]"));
  }

  public String selectScheduleGetId() {
    click(By.xpath("//div[@class='panel-body'][1]"));
    String idSchedule = getId(getURL());
    return idSchedule;
  }

  private void selectCancel() {
    WebElement dynamicElement =
        (new WebDriverWait(wd, 10))
            .until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(@class,'cancel')]")));
    dynamicElement.click();
  }

  public void selectTrainer() {}

  public void selectScype() {}

  public void btnCreate() {
    click(By.xpath("//div[contains(@class,'footer')]//button"));
  }

  public void bntPoints() {
    moveToElementWithWait(10, " //button[@id='dropdownMenuActions']");
  }

  public void createSingleSchedule() {
    btnCreateSchedule();
    checkBoxConst();
    selectTime();
    selectTrainer();
    selectScype();
    btnCreate();
    selectSchedule();
    noErrorMessage();
    refresh();
  }

  public void createSingleScheduleTest() {
    btnCreateSchedule();
    checkBoxConst();
    selectTime();
    selectTrainer();
    selectScype();
    btnCreate();
  }

  public void createRegularSchedule() {
    btnCreateSchedule();
    selectTime();
    selectTrainer();
    selectScype();
    btnCreate();
    noErrorMessage();
    refresh();
  }

  public void selectErrorLateData() {
    click(By.xpath("//div[@class='DayPickerInput']"));
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

  public void move(String period, String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectMove();
    fillFormMove(period);
    btnMove();
    noErrorMessage();
    refresh();
  }

  public void badMoveOccupied(String period, String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectMove();
    fillFormMove(period);
    btnMoveDisabled();
    noErrorMessage();
    refresh();
  }

  public void checkFindBusyStuden(String idStudent) {
    WebElement element = wd.findElement(By.xpath("//a[@href='/profile/" + idStudent + "']"));
    if (element != null) {
      assertTrue(true);
    } else {
      assertTrue(false);
    }
  }

  public void badMove(String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectMove();
    fillBadTime();
    btnMoveDisabled();
    thereAreErrorMessages();
    refresh();
  }

  public void badMoveNotChangeDateTime(String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectMove();
    fillMoveOnlyTrainer();
    btnMoveDisabled();
    refresh();
  }

  private void fillMoveOnlyTrainer() {
    // выбор тренера
    WebElement dynamicElementTrainer =
        (new WebDriverWait(wd, 5)).until(ExpectedConditions.elementToBeClickable(By.id("trainer")));
    dynamicElementTrainer.click();
    click(By.xpath("//select[@id='trainer']//option[@value='7']"));
  }

  public void block(String id, String note) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectBlock();
    writeNote(note);
    btnBlock();
    noErrorMessage();
    refresh();
  }

  public void blockAll(String id, String note) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectBlock();
    selectAllScheduleBlock();
    writeNote(note);
    btnBlock();
    noErrorMessage();
    refresh();
  }

  private void selectAllScheduleBlock() {
    moveToElementWithWait(10, "//input[@value='1']");
  }

  public void cancel(String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectCancel();
    btnCancel();
    noErrorMessage();
    refresh();
  }

  public void assignTrainer(String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectAssign();
    selectNewTrainer();
    btnAssign();
    noErrorMessage();
    refresh();
  }

  public void assignTrainerAll(String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectAssign();
    selectOnAllSchedule();
    selectNewTrainer();
    btnAssign();
    noErrorMessage();
  }

  private void btnMove() {
    moveToElementWithWait(10, "//button[contains(@class,'accept')]");
  }

  private void btnMoveDisabled() {
    String dis =
        wd.findElement(By.xpath(" //button[contains(@class,'accept')]")).getAttribute("disabled");
    assert (dis.equals("true"));
  }

  private void btnBlock() {
    WebElement dynamicElement =
        (new WebDriverWait(wd, 10))
            .until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'block')]")));
    dynamicElement.click();
  }

  private void btnAssign() {
    click(By.xpath(" //button[contains(@class,'assign')]"));
  }

  private void btnCancel() {
    moveToElementWithWait(10, "//button[contains(@class,'cancel')]");
  }

  private void selectNewTrainer() {
    WebElement dynamicElement1 =
        (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.id("trainer")));
    dynamicElement1.click();
    click(By.xpath("//select[@id='trainer']//option[@value='18']"));
  }

  public void selectScheduleInListUIById(String id) {
    // находим пагинатор
    String next =
        wd.findElement(By.xpath("//button[contains(@class,'next')]")).getAttribute("class");
    // есть ли на первой странице наше занятие
    List<WebElement> list = wd.findElements(By.xpath("//a[contains(@href,'" + id + "')]"));
    if (list.size() > 0) {
      wd.findElement(By.xpath("//a[contains(@href,'" + id + "')]")).click();
    } else {
      // если занятие не на первой странице, надо нажать пагинатор, пока не найдем
      while (!next.equals("disabled")) {
        List<WebElement> list_pagin =
            wd.findElements(By.cssSelector("a[href='/lesson/" + id + "'"));
        if (list_pagin.size() > 0) {
          wd.findElement(By.cssSelector("a[href='/lesson/" + id + "'")).click();
          break;
        } else {
          wd.findElement(By.xpath("//button[contains(@class,'next')]")).click();
        }
      }
    }
  }

  private void fillFormMove(String period) {
    // выбор текущей даты в календаре
    WebElement dynamicElement =
        (new WebDriverWait(wd, 5))
            .until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(@class,'picker')]")));
    dynamicElement.click();

    // решение на периодическое не открытие календаря
    if (isElementPresent(By.xpath("//td[contains(@class,'active')]"))) {
      click(By.xpath(" //td[contains(@class,'active')]"));
    } else {
      dynamicElement.click();
      click(By.xpath(" //td[contains(@class,'active')]"));
    }

    // выбор времени (крайнее на сегодня)
    WebElement Selectbox_times = wd.findElement(By.xpath("//select[contains(@id,'tp')]"));
    Select select1 = new Select(Selectbox_times);
    select1.selectByVisibleText(period);

    // выбор тренера
    WebElement dynamicElementTrainer =
        (new WebDriverWait(wd, 5)).until(ExpectedConditions.elementToBeClickable(By.id("trainer")));
    dynamicElementTrainer.click();
    click(By.xpath("//select[@id='trainer']//option[@value='7']"));

    // выбор скайпа
    click(By.xpath("//div[contains(@class,'modal-body')]"));
  }

  private void fillBadTime() {
    wd.findElement(By.xpath("//div[@class='modal-body']")).click();
    wd.findElement(By.id("tp")).click();
    new Select(wd.findElement(By.id("tp"))).selectByVisibleText("02:30 - 04:30");
    wd.findElement(By.id("tp")).click();
  }

  public void recordStudentOn2h(String name, String id) {
    selectScheduleInListUIById(id);
    bntRecordStudent();
    selectStudent(name);
    selectNo();
    btnRecord();
    noErrorMessage();
  }

  private void btnRecord() {
    moveToElementWithWait(10, "//button[contains(@class,'create')]");
  }

  private void selectNo() {
    click(By.xpath("//button[contains(@class,'change')][2]"));
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

  private void bntRecordStudent() {
    click(By.xpath("//button[contains(@class,'create')]"));
  }

  public void recordStudentOnFirst1h(String name, String id) {
    selectScheduleInListUIById(id);
    bntRecordStudent();
    selectStudent(name);
    selectNo();
    selectFirst();
    btnRecord();
    noErrorMessage();
    refresh();
  }

  private void selectFirst() {
    click(By.xpath("//button[contains(@data-itemid,'1')]"));
  }

  public void recordStudentOnSecond1h(String name, String id) {
    selectScheduleInListUIById(id);
    bntRecordStudent();
    selectStudent(name);
    selectNo();
    selectSecond();
    btnRecord();
    noErrorMessage();
    refresh();
  }

  private void selectSecond() {
    click(By.xpath("//button[contains(@data-itemid,'2')]"));
  }

  public void removeStudent(String id) {
    selectScheduleInListUIById(id);
    clickEmptyArea();
    bntRemove();
    alertDeleteStudent();
    noErrorMessage();
  }

  private void alertDeleteStudent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    noErrorMessage();
  }

  private void bntRemove() {
    By locator = By.xpath("//button[contains(@class,'remove')]");
    new WebDriverWait(wd, 10).until(ExpectedConditions.elementToBeClickable(locator)).click();
    if (!isElementPresent(By.cssSelector("div.modal-header"))) {
      refresh();
      clickEmptyArea();
      click(locator);
    }
  }

  private void clickEmptyArea() {
    click(By.xpath("//div[contains(@class,'list')]"));
  }

  public String getNewScheduleDB(Schedules before, Schedules after) {
    boolean a = true;
    String getAfter = null;
    for (ScheduleData schedule : after) {
      getAfter = schedule.getId();
      for (ScheduleData schedule_before : before) {
        String getBefore = schedule_before.getId();
        if (!getAfter.equals(getBefore)) {
          a = false;
          break;
        }
      }
      if (!a) {
        break;
      }
    }
    return getAfter;
  }

  public void recordStudentOnTrial(String name, String id) {
    selectScheduleInListUIById(id);
    bntRecordStudent();
    selectStudent(name);
    btnRecord();
    noErrorMessage();
    refresh();
  }

  public void waitForLoadTextCenterOnMainSchedule() {
    new WebDriverWait(wd, 10)
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[@class='text-center']")));
  }
}
