package data.model.usersGeneral;

public class TwentyOne {

  private int count;
  private int minutes;

  public TwentyOne() {
  }

  public int getCount() {
    return count;
  }

  public TwentyOne withCount(int count) {
    this.count = count;
    return this;
  }

  public int getMinutes() {
    return minutes;
  }

  public TwentyOne withMinutes(int minutes) {
    this.minutes = minutes;
    return this;
  }
}
