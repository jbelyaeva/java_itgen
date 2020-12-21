package data.precondition;

public class Payments extends TranzactionManager {

  protected static final DataManager data = new DataManager();

  /**
   * пополнен баланс админом у дефолтной семьи
   */
  public void set1_addPaymentInDefaultFamily() {
    trPayment().newPayment("newPayment", "111", "666", 1, 2, "Корректировка", true, 100);
  }
}
