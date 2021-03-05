package app.appmanager;

import data.services.TrainerService;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WindowScheduleHelper extends HelperBase {

  private final By selectTrainer = By.xpath("(//div[contains(@class,'select')])[3]");
  private final By btnIF = By.xpath("(//div[@class='lesson-formats']//button)[2]");
  private final By listTrainersInDropdown = By.xpath("//div[@id='menu-']//div//li");
  private final By trainerInInput = By.xpath("//div[@class='trainer-name']//div//div");
  private final By btnCreateRequests = By.xpath("//button[contains(@class,'create-request')]");
  private final By singleLesson = By.xpath("(//div[@class='lesson-types']//button)[2]");
  private final By btnRecord = By.xpath("(//div[@class='schedule-menu']//button)[1]");
  private final By selectLesson = By.xpath("//div[@class='group']//div[@class='trainer-name']");
  private final By selectRegular = By.xpath("(//div[@class='lesson-types']//button)[1]");
  private final By selectIF = By.xpath("(//div[@class='lesson-formats']//div//button)[2]");
  private final By createLessonInSchedule = By.xpath("//a[@href='/createSchedule']");
  private final By inputNameStudent = By.xpath("//div[@class='child-search dropdown']//input");
  private final By searchResultNameStudent = By.xpath("//span[contains(@class,'result')]");
  private final By skillInFilter = By.xpath("(//div[@class='filter']//div)[16]//div");
  private final By durationDisabledInFilter = By.xpath(
      "(//div[@class='filter']//div)[19]//div//div[contains(@class,'disabled')]");
  private final By groupLesson = By.xpath("//div[@class='group']");

  TrainerService trainerService = new TrainerService();

  public WindowScheduleHelper(WebDriver wd) {
    super(wd);
  }

  public By getSkillInFilter() {
    return skillInFilter;
  }

  public By getDurationDisabledInFilter() {
    return durationDisabledInFilter;
  }

  public By getGroupLesson() {
    return groupLesson;
  }

  public void recordStudentOn2hRegular(String name) {
    btnRecordOnLesson();
    selectStudent(name);
    selectRegular();
    selectLesson();
    btnRecord();
    noErrorMessage();
  }

  public void showWindowForStudentOn1hTrial(String name) {
    btnRecordOnLesson();
    selectStudent(name);
  }

  public void recordOn1hTrialOnFirstHour(String name) {
    btnRecordOnLesson();
    selectStudent(name);
    selectLesson();
    btnRecord();
    noErrorMessage();
  }

  public void recordStudentFromRequest() {
    selectLesson();
    btnRecord();
    noErrorMessage();
  }

  public void recordStudentOn2hRegularFromRequest() {
    selectRegular();
    selectLesson();
    btnRecord();
    noErrorMessage();
  }

  public void recordStudentOnIFFromRequest() {
    selectLesson();
    btnRecord();
    noErrorMessage();
  }

  private void btnRecord() {
    click(btnRecord);
  }

  private void selectLesson() {
    click(selectLesson);
  }

  private void selectRegular() {
    clickWithMoveToElementAndWait(10, selectRegular);
  }

  private void btnRecordOnLesson() {
    click(createLessonInSchedule);
  }

  private void selectStudent(String name) {
    type(inputNameStudent, name);
    if (isElementPresent(searchResultNameStudent)) {
      click(searchResultNameStudent);
    } else {
      wd.findElement(inputNameStudent).clear();
      type(inputNameStudent, name);
      click(searchResultNameStudent);
    }
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
    clickWithMoveToElementAndWait(10, singleLesson);
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

  public void makeRequestOnTrialScratch(String name) {
    btnRecordOnLesson();
    selectStudent(name);
    btnRequest();
    noErrorMessage();
  }

  private void btnRequest() {
    click(btnCreateRequests);
  }

  public void selectStudentForSshot(String name) {
    btnRecordOnLesson();
    selectStudent(name);
  }

  public void makeRequestOnRegular2hScratch(String name) {
    btnRecordOnLesson();
    selectStudent(name);
    selectRegular();
    btnRequest();
    noErrorMessage();
  }

  public void makeRequestOnSingle2hScratch(String name) {
    btnRecordOnLesson();
    selectStudent(name);
    selectSingle();
    btnRequest();
    noErrorMessage();
  }

  public void makeRequestToTrainer(String name, String idTrainer) {
    btnRecordOnLesson();
    selectStudent(name);
    selectSingle();
    selectDropdownTrainer();
    selectTrainer(idTrainer);
    btnRequest();
    noErrorMessage();
  }

  private void selectTrainer(String idTrainer) {
    click(By.xpath("//li[@data-value='" + idTrainer + "']"));

    TrainerService trainerService = new TrainerService();
    String surname = trainerService.findById(idTrainer).getLastName();
    String name = trainerService.findById(idTrainer).getFirstName();
    String[] split = wd.findElement(trainerInInput)
        .getText()
        .split("\n");
    if (!(surname + " " + name).equals(split[0])) {
      selectDropdownTrainer();
      click(By.xpath("//li[@data-value='" + idTrainer + "']")); //попробовать еще раз
    }
  }

  private void selectDropdownTrainer() {
    waitVisibleElement(3, By.xpath("//label[text()='Тренер']"));
    click(selectTrainer);
  }

  public void makeRequestOnIF(String name) {
    btnRecordOnLesson();
    refresh();
    selectStudent(name);
    btnIF();
    btnRequest();
    noErrorMessage();
  }

  private void btnIF() {
    click(btnIF);
  }

  public void requestOnFrenchPython(String name) {
    btnRecordOnLesson();
    selectStudent(name);
    waitVisibleElement(3, selectTrainer);
    selectDropdownTrainer();
  }

  public void makeRequestOnFrenchPython(String name) {
    btnRecordOnLesson();
    selectStudent(name);
    if (!isElementPresent(selectTrainer)) {
      refresh();
      selectStudent(name);
    }
    btnRequest();
    noErrorMessage();
  }

  public String[] getListTrainer() {
    List<WebElement> list = wd.findElements(listTrainersInDropdown);
    String[] listUI = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      listUI[i] = list.get(i).getText();
    }
    return listUI;
  }

  public void recordOnIFRegular1h(String name) {
    btnRecordOnLesson();
    selectStudent(name);
    selectIF();
    selectRegular();
    selectLesson();
    btnRecord();
    noErrorMessage();
  }

  private void selectIF() {
    click(selectIF);
  }
}
