package io.itgen.appmanager;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.tasks.TaskData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TaskHelper extends HelperBase {

  public TaskHelper(WebDriver wd) {
    super(wd);
  }

  public void btnAdd() {
    click(By.xpath("//div[@class='actions']//button"));
  }

  public void addNewTask(TaskData task) {
    btnAdd();
    fillFormTask(task);
    btnSaveNewTask();
  }

  private void btnSaveNewTask() {
    click(By.xpath("//button[contains(@class,'create')]"));
  }

  private void fillFormTask(TaskData task) {
    click(By.xpath("//div[@class='form-group']//input"));
    type(By.xpath("//div[@class='form-group']//input"), task.getText());

    type(By.xpath("//div[@class='input-group']//input[@name='user-name']"), "Admin");
    click(By.xpath("(//span[contains(@class,'result')])[1]"));

    dropDownList_Integer(By.xpath("//select[@id='task-priority']"), task.getPriority());
    enterADate(
        By.name("task-date"), task.getDateUi());
    type(By.xpath("//input[contains(@class,'task-time')]"), task.getTimeUi());

    type(By.xpath("//input[contains(@class,'child-name')]"), task.getUserUi().substring(0, 5));
    click(By.xpath("//li[@class='result']//span"));
  }

  public void doneTaskInStek() {
    btnLowPriority();
    btnPoints();
    btnDoneManualTask();
  }

  private void btnDoneManualTask() {
    click(By.xpath("(//ul[contains(@class,'dropdown')]//button)[2]"));
  }

  private void btnPoints() {
    try {
      clickWaitElementToBeClicable(5,By.xpath("//button[contains(@class,'dropdown')]"));
    }catch (ElementClickInterceptedException e){
      btnClosePopup();
      goToPopup();
      clickWaitElementToBeClicable(5,By.xpath("//button[contains(@class,'dropdown')]"));
    }
  }

  public void btnLowPriority() {
    click(By.xpath("(//div[@role='group'])[3]//button[2]"));
  }

  public void btnMiddlePriority() {
    click(By.xpath("(//div[@role='group'])[3]//button[1]"));
  }

  public void takeAutoTask() {
    click(By.xpath("(//div[@class='actions']//button)[2]"));
  }

  public void doneAutoTask() {
    btnPoints();
    btnDoneAutoTask();
  }

  private void btnDoneAutoTask() {
    click(By.xpath("(//ul[contains(@class,'dropdown')]//button)[1]"));
  }

  public void waitAnswerInStack() {
    btnLowPriority();
    btnPoints();
    btnWaitAnswer();
  }

  public void deleteInStack() {
    btnLowPriority();
    btnPoints();
    btnDelete();
    assertDelete();
    noErrorMessage();
  }

  public void assertDelete() {
    click(By.cssSelector("div.modal-header"));
    clickWithMoveToElementAndWait(5,By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(
        isElementPresent(By.cssSelector("[id^=alert]")));
  }

  private void btnDelete() {
    click(By.xpath("(//ul[contains(@class,'dropdown')]//button)[5]"));
  }

  private void btnWaitAnswer() {
    click(By.xpath("(//ul[contains(@class,'dropdown')]//button)[1]"));
  }

  public void deleteAutoTask() {
    btnPoints();
    btnDaleteAutoTask();
    assertDelete();
    noErrorMessage();
  }

  private void btnDaleteAutoTask() {
    click(By.xpath("(//ul[contains(@class,'dropdown')]//button)[3]"));
  }

  public void takeOnControlAutoTaskInStack() {
    btnPoints();
    btnTakeOnControlAutoTask();
  }

  private void btnTakeOnControlAutoTask() {
    click(By.xpath("(//ul[contains(@class,'dropdown')]//button)[2]"));
  }

  public void selectOnTomorrowInStack() {
    btnLowPriority();
    btnPoints();
    btnOnTomorrowTask();
  }

  private void btnOnTomorrowTask() {
    click(By.xpath("(//ul[contains(@class,'dropdown')]//button)[3]"));
  }

  public void takeOnControManualTaskInStack() {
    btnLowPriority();
    btnPoints();
    btnOnControlManualTask();
  }

  private void btnOnControlManualTask() {
    click(By.xpath("(//ul[contains(@class,'dropdown')]//button)[4]"));
  }

  public void filtrByUserInStek(String user) {
    type(By.xpath("(//div[@class='filter']//input)[4]"), user);
    wd.findElement(By.xpath("(//div[@class='filter']//input)[4]")).sendKeys(Keys.DOWN);
    wd.findElement(By.xpath("(//div[@class='filter']//input)[4]")).sendKeys(Keys.ENTER);
  }

  public Integer getCountSearchUI() {
    String text = wd.findElement(By.xpath("//div[@class='panel-footer']//p")).getText();
    String[] elementsText = text.split(":");
    Integer count = Integer.valueOf((elementsText[1]).trim());
    return count;
  }

  public String getNameSearchUI() {
    List<WebElement> list = wd.findElements(By.xpath("//tr[@class='tasks-list-item']//td[4]"));
    assertThat(list.size(), equalTo(1));
    return wd.findElement(By.xpath("//tr[@class='tasks-list-item']//td[4]")).getText();
  }

  public String getNameClientUI() {
    List<WebElement> list = wd.findElements(By.xpath("//tr[@class='tasks-list-item']//td[5]"));
    assertThat(list.size(), equalTo(1));
    return wd.findElement(By.xpath("//tr[@class='tasks-list-item']//td[5]")).getText();
  }

  public ArrayList<String> getNamesClientUI() {
    List<String> namesClient = new ArrayList<>();
    List<WebElement> names = wd.findElements(By.xpath("//tr[@class='tasks-list-item']//td[5]"));
    for (WebElement name : names) {
      namesClient.add(name.getText());
    }
    return (ArrayList<String>) namesClient;
  }

  public Integer getCountInTabUI() {
    String text = wd.findElement(By.xpath("(//div[@class='actions']//span)[4]")).getText();
    return Integer.valueOf(text);
  }

  public Integer getCountInMenuUI() {
    String text = wd.findElement(By.xpath("//div[contains(@class,'alert')]")).getText();
    return Integer.valueOf(text);
  }

  public void selectCreator() {
    click(By.name("createdByMe"));
  }

  public void selectAssigner() {
    click(By.name("assigneedToMe"));
  }

  public void selectStatusDone() {
    click(By.xpath("//div[@role='group']//button[3]"));
  }

  public void selectStatusWaitAnswer() {
    click(By.xpath("//div[@role='group']//button[2]"));
  }

  public void changeTitleManualTaskInPopup(String newTitle) {
    goToPopup();
    selectChangeTitle();
    fillNewTitle(newTitle);
    clickOnFullArea();
    btnClosePopup();
  }

  private void btnClosePopup() {
    click(By.className("close"));
  }

  private void clickOnFullArea() {
    click(By.xpath("//div[@class='tasks-task']"));
  }

  private void fillNewTitle(String newTitle) {
    type(By.xpath("//div[contains(@class,'task-text')]"), newTitle);
  }

  private void selectChangeTitle() {
    clickWithMoveToElementAndWait(5,By.xpath("//div[@class='editable']"));
  }

  public void goToPopup() {
    clickWithMoveToElementAndWait(5,By.xpath("//tbody//tr//td[2]"));
  }

  public void changePriorityManualTaskInPopup(String priority) {
    wd.manage().timeouts().implicitlyWait(1000,TimeUnit.MICROSECONDS);
    goToPopup();
    selectChangePriority();
    changePriority(priority);
    btnClosePopup();
    wd.manage().timeouts().implicitlyWait(0,TimeUnit.MICROSECONDS);
  }

  private void changePriority(String priority) {
    try {
      clickWaitElementToBeClicable(5,By.xpath("//select[@class='task-priority']"));
    }catch (TimeoutException e){
      btnClosePopup();
      goToPopup();
      changePriority(priority);
      clickWaitElementToBeClicable(5,By.xpath("//select[@class='task-priority']"));
    }
    click(By.xpath("//select[@class='task-priority']//option[@value='" + priority + "']"));
  }


  private void selectChangePriority() {
    clickWithMoveToElementAndWait(5,By.xpath("(//div[@class='editable'])[3]"));
  }

  public void changeAssigneeManualTaskInPopup(String newAssignee) {
    goToPopup();
    selectChangeAssignee();
    changeUser(newAssignee);
    btnClosePopup();
  }

  private void changeUser(String newAssignee) {
    moveToElement(5, By.xpath("//div[@class='input-group']//input"));
    click(By.xpath("//div[@class='input-group']//input"));
    type(By.xpath("//div[@class='input-group']//input"), newAssignee);
    click(By.xpath("//span[contains(@class,'result')]"));
  }

  private void selectChangeAssignee() {
    moveToElement(5, By.xpath("(//div[@class='modal-content']//div[@class='editable'])[2]"));
    clickWithMoveToElementAndWait(1, By.xpath("(//span[contains(@class,'pencil')])[2]"));
  }

  public void changeDateManualTaskInPopup() {
    goToPopup();
    wd.manage().timeouts().implicitlyWait(500, TimeUnit.MICROSECONDS);
    selectChangeDateByManualTask();
    changeDateNextDay();
    clickOnFullArea();
    btnClosePopup();
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.MICROSECONDS);
  }

  private void changeDateNextDay() {
    click(By.xpath("//span[contains(@class,'picker')]"));
    if (isElementPresent(
        By.xpath("//td[contains(@class,'day active')]//following-sibling::td[1]"))) {
      click(By.xpath("//td[contains(@class,'day active')]//following-sibling::td[1]"));
    } else {
      click(By.xpath(
          "//td[contains(@class,'day active')]/ancestor-or-self::tr//following-sibling::tr[1]//td[1]"));
    }
  }

  private void selectChangeDateByManualTask() {
    moveToElement(5, By.xpath("(//div[contains(@class,'editable')])[7]"));
    clickWithMoveToElementAndWait(1, By.xpath("(//div[contains(@class,'editable')])[7]"));
  }

  public void deleteTaskInPopup() {
    goToPopup();
    btnPoints();
    btnDeletePopup();
    assertDelete();
    noErrorMessage();
  }

  private void btnDeletePopup() {
    try {
      clickWaitElementToBeClicable(5,By.xpath("//a[contains(@class,'remove')]"));
    }catch (ElementClickInterceptedException e){
      btnClosePopup();
      goToPopup();
      btnPoints();
      click(By.xpath("//a[contains(@class,'remove')]"));
    }
  }

  public void doneManualTaskInPopup() {
    goToPopup();
    btnPoints();
    btnDonePopup();
    btnClosePopup();
  }

  private void btnDonePopup() {
    click(By.xpath("//a[contains(@class,'close')]"));
  }

  public void waitAnswerManualTaskInPopup() {
    goToPopup();
    btnPoints();
    btnWaitAnswerPopup();
    btnClosePopup();
  }

  private void btnWaitAnswerPopup() {
    click(By.xpath("//a[contains(@class,'wait')]"));
  }

  public void onTomorrowManualTaskInPopup() {
    goToPopup();
    btnPoints();
    btnOnTomorrowPopup();
    btnClosePopup();
  }

  private void btnOnTomorrowPopup() {
    click(By.xpath("//a[contains(@class,'tomorrow')]"));
  }

  public void onControlManualTaskInPopup() {
    goToPopup();
    btnPoints();
    btnOnControlPopup();
    btnClosePopup();
  }

  private void btnOnControlPopup() {
    click(By.xpath("//a[contains(@class,'watch')]"));
  }

  public void changeAssigneeAutoTaskInPopup(String newAssignee) {
    goToPopup();
    selectChangeAssigneeForAutoTask();
    changeUser(newAssignee);
    btnClosePopup();
  }

  private void selectChangeAssigneeForAutoTask() {
    moveToElement(5, By.xpath("(//div[@class='modal-content']//div[@class='editable'])[1]"));
    clickWithMoveToElementAndWait(1, By.xpath("(//span[contains(@class,'pencil')])[1]"));
  }

  public void changeDateAutoTaskInPopup() {
    goToPopup();
    selectChangeDateByAutoTask();
    changeDateNextDay();
    clickOnFullArea();
    btnClosePopup();
  }

  private void selectChangeDateByAutoTask() {
    moveToElement(5, By.xpath("(//div[contains(@class,'editable')])[3]"));
    clickWithMoveToElementAndWait(1, By.xpath("(//div[contains(@class,'editable')])[3]"));
  }

  public void changeClientAutoTaskInPopup(String nameClient) {
    goToPopup();
    selectChangeClentForAutoTask();
    changeUser(nameClient);
    btnClosePopup();
  }

  private void selectChangeClentForAutoTask() {
    moveToElement(5, By.xpath("(//div[@class='modal-content']//div[@class='editable'])[2]"));
    clickWithMoveToElementAndWait(1, By.xpath("(//span[contains(@class,'pencil')])[3]"));
  }

  public void deleteAutoTaskInPopup() {
    goToPopup();
    btnPoints();
    btnDeletePopup();
    assertDelete();
    noErrorMessage();
  }

  public void doneAutoTaskInPopup() {
    goToPopup();
    btnPoints();
    btnDoneAutoTaskPopup();
    btnClosePopup();
  }

  private void btnDoneAutoTaskPopup() {
    click(By.xpath("//a[contains(@class,'complete')]"));
  }

  public void takeOnControlAutoTaskInPopup() {
    goToPopup();
    btnPoints();
    btnOnControlPopup();
    btnClosePopup();
  }

  public void leaveCommentTask(String comment) {
    goToPopup();
    fillComment(comment);
    bntAddComment();
    btnClosePopup();
  }

  private void bntAddComment() {
    click(By.xpath("//button[contains(@class,'add-comment')]"));
  }

  private void fillComment(String comment) {
    clickWithMoveToElementAndWait(5,By.xpath("//div[contains(@class,'new-comment')]"));
    WebElement element = wd.findElement(By.xpath("//div[contains(@class,'new-comment')]"));
    ((JavascriptExecutor) wd).executeScript(
        "var ele=arguments[0]; ele.innerHTML = '"+comment+"';",
        element);
  }

}
