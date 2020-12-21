package app.appmanager;

import static app.appmanager.ApplicationManager.properties;

import core.general.FileHelper;
import data.model.communities.CommunityData;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CommunityHelper extends HelperBase {

  public CommunityHelper(WebDriver wd) {
    super(wd);
  }

  public void addNewCommunity(CommunityData community) {
    btnCreate();
    fillFormCreate(community);
    btnSave();
  }

  private void fillFormCreate(CommunityData community) {
    type(By.xpath("//input[@id-qa='title']"), community.getTitle());

    clickWithMoveToElementAndWait(1, By.xpath("//textarea[@id-qa='desc']"));
    wd.findElement(By.xpath("//textarea[@id-qa='desc']")).sendKeys(community.getDescription());

    click(By.xpath("//div[@role='select-open-role']"));
    click(By.xpath("//li[@data-value='1']"));
    JSClickByXY(400, 400);

    for (int i = 0; i < community.getTagUI().size(); i++) {
      type(By.xpath("//input[@id-qa='tags']"), community.getTagUI().get(i));
      wd.findElement(By.xpath("//input[@id-qa='tags']")).sendKeys(Keys.ENTER);
    }
  }

  private void btnSave() {
    click(By.xpath("//div[@class='form']//button[@id-qa='create']"));
    noErrorMessage();
  }

  private void btnCreate() {
    click(By.xpath("//button[@id-qa='create']"));
    noErrorMessage();
  }

  public void addNewBadCommunity(CommunityData community) {
    btnCreate();
    fillFormCreate(community);
    btnBadSave();
  }

  private void btnBadSave() {
    click(By.xpath("//div[@class='form']//button[@id-qa='create']"));
    thereAreErrorMessages();
  }

  public void addNewCommunityWithFile(CommunityData community, String path)
      throws IOException {
    btnCreate();
    fillFormCreate(community);
    addFile(path);
    btnSave();
  }

  private void addFile(String path) throws IOException {
    String script =
        "let avatar = document.querySelector('.community-avatar-view');"
            + "let element = document.createElement('input');"
            + "element.type = 'file';"
            + "avatar.appendChild(element);";
    ((JavascriptExecutor) wd).executeScript(script);

    if (!"".equals(properties.getProperty("selenium.server"))) {
      ((RemoteWebDriver) wd).setFileDetector(new LocalFileDetector());
    }
    wd.findElement(By.xpath("//input[@type='file']")).sendKeys(FileHelper.getAbsolutePath(path));
  }

  public void btnAdministration() {
    click(By.xpath("//button[@id-qa='administration']"));
  }

  public void goInCommunity() {
    click(By.xpath("//h4[@id-qa='title']"));
  }

  public void modifyCommunity(CommunityData community) {
    btnAdministration();
    goInCommunity();
    btnChange();
    fillModifyForm(community);
    btnSaveEdit();
  }

  private void btnSaveEdit() {
    click(By.xpath("//button[@id-qa='edit']"));
  }

  private void fillModifyForm(CommunityData community) {
    clear(20, By.xpath("//input[@id-qa='title']"));
    type(By.xpath("//input[@id-qa='title']"), community.getTitle());

    clear(50, By.xpath("//textarea[@id-qa='desc']"));
    clickWithMoveToElementAndWait(1, By.xpath("//textarea[@id-qa='desc']"));
    wd.findElement(By.xpath("//textarea[@id-qa='desc']")).sendKeys(community.getDescription());
  }

  private void btnChange() {
    click(By.xpath("//a[@id-qa='edit']"));
  }

  public void addTextPost(String text) {
    btnAdministration();
    goInCommunity();
    btnAddPost();
    fillPost(text);
    btnCreatePost();
  }

  public void addFilePost(String path) throws IOException {
    btnAdministration();
    goInCommunity();
    btnAddPost();
    fillFilePost(path);
    btnCreatePost();
  }

  private void fillFilePost(String path) throws IOException {
    unhide(wd, wd.findElement(By.xpath("//input[@type='file']")));
    if (!"".equals(properties.getProperty("selenium.server"))) {
      ((RemoteWebDriver) wd).setFileDetector(new LocalFileDetector());
    }
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

  private void btnCreatePost() {
    clickWithMoveToElementAndWait(2, By.xpath("//button[@id-qa='create']"));
  }

  private void fillPost(String text) {
    By locator = By.xpath("//div[@id-qa='post-text']");
    clickWithMoveToElementAndWait(1, By.xpath("//div[@id-qa='post-text']"));
    JavascriptExecutor exe = (JavascriptExecutor) wd;
    WebElement element = wd.findElement(By.xpath("//div[@id-qa='post-text']"));
    exe.executeScript("arguments[0].innerHTML='" + text + "';", element);
    Actions action = new Actions(wd);
    action.sendKeys(Keys.ENTER).build().perform();

    if (!wd.findElement(locator).getText().equals(text)) {
      clear(50, locator);
      wd.findElement(locator).sendKeys(text);
    }
  }

  private void btnAddPost() {
    click(By.xpath("//div[@id-qa='add-post']"));
  }

  public boolean filePostGetTrainerFromAdmin(String login, String password, String fileName) {
    logout();
    login(login, password);
    menuCommunities();
    goInCommunity();
    return getByTrainerFile(fileName);
  }

  private void menuCommunities() {
    clickWithMoveToElementAndWait(5, By.xpath("//a[contains(@href, '/communities')]"));
  }

  private boolean getByTrainerFile(String fileName) {
    boolean getFile = false;
    try {
      waitVisibilityOfElementLocated(5, By.xpath("//img[@alt='" + fileName + "']"));
      getFile = isElementPresent(By.xpath("//img[@alt='" + fileName + "']"));
    } catch (TimeoutException e) {
      System.out.println("Исключение:" + e);
    } finally {
      logout();
      login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    }
    return getFile;
  }

  public void modifyTextPost(String newText) {
    btnAdministration();
    goInCommunity();
    btnPointPost();
    btnModifyPost();
    modifyPost(newText);
    btnSavePost();
  }

  private void btnSavePost() {
    clickWithMoveToElementAndWait(3, By.xpath("//button[@id-qa='save']"));
  }

  private void modifyPost(String newText) {
    clear(50, By.xpath("//div[@id-qa='post-text']"));
    clickWithMoveToElementAndWait(1, By.xpath("//div[@id-qa='post-text']"));
    JavascriptExecutor exe = (JavascriptExecutor) wd;
    WebElement element = wd.findElement(By.xpath("//div[@id-qa='post-text']"));
    exe.executeScript("arguments[0].innerHTML='" + newText + "';", element);
    Actions action = new Actions(wd);
    action.sendKeys(Keys.ENTER).build().perform();
  }

  private void btnModifyPost() {
    clickWithMoveToElementAndWait(3, By.xpath("//li[@id-qa='menu-edit']"));
  }

  public void btnPointPost() {
    clickWithMoveToElementAndWait(3, By.xpath("//button[@id-q='menu']"));
  }

  public void deleteTextPost() {
    btnAdministration();
    goInCommunity();
    btnPointPost();
    btnDeletePost();
  }

  private void btnDeletePost() {
    clickWithMoveToElementAndWait(3, By.xpath("//li[@id-qa='menu-delete']"));
  }

  public void btnSubscribers() {
    click(By.xpath("//button[@id-qa='subscribers']"));
  }

  public void goToSubscribers() {
    menuCommunities();
    btnAdministration();
    goInCommunity();
    btnSubscribers();
  }

  public void SubscriberDidAdmin() {
    goToSubscribers();
    btnPointSubsc();
    btnDidAdmin();
  }

  private void btnDidAdmin() {
    click(By.xpath("//span[@id-qa='add-manager-item']"));
  }

  private void btnPointSubsc() {
    click(By.xpath("//div[@class='user-item']//button"));
  }

  public void deleteManager() {
    goToSubscribers();
    deleteStudentFromAdmins();
    btnAlertYes();
  }

  private void btnAlertYes() {
    click(By.xpath("//button[contains(@class,'delete')]"));
  }

  private void deleteStudentFromAdmins() {
    click(
        By.xpath("(//div[@class='actions-buttons-wrap']//*[name()='svg' and @id-qa='trash'])[2]"));
  }
}
