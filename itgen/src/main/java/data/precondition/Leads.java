package data.precondition;

public class Leads extends TransactionManager {

  protected static final DataManager data = new DataManager();

  public void set1_leadParent(String idLead) {
    trLead()
        .leadParent(
            idLead,
            "Лид",
            "Лидов",
            "parent",
            "BY",
            "Europe/Minsk",
            "ru",
            "+7859561122",
            "mail@list.ru",
            "new",
            "site",
            "manual");
  }
}
