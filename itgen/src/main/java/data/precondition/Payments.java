package data.precondition;

public class Payments extends TransactionManager {

  protected static final DataManager data = new DataManager();

  /**
   * пополнен баланс админом у дефолтной семьи
   */
  public void set1_addPaymentInDefaultFamily() {
    trPayment().newPayment("newPayment", "111", "666", 1, 2, "Корректировка", true, 100);
  }

  /**
   * пополнен баланс админом у семьи
   */
  public void set2_addPaymentInFamily(String idFamily) {
    trPayment().newPayment("newPayment", idFamily, "666", 1, 2, "Корректировка", true, 100);
  }
}
