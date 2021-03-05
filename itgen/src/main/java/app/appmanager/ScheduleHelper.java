package app.appmanager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScheduleHelper extends HelperBase {

  private final By labelBusyChild = By.xpath(
      "//div[@class='busy-childs']//a[contains(@href,'newStudent')]");
  private final By btnMoveInPopup = By.xpath("//div[@class='modal-footer']//button");
  private final By btnCreateSchedule = By.xpath("//a[contains(@href,'/createSchoolSchedule')]");
  private final By labelBlockSchedule = By.xpath("//label[text()='Заблокировано']");
  private final By labelInformationAboutSchedule = By.xpath(
      "//div[@class='text-muted header-labels']");
  private final By labelCancelSchedule = By.xpath(
      "(//div[@class='text-muted header-labels']//span)[2]");
  private final By checkBoxConst = By.xpath("//div[@class='permanent-form-group']//div");
  private final By selectMove = By.xpath("//a[contains(@class,'change')]");
  private final By selectBlock = By.xpath("//a[contains(@class,'block')]");
  private final By selectAssign = By.xpath("//a[contains(@class,'assign')]");
  private final By selectCancel = By.xpath("//a[contains(@class,'cancel')]");
  private final By selectArchive = By.xpath("//a[contains(@class,'archive')]");
  private final By selectRestore = By.xpath("//a[contains(@class,'restore')]");
  private final By btnArchive = By.xpath("//button[contains(@class,'btn-archive')]");
  private final By typeDesc = By.name("block-desc");
  private final By radioButtonAll = By.xpath("//input[@value='1']");
  private final By btnCreate = By.xpath("//div[contains(@class,'footer')]//button");
  private final By btnPoints = By.xpath(" //button[@id='dropdownMenuActions']");
  private final By selectDataToday = By.xpath("//button[contains(@class,'daySelect')]");
  private final By btnCalendar = By.xpath("//div[@id='date-from']//button");
  private final By selectListTrainer = By.id("trainer");
  private final By selectListTrainerInCreate = By.xpath("//input[@id-qa='trainers']");
  private final By selectListTime = By.xpath("//select[contains(@id,'tp')]");
  private final By selectTrainerWithId7 = By.xpath("//select[@id='trainer']//option[@value='7']");
  private final By selectTrainerWithId18 = By.xpath("//select[@id='trainer']//option[@value='18']");
  private final By studentOnLessonInSchedule = By.xpath("//a[@class='text-capitalize title-name']");
  private final By btnMove = By.xpath("//button[contains(@class,'accept')]");
  private final By btnBlock = By.xpath("//button[contains(@class,'block')]");
  private final By btnAssign = By.xpath(" //button[contains(@class,'assign')]");
  private final By btnCancel = By.xpath("//button[contains(@class,'cancel')]");
  private final By btnNextInPaginator = By.xpath("//button[contains(@class,'next')]");
  private final By btnPrevInPaginator = By.xpath("//button[contains(@class,'prev')]");
  private final By btnRemove = By.xpath("//button[contains(@class,'remove')]");
  private final By selectFirst1h = By.xpath("//button[contains(@data-itemid,'1')]");
  private final By selectSecond1h = By.xpath("//button[contains(@data-itemid,'2')]");
  private final By btnRecord = By.xpath("//button[contains(@class,'create')]");
  private final By typeChildName = By.id("child-name");
  private final By searchResult = By.xpath("//span[contains(@class,'result')]");
  private final By selectNo = By.xpath("//button[contains(@class,'change')][2]");
  private final By labelTrialOnLesson = By.xpath(
      "//div[@class='skill editable']/../following-sibling::span");
  private final By duration2h = By.xpath("//button[@data-itemid='3']");
  private final By typeLessonTrialSelected = By.xpath(
      "//button[@class='btn btn-primary btn-trial-change']");
  private final By typeLessonTrialDefault = By.xpath(
      "//button[@class='btn btn-default btn-trial-change']");
  private final By hours = By.xpath("(//div[@class='change-panel']//span)[1]");
  private final By dropdownDurationInPopup = By.xpath("//select[@id='durations']");
  private final By duration2hInPopup = By.xpath("//select[@id='durations']//option[@value='2']");
  private final By btnSaveDurationInPopup = By.xpath("//button[contains(@class,'save')]");
  private final By labelPeriodLessonInStudent = By.xpath("//span[@class='text-muted small']");
  private final By labelPeriodLesson = By.xpath(
      "(//div[@class='text-muted header-labels']//span)[1]");
  private final By clickAroundChildInLesson = By.xpath("//div[contains(@class,'ist-group-item')]");
  private final By btnClosePopup = By.xpath("//button[@class='close']");
  private final By checkboxIF = By.xpath("//input[@id-qa='isIndividual-checkbox']");
  private final By elementWithCalendar = By.xpath("//div[contains(@class,'child-schedule')]");
  private final By btnScheduleInProfile = By.xpath("(//div[@class='links']//a)[2]");
  private final By labelIFInCalendar = By.xpath("//span[text()='ИФ']");
  private final By btnWorkingOff = By.xpath("//button[contains(@class,'working-off')]");
  private final By lessonsForWorkingOff = By.xpath(
      "//div[@class='modal-content']//div[contains(@class,'tem-cell')]");
  private final By btnRecordOnWorkingOffInPopup = By.xpath(
      "//div[@class='modal-footer']//button[contains(@class,'assign')]");
  private final By trainerInWorkingOffPopup = By.xpath(
      "//li[@class='list-group-item list-group-item-warning']//label//span");
  private final By labelWorkingOffInCalendar = By.xpath(
      "//span[contains(@class,'label-danger')][text()='Отработка']");

  public ScheduleHelper(WebDriver wd) {
    super(wd);
  }

  public By getLabelBusyChild() {
    return labelBusyChild;
  }

  public By getBtnMoveInPopup() {
    return btnMoveInPopup;
  }

  public By getBtnCreateSchedule() {
    return btnCreateSchedule;
  }

  public By getLabelBlockSchedule() {
    return labelBlockSchedule;
  }

  public By getLabelInformationAboutSchedule() {
    return labelInformationAboutSchedule;
  }

  public By getLabelCancelSchedule() {
    return labelCancelSchedule;
  }

  public By getStudentOnLessonInSchedule() {
    return studentOnLessonInSchedule;
  }

  public By getLabelTrialOnLesson() {
    return labelTrialOnLesson;
  }

  public By getDuration2h() {
    return duration2h;
  }

  public By getSelectFirst1h() {
    return selectFirst1h;
  }

  public By getSelectSecond1h() {
    return selectSecond1h;
  }

  public By getTypeLessonTrialSelected() {
    return typeLessonTrialSelected;
  }

  public By getSelectMove() {
    return selectMove;
  }

  public By getCheckboxIF() {
    return checkboxIF;
  }

  public By getTypeLessonTrialDefault() {
    return typeLessonTrialDefault;
  }

  public By getElementWithCalendar() {
    return elementWithCalendar;
  }

  public By getLabelIFInCalendar() {
    return labelIFInCalendar;
  }

  public By getLessonsForWorkingOff() {
    return lessonsForWorkingOff;
  }

  public By getTrainerInWorkingOffPopup() {
    return trainerInWorkingOffPopup;
  }

  public By getLabelWorkingOffInCalendar() {
    return labelWorkingOffInCalendar;
  }

  public void btnCreateSchedule() {
    clickWithMoveToElementAndWait(10, btnCreateSchedule);
  }

  public void checkBoxConst() {
    click(checkBoxConst);
  }

  private void selectMove() {
    click(selectMove);
  }

  private void selectBlock() {
    clickWithMoveToElementAndWait(10, selectBlock);
  }

  private void selectAssign() {
    click(selectAssign);
  }

  public void writeNote(String note) {
    waitVisibilityOfElementLocated(5, typeDesc);
    wd.findElement(typeDesc).clear();
    type(typeDesc, note);
  }

  private void selectOnAllSchedule() {
    clickWithMoveToElementAndWait(10, radioButtonAll);
  }

  public void selectTime() {
  }

  private void selectCancel() {
    clickWithMoveToElementAndWait(5, selectCancel);
  }

  public void btnCreate() {
    click(btnCreate);
  }

  public void bntPoints() {
    clickWithMoveToElementAndWait(10, btnPoints);
  }

  public void createSingleSchedule() {
    btnCreateSchedule();
    checkBoxConst();
    selectTime();
    selectAnyTrainer();
    btnCreate();
  }

  private void selectAnyTrainer() {
    type(selectListTrainerInCreate, "Бокша");
    JSClickByXY(217, 417);
  }

  public void createSingleScheduleTest() {
    btnCreateSchedule();
    checkBoxConst();
    selectTime();
    btnCreate();
  }

  public void createRegularSchedule() {
    btnCreateSchedule();
    selectTime();
    selectAnyTrainer();
    btnCreate();
    noErrorMessage();
    refresh();
  }

  public void move(String period, String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectMove();
    btnCalendar();
    selectDateToday();
    fillFormMove(period);
    btnMove();
    noErrorMessage();
    refresh();
  }

  public void badMoveOccupied(String period, String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectMove();
    btnCalendar();
    selectDateToday();
    fillFormMove(period);
    btnMoveDisabled();
    noErrorMessage();
  }

  public void badMove(String id) {
    selectScheduleInListUIById(id);
    bntPoints();
    selectMove();
    btnCalendar();
    selectDateToday();
    fillBadTime();
    btnMoveDisabled();
    refresh();
  }

  private void selectDateToday() {
    click(selectDataToday);
  }

  private void btnCalendar() {
    click(btnCalendar);
  }

  public void badMoveNotChangeDateTime(String idSchedule) {
    selectScheduleInListUIById(idSchedule);
    bntPoints();
    selectMove();
    fillMoveOnlyTrainer();
    btnMoveDisabled();
    refresh();
  }

  private void fillMoveOnlyTrainer() {
    clickWithMoveToElementAndWait(5, selectListTrainer);
    click(selectTrainerWithId7);
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
    clickWithMoveToElementAndWait(10, radioButtonAll);
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

  public void assignTrainerAll(String idSchedule) {
    selectScheduleInListUIById(idSchedule);
    bntPoints();
    selectAssign();
    selectOnAllSchedule();
    selectNewTrainer();
    btnAssign();
    noErrorMessage();
  }

  private void btnMove() {
    clickWithMoveToElementAndWait(10, btnMove);
  }

  private void btnMoveDisabled() {
    String dis = wd.findElement(btnMove).getAttribute("disabled");
    assert (dis.equals("true"));
  }

  private void btnBlock() {
    clickWithMoveToElementAndWait(5, btnBlock);
  }

  private void btnAssign() {
    click(btnAssign);
  }

  private void btnCancel() {
    clickWithMoveToElementAndWait(10, btnCancel);
  }

  private void selectNewTrainer() {
    clickWithMoveToElementAndWait(5, selectListTrainer);
    click(selectTrainerWithId18);
  }

  public void selectScheduleInListUIById(String id) {
    // находим пагинатор
    waitVisibleElement(5, btnNextInPaginator);
    String next =
        wd.findElement(btnNextInPaginator).getAttribute("class");
    // есть ли на первой странице наше занятие
    List<WebElement> list = wd.findElements(By.xpath("//a[contains(@href,'" + id + "')]"));
    if (list.size() > 0) {
      wd.findElement(By.xpath("//a[contains(@href,'" + id + "')]")).click();
    } else {
      // если занятие не на первой странице, надо нажать пагинатор, пока не найдем
      while (!next.equals("disabled")) {
        List<WebElement> list_pagin =
            wd.findElements(By.xpath("//a[contains(@href,'" + id + "')]"));
        if (list_pagin.size() > 0) {
          wd.findElement(By.xpath("//a[contains(@href,'" + id + "')]")).click();
          break;
        } else {
          wd.findElement(btnNextInPaginator).click();
        }
      }
    }
  }

  private void fillFormMove(String period) {
    // выбор времени (крайнее на сегодня)
    WebElement Selectbox_times = wd.findElement(selectListTime);
    Select select1 = new Select(Selectbox_times);
    select1.selectByVisibleText(period);

    // выбор тренера
    clickWithMoveToElementAndWait(5, selectListTrainer);
    click(selectTrainerWithId7);
  }

  private void fillBadTime() {
    wd.findElement(By.xpath("//div[@class='modal-body']")).click();
    wd.findElement(By.id("tp")).click();
    new Select(wd.findElement(By.id("tp"))).selectByVisibleText("02:30 - 04:30");
    wd.findElement(By.id("tp")).click();
  }

  public void recordStudentOn2h(String name, String idSchedule) throws InterruptedException {
    selectScheduleInListUIById(idSchedule);
    bntRecordStudent();
    selectStudent(name);
    selectNo();
    btnRecord();
    noErrorMessage();
  }

  private void btnRecord() {
    clickWithMoveToElementAndWait(10, btnRecord);
  }

  private void selectNo() throws InterruptedException {
    Thread.sleep(3000);
    click(selectNo);
  }

  private void selectStudent(String name) {
    type(typeChildName, name);
    if (isElementPresent(searchResult)) {
      click(searchResult);
    } else {
      wd.findElement(typeChildName).clear();
      type(typeChildName, name);
      click(searchResult);
    }
  }

  private void bntRecordStudent() {
    click(btnRecord);
  }

  public void recordStudentOnFirst1h(String name, String idSchedule) throws InterruptedException {
    selectScheduleInListUIById(idSchedule);
    bntRecordStudent();
    selectStudent(name);
    selectNo();
    selectFirst();
    btnRecord();
    noErrorMessage();
    refresh();
  }

  private void selectFirst() {
    click(selectFirst1h);
  }

  public void recordStudentOnSecond1h(String name, String idSchedule) throws InterruptedException {
    selectScheduleInListUIById(idSchedule);
    bntRecordStudent();
    selectStudent(name);
    selectNo();
    selectSecond();
    btnRecord();
    noErrorMessage();
    refresh();
  }

  public void recordStudentOnTrialSecond1h(String name, String idSchedule) {
    selectScheduleInListUIById(idSchedule);
    bntRecordStudent();
    selectStudent(name);
    selectSecond();
    btnRecord();
    noErrorMessage();
    refresh();
  }

  public void recordStudentOnTrialFirst1h(String name, String idSchedule) {
    selectScheduleInListUIById(idSchedule);
    bntRecordStudent();
    selectStudent(name);
    btnRecord();
    noErrorMessage();
    refresh();
  }

  private void selectSecond() {
    click(selectSecond1h);
  }

  public void removeStudent(String idSchedule) {
    selectScheduleInListUIById(idSchedule);
    clickEmptyArea();
    bntRemove();
    noErrorMessage();
    alertDeleteStudent();
    noErrorMessage();
  }

  private void alertDeleteStudent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    noErrorMessage();
  }

  private void bntRemove() {

    new WebDriverWait(wd, 10).until(ExpectedConditions.elementToBeClickable(btnRemove)).click();
    if (!isElementPresent(By.cssSelector("div.modal-header"))) {
      refresh();
      clickEmptyArea();
      click(btnRemove);
    }
  }

  private void clickEmptyArea() {
    click(By.xpath("//div[contains(@class,'list')]"));
  }

  public void recordStudentOnTrial(String name, String idSchedule) {
    selectScheduleInListUIById(idSchedule);
    bntRecordStudent();
    selectStudent(name);
    btnRecord();
    noErrorMessage();
    refresh();
    waitElementWithAttribute(5, By.xpath("//div[@class='name-block']//a"), "text", name);
  }

  public void tryChangeDurationInUsualTrial(String idSchedule) {
    selectScheduleInListUIById(idSchedule);
    clickEmptyArea();
  }

  public void openRecordOnTrial(String name, String idSchedule) {
    selectScheduleInListUIById(idSchedule);
    bntRecordStudent();
    selectStudent(name);
  }

  public void recordOnRegularIF(String name, String idSchedule) {
    selectScheduleInListUIById(idSchedule);
    bntRecordStudent();
    selectStudent(name);
    btnRecord();
    noErrorMessage();
  }

  public void waitForLoadTextCenterOnMainSchedule() {
    new WebDriverWait(wd, 10)
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[@class='text-center']")));
  }

  public void btnClosePopup() {
    click(btnClosePopup);
  }

  public void clickOnLessonWithChild() {
    click(clickAroundChildInLesson);
  }

  public int getDuration() {
    String[] period = wd.findElement(labelPeriodLessonInStudent).getText().split(" ");
    String[] timeFirst = period[0].split(":");
    String[] timeSecond = period[2].split(":");
    return Integer.parseInt(timeSecond[0]) - Integer.parseInt(timeFirst[0]);
  }

  public int getDiffHoursStart() {
    String[] periodLessonStudent = wd.findElement(labelPeriodLessonInStudent).getText().split(" ");
    String[] timeFirst = periodLessonStudent[0].split(":");
    String[] periodLesson = wd.findElement(labelPeriodLesson).getText().split(" ");
    String[] timeFirstLesson = periodLesson[0].split(":");
    return Integer.parseInt(timeFirst[0]) - Integer.parseInt(timeFirstLesson[0]);
  }

  public void changeDuration() {
    click(hours);//часики
    click(dropdownDurationInPopup);//дропдаун
    click(duration2hInPopup);//значение
    click(btnSaveDurationInPopup);
  }

  public void archivingSingleSchedule(String idSchedule) {
    selectScheduleInListUIById(idSchedule);
    click(btnPoints);
    click(selectArchive);
    btnBlock();
    click(btnArchive);
    noErrorMessage();
  }

  public void restore() {
    bntPoints();
    selectRestore();
  }

  private void selectRestore() {
    JSclick(selectRestore);
  }

  public void restoreSecondRegularLesson(String idSchedule) {
    for (int i = 1; i <= 7; i++) {
      click(btnNextInPaginator);
    }
    selectScheduleInListUIById(idSchedule);
    bntPoints();
  }

  public void selectCheckboxIF() {
    JSclick(checkboxIF);
  }

  public void dropdownTime() {
    click(By.xpath("(//div[@class='createSchoolSchedule-body']//div)[14]"));
  }

  public void checkPeriods() {
    List<WebElement> list = wd.findElements(By.xpath("//div[contains(@class,'item')]"));
    for (int i = 0; i < list.size() - 2; i++) {
      String period = list.get(i).getText();
      String[] periodParse = period.split(" ");
      String[] periodFirst = periodParse[0].split(":");
      String[] periodSecond = periodParse[2].split(":");
      assertThat(Integer.parseInt(periodSecond[0]) - Integer.parseInt(periodFirst[0]), equalTo(1));
    }
  }

  public void createSingleIFSchedule() {
    btnCreateSchedule();
    checkBoxConst();
    checkboxIF();
    selectTime();
    selectAnyTrainer();
    btnCreate();
  }

  private void checkboxIF() {
    JSclick(checkboxIF);
  }

  public void chooseDefaultStudentFoRecordOnIF(String idSchedule) {
    selectScheduleInListUIById(idSchedule);
    bntRecordStudent();
    selectStudent("Дефолтный");
  }

  public void goInCalendar(String name) {
    clickByHrefStudent(name);
    btnSchedule();
  }

  public void btnSchedule() {
    click(btnScheduleInProfile);
  }

  private void clickByHrefStudent(String name) {
    click(By.xpath("//a[text()='" + name + "']"));
  }

  public void btnWorkingOff() {
    if (!isElementPresent(btnWorkingOff)) {
      click(btnPrevInPaginator);
    }
    click(btnWorkingOff);
  }

  public void recordOnWorkingOff() {
    clickWithMoveToElementAndWait(3, trainerInWorkingOffPopup);
    clickWithMoveToElementAndWait(3, btnRecordOnWorkingOffInPopup);
    noErrorMessage();
  }

  public void checkShowWorkingOff() {
    if (!isElementPresent(labelWorkingOffInCalendar)) {
      click(btnNextInPaginator);
    }
  }
}
