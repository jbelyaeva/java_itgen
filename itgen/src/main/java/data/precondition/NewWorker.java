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
        "Sem",
        "Semov",
        new String[]{"trainer", "employee"},
        "BL",
        "Europe/Minsk",
        "fr",
        "fr",
        "+7123455667",
        "email@email.ru",
        new String[]{"2"}
    );
  }

  public void set3_Trainer() {
    trWorker().saveNewTrainer(
        "newTrainer",
        "Маша",
        "Машина",
        "Sem",
        "Semov",
        new String[]{"trainer", "employee"},
        "AL",
        "Europe/Minsk",
        "ru",
        "ru",
        "+7123455667",
        "email@email.ru",
        new String[]{"2"}
    );
  }
}
