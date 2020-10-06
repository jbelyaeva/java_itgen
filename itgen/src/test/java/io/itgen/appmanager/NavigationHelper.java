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
    doubleClick(By.xpath("//a[contains(@href, '/childs')]"));
  //  clickWithMoveToElementAndWait(10,By.xpath("//a[contains(@href, '/childs')]"));
  }

  public void menuTrainers() {
    clickWithMoveToElementAndWait(10, By.xpath("//a[contains(@href, '/trainers')]"));
  }

  public void menuTasks() {
    clickWithMoveToElementAndWait(8,By.xpath("//a[contains(@href, '/tasks')]"));
  }

  public void menuTests() {
    clickWithMoveToElementAndWait(5,By.xpath("//a[contains(@href, '/tests')]"));
  }

  public void menuLeads() {
    click(By.xpath("//a[contains(@href, '/leads')]"));
  }

  public void menuSchedule() {
    clickWithMoveToElementAndWait(8, By.xpath("//a[contains(@href, '/schoolSchedule')]"));
  }

  public void menuRequests() {
    click(By.xpath("//a[contains(@href, '/requests')]"));
  }

  public void menuMaterials() {
    click(By.xpath("//a[contains(@href, '/materials')]"));
  }
}
