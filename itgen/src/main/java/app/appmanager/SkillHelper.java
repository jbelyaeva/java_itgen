package app.appmanager;

import data.model.skills.SkillsData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SkillHelper extends HelperBase {

  private By btnCreateSkill = By.xpath("//button[@id-qa='create']");
  private By selectLangSkill = By.xpath("//li[text()='Русский']");
  private By visualFormat = By.xpath("(//div[contains(@class,'select')])[6]");

  public SkillHelper(WebDriver wd) {
    super(wd);
  }

  public void goToFormWithCrateSkill() {
    click(btnCreateSkill);
    click(selectLangSkill);
  }

  public String getVisualFormat() {
    return wd.findElement(visualFormat).getText();
  }

  public void fillFormSkillWithIF(SkillsData skill) {
    type(By.xpath("//input[@id-qa='title']"), skill.getTitle());

    clear(20, By.xpath("//textarea[@id-qa='description']"));
    clickWithMoveToElementAndWait(1, By.xpath("//textarea[@id-qa='description']"));
    wd.findElement(By.xpath("//textarea[@id-qa='description']")).sendKeys(skill.getDesc());

    type(By.xpath("//input[@id-qa='p25']"), String.valueOf(skill.getAmountOfProjects().getP25()));
    type(By.xpath("//input[@id-qa='p50']"), String.valueOf(skill.getAmountOfProjects().getP50()));
    type(By.xpath("//input[@id-qa='p75']"), String.valueOf(skill.getAmountOfProjects().getP75()));
  }

  public void btnCreateSkill() {
    click(By.xpath("//button[@id-qa='add']"));
  }
}
