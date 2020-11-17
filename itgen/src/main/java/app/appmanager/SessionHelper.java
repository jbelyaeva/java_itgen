package app.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String username, String password) {
    type(By.name("username"), username);
    type(By.name("password"), password);
    clickWithMoveToElementAndWait(5, By.xpath("//button[contains(@class,'btn-login')]"));
  }
}
