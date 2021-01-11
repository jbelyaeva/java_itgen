package app.appmanager;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

import data.model.materials.MaterialData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MaterialHelper extends HelperBase {

  public MaterialHelper(WebDriver wd) {
    super(wd);
  }

  public void tabInProgress() {
    if (!isElementPresent(By.xpath("//a[@href='#inProgress']"))) {
      refresh();
    }
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

  public void btnSendToCheck() {
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
    tabSend();
    btnSend();
    fillForm(material);
    btnSendToCheck();
  }

  private void btnSend() {
    click(By.xpath("//a[@href='#sendMaterial']"));
  }

  private void tabSend() {
    click(By.xpath("//button[@id-qa='send']"));
  }

  public void addNewMaterialBad(MaterialData material) {
    tabInProgress();
    tabSend();
    btnSend();
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

  public void TakeOnCheck(String id) {
    tabInProgress();
    tabCheck();
    branchWaitForReview();
    selectMaterial(id);
    btnTakeForReview();
  }

  public void trainerTakeOnCheck(String id) {
    tabInProgress();
    tabCheck();
    tabWaitChecking();
    selectMaterial(id);
    btnTakeForReview();
  }

  public void tabWaitChecking() {
    click(By.xpath("(//h4[@class='branch-header'])[2]//span"));
  }

  private void btnTakeForReview() {
    click(By.xpath("//button[@id-qa='take-for-review']"));
    noErrorMessage();
  }

  public void deleteMaterial(String id) {
    tabInProgress();
    btnChecking();
    branchTookForReview();
    selectMaterial(id);
    btnDelete();
    alertDeleteSelectedParent();
  }

  private void branchTookForReview() {
    click(By.xpath("(//h4[@class='branch-header'])[1]//span"));
  }

  private void btnChecking() {
    click(By.xpath("//button[@id-qa='approval']"));
  }

  private void alertDeleteSelectedParent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    noErrorMessage();
  }

  private void btnDelete() {
    click(By.xpath("//span[contains(@class,'remove')]"));
  }

  private void selectMaterial(String id) {
    click(By.xpath("//a[@aria-controls='" + id + "']"));
    noErrorMessage();
  }

  public void makeReview(String id) {
    tabInProgress();
    btnChecking();
    branchTookForReview();
    selectMaterial(id);
    selectReady();
    clickByBranch();
    assertTrue(isElementPresent(By.xpath("//a[@href='#" + id + "']")));
  }

  private void branchWaitForReview() {
    click(By.xpath("//span[@id-qa='show-full']"));
  }

  public void makeReviewTrainer(String id) {
    tabInProgress();
    tabCheck();
    branchTookForReview();
    selectMaterial(id);
    selectReady();
    clickByBranch();
    assertTrue(isElementPresent(By.xpath("//a[@href='#" + id + "']")));
  }

  private void tabCheck() {
    click(By.xpath("//button[@id-qa='approval']"));
  }

  private void clickByBranch() {
    click(By.xpath("//span[@data-branch]"));
    noErrorMessage();
  }

  private void selectReady() {
    dropDownList(By.id("material-status"), "published");
    noErrorMessage();
  }

  public void linkMaterials(String idRU, String name) {
    clickByBranch();
    selectMaterial(idRU);
    selectLinkMaterial(name);
    btnLink();
    tabEng();
  }

  private void tabEng() {
    ((JavascriptExecutor) wd).executeScript("window.scrollBy(0,-250)", "");
    click(By.xpath("//li[contains(@class,'switcher')][2]"));
  }

  private void btnLink() {
    click(By.xpath("//button[contains(@class,'create-link')]"));
    noErrorMessage();
  }

  private void selectLinkMaterial(String name) {
    type(By.id("searchLinkedMaterialsInput"), name);
    if (isElementPresent(By.xpath("//li[@class='search-result']"))) {
      click(By.xpath("//li[@class='search-result']"));
    } else {
      wd.findElement(By.id("id(searchLinkedMaterialsInput)")).clear();
      type(By.id("id(searchLinkedMaterialsInput)"), name);
      click(By.xpath("//li[@class='search-result']"));
    }
  }

  public void leaveComment(String id, String text) {
    clickByBranch();
    selectMaterial(id);
    writeComment(text);
    btnAddComment();
    checkComment(text);
  }

  private void checkComment(String text) {
    assertThat(
        wd.findElement(By.xpath("//span[contains(@class,'multiline')]")).getText(), equalTo(text));
  }

  private void btnAddComment() {
    click(By.xpath("//button[contains(@class,'add-comment')]"));
    noErrorMessage();
  }

  private void writeComment(String text) {
    click(By.xpath("//div[contains(@class,'text')]"));
    type(By.xpath("//div[contains(@class,'text')]"), text);
  }
}
