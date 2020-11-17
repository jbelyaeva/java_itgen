package app.appmanager;

import static app.appmanager.ApplicationManager.properties;

import core.general.FileHelper;
import data.model.communities.CommunityData;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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

}
