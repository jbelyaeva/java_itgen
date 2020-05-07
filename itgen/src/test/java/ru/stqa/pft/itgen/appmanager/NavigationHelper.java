package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void worker() {
    click(By.xpath("//a[contains(@href, '/employees')]"));
  }

  public void students() {
    click(By.xpath("//a[contains(@href, '/childs')]"));
  }

  public void gotoTrainer() {
    click(By.xpath("//a[contains(@href, '/trainers')]"));
  }

  public void tasks() {
    click(By.xpath("//a[contains(@href, '/tasks')]"));
  }
}
