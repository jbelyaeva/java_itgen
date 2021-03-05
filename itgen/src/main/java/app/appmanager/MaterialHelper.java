package app.appmanager;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

import data.model.materials.MaterialData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MaterialHelper extends HelperBase {

  private final By materialInTabNew = By.xpath(
      "//div[contains(@class,'new-material-item-content')]");
  private final By counterInTabNew = By.xpath("//li[@role='new']//span");
  private final By tabInProgress = By.xpath("//a[@href='#inProgress']");
  private final By tabAll = By.xpath("//a[@href='#all']");
  private final By dropdownOriginality = By.id("material-originality");
  private final By dropdownLang = By.id("material-lang");
  private final By dropdownSkill = By.id("material-skill");
  private final By materialTitle = By.name("material-title");
  private final By materialType = By.id("material-type");
  private final By materialLink = By.name("material-materialLink");
  private final By materialSourceLink = By.name("material-sourceLink");
  private final By materialProjectLink = By.name("material-projectLink");
  private final By materialDesc = By.name("material-desc");
  private final By materialLevel = By.id("material-level");
  private final By btnRemoveLink = By.xpath("//button[contains(@class,'remove-link')]");
  private final By errorFieldProject = By.xpath("//span[contains(@class,'error')]");
  private final By tabRussian = By.xpath("//li[text()='Русский']");
  private final By tabEng = By.xpath("//li[text()='Английский']");
  private final By projectsInBranch = By.xpath("//ul[@data-branch='CreateNewMaterial']//li");
  private final By btnLink = By.xpath("//button[contains(@class,'create-link')]");
  private final By selectOriginalityInCheckingMaterial = By.xpath(
      "(//div[contains(@class,'select')][5]//div)[2]");
  private final By dropdownOriginalityInCheckingMaterial = By.xpath(
      "//div[contains(@class,'select')][5]//select");
  private final By selectTranslationInList = By.xpath("//option[@value='translation']");
  private final By inputLinkingMaterial = By.xpath("(//div[@class='dropdown']//input)[2]");
  private final By searchResult = By.xpath("//li[@class='search-result']");
  private final By btnAdd = By.xpath("//a[contains(@class,'publish-material')]");
  private final By btnAddMaterial = By.xpath("//button[@id-qa='send-material']");
  private final By btnEditComment = By.xpath("//a[@class='btn-edit-comment']");
  private final By btnRemoveComment = By.xpath("//a[@class='btn-remove-comment']");
  private final By textComment = By.xpath("(//div[contains(@class,'comment-text')])[2]");
  private final By btnSaveComment = By.xpath("//button[contains(@class,'save-comment')]");
  private final By textCommentInMaterial = By.xpath("//div[@class='panel panel-default']//span");
  private final By materialsOnBranchTakenForReview = By.xpath(
      "//li[@data-id='takenForReview']//div");
  private final By tabChecking = By.xpath("//button[@id-qa='approval']");

  public By getMaterialsOnBranchTakenForReview() {
    return materialsOnBranchTakenForReview;
  }

  public By getTextCommentInMaterial() {
    return textCommentInMaterial;
  }

  public MaterialHelper(WebDriver wd) {
    super(wd);
  }

  public By getMaterialInTabNew() {
    return materialInTabNew;
  }

  public By getCounterInTabNew() {
    return counterInTabNew;
  }

  public By getBtnRemoveLink() {
    return btnRemoveLink;
  }

  public By getErrorFieldProject() {
    return errorFieldProject;
  }

  public By getTabRussian() {
    return tabRussian;
  }

  public By getTabEng() {
    return tabEng;
  }

  public By getProjectsInBranch() {
    return projectsInBranch;
  }

  public By getBtnLink() {
    return btnLink;
  }

  public By getTabChecking() {
    return tabChecking;
  }

  public void tabInProgress() {
    if (!isElementPresent(tabInProgress)) {
      refresh();
    }
    click(tabInProgress);
    noErrorMessage();
  }

  public void fillForm(MaterialData materialData) {
    dropDownList(dropdownOriginality, materialData.getOriginality());
    dropDownList(dropdownLang, materialData.getLang());
    dropDownList(dropdownSkill, materialData.getSkill());
    type(materialTitle, materialData.getTitle());
    dropDownList(materialType, materialData.getType());
    type(materialLink, materialData.getMaterialLink());
    type(materialSourceLink, materialData.getSourceLink());
    type(materialProjectLink, materialData.getProjectLink());
    type(materialDesc, materialData.getDesc());
    dropDownList(materialLevel, materialData.getLevel());
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

  public void tabSend() {
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

  public void branchTookForReview() {
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

  public void branchWaitForReview() {
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

  public void tabCheck() {
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

  private void selectRevision(String text) {
    dropDownList(By.id("material-status"), "rework");
    noErrorMessage();
    clickWithMoveToElementAndWait(1, By.xpath("//textarea[contains(@class,'rework-comment')]"));
    wd.findElement(By.xpath("//textarea[contains(@class,'rework-comment')]")).sendKeys(text);
    click(By.xpath("//button[contains(@class,'btn-send')]"));
  }

  public void linkMaterials(String idRU, String name) {
    click(tabAll);
    clickByBranch();
    selectMaterial(idRU);
    selectLinkMaterial(name);
    btnLink();
    tabEng();
  }

  public void goInLinkedMaterials(String idRU) {
    click(tabAll);
    clickByBranch();
    selectMaterial(idRU);
  }

  private void tabEng() {
    ((JavascriptExecutor) wd).executeScript("window.scrollBy(0,-250)", "");
    click(By.xpath("//li[contains(@class,'switcher')][2]"));
  }

  private void btnLink() {
    click(btnLink);
    noErrorMessage();
  }

  private void selectLinkMaterial(String name) {
    type(By.id("searchLinkedMaterialsInput"), name);
    if (isElementPresent(searchResult)) {
      click(searchResult);
    } else {
      wd.findElement(By.id("id(searchLinkedMaterialsInput)")).clear();
      type(By.id("id(searchLinkedMaterialsInput)"), name);
      click(searchResult);
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

  public void makeSendRevision(String id, String text) {
    tabInProgress();
    tabCheck();
    branchTookForReview();
    selectMaterial(id);
    selectRevision(text);
  }

  public void checkNew() {
    click(By.xpath("//div[contains(@class,'wrap-checkbox')]"));
  }

  public void btnRemoveFromNew() {
    click(By.xpath("//div[@class='button-wrap']//button"));
    waitVisibleElement(5, By.xpath("//div[contains(@class,'new-material-doesnt-exist')]"));
    refresh();
  }

  public void addNewLinkMaterial(MaterialData material) {
    tabInProgress();
    tabSend();
    btnSend();
    fillFormLinkMaterial(material);
    btnSendToCheck();
  }

  public void addNewLinkMaterialBad(MaterialData material) {
    tabInProgress();
    tabSend();
    btnSend();
    fillFormLinkMaterialBad(material);
    btnSendToCheck();
  }

  private void fillFormLinkMaterialBad(MaterialData material) {
    dropDownList(dropdownOriginality, material.getOriginality());
    type(materialTitle, material.getTitle());
    type(materialLink, material.getMaterialLink());
    type(materialSourceLink, material.getSourceLink());
    type(materialProjectLink, material.getProjectLink());
    type(materialDesc, material.getDesc());
    dropDownList(materialLevel, material.getLevel());
  }

  private void fillFormLinkMaterial(MaterialData material) {
    dropDownList(dropdownOriginality, material.getOriginality());
    type(inputLinkingMaterial, "Beetles");
    click(searchResult);
    type(materialTitle, material.getTitle());
    type(materialLink, material.getMaterialLink());
    type(materialSourceLink, material.getSourceLink());
    type(materialProjectLink, material.getProjectLink());
    type(materialDesc, material.getDesc());
    dropDownList(materialLevel, material.getLevel());
  }

  public void goInPublishedMaterial(String idBranch, String idMaterial) {
    click(tabAll);
    click(By.xpath("//li[@data-id='" + idBranch + "']//span"));
    click(By.xpath("//a[@href='#" + idMaterial + "']"));
  }

  public void btnRemoveLink() {
    click(btnRemoveLink);
    waitVisibilityOfElementLocated(3, btnLink);
  }

  public void trainerLinkedMaterialWhenChecking(String material, String name) {
    tabInProgress();
    tabCheck();
    branchTookForReview();
    selectMaterial(material);
    fillFormLinkMaterialWhichOnReview(name);
    btnLink();
  }

  private void fillFormLinkMaterialWhichOnReview(String name) {
    click(selectOriginalityInCheckingMaterial);
    click(dropdownOriginalityInCheckingMaterial);
    click(selectTranslationInList);
    type(inputLinkingMaterial, name);
    click(searchResult);
  }

  public void findPublishedMaterial(String idMaterial) {
    tabAll();
    clickByBranch();
    selectMaterial(idMaterial);
  }

  public void trainerRemoveLinkMaterialWhenChecking(String material) {
    tabInProgress();
    tabCheck();
    branchTookForReview();
    selectMaterial(material);
    btnRemoveLink();
  }

  public void addNewMaterialInTabAll(MaterialData material) {
    tabAll();
    btnAdd();
    fillFormForPublishing(material);
    btnAddMaterial();
  }

  private void btnAddMaterial() {
    click(btnAddMaterial);
  }

  private void fillFormForPublishing(MaterialData materialData) {
    type(materialTitle, materialData.getTitle());
    type(materialLink, materialData.getMaterialLink());
    type(materialSourceLink, materialData.getSourceLink());
    type(materialProjectLink, materialData.getProjectLink());
    type(materialDesc, materialData.getDesc());
  }

  private void btnAdd() {
    click(btnAdd);
  }

  private void tabAll() {
    click(tabAll);
  }

  public void modifyComment(String material, String newComment) {
    tabInProgress();
    tabCheck();
    branchTookForReview();
    selectMaterial(material);
    btnEditComment();
    clear(15, textComment);
    type(textComment, newComment);
    btnSaveComment();
  }

  private void btnSaveComment() {
    click(btnSaveComment);
  }

  private void btnEditComment() {
    click(btnEditComment);
  }

  public void btnRemoveComment() {
    click(btnRemoveComment);
  }

  public void trainerTakeSecondMaterialOnCheck(String id) {
    selectMaterial(id);
    btnTakeForReview();
  }
}