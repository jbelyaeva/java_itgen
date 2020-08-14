package io.itgen.appmanager;

import io.itgen.model.materials.MaterialData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MaterialHelper extends HelperBase {

  public MaterialHelper(WebDriver wd) {
    super(wd);
  }

  public void tabInProgress() {
    click(By.xpath("//a[@href='#inProgress']"));
    noErrorMessage();
  }

  public void fillForm(MaterialData materialData) {
    dropDownList(By.id("material-originality"), materialData.getOriginality());
    dropDownList(By.id("material-lang"), materialData.getLang());
    dropDownList(By.id("material-skill"), materialData.getSkill());
    type(By.name("material-title"), materialData.getTitle());
    dropDownList(By.id("material-type"), materialData.getType());
    type(By.name("material-materialLink"), materialData.getMaterialLink());
    type(By.name("material-sourceLink"), materialData.getSourceLink());
    type(By.name("material-projectLink"), materialData.getProjectLink());
    type(By.name("material-desc"), materialData.getDesc());
    dropDownList(By.id("material-level"), materialData.getLevel());
  }

  public void btnSend() {
    click(By.xpath("//button[contains(@class,'send')]"));
    noErrorMessage();
  }

  public void addNewBranch() {
    click(By.xpath("//button[contains(@class,'dropdown')]"));
    noErrorMessage();
    click(By.xpath("//a[contains(@class,'add-branch')]"));
    noErrorMessage();
    click(By.xpath("//div[contains(@class,'branch')]"));
  }

  public void addNewMaterial(MaterialData material) {
    addNewBranch();
    tabInProgress();
    fillForm(material);
    btnSend();
  }
}
