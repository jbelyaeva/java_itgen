package app.appmanager;

import static app.appmanager.ApplicationManager.properties;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LkStudentHelper extends HelperBase {

  private final By header = By.xpath("//div[@class='arrow']");
  private final By goOutFromAccount = By.xpath("//ul[contains(@class,'list')]/li[4]");
  private final By btnCloseTutorial = By.xpath("//button[@id-qa='cancel']");
  private final By btnCloseTutorialTips = By.xpath("//div[@class='button-close']");
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
  private final By monthUI = By.xpath("//span[contains(@class,'capitalize')]");
  private final By menuInPostCommunity = By.xpath("//button[@id-q='menu']");
  private final By counterPosts = By.xpath("//span[@class='count']");
  private final By textFirstPost = By.xpath(
      "//div[@class='post-comments']//span[@class='multiline-text']");
  private final By titleCommunity = By.xpath("//h4[@id-qa='title']");
  private final By rightPaginatorInsSchedule = By.xpath("//button[contains(@class,'btn-next')]");
  private final By leftPaginatorInSchedule = By.xpath("//button[contains(@class,'btn-prev')]");
  private final By alongLessonInCalendar = By.xpath("//div[contains(@class,'lesson-container')]");
  private final By chatOnLesson = By.xpath("//div[@class='child-lesson-chat']");
  private final By alongMessageInChatOnLesson = By.xpath("//div[@class='message-text']//div");
  private final By alongMessageInChatPrewiev = By.xpath(
      "//div[@class='room-item']//div[@class='last-message']//span");
  private final By labelNotPosts = By.xpath("//div[@class='posts-not-exist-item']");
  private final By labelNotTests = By.xpath("//div[@class='childTesting-page']//span");
  private final By progressBar = By.xpath(
      "//div[@class='child-progress']//div[@class='progress-bar-item']");
  private final By btnShowAllProgressBar = By.xpath("//button[@id-qa='show-all']");
  private final By editTextMessageInChatOnLesson = By.xpath("//textarea[@id-qa='message']");
  private final By btnSendMessageInChatOnLesson = By.xpath("//button[@id-qa='send']");
  private final By dropdownInOldMessageInChatOnLesson = By.xpath("//span[@id-qa='dropdown']");
  private final By btnEditMessageInChatOnLesson = By.xpath("//a[@id-qa='edit']");
  private final By btnDeleteMessageInChatOnLesson = By.xpath("//a[@id-qa='remove']");
  private final By indicatorChat = By.xpath("//div[@class='chat-alert circle']");
  private final By editInformationAboutMyself = By.xpath("//textarea[1]");
  private final By fullAreaInoProfile = By.xpath("//div[@class='main-layout']");
  private final By alongCommunityScratch = By.xpath("//a[text()='Scratch']");
  private final By tabSubscribers = By.xpath("//button[@id-qa='subscribers']");
  private final By achievements = By.xpath("//div[@class='achievements-item no-progress']");
  private final By achievementsAll = By.xpath("//div[contains(@class,'achievements-item')]");
  private final By finishedHours = By.xpath("//div[@class='finished-hours']");
  private final By informationAboutMyselfInProfile = By.xpath(
      "//div[@class='about multiline-text']");
  private final By btnShowAllAchievement = By.xpath("//button[@id-qa='see-all']");
  private final By btnSaveComment = By.xpath("//button[@id-qa='save']");
  private final By textComment = By.xpath("//div[@class='text']//span[@class='multiline-text']");
  private final By dropdownLang = By.xpath("//div[@id-qa='langs']");
  private final By rusLang = By.xpath("//div[text()='Русский']");
  private final By enLang = By.xpath("//div[text()='Английский']");
  private final By allLang = By.xpath("//div[text()='Все']");
  private final By greenHeart = By.xpath("//span[contains(@class,'checked')]");

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

  public By getBtnImReady() {
    return btnImReady;
  }

  public By getMenuInPostCommunity() {
    return menuInPostCommunity;
  }

  public By getCounterPosts() {
    return counterPosts;
  }

  public By getTextFirstPost() {
    return textFirstPost;
  }

  public By getTitleCommunity() {
    return titleCommunity;
  }

  public By getChatOnLesson() {
    return chatOnLesson;
  }

  public By getAlongMessageInChatOnLesson() {
    return alongMessageInChatOnLesson;
  }

  public By getAlongMessageInChatPrewiev() {
    return alongMessageInChatPrewiev;
  }

  public By getTabAll() {
    return tabAll;
  }

  public By getTabFeedInCommunity() {
    return tabFeedInCommunity;
  }

  public By getLabelNotPosts() {
    return labelNotPosts;
  }

  public By getBtnSubscribe() {
    return btnSubscribe;
  }

  public By getBtnUnsubscribe() {
    return btnUnsubscribe;
  }

  public By getLabelNotTests() {
    return labelNotTests;
  }

  public By getProgressBar() {
    return progressBar;
  }

  public By getBtnShowAllProgressBar() {
    return btnShowAllProgressBar;
  }

  public By getIndicatorChat() {
    return indicatorChat;
  }

  public By getEditInformationAboutMyself() {
    return editInformationAboutMyself;
  }

  public By getAchievements() {
    return achievements;
  }

  public By getAchievementsAll() {
    return achievementsAll;
  }

  public By getFinishedHours() {
    return finishedHours;
  }

  public By getInformationAboutMyselfInProfile() {
    return informationAboutMyselfInProfile;
  }

  public By getBtnShowAllAchievement() {
    return btnShowAllAchievement;
  }

  public By getTextComment() {
    return textComment;
  }

  public By getBtnPoint() {
    return btnPoint;
  }

  public void logout() {
    click(header);
    click(goOutFromAccount);
  }

  public void goOnLesson() {
    if (isElementPresent(btnCloseTutorial)) {
      btnCloseTutorial();
    }
    refresh();
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
    btnCloseTutorial();
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
    if (isElementPresent(btnCloseTutorial)) {
      clickWithMoveToElementAndWait(5, btnCloseTutorial);
    }
  }

  public void btnCloseTutorialTips() {
    if (isElementPresent(btnCloseTutorialTips)) {
      clickWithMoveToElementAndWait(5, btnCloseTutorialTips);
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
    btnCloseTutorial();
    clickWithMoveToElementAndWait(2, tabCommunities);
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
    waitVisibleElement(5, greenHeart);
  }

  public void addCommentInFeed(String comment) {
    refresh();
    goToFeed();
    fillNewComment(comment);
    btnCreateComment();
    waitVisibilityOfElementLocated(5, counterPosts);
  }

  private void btnCreateComment() {
    click(btnCreateComment);
  }

  public void fillNewComment(String comment) {
    clickWaitElementToBeClicable(5, By.xpath("//div[@id-qa='wrap']"));
    clear(50, typeCommentText);
    clickWithMoveToElementAndWait(1, typeCommentText);
    JavascriptExecutor exe = (JavascriptExecutor) wd;
    WebElement element = wd.findElement(typeCommentText);
    exe.executeScript("arguments[0].innerHTML='" + comment + "';", element);
    Actions action = new Actions(wd);
    action.sendKeys(Keys.ENTER).build().perform();
  }

  public void fillEditComment(String comment) {
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
    clickWithMoveToElementAndWait(5, By.xpath("//div[@class='head']"));
    clickWithMoveToElementAndWait(5, By.xpath("//li[2]//button"));
  }

  public void tabAdministration() {
    click(tabAdministrator);
  }

  public void tabFeedInCommunity() {
    click(tabFeedInCommunity);
  }

  public String monthUI() {
    String data = wd.findElement(monthUI).getText();
    return data.split(" ")[0]; // достали id
  }

  public void clickByRightPaginator() {
    click(rightPaginatorInsSchedule);
  }

  public void clickByLeftPaginator() {
    click(leftPaginatorInSchedule);
  }

  public void selectLessonInSchedule() {
    if (!isElementPresent(alongLessonInCalendar)) {
      click(leftPaginatorInSchedule);
    }
    click(alongLessonInCalendar);
  }

  public void sendMessageToTrainer(String message) {
    clear(10, editTextMessageInChatOnLesson);
    clickWithMoveToElementAndWait(1, editTextMessageInChatOnLesson);
    wd.findElement(editTextMessageInChatOnLesson).sendKeys(message);
    click(btnSendMessageInChatOnLesson);
  }

  public void modifyMessage(String message) {
    clickWithMoveToElementAndWait(2, dropdownInOldMessageInChatOnLesson);
    clickWithMoveToElementAndWait(2, btnEditMessageInChatOnLesson);
    sendMessageToTrainer(message);
  }

  public void deleteMessage() {
    clickWithMoveToElementAndWait(2, dropdownInOldMessageInChatOnLesson);
    clickWithMoveToElementAndWait(2, btnDeleteMessageInChatOnLesson);
  }

  public void btnShowAllInProgressBar() {
    deleteAlerts();
    click(btnShowAllProgressBar);
  }

  public void editInformationAboutMyself(String text) {
    clear(20, editInformationAboutMyself);
    clickWithMoveToElementAndWait(1, editInformationAboutMyself);
    wd.findElement(editInformationAboutMyself).sendKeys(text);
    doubleClick(fullAreaInoProfile);
  }

  public void selectCommunityScratch() {
    click(alongCommunityScratch);
  }

  public void selectTitleCommunity() {
    click(titleCommunity);
  }

  public void tabSubscrimers() {
    click(tabSubscribers);
  }

  public void selectDifferentStudent(String idStudent) {
    click(By.xpath("//a[contains(@href,'" + idStudent + "')]"));
  }

  public void btnShowAllAchievements() {
    click(btnShowAllAchievement);
  }

  public void btnEditPost() {
    click(btnEditPost);
  }

  public void modifyComment(String text) {
    fillEditComment(text);
    btnSaveComment();
  }

  private void btnSaveComment() {
    click(btnSaveComment);
  }

  public void btnDeletePost() {
    click(btnDeletePost);
  }

  public String[] getCommunities() {
    waitVisibilityOfElementLocated(5, By.xpath("//h4"));
    List<WebElement> dialog = wd.findElements(By.xpath("//h4"));
    String[] communuiesList = new String[3];
    for (int i = 0; i < dialog.size(); i++) {
      communuiesList[i] = dialog.get(i).getText();
    }
    return communuiesList;
  }

  public void selectRusLanguage() {
    click(dropdownLang);
    click(rusLang);
  }

  public void selectAllLanguage() {
    click(dropdownLang);
    click(allLang);
  }

  public void selectEnLanguage() {
    click(dropdownLang);
    click(enLang);
  }
}
