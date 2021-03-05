package app.appmanager;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RequestHelper extends HelperBase {

  private final By requestInStack = By.xpath("//tr[@class='request-list-item']");
  private final By requestGreenInStack = By.xpath("//tr[@class='request-list-item bg-success']");
  private final By btnPoint = By.xpath("//button[contains(@class,'dropdown')]");
  private final By btnDoneRequest = By.xpath("//a[contains(@class,'close')]");
  private final By btnClosePopup = By.xpath("(//button[@class='close'])[1]");
  private final By statusRequest = By.xpath("//td[@class='request-status']");
  private final By btnApply = By.xpath("//button[contains(@class,'apply')]");
  private final By btnDelete = By.xpath("//button[contains(@class,'remove')]");
  private final By btnAlertDelete = By.xpath("//button[@class='btn btn-danger']");
  private final By labelWindowsRecord = By.xpath("//h2[text()='Запись на занятие']");
  private final By labelListEmpty = By.xpath("//span[text()='Список пуст']");
  private final By indicator = By.xpath("//span[contains(@class,'skill-alert')]");
  private final By indicatorMessage = By.xpath("//div[@class='tooltip fade bottom in']");
  private final By labelTrainer = By.xpath("//td[@class='request-trainer']");
  private final By btnFilterClose = By.xpath("//button[@class='close']");
  private final By dropdownSkillInFilter = By.xpath("//div[contains(@class,'skill')]//button");
  private final By selectScratch = By.xpath("//input[@data-id='1']");
  private final By btnAcceptInFilter = By.xpath("//button[contains(@class,'accept')]");
  private final By dropdownLangInFilter = By.xpath("//select[@id='filter-lang']");
  private final By selectEng = By.xpath("//select[@id='filter-lang']//option[@value='en']");
  private final By selectAllEng = By.xpath("//select[@id='filter-lang']//option[text()='Все']");
  private final By dropdownTrainerInFilter = By.xpath("//select[@id='filter-trainer']");
  private final By selectAllTrainer = By.xpath(
      "//select[@id='filter-trainer']//option[text()='Все']");
  private final By dropdownTypeLessonInFilter = By.xpath("//select[@id='filter-trial']");
  private final By selectTrial = By.xpath("//select[@id='filter-trial']//option[@value='trial']");
  private final By selectAllType = By.xpath("//select[@id='filter-trial']//option[text()='Все']");
  private final By dropdownDurationInFilter = By.xpath("//select[@id='filter-duration']");
  private final By select1h = By.xpath("//select[@id='filter-duration']//option[@value='1']");
  private final By selectAllDuration = By.xpath(
      "//select[@id='filter-duration']//option[text()='Все']");
  private final By dropdownGenderInFilter = By.xpath("//select[@id='filter-gender']");
  private final By selectMale = By.xpath("//select[@id='filter-gender']//option[@value='1']");
  private final By selectAllGenders = By.xpath(
      "//select[@id='filter-gender']//option[text()='Любой']");

  public RequestHelper(WebDriver wd) {
    super(wd);
  }

  public By getRequestInStack() {
    return requestInStack;
  }

  public By getRequestGreenInStack() {
    return requestGreenInStack;
  }

  public By getStatusRequest() {
    return statusRequest;
  }

  public By getLabelWindowsRecord() {
    return labelWindowsRecord;
  }

  public By getLabelListEmpty() {
    return labelListEmpty;
  }

  public By getIndicator() {
    return indicator;
  }

  public By getIndicatorMessage() {
    return indicatorMessage;
  }

  public By getLabelTrainer() {
    return labelTrainer;
  }

  public void selectGreenRequest() {
    click(requestGreenInStack);
  }

  public void btnPoint() {
    click(btnPoint);
  }

  public void btnDoneRequest() {
    click(btnDoneRequest);
  }

  public void btnClosePopup() {
    click(btnClosePopup);
  }

  public void btnApply() {
    click(btnApply);
  }

  public void btnDeleteRequest() {
    click(btnDelete);
  }

  public void btnAlertDelete() {
    click(btnAlertDelete);
  }

  public void btnFilterClose() {
    click(btnFilterClose);
  }

  public void btnDropdownSkill() {
    click(dropdownSkillInFilter);
  }

  public void selectScratch() {
    click(selectScratch);
  }

  public void btnAcceptInFilter() {
    clickWithMoveToElementAndWait(3, btnAcceptInFilter);
  }

  public String[] getListStudents() throws InterruptedException {
    Thread.sleep(4000);
    List<WebElement> list = wd.findElements(By.xpath("//td[@class='request-child']//a"));
    String[] listUI = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      listUI[i] = list.get(i).getText();
    }
    return listUI;
  }

  public String[] getListTypeLesson() {
    List<WebElement> list = wd.findElements(By.xpath("//td[@class='request-schedule-type']"));
    String[] listUI = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      listUI[i] = list.get(i).getText();
    }
    return listUI;
  }

  public void btnDropdownLang() {
    click(dropdownLangInFilter);
  }

  public void selectEng() {
    click(selectEng);
  }

  public void selectAllLang() {
    click(selectAllEng);
  }

  public void btnDropdownTrainer() {
    click(dropdownTrainerInFilter);
  }

  public void selectTrainer(String idTrainer) {
    click(By.xpath("//option[@value='" + idTrainer + "']"));
  }

  public void selectAllTrainers() {
    click(selectAllTrainer);
  }

  public void btnDropdownTypeLesson() {
    click(dropdownTypeLessonInFilter);
  }

  public void selectTrial() {
    click(selectTrial);
  }

  public void selectAllTypes() {
    click(selectAllType);
  }

  public void btnDropdownDuration() {
    click(dropdownDurationInFilter);
  }

  public void select1h() {
    click(select1h);
  }

  public void selectAllDuration() {
    click(selectAllDuration);
  }

  public void btnDropdownGender() {
    click(dropdownGenderInFilter);
  }

  public void selectMale() {
    click(selectMale);
  }

  public void selectAllGenders() {
    click(selectAllGenders);
  }
}
