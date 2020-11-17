package app.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void menuWorkers() {
    click(By.xpath("//a[contains(@href, '/employees')]"));
  }

  public void menuStudents() {
    clickWithMoveToElementAndWait(8, By.xpath("//a[contains(@href, '/childs')]"));
  }

  public void menuTrainers() {
    clickWithMoveToElementAndWait(10, By.xpath("//a[contains(@href, '/trainers')]"));
  }

  public void menuTasks() {
    clickWithMoveToElementAndWait(8, By.xpath("//a[contains(@href, '/tasks')]"));
  }

  public void menuTests() {
    clickWithMoveToElementAndWait(5, By.xpath("//a[contains(@href, '/tests')]"));
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

  public void menuCandidates() {
    clickWithMoveToElementAndWait(5, By.xpath("//a[contains(@href, '/candidates')]"));
  }

  public void urlSchedule() {
    wd.get(address() + "/schoolSchedule");
  }

  public void urlTests() {
    wd.get(address() + "/tests");
    waitVisibleElement(10, By.xpath("//h2"));
  }

  public void urlTasks() {
    wd.get(address() + "/tasks");
    waitVisibleElement(5, By.xpath("//div[@class='panel-footer']"));
  }

  public void urlMaterials() {
    wd.get(address() + "/materials");
    waitVisibleElement(5, By.xpath("//h1"));
  }

  public void urlCandidates() {
    try {
      wd.get(address() + "/candidates");
      waitVisibleElement(7, By.xpath("//h2"));
    } catch (TimeoutException e) {
      refresh();
      wd.get(address() + "/candidates");
      waitVisibleElement(7, By.xpath("//h2"));
    }
  }

  public void urlStudents() {
    wd.get(address() + "/childs");
    waitVisibleElement(5, By.xpath("//h2"));
  }

  public void menuCommunities() {
    clickWithMoveToElementAndWait(5, By.xpath("//a[contains(@href, '/communities')]"));
  }
}
