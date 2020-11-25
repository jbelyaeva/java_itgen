package app.appmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HelperBase {

  public WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  public HelperBase() {
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      wd.findElement(locator).clear();
      wd.findElement(locator).sendKeys(text);
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException ex) {
      return false;
    }
  }

  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

  protected void dropDownList(By locator, String text) {
    if (text != null) {
      new Select(wd.findElement(locator)).selectByValue(text);
    }
  }

  protected void dropDownList_Integer(By locator, Integer parametr) {
    if (parametr != null) {
      new Select(wd.findElement(locator)).selectByValue(String.valueOf(parametr));
    }
  }

  protected void enterADate(By locator, String date) {
    if (date != null) {
      click(locator);
      wd.findElement(locator).clear();
      click(locator);
      Actions builder =
          new Actions(
              wd); // Создаем объект класса Actions, с помощью которого будем генерировать действия
      builder
          .sendKeys(date)
          .perform(); // исполнить нужную последовательность действий (ввести дату в поле)
    }
  }

  protected int countingWithPaginated() {
    int count = 0;
    // явное ожидание появления элемента
    WebDriverWait wait = new WebDriverWait(wd, 2);
    wait.until(
        ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='pagination']//li[2]")));
    //
    String next =
        wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
    if (!next.equals("disabled")) {
      while (!next.equals("disabled")) {
        count = count + wd.findElements(By.cssSelector("a.btn-link")).size();
        wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
      }
    }
    return count + wd.findElements(By.cssSelector("a.btn-link")).size();
  }

  public String getURL() {
    return wd.getCurrentUrl();
  }

  public String getId(String url) {
    String[] getIdSplit = url.split("/");
    String id = getIdSplit[4]; // достали id
    return id;
  }

  public void refresh() {
    wd.navigate().refresh();
  }

  protected void noErrorMessage() {
    Assert.assertFalse(isElementPresent(By.xpath("//div[@ia-qa='notification-error']")));
  }

  protected void thereAreErrorMessages() {
    Assert.assertTrue(
        isElementPresent(By.xpath("//div[@id-qa='notification-error']"))
            || isElementPresent(By.xpath("//div[contains(@class,'has-error')]"))
            || isElementPresent(By.xpath("//p[contains(@class,'error')]")));
  }

  public void logout() {
    click(By.xpath("//div[@class='head']"));
    if (isElementPresent(By.xpath("(//ul[contains(@class,'Menu')])[2]//li[5]"))) {
      click(By.xpath("(//ul[contains(@class,'Menu')])[2]//li[5]"));
    } else {
      click(By.xpath("(//ul[contains(@class,'Menu')])//li[4]"));
    }
  }

  public void logoutByStudent() {
    click(By.xpath("//div[@class='head']"));
    if (isElementPresent(By.xpath("(//ul[contains(@class,'Menu')])//li[4]"))) {
      click(By.xpath("(//ul[contains(@class,'Menu')])//li[4]"));
    } else {
      click(By.xpath("(//ul[contains(@class,'Menu')])//li[3]"));
    }
  }

  public void login(String login, String password) {
    type(By.name("username"), login);
    type(By.name("password"), password);
    clickWithMoveToElementAndWait(5, By.xpath("//button[contains(@class,'btn-login')]"));
    // ждать появления иконки чата
    waitVisibilityOfElementLocated(5, By.xpath("//div[@class='right']//div[@class='chat-button']"));
  }

  public void goByHref(String Url) {
    wd.get(Url);
  }

  public String getText(String locator) {
    return wd.findElement(By.xpath(locator)).getText();
  }

  public void clickWithMoveToElementAndWait(int second, By locator) {
    WebElement dynamicElement =
        (new WebDriverWait(wd, second)).until(ExpectedConditions.elementToBeClickable(locator));
    Actions actions = new Actions(wd);
    actions.moveToElement(dynamicElement).build().perform();
    dynamicElement.click();
  }

  public void doubleClick(By locator) {
    Actions actions = new Actions(wd);
    actions.doubleClick(wd.findElement(locator)).build().perform();
  }

  public void clickWaitElementToBeClicable(int second, By locator) {
    WebElement dynamicElement =
        (new WebDriverWait(wd, second)).until(ExpectedConditions.elementToBeClickable(locator));
    dynamicElement.click();
  }

  public void moveToClicableElement(int second, By locator) {
    WebElement element = wd.findElement(locator);
    (new WebDriverWait(wd, second)).until(ExpectedConditions.elementToBeClickable(locator));
    Actions actions = new Actions(wd);
    actions.moveToElement(element).build().perform();
  }

  // навести курсор
  public void moveToElement(By locator) {
    WebElement element = wd.findElement(locator);
    Actions actions = new Actions(wd);
    actions.moveToElement(element).build().perform();
  }

  public void waitVisibleElement(int sec, By locator) {
    WebDriverWait wait = new WebDriverWait(wd, sec);
    wait.until(ExpectedConditions.visibilityOf(wd.findElement(locator)));
  }

  public void waitElementWithText(int sec, By locator, String text) {
    WebDriverWait wait = new WebDriverWait(wd, sec);
    wait.until(ExpectedConditions.textToBe(locator, text));
  }

  public void waitElementWithValue(int sec, By locator, String text) {
    WebDriverWait wait = new WebDriverWait(wd, sec);
    wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
  }

  public void waitElementWithAttribute(int sec, By locator, String attribute, String value) {
    WebDriverWait wait = new WebDriverWait(wd, sec);
    wait.until(ExpectedConditions.attributeToBe(wd.findElement(locator), attribute, value));
  }

  public void waitVisibilityOfElementLocated(int sec, By locator) {
    WebDriverWait wait = new WebDriverWait(wd, sec);
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public Date stringToDate(String stringDate) throws ParseException {
    String startDate = stringDate; // "Tue May 15 00:00:01 MSK 2012";
    SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.US);
    Date date = parser.parse(startDate);
    return date;
  }

  public String elementAtributAvailable(By locator) {
    // из документации: атрибут disabled возвращает либо true, либо null
    return wd.findElement(locator).getAttribute("disabled");
  }

  public Date DateWithCorrectionDays(int days) {
    Date data = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(data);
    c.add(Calendar.DATE, days);
    return c.getTime();
  }

  public String DateISOToUsualDataString(Date dateISO) {
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
    return formatForDateNow.format(dateISO);
  }

  public void clear(int count, By locator) {
    for (int i = 0; i <= count; i++) {
      wd.findElement(locator).sendKeys(Keys.BACK_SPACE);
    }
  }

  public void JSclick(By locator) {
    WebElement element = wd.findElement(locator);
    JavascriptExecutor executor = (JavascriptExecutor) wd;
    executor.executeScript("arguments[0].click();", element);
  }

  public void maxBrowser() {
    wd.manage().window().maximize();
  }

  public void explicitWait(int ms) {
    wd.manage().timeouts().implicitlyWait(ms, TimeUnit.MICROSECONDS);
  }

  public void waitUntilRefreshElement(WebElement element) {
    WebDriverWait waitRefreshElement = new WebDriverWait(wd, 10);
    waitRefreshElement.until(ExpectedConditions.stalenessOf(element));
  }

  public String address() {
    String[] split = ApplicationManager.properties.getProperty("web.baseUrl").split("/");
    return split[0] + "//" + split[2];
  }

  public void trySearchElementTwoTimesAndClickWithWaiteAndMove(int sec, By locator) {
    if (isElementPresent(locator)) {
      clickWithMoveToElementAndWait(sec, locator);
    } else {
      refresh();
      if (isElementPresent(locator)) {
        clickWithMoveToElementAndWait(sec, locator);
      }
    }
  }

  public void deleteElements(String[] deleteElements) {
    if (deleteElements != null) {
      for (int i = 0; i <= deleteElements.length - 1; i++) {
        List<WebElement> elementsList = wd.findElements(By.xpath(deleteElements[i]));
        for (WebElement element : elementsList) {
          ((JavascriptExecutor) wd).executeScript("arguments[0].remove();", element);
        }
      }
    }
  }

  public boolean checkMatchTZServerUTC() {
    SimpleDateFormat sdf = new SimpleDateFormat("hh X");
    String dateWithTz = sdf.format(new Date());
    String[] lettersDate = dateWithTz.split(" ");
    // проверка, что чп 00
    Boolean a = !lettersDate[1].equals("+03");
    // проверка, что время > 21
    Boolean b = Integer.parseInt(lettersDate[0]) >= Integer.parseInt("21");
    return a && b;
  }

  //метод для удаления предупреждения об открытых двух вкладках, дать разрешение на микрофон  и камеру
  public void deleteAlerts() {
    if (isElementPresent(By.xpath("//div[@id-qa='notification-audio-video-screen-permissions']")) ||
        isElementPresent(By.xpath("//div[@id-qa='notification-two-tabs-are-opened']"))) {
      String[] deleteElements = {"//div[@class='notifications-error-container']"};
      deleteElements(deleteElements);
    }
  }

  public void clickByCoordinats(int x, int y) {
    JavascriptExecutor exe = (JavascriptExecutor) wd;
    exe.executeScript("$(document.elementFromPoint(" + x + "," + y + ")).click();");
  }
}
