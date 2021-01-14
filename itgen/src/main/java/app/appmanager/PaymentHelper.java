package app.appmanager;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import data.model.payment.PaymentData;
import data.model.payment.Payments;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentHelper extends HelperBase {

  StudentHelper student = new StudentHelper(wd);
  private final By btnPay = By.xpath("//a[contains(@href,'/pay/')]");

  public By getBtnPay() {
    return btnPay;
  }

  public PaymentHelper(WebDriver wd) {
    super(wd);
  }

  public void btnPay() {
    click(btnPay);
  }

  public void btnPay4Lessons() {
    WebElement dynamicElement =
        (new WebDriverWait(wd, 20))
            .until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='family-product-item'][1]//a")));
    dynamicElement.click();
    noErrorMessage();
  }

  public void fillTestCard() {
    if (!isElementPresent(By.id("request_credit_card_number_1"))) btnPay4Lessons();

    type(By.id("request_credit_card_number_1"), "4200");
    type(By.id("request_credit_card_number_1"), "4200");
    type(By.id("request_credit_card_number_2"), "0000");
    type(By.id("request_credit_card_number_3"), "0000");
    type(By.id("request_credit_card_number_4"), "0000");
    type(By.id("request_credit_card_exp_month"), "01");
    type(By.id("request_credit_card_exp_year"), "25");
    type(By.xpath("//input[contains(@placeholder,'IVAN')]"), "IVAN IVANOV");
    type(By.xpath("//input[contains(@placeholder,'CVC')]"), "123");
    type(By.xpath("//input[contains(@type,'email')]"), "julja@list.ru");
  }

  public void paymentAdmin(String id) {
    student.selectStudentInListUIById(id);
    student.btnFamily();
    btnPay();
    btnPay4Lessons();
    fillTestCard();
    // btnBuy(); нужно решение с внешним апи
  }

  private void btnBuy() {
    click(By.xpath("//input[contains(@name,'commit')]"));
  }

  public Boolean findPictureSuccessPay() {
    wd.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    return isElementPresent(By.xpath("//img[contains(@alt,'Success')]"));
  }

  public void paymentAdminShop(String id) {
    student.selectStudentInListUIById(id);
    student.btnFamily();
    btnPay();
    wd.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
  }

  public void paymentAdminCard(String id) {
    student.selectStudentInListUIById(id);
    student.btnFamily();
    btnPay();
    wd.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    btnPay4Lessons();
    fillTestCard();
  }

  public void goToFamily(String id) {
    wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    wd.get("http://localhost:3000/family/" + id);
  }

  public void goToShopByParent() {
    btnPay();
    wd.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    noErrorMessage();
  }

  public String getHrefOnPaymentForGuest() {
    return wd.getCurrentUrl();
  }

  public void paymentByGuest() {
    wd.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    btnPay4Lessons();
    fillTestCard();
    //  btnBuy(); нужно решение по взаимодействию с внешним апи
    wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }

  public void goToShopByGuest() {
    String urlForGuest = getHrefOnPaymentForGuest();
    logout();
    goByHref(urlForGuest);
  }

  public void paymentByParent() {
    btnPay4Lessons();
    fillTestCard();
    btnBuy();
    new WebDriverWait(wd, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1")));
  }

  public void increaseAdmin(String id, String lessons) {
    student.selectStudentInListUIById(id);
    student.btnFamily();
    Integer before = Integer.valueOf(getBalance());
    btnPencil();
    fillForm(lessons, "Увеличение баланса на ");
    btnConfirm();
    Integer after = Integer.valueOf(getBalance());
    // проверка, что баланс увеличился на 1 через ui
    assertThat(after, equalTo(before + 1));
  }

  private String getBalance() {
    String balance = getText("//span[@class='count']");
    String[] text = balance.split(" ");
    return text[0];
  }

  private void btnConfirm() {
    click(By.xpath("//button[contains(@class,'change')]"));
    noErrorMessage();
  }

  private void fillForm(String lessons, String text) {
    type(By.name("balance"), lessons);
    type(By.name("sum"), "100");
    wd.findElement(By.xpath("//input[@name='desc']")).clear();
    String value =
        wd.findElement(By.xpath("//datalist[@id='desc-templates-list']//option[9]"))
            .getAttribute("value");
    wd.findElement(By.xpath("//input[@name='desc']")).sendKeys(value);
    type(By.name("note"), text + lessons);
  }

  private void btnPencil() {
    click(By.xpath("//div[contains(@class,'balance')]//span[contains(@class,'pencil')]"));
    noErrorMessage();
  }

  public PaymentData getNewPaymentDB(Payments before, Payments after) {
    PaymentData paymentNew = null;
    for (PaymentData paymentListAfter : after) {
      if (!before.contains(paymentListAfter)) {
        paymentNew = paymentListAfter;
        break;
      }
    }
    return paymentNew;
  }

  public void decreaseAdmin(String id, String lessons) {
    student.selectStudentInListUIById(id);
    student.btnFamily();
    int before = Integer.valueOf(getBalance());
    btnPencil();
    fillForm(lessons, "Уменьшение баланса на ");
    btnConfirm();
    int after = Integer.valueOf(getBalance());
    // проверка, что баланс уменьшился на 1 через ui
    assertThat(after, equalTo(before - 1));
  }

  public String getPayer() {
    waitVisibleElement(5, By.xpath("(//p[contains(@class,'static')])[2]//span"));
    return getText("(//p[contains(@class,'static')])[2]//span");
  }
}
