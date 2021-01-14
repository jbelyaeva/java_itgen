package app.appmanager;

import static app.appmanager.ApplicationManager.properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LkStudentHelper extends HelperBase {

  private final By header = By.xpath("//div[@class='arrow']");
  private final By goOutFromAccount = By.xpath("//ul[contains(@class,'list')]/li[4]");
  private final By btnCloseTutorial = By.xpath("//button[@id-qa='cancel']");
  private final By btnImReady = By.xpath("//div[contains(@class,'preview')]//button");
  private final By btnGotoLesson = By.xpath("//button[@tutorialtarget-id='child-go-to-lesson']");
  private final By messageReadyFromTrainer = By.xpath("//div[contains(@class,'preview')]//span");
  private final By tabTests = By.xpath("//button[@id-qa='testing']");
  private final By dropDownInHistory = By.xpath("//div[contains(@class,'dropdown')]");
  private final By dropDownInHistoryViewAll = By.xpath("//button[@id-qa='view-all']");
  private final By closeHistory = By.xpath("(//button)[9]");
  private final By tabSettings = By.xpath("//button[@id-qa='settings']");
  private final By btnChat = By.xpath("//div[@class='chat-button']");
  private final By winTutorial = By.xpath("//div[contains(@class,'tutorials')]");
  private final By tabFeed = By.xpath("//button[@id-qa='feed']");
  private final By tabFeedInCommunity = By.xpath("//div[@role='tablist']//button[@id-qa='feed']");
  private final By tabSchedule = By.xpath("//button[@id-qa='schedule']");
  private final By tabCommunities = By.xpath("//button[@id-qa='communities']");
  private final By btnSubscribe = By.xpath("//button[@id-qa='subscribe']");
  private final By btnUnsubscribe = By.xpath("//button[@id-qa='unsubscribe']");
  private final By btnLike = By.xpath("//span[@id-qa='like']");
  private final By btnCreateComment = By.xpath(
      "//button[@id='pick-attachments-application']/../following-sibling::button");
  private final By typeCommentText = By.xpath("//div[@id-qa='comment-text']");
  private final By tabAll = By.xpath("//div[@role='tablist']//button[@id-qa='all']");
  private final By btnPoint = By.xpath("//div[@class='right']//button");
  private final By tabAdministrator = By.xpath(
      "//div[@role='tablist']//button[@id-qa='administration']");
  private final By countLikes = By.xpath("//span[@class='likes-count liked']");
  private final By postCommunity = By.xpath(
      "//div[@class='post-comments']//span[@class='multiline-text']");
  private final By btnPointsInCommunities = By.xpath("//div[@class='right']//button");
  private final By btnEditPost = By.xpath("//span[@id-qa='menu-edit']");
  private final By btnDeletePost = By.xpath("//span[@id-qa='menu-delete']");
  private final By btnAllSchedule = By.xpath("//button[@id-qa='schedule']");

  public LkStudentHelper(WebDriver wd) {
    super(wd);
  }

  public By getCountLikes() {
    return countLikes;
  }

  public By getPostCommunity() {
    return postCommunity;
  }

  public By getTabAdministrator() {
    return tabAdministrator;
  }

  public By getTabFeed() {
    return tabFeed;
  }

  public By getBtnPointsInCommunities() {
    return btnPointsInCommunities;
  }

  public By getBtnEditPost() {
    return btnEditPost;
  }

  public By getBtnDeletePost() {
    return btnDeletePost;
  }

  public void logout() {
    click(header);
    click(goOutFromAccount);
  }

  public void goOnLesson() {
    if (isElementPresent(btnCloseTutorial)) {
      btnCloseTutorial();
    }
    btnGoToLesson();
  }

  public void btnImReady() {//закрыть несколько туториалов
    if (isElementPresent(btnCloseTutorial)) {
      btnCloseTutorial();
    }
    if (isElementPresent(btnCloseTutorial)) {
      btnCloseTutorial();
    }
    clickWithMoveToElementAndWait(3, btnImReady);
  }

  public void btnAllSchedule() {
    click(btnAllSchedule);
  }

  private void btnGoToLesson() {
    if (isElementPresent(btnCloseTutorial)) {
      btnCloseTutorial();
    }
    clickWithMoveToElementAndWait(5, btnGotoLesson);
  }

  public void btnLogo() {
    click(By.xpath("//img[contains(@src,'logo')]"));
    noErrorMessage();
  }

  public String getMessageTrainer() {
    String message = wd.findElement(messageReadyFromTrainer).getText();
    btnLogo();
    return message;
  }

  public void goToStudentProfileTabTests() {
    btnCloseTutorial();
    click(tabTests);
  }

  public void skipHelper() {
    deleteAlerts();
    goInAccountStudentAfterChanged();
    btnCloseTutorial();
  }

  public void btnCloseTutorial() {
    try {
      clickWithMoveToElementAndWait(5, btnCloseTutorial);
    } catch (TimeoutException e) {
      System.out.println("Исключение:" + e);
    }
  }

  public void goToHistory() {
    btnHistory();
    openDropDown();
    deleteAlerts();
  }

  private void openDropDown() {
    clickWithMoveToElementAndWait(5, dropDownInHistory);
  }

  public void btnHistory() {
    clickWaitElementToBeClicable(5, dropDownInHistoryViewAll);
  }

  public void closeHistory() {
    clickWithMoveToElementAndWait(7, closeHistory);
  }

  public void goToCheckConnection() {
    clickWithMoveToElementAndWait(5, tabSettings);
  }

  public void openChat() {
    if (isElementPresent(btnCloseTutorial)) {
      btnCloseTutorial();
    }
    clickWithMoveToElementAndWait(5, btnChat);
  }

  public void goToLesson() {
    btnGoToLesson();
    btnImReady();
  }

  public Boolean findTutorials() {
    return isElementPresent(winTutorial);
  }

  public void goInAccountStudentAfterChanged() {
    refresh();
    wd.get(address() + "/login");
    login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  public boolean openCheckConnection() {
    return isElementPresent(By.xpath("//h2"));
  }

  public void goToFeed() {
    if (isElementPresent(btnCloseTutorial)) {
      btnCloseTutorial();
    }
    click(tabFeed);
  }

  public boolean goOnSchedule() {
    btnSchedule();
    assert (wd.findElement(By.xpath("//h2")).getText().equals("Мое расписание"));
    return isElementPresent(By.xpath("//h2"));
  }

  private void btnSchedule() {
    click(tabSchedule);
  }

  public void btnCommunities() {
    click(tabCommunities);
  }

  public void subscribeOnCommunity() {
    btnCommunities();
    btnSubscribe();
  }

  private void btnSubscribe() {
    click(btnSubscribe);
  }

  public void unsubscribeOnCommunity() {
    btnCommunities();
    btnUnsubscribe();
  }

  private void btnUnsubscribe() {
    click(btnUnsubscribe);
  }

  public void addLike() {
    click(btnLike);
  }

  public void addCommentInFeed(String comment) {
    refresh();
    goToFeed();
    fillNewComment(comment);
    btnCreateComment();
  }

  private void btnCreateComment() {
    click(btnCreateComment);
  }

  private void fillNewComment(String comment) {
    clickWaitElementToBeClicable(5, By.xpath("//div[@id-qa='wrap']"));
    clear(50, typeCommentText);
    clickWithMoveToElementAndWait(1, typeCommentText);
    JavascriptExecutor exe = (JavascriptExecutor) wd;
    WebElement element = wd.findElement(typeCommentText);
    exe.executeScript("arguments[0].innerHTML='" + comment + "';", element);
    Actions action = new Actions(wd);
    action.sendKeys(Keys.ENTER).build().perform();
  }

  public void tabAll() {
    click(tabAll);
  }

  public void btnPoint() {
    click(btnPoint);
  }

  public void goInProfile() {
    click(By.xpath("//div[@class='head']"));
    clickWithMoveToElementAndWait(5, By.xpath("//li[2]//button"));
  }

  public void tabAdministration() {
    click(tabAdministrator);
  }

  public void tabFeedInCommunity() {
    click(tabFeedInCommunity);
  }
}
