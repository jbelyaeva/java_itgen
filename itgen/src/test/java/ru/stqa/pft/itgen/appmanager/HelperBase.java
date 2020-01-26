package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class HelperBase {

  public WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd=wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if(text != null) {
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
    new Select(wd.findElement(locator)).selectByVisibleText(text);
  }

  protected void enterADate(String date) {
    Actions builder = new Actions(wd); // Создаем объект класса Actions, с помощью которого будем генерировать действия
    builder.sendKeys(date).perform(); // исполнить нужную последовательность действий (ввести дату в поле)
  }

  private boolean areElementsPresent(By locator) {
    return wd.findElements(locator).size() > 0;
  }
}
