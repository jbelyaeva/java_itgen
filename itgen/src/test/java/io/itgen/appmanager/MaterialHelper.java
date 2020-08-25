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

  public void addNewBranch(String name) {
    click(By.xpath("//button[contains(@class,'dropdown')]"));
    noErrorMessage();
    click(By.xpath("//a[contains(@class,'add-branch')]"));
    noErrorMessage();
  }

  public void addNewMaterial(MaterialData material) {
    tabInProgress();
    fillForm(material);
    btnSend();
  }

  public void addNewMaterialBad(MaterialData material) {
    tabInProgress();
    fillForm(material);
    btnSendBad();
  }

  private void btnSendBad() {
    click(By.xpath("//button[contains(@class,'send')]"));
    thereAreErrorMessages();
  }

  public void openBranch() {
    click(By.xpath("//span[@data-branch]"));
    noErrorMessage();
  }

  public void TakeOnCheck() {
    tabInProgress();
    btnTakeForReview();
  }

  private void btnTakeForReview() {
    click(By.xpath("//button[contains(@class,'take-for-review')]"));
    noErrorMessage();
  }

  public void deleteMaterial() {
    tabInProgress();
    selectMaterial();
    btnDelete();
    alertDeleteSelectedParent();
  }

  private void alertDeleteSelectedParent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    noErrorMessage();
  }

  private void btnDelete() {
    click(By.xpath("//span[contains(@class,'remove')]"));
  }

  private void selectMaterial() {
    click(By.xpath("//ul[contains(@class,'stacked')]//li[2]"));
    noErrorMessage();
  }
}
