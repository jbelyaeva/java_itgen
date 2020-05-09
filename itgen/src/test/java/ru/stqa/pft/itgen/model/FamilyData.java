package ru.stqa.pft.itgen.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "families")
public class FamilyData {
  @Id
  @Column(name = "_id")
  private String id;

  @Column(name = " isTrialBonusOff")
  private Boolean isTrialBonusOff;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "tierHistory")
  private List<FamilyData.TierHistory> tierHistory = new ArrayList<FamilyData.TierHistory>();

  @Embeddable
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

  @Column(name = "tierId")
  private String tierId;

  //сеттеры
  public FamilyData withId(String id) {
    this.id = id;
    return this;
  }

  public FamilyData withTrialBonusOff(Boolean trialBonusOff) {
    this.isTrialBonusOff = trialBonusOff;
    return this;
  }

  public FamilyData withTierHistory(List<TierHistory> tierHistory) {
    this.tierHistory = tierHistory;
    return this;
  }

  public FamilyData withTierId(String tierId) {
    this.tierId = tierId;
    return this;
  }

  //геттеры


  public String getId() {
    return id;
  }

  public Boolean getTrialBonusOff() {
    return isTrialBonusOff;
  }

  public List<TierHistory> getTierHistory() {
    return tierHistory;
  }

  public String getTierId() {
    return tierId;
  }


}
