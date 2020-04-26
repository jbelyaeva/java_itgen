package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {

  public WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
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
      Actions builder = new Actions(wd); // Создаем объект класса Actions, с помощью которого будем генерировать действия
      builder.sendKeys(date).perform(); // исполнить нужную последовательность действий (ввести дату в поле)
    }
  }

  private boolean areElementsPresent(By locator) {
    return wd.findElements(locator).size() > 0;
  }

  protected int countingWithPaginated() {
    int count = 0;
    // явное ожидание появления элемента
    WebDriverWait wait = new WebDriverWait(wd, 2);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='pagination']//li[2]")));
    //
    String next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
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
}
