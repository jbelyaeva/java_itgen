package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void menuWorkers() {
    click(By.xpath("//a[contains(@href, '/employees')]"));
  }

  public void menuStudents() {
    click(By.xpath("//a[contains(@href, '/childs')]"));
  }

  public void menuTrainers() {
    click(By.xpath("//a[contains(@href, '/trainers')]"));
  }

  public void menuTasks() {
    click(By.xpath("//a[contains(@href, '/tasks')]"));
  }
  public void menuLeads() {
    click(By.xpath("//a[contains(@href, '/leads')]"));
  }
  public void menuSchedule() {
    click(By.xpath("//a[contains(@href, '/schoolSchedule')]"));
  }
}
