package data.precondition;

public class NewWorker extends TransactionManager {

  public void set1_NewWorker() {
    trWorker().saveNewWorker(
            "newWorker",
            "Sem",
            "Semov",
            "employee",
            "BL",
            "Europe/Minsk",
            "ru",
        "ru",
        "+7123455667",
        "email@email.ru"
    );
  }
}
