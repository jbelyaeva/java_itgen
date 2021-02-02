package app.appmanager;

import static app.appmanager.ApplicationManager.properties;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import core.general.FileHelper;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChatHelper extends HelperBase {

  private final By btnCall = By.xpath("//div[@class='btn-container']/*");
  private final By btnCreateTalk = By.xpath("(//div[@class='chat-header'])[1]/*[2]");

  public ChatHelper(WebDriver wd) {
    super(wd);
  }

  public By getBtnCall() {
    return btnCall;
  }

  public By getBtnCreateTalk() {
    return btnCreateTalk;
  }

  public void openCloseChat() {
    waitVisibilityOfElementLocated(5, By.xpath("//h2[@class='title']"));
    btnOpenChat();
    btnCloseChat();
  }

  public void btnCloseChat() {
    clickWithMoveToElementAndWait(5, By.xpath("//button[contains(@class,'close-chat')]"));
  }

  public void btnOpenChat() {
    if (isElementPresent(By.xpath("//button[contains(@class,'close-chat')]"))) {
      click(By.xpath("//button[contains(@class,'close-chat')]"));
    }
    clickWithMoveToElementAndWait(7, By.xpath("//div[@class='right']//div[@class='chat-button']"));
  }

  public void btnOpenChatBeforeLogin() {
    if (isElementPresent(By.xpath("//button[contains(@class,'close-chat')]"))) {
      click(By.xpath("//button[contains(@class,'close-chat')]"));
    }
    clickWithMoveToElementAndWait(5, By.xpath(" //div[@id-qa='chat']"));
  }

  public void sendMessage(String userName, String text) {
    btnOpenChat();
    search(userName);
    fillMessage(text);
    btnSendMessage();
    btnCloseChat();
  }

  private void btnSendMessage() {
    click(By.xpath("//div[contains(@class,'chat-message')]//button[2]"));
  }

  private void fillMessage(String text) {
    click(By.xpath("//div[contains(@class,'chat-message')]//textarea[1]"));
    wd.findElement(By.xpath("//div[contains(@class,'chat-message')]//textarea[1]")).sendKeys(text);
  }

  public void search(String userName) {
    click(By.xpath("//input[@name='search-by-user']"));
    type(By.xpath("//input[@name='search-by-user']"), userName);
    if (isElementPresent(By.xpath("//div[contains(@class,'result')]//a"))) {
      click(By.xpath("//div[contains(@class,'result')]//a"));
    } else {
      click(By.xpath("//div[contains(@class,'room-item empty')]"));
    }
  }

  public String getByTrainerMessageFromAdmin(String login, String password) {
    logout();
    return getByTrainerText(login, password);
  }

  public String getByTrainerMessageFromStudent(String login, String password) {
    logoutByStudent();
    return getByTrainerText(login, password);
  }

  public String getByTrainerDeletionMessageFromStudent(String login, String password) {
    logoutByStudent();
    return getByTrainerDeletionText(login, password);
  }

  private String getByTrainerDeletionText(String login, String password) {
    deleteAlerts();
    login(login, password);
    String text = "";
    btnOpenChatBeforeLogin();
    try {
      if (isElementPresent(By.xpath("//div[@class='last-message']//span"))) {
        assertThat(isElementPresent(By.xpath("//div[@class='last-message']//span")), is(false));
      }
      btnCloseChat();
    } catch (TimeoutException e) {
      System.out.println("Исключение:" + e);
    } finally {
      logout();
      login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    }
    return text;
  }

  public String getByTrainerText(String login, String password) {
    deleteAlerts();
    login(login, password);
    String text = "";
    btnOpenChat();
    try {
      waitVisibilityOfElementLocated(5, By.xpath("//div[@class='last-message']//span"));
      text =
          wd.findElement(By.xpath("//div[@class='last-message']//span"))
              .getAttribute("textContent");
      btnCloseChat();
    } catch (TimeoutException e) {
      System.out.println("Исключение:" + e);
    } finally {
      logout();
      login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    }
    return text;
  }

  public Boolean fileGetTrainerFromAdmin(String login, String password, String fileName)
      throws InterruptedException {
    logout();
    login(login, password);
    btnOpenChat();
    clickByMessageFromItigenik();
    return getByTrainerFile(fileName);
  }

  public Boolean fileGetTrainerFromStudent(String login, String password, String fileName,
      String idStudent) throws InterruptedException {
    logoutByStudent();
    deleteAlerts();//удалить алерт на две вкладки и по алерту на микрофон и камеру
    deleteAlerts();
    deleteAlerts();
    login(login, password);
    btnOpenChat();
    Thread.sleep(2000);
    clickByMessageByStudentForTrainer(idStudent);
    return getByTrainerFile(fileName);
  }

  private void clickByMessageByStudentForTrainer(String idStudent) {
    click(By.xpath("//a[contains(@href, '" + idStudent + "')]"));
  }

  private void clickByMessageFromItigenik() {
    click(By.xpath("//div[@class='avatar']"));
  }

  public void clickByFirstMessage() {
    click(By.xpath("//div[contains(@class,'room-item')]"));
  }

  public Boolean getByTrainerDeletedMessageFromAdmin(String login, String password) {
    logout();
    return getByTrainerDeleteMessage(login, password);
  }

  public Boolean getByTrainerDeletedMessageFromStudent(String login, String password) {
    deleteAlerts();
    logoutByStudent();
    return getByTrainerDeleteMessage(login, password);
  }

  private Boolean getByTrainerDeleteMessage(String login, String password) {
    deleteAlerts();
    login(login, password);
    wd.findElement(By.xpath("//div[@class='right']//div[@class='chat-button']")).click();
    Boolean deleted = isElementPresent(By.xpath("//div[@class='last-message']//span"));
    btnCloseChat();
    logout();
    login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    return deleted;
  }

  private Boolean getByTrainerFile(String fileName) throws InterruptedException {
    deleteAlerts();
    Boolean getFile = false;
    try {
      waitVisibilityOfElementLocated(5, By.xpath("//img[@alt='" + fileName + "']"));
      deleteAlerts();//удалить алерт на две вкладки и по алерту на микрофон и камеру
      deleteAlerts();
      deleteAlerts();
      getFile = isElementPresent(By.xpath("//img[@alt='" + fileName + "']"));
      btnCloseChat();
    } catch (TimeoutException e) {
      System.out.println("Исключение:" + e);
    } finally {
      logout();
      login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
      Thread.sleep(2000);
      if (isElementPresent(By.name("username"))) {
        clear(10, By.name("username"));
        clear(10, By.name("password"));
        login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
      }
    }
    return getFile;
  }

  public void modifyMessageByAdmin(String idUser, String messageNew) {
    btnOpenChat();
    chooseDialog(idUser);
    btnPoints();
    btnCorrect();
    correctMessage(messageNew);
    btnSendMessage();
    btnCloseChat();
  }

  public void modifyMessageByStudent(String messageNew) {
    deleteAlerts();
    btnOpenChat();
    chooseFirstWithUser();
    deleteAlerts();//удалить алерт на две вкладки и по алерту на микрофон и камеру
    deleteAlerts();
    deleteAlerts();
    btnPoints();
    btnCorrect();
    correctMessage(messageNew);
    btnSendMessage();
    btnCloseChat();
  }

  private void chooseDialog(String idUser) {
    click(By.xpath("//a[@href='/profile/" + idUser + "']"));
  }

  private void correctMessage(String text) {
    click(By.xpath("//div[contains(@class,'chat-message')]//textarea[1]"));
    clear(15, By.xpath("//div[contains(@class,'chat-message')]//textarea[1]"));
    wd.findElement(By.xpath("//div[contains(@class,'chat-message')]//textarea[1]")).sendKeys(text);
  }

  private void btnCorrect() {
    click(By.xpath("//a[contains(@class,'edit-message')]"));
  }

  private void btnPoints() {
    clickWithMoveToElementAndWait(5, By.xpath("//div[contains(@class,'dropdown')]//span"));
  }

  public void deleteMessage(String idUser) {
    btnOpenChat();
    chooseDialog(idUser);
    btnPoints();
    btnDelete();
    btnCloseChat();
  }

  private void btnDelete() {
    click(By.xpath("//a[contains(@class,'remove-message')]"));
  }

  public Boolean deleteForegnMessage(String idUser) {
    btnOpenChat();
    chooseDialog(idUser);
    return isElementPresent(By.xpath("//div[contains(@class,'dropdown')]//span"));
  }

  public Boolean findDateForeignMessage(String idUser) {
    chooseDialog(idUser);
    return isElementPresent(By.xpath("//div[@class='date-separator']"));
  }

  public Boolean findTimeForeignMessage() {
    return isElementPresent(By.xpath("(//span[@class='date'])[2]"));
  }

  public Boolean findIndicatorMessage() {
    return isElementPresent(By.xpath("//div[contains(@class,'alert circle')]"));
  }

  public String findRoleInterlocutor(String idUser) {
    chooseDialog(idUser);
    return wd.findElement(By.xpath("//div[@class='chat-header']//span[@class='name']"))
        .getAttribute("title");
  }

  public Boolean deleteAutoMessage(String idUser) {
    chooseDialog(idUser);
    return isElementPresent(By.xpath("//div[contains(@class,'dropdown')]//span"));
  }

  public void sendFileByAdmin(String userName, String path) throws IOException {
    btnOpenChat();
    search(userName);
    fillFile(path);
    btnSendMessage();
    btnCloseChat();
  }

  private void fillFile(String path) throws IOException {
    unhide(wd, wd.findElement(By.xpath("//input[@type='file']")));
    if (!"".equals(properties.getProperty("selenium.server"))) {
      ((RemoteWebDriver) wd).setFileDetector(new LocalFileDetector());
    }
    deleteAlerts();//удалить алерт на две вкладки и по алерту на микрофон и камеру
    deleteAlerts();
    deleteAlerts();
    wd.findElement(By.xpath("//input[@type='file']")).sendKeys(FileHelper.getAbsolutePath(path));
  }

  public void unhide(WebDriver driver, WebElement element) {
    String script =
        "arguments[0].style.opacity=1;"
            + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
            + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
            + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
            + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
            + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
            + "arguments[0].style['border']='1px';"
            + "arguments[0].style['display']='';"
            + "return true;";
    ((JavascriptExecutor) driver).executeScript(script, element);
  }

  public boolean indicatorNewMessageOnLessonByTrainer() {
    return isElementPresent(
        By.xpath("//div[@class='child-list']//div[@class='chat-alert circle']"));
  }

  public String chatByDefault() {
    return wd.findElement(By.xpath("//div[@class='room-item empty']//span[@class='name']"))
        .getText();
  }

  public boolean searchPerson(String name) {
    searchBad(name);
    return isElementPresent(By.xpath("//div[contains(@class,'result')]//a"));
  }

  private void searchBad(String name) {
    click(By.xpath("//input[@name='search-by-user']"));
    type(By.xpath("//input[@name='search-by-user']"), name);
  }

  public String[] getDialogs() {
    if (isElementPresent(By.xpath("//button[@id-qa='cancel']"))) {
      clickWithMoveToElementAndWait(2, By.xpath("//button[@id-qa='cancel']"));
    }
    btnOpenChat();
    deleteAlerts();
    return getDialogs(By.xpath("//div[@class='room-info']//span[@class='name']"));
  }

  private String[] getDialogs(By locator) {
    waitVisibilityOfElementLocated(5, locator);
    Set<String> dialogs = new HashSet<>();
    List<WebElement> dialog = wd.findElements(locator);
    for (int i = 0; i < dialog.size(); i++) {
      dialogs.add(dialog.get(i).getText());
    }
    return dialogs.stream().toArray(String[]::new);
  }

  public String[] searchPersonByUser(String name) {
    click(By.xpath("//input[@name='search-by-user']"));
    type(By.xpath("//input[@name='search-by-user']"), name);
    deleteAlerts();
    return getDialogs(By.xpath("//div[@class='room-info']//span[@class='name']"));
  }

  public String searchBadPersonByUser(String name) {
    click(By.xpath("//input[@name='search-by-user']"));
    type(By.xpath("//input[@name='search-by-user']"), name);
    return wd.findElement(By.xpath("//p[@class='text-center']")).getText();
  }

  public void sendFileByStudent(String userName, String path) throws IOException {
    btnOpenChat();
    searchByStudent(userName);
    fillFile(path);
    btnSendMessage();
    btnCloseChat();
  }

  public void sendFileByStudentInChatOnLesson(String userName, String path)
      throws IOException, InterruptedException {
    fillFile(path);
    btnSendMessageInChatOnLesson();
  }

  private void btnSendMessageInChatOnLesson() throws InterruptedException {
    Thread.sleep(2000);
    click(By.xpath("//button[@id-qa='send']"));
  }

  private void searchByStudent(String userName) {
    click(By.xpath("//input[@name='search-by-user']"));
    type(By.xpath("//input[@name='search-by-user']"), userName);
    click(By.xpath("(//div[contains(@class,'result')]//span)[2]"));
  }

  public void deleteMessageByStudent() {
    deleteAlerts();
    btnOpenChat();
    chooseFirstWithUser();
    btnPoints();
    btnDelete();
    btnCloseChat();
  }

  public void chooseFirstWithUser() {
    click(By.xpath("//div[@class='room-item']"));
  }

  public boolean getNameFile(String fileName) {
    deleteAlerts();//удалить алерт на две вкладки и по алерту на микрофон и камеру
    deleteAlerts();
    deleteAlerts();
    boolean getFile = isElementPresent(By.xpath("//img[@alt='" + fileName + "']"));
    btnCloseChat();
    return getFile;
  }

  public Boolean getNameFileInChatOnLesson(String fileName) {
    boolean getFile = isElementPresent(
        By.xpath("//div[contains(@class,'history-container')]//img[@alt='" + fileName + "']"));
    return getFile;
  }
}
