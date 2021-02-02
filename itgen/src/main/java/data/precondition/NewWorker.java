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

  public void set2_NewFrenchTrainer() {
    trWorker().saveNewTrainer(
        "newTrainer",
        "Sem",
        "Semov",
        "trainer",
        "BL",
        "Europe/Minsk",
        "fr",
        "fr",
        "+7123455667",
        "email@email.ru",
        "2"
    );
  }
}
