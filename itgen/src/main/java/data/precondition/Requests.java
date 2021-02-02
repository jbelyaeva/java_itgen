package data.precondition;

public class Requests extends TransactionManager {

  protected static final DataManager data = new DataManager();

  //заявка на пробное скретч на 2ч
  public void set1_requestOnScratch2hTrial() {
    trRequest()
        .newRequest(
            "newRequest", "666", "open", "student",
            "1", 2, false, true);
  }

  //заявка на пробное скретч на 1ч
  public void set2_requestOnScratch1hTrial() {
    trRequest()
        .newRequest(
            "newRequest", "666", "open", "student", "1", 1, false, true);
  }

  //заявка на постоянку скретч на 2ч
  public void set3_requestOnScratch2hRegular() {
    trRequest()
        .newRequest(
            "newRequest1", "666", "open", "student", "1", 2, true, false);
  }

  //заявка на разовое скретч на 2ч
  public void set4_requestOnScratch2hSingle() {
    trRequest()
        .newRequest(
            "newRequest", "666", "open", "student", "1", 2, false, false);
  }

  //заявка на разовое python на 2ч к Веронике
  public void set5_requestOnPython2hSingle() {
    trRequest()
        .newRequestToTrainer(
            "666", "open", "student", "2", 2, false, false, "4");
  }

  //заявка на разовое скретч на 2ч
  public void set6_requestOnScratch2hSingle(String idTrainer, String idStudent) {
    trRequest()
        .newRequestToTrainer(
            "666", "open", idStudent, "1", 2, false, false, idTrainer);
  }

  //заявка на разовое python на 2ч
  public void set7_requestOnPython2hSingle(int gender) {
    trRequest()
        .newRequestToGender(
            "newRequest2", "666", "open", "student", "2",
            2, false, false, gender);
  }

  //заявка на разовое python на 1ч
  public void set8_requestOnPython1hSingle(int gender) {
    trRequest()
        .newRequestToGender(
            "newRequest3", "666", "open", "student", "2", 1, false, false, gender);
  }

  //заявка на пробное скретч на 2ч на студента с др id
  public void set9_requestOnScratch1hTrial() {
    trRequest()
        .newRequest(
            "newRequest4", "666", "open", "newStudent", "1",
            2, false, true);
  }

  //заявка на пробное на ИФ скретч на 1ч
  public void set10_requestOnIFScratch1hTrial() {
    trRequest()
        .newRequestOnIF(
            "newRequest", "666", "open", "student", "1", 1, false, true);
  }

  //заявка на пайтон на пробное ИФ  на 1ч
  public void set11_requestOnIFPythonTrial() {
    trRequest()
        .newRequestOnIF(
            "newRequest", "666", "open", "student", "2", 1, false, true);
  }
}
