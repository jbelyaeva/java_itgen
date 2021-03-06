package data.model.family;

import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.List;

@dev.morphia.annotations.Entity("families")
public class FamilyData {

  @Id
  @Property("_id")
  private String id;

  @Property("className")
  private String className;

  @Property("isTrialBonusOff")
  private Boolean isTrialBonusOff;

  @Property("tierHistory")
  private List<String> tierHistory = new ArrayList<String>();

  public static class TierHistory {

    private String tierHistory;

    public FamilyData.TierHistory withTierHistory(String type) {
      this.tierHistory = type;
      return this;
    }

    public String getTierHistory() {
      return tierHistory;
    }
  }

  @Property("tierId")
  private String tierId;

  // сеттеры
  public FamilyData withId(String id) {
    this.id = id;
    return this;
  }

  public FamilyData withTrialBonusOff(Boolean trialBonusOff) {
    this.isTrialBonusOff = trialBonusOff;
    return this;
  }

  public FamilyData withTierHistory(List<String> tierHistory) {
    this.tierHistory = tierHistory;
    return this;
  }

  public FamilyData withTierId(String tierId) {
    this.tierId = tierId;
    return this;
  }

  // геттеры

  public String getId() {
    return id;
  }

  public Boolean getTrialBonusOff() {
    return isTrialBonusOff;
  }

  public List<String> getTierHistory() {
    return tierHistory;
  }

  public String getTierId() {
    return tierId;
  }
}
