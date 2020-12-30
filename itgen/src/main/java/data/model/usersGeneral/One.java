package data.model.usersGeneral;

public class One {

  private int count;
  private int minutes;

  public One() {
  }

  public int getCount() {
    return count;
  }

  public One withCount(int count) {
    this.count = count;
    return this;
  }

  public int getMinutes() {
    return minutes;
  }

  public One withMinutes(int minutes) {
    this.minutes = minutes;
    return this;
  }
}
