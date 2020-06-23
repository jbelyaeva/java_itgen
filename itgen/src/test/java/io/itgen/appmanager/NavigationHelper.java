package io.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void menuWorkers() {
    click(By.xpath("//a[contains(@href, '/employees')]"));
  }

  public void menuStudents() {
    WebElement dynamicElement = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/childs')]")));
    Actions actions = new Actions(wd);
    actions.moveToElement(dynamicElement).build().perform();
    dynamicElement.click();
  }

  public void menuTrainers() {
    WebElement dynamicElement = (new WebDriverWait(wd, 10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/trainers')]")));
    Actions actions = new Actions(wd);
    actions.moveToElement(dynamicElement).build().perform();
    dynamicElement.click();

  }

  public void menuTasks() {
    click(By.xpath("//a[contains(@href, '/tasks')]"));
  }

  public void menuLeads() {
    click(By.xpath("//a[contains(@href, '/leads')]"));
  }

  public void menuSchedule() { click(By.xpath("//a[contains(@href, '/schoolSchedule')]"));
  }
  public void menuRequests() { click(By.xpath("//a[contains(@href, '/requests')]"));
  }
}
