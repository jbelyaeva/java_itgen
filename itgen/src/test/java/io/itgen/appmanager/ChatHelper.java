package io.itgen.appmanager;

import static io.itgen.appmanager.ApplicationManager.properties;

import io.itgen.general.FileHelper;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChatHelper extends HelperBase {

  SessionHelper sessionHelper;

  public ChatHelper(WebDriver wd) {
    super(wd);
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
    clickWithMoveToElementAndWait(5, By.xpath("//div[@class='right']//div[@class='chat-button']"));
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
    wd.findElement(By.xpath("//div[contains(@class,'chat-message')]//textarea[1]"))
        .sendKeys(text);
  }

  private void search(String userName) {
    click(By.xpath("//input[@name='search-by-user']"));
    type(By.xpath("//input[@name='search-by-user']"), userName);
    click(By.xpath("//div[contains(@class,'result')]//a"));
  }

  public String getByTrainerMessage(String login, String password) {
    logout();
    login(login, password);
    wd.findElement(By.xpath("//div[@class='right']//div[@class='chat-button']")).click();
    String text = wd.findElement(By.xpath("//div[@class='last-message']//span"))
        .getAttribute("textContent");
    btnCloseChat();
    logout();
    login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    return text;
  }

  public Boolean fileGetTrainer(String login, String password, String fileName) {
    logout();
    login(login, password);
    btnOpenChat();
    clickByLastMessage();
    Boolean getFile = isElementPresent(By.xpath("//img[@alt='file.jpg']"));
    btnCloseChat();
    logout();
    login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    return getFile;
  }

  private void clickByLastMessage() {
    click(By.xpath("//div[@class='last-message']"));
  }

  public Boolean getByTrainerDeletedMessage(String login, String password) {
    logout();
    login(login, password);
    wd.findElement(By.xpath("//div[@class='right']//div[@class='chat-button']")).click();
    Boolean deleted = isElementPresent(By.xpath("//div[@class='last-message']//span"));
    btnCloseChat();
    logout();
    login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    return deleted;
  }

  public void modifyMessage(String idUser, String messageNew) {
    btnOpenChat();
    chooseDialog(idUser);
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
    wd.findElement(By.xpath("//div[contains(@class,'chat-message')]//textarea[1]"))
        .sendKeys(text);
  }

  private void btnCorrect() {
    click(By.xpath("//a[contains(@class,'edit-message')]"));
  }

  private void btnPoints() {
    click(By.xpath("//div[contains(@class,'dropdown')]//span"));
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

  public void sendFile(String userName, String path) throws IOException {
    btnOpenChat();
    search(userName);
    fillFile(path);
    btnSendMessage();
    btnCloseChat();
  }

  private void fillFile(String path) throws IOException {
    unhide(wd, wd.findElement(By.xpath("//input[@type='file']")));
    wd.findElement(By.xpath("//input[@type='file']"))
        .sendKeys(FileHelper.getAbsolutePath(path));
  }

  public void unhide(WebDriver driver, WebElement element) {
    String script = "arguments[0].style.opacity=1;"
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
   return isElementPresent(By.xpath("//div[@class='child-list']//div[@class='chat-alert circle']"));
  }

  public String  chatByDefolt() {
    return wd.findElement(By.xpath("//div[@class='room-item empty']//span[@class='name']")).getText();
  }

  public boolean searchPerson(String name) {
    searchBad(name);
    return isElementPresent(By.xpath("//div[contains(@class,'result')]//a"));
  }

  private void searchBad(String name) {
    click(By.xpath("//input[@name='search-by-user']"));
    type(By.xpath("//input[@name='search-by-user']"), name);
  }
}
