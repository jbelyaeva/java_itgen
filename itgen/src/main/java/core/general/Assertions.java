package core.general;

import static core.general.DateFormat.formatMMMM;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertFalse;

import app.appmanager.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.collections.Sets;

public class Assertions extends HelperBase {
  public Assertions(WebDriver wd) {
    super(wd);
  }

  public Assertions() {
  }

  /* переводит массивы как множества (порядок не важен, элементы все уникальные) */
  public void twoMas(String[] masOld, String[] masNew){
    assertThat(SetUtils.equals(Sets.newHashSet(masOld), Sets.newHashSet(masNew)), is(true));
  }

  /* проверяет текст элемента с заданым значением
  * @locator - локатор элемента
  * @text - эталонный текст
  * */
  public void textElement(By locator, String text) {
    assertThat(wd.findElement(locator).getText(), is(text));
  }

  /* проверяет найден ли элемент по локатору
   * @locator - локатор элемента
   * */
  public void findElement(By locator) {
    assertTrue(isElementPresent(locator));
  }

  /* проверяет, что элемент не найден
   * @locator - локатор элемента
   * */
  public void notFindElement(By locator) {
    assertFalse(isElementPresent(locator));
  }

  /* проверяет, что с данным локатором найдено количество элементов равное ОР
   * @locator - локатор элемента
   * @result - ожидаемое количество
   * */
  public void countElements(By locator, int result) {
    assertThat(wd.findElements(locator).size(), is(result));
  }

  /* проверяет, что значение элемента (м/б разного типа данных) совпадает с эталонным
   * @element - проверяемое значение
   * @result - ожидаемый результат
   * */
  public <E> void equalityOfTwoElements(E element, E etalon) {
    assertThat(element, is(etalon));
  }

  /* проверяет, что переменная имеет статус true
   * @var - переменная
   * */
  public void assertTrue(Boolean var) {
    assertThat(var, is(true));
  }

  /* проверяет отображение следующего месяца
   *@monthUI - месяц, отображаемый в ui, который хотим проверить
   * */
  public void nextMonth(String monthUI) {
    assertThat(monthUI, is(formatMMMM(DateWithCorrectionMonth(+1))));
  }

  /* проверяет отображение предыдущего месяца
   *@monthUI - месяц, отображаемый в ui, который хотим проверить
   * */
  public void previosMonth(String monthUI) {
    assertThat(monthUI, is(formatMMMM(DateWithCorrectionMonth(-1))));
  }
}
