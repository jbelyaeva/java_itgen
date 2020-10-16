package io.itgen.appmanager;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;

import io.itgen.model.typeform.TestData;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestHelper extends HelperBase {

  public static Properties properties;

  public String buttonAtributAvailable(By locator) {
    //из документации: атрибут disabled возвращает либо true, либо null
    return wd.findElement(locator).getAttribute("disabled");
  }

  public void checkHrefInProfileStudent(By locator) throws InterruptedException {
    String href = wd.findElement(locator).getAttribute("defaultValue");
    wd.findElement(locator).click();
    click(By.xpath("//div[@class='chat-button']"));
    click(By.xpath("//div[@class='search-input']//input"));
    type(By.xpath("//div[@class='search-input']//input"), "Дефолтный ребенок");
    click(By.xpath("//a[@href='/profile/21']"));
    Thread.sleep(5000);
    click(By.xpath("//textarea[1]"));

    Actions actions = new Actions(wd);
    wd.findElement(By.xpath("//textarea[1]")).sendKeys(Keys.COMMAND, "v");
    actions.sendKeys(Keys.chord(Keys.COMMAND + "v")).build().perform();
    actions.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
    String hrefNew = wd.findElement(By.xpath("//div[@class='message multiline-text']")).getText();
    click(By.xpath("//div[contains(@class,'dropdown')]//span"));
    click(By.xpath("//a[@class='remove-message']"));
    assertThat(href, equalTo(hrefNew));
  }


  public TestHelper(WebDriver wd) {
    super(wd);
  }

  public void createTest(TestData test) {
   // btnAddTestInAdm(); - очень глючная кнопка Добавить, делает тест нестабильным
    wd.get(address()+"/tests/addTest");
    selectFirstLangInList();
    fillFormCreateTest(test);
    btnAddTest();
  }

  private void btnAddTest() {
    click(By.xpath("//button[contains(@class,'create-test')]"));
    WebDriverWait wait = new WebDriverWait(wd, 50);
    wait.until(ExpectedConditions
        .elementToBeClickable(wd.findElement(By.xpath("//div[@class='test-buttons']"))));
  }

  private void fillFormCreateTest(TestData test) {
    type(By.xpath("//div[@id-qa='title']//input"), test.getTitle());
    wd.findElement(By.xpath("//div[@id-qa='description']//textarea"))
        .sendKeys(test.getDescription());

    type(By.xpath("//div[@id-qa='rootFormId']//input"), test.getRootFormId());
    click(By.xpath("//button[@id-qa='skills']")); //открыть список
    click(
        By.xpath("(//form//div)[2]"));  // первый - скретч - по умолчанию, добавим еще компьютерную
    //грамотность
    click(By.xpath("//button[@id-qa='skills']"));//закрыть список

    type(By.xpath("//div[@id-qa='maxScore']//input"), String.valueOf(test.getMaxScore()));
    type(By.xpath("//div[@id-qa='minScore']//input"), String.valueOf(test.getMinScore()));
    type(By.xpath("//div[@id-qa='timeForPassing']//input"),
        String.valueOf(test.getTimeForPassing()));
  }

  private void selectFirstLangInList() {
    click(By.xpath("//li[@id-qa='new-lang'][1]"));
  }

  private void btnAddTestInAdm() {
    moveToElement(By.xpath("//button[@id-qa='create']"));
    clickWaitElementToBeClicable(5, By.xpath("//button[@id-qa='create']"));
    waiteVisibleElement(10, By.xpath("//li[@id-qa='new-lang'][1]"));
  }

  public void addEnglishTest(TestData test) {
    btnEditTest();
    btnAddTab();
    selectFirstLangInList();
    fillFormAddLang(test);
    btnSave();
  }

  private void btnSave() {
    click(By.xpath("//div[contains(@class,'inputs')]/following-sibling::*"));
  }

  private void fillFormAddLang(TestData test) {
    type(By.xpath("//div[@id-qa='title']//input"), test.getTitle());
    wd.findElement(By.xpath("//div[@id-qa='description']//textarea"))
        .sendKeys(test.getDescription());
    type(By.xpath("//div[@id-qa='rootFormId']//input"), test.getRootFormId());
    //проверить, что выбран чек-бокс
    click(By.xpath("//button[@id-qa='skills']"));
    assertThat(wd.findElement(By.xpath("//input[@id='checkbox-id-1']"))
        .getAttribute("defaultChecked"), equalTo("true"));
    click(By.xpath("//button[@id-qa='skills']"));

    //проверить, что общие поля заполнены
    String maxScore = wd.findElement(By.xpath("//div[@id-qa='maxScore']//input"))
        .getAttribute("defaultValue");
    assertFalse(maxScore.isEmpty());

    String minScore = wd.findElement(By.xpath("//div[@id-qa='minScore']//input"))
        .getAttribute("defaultValue");
    assertFalse(minScore.isEmpty());

    String timeForPassing = wd.findElement(By.xpath("//div[@id-qa='timeForPassing']//input"))
        .getAttribute("defaultValue");
    assertFalse(timeForPassing.isEmpty());

  }

  private void fillFormModify(TestData test) {
    click(By.xpath("//div[@id-qa='title']//input"));
    clear(30, By.xpath("//div[@id-qa='title']//input"));
    type(By.xpath("//div[@id-qa='title']//input"), test.getTitle());

    wd.findElement(By.xpath("//div[@id-qa='description']//textarea")).sendKeys(Keys.END);
    clear(50, By.xpath("//div[@id-qa='description']//textarea"));
    wd.findElement(By.xpath("//div[@id-qa='description']//textarea"))
        .sendKeys(test.getDescription());

    click(By.xpath("//div[@id-qa='rootFormId']//input"));
    clear(30, By.xpath("//div[@id-qa='rootFormId']//input"));
    type(By.xpath("//div[@id-qa='rootFormId']//input"), test.getRootFormId());

    click(By.xpath("//button[@id-qa='skills']")); //открыть список
    click(By.xpath("(//form//div)[2]"));  //поставим компьютерную грамотность
    click(By.xpath("//label[@for='checkbox-id-1']")); //уберем скретч
    click(By.xpath("//button[@id-qa='skills']"));//закрыть список

    click(By.xpath("//div[@id-qa='maxScore']//input"));
    clear(5, By.xpath("//div[@id-qa='maxScore']//input"));
    type(By.xpath("//div[@id-qa='maxScore']//input"), String.valueOf(test.getMaxScore()));

    click(By.xpath("//div[@id-qa='minScore']//input"));
    clear(5, By.xpath("//div[@id-qa='minScore']//input"));
    type(By.xpath("//div[@id-qa='minScore']//input"), String.valueOf(test.getMinScore()));

    click(By.xpath("//div[@id-qa='timeForPassing']//input"));
    clear(5, By.xpath("//div[@id-qa='timeForPassing']//input"));
    type(By.xpath("//div[@id-qa='timeForPassing']//input"),
        String.valueOf(test.getTimeForPassing()));
  }

  private void btnAddTab() {
    click(By.xpath("//div[@class='add-tab-btn']"));
  }

  private void btnEditTest() {
    click(By.xpath("//div[@class='test-buttons']/*"));
  }

  public void deleteTest() {
    btnDeleteTest();
    bntConfirmDelete();
    waiteVisibleElement(5, By.xpath("//div[@class='childTests-page']//span//span"));
  }

  private void bntConfirmDelete() {
    click(By.xpath("//div[@role='dialog']//button[2]"));
  }

  private void btnDeleteTest() {
    click(By.xpath("//div[@class='test-buttons']/*[2]"));
  }

  public void modifyTest(TestData test) {
    btnModifyTest();
    fillFormModify(test);
    btnSave();
  }

  private void btnModifyTest() {
    click(By.xpath("//div[@class='test-buttons']/*[1]"));
  }

  public void goToStudentProfileTabTests(String idStudent) {
    wd.get(address() + "/profile/" + idStudent + "?tab=tests");
  }

  public void goToStudentProfileTabHistory(String idStudent) {
    wd.get(address() + "/profile/" + idStudent + "?tab=history");
  }

  public Boolean buttonGiveTestMissing() {
    return isElementPresent(By.xpath("//button[@id-qa='give-test']"));
  }

  public void checkHrefResults() {
    clickWithMoveToElementAndWait(5, By.xpath("//a[@class='answers']"));
    assertThat(wd.findElement(By.xpath("//div[@class='title']")).getText(),
        equalTo("Просмотр ответов"));
  }

  public void deleteTestInProfile() {
    clickWithMoveToElementAndWait(8, By.xpath("//button[@id-qa='delete-test']"));
  }

  public Set<String> getLanguagesInDropdown() {
     Set<String> langs = new HashSet<>();
    click(By.xpath("//div[@role='button']"));

    List<WebElement> langsUI = wd.findElements(By.xpath("//ul[@role='listbox']//li"));
    int count = langsUI.size();
    for (int i=1; i<=count ; i++){
      String lang = wd.findElement(By.xpath("//ul[@role='listbox']//li["+i+"]")).getAttribute("textContent");
      langs.add(lang);
    }
    return langs;
  }

  public Set<String> getEtalonSetInDropdown(String lang1, String lang2) {
    Set<String> etalon = new HashSet<>();
    etalon.add(lang1);
    etalon.add(lang2);
    return etalon;
  }

  public void deleteTestConnectionWithSkills() {
    btnDeleteTest();
    bntConfirmDelete();
    thereAreErrorMessages();
  }
}
