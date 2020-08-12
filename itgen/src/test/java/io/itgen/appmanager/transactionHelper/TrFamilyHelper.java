package io.itgen.appmanager.transactionHelper;

import io.itgen.model.FamilyData;
import io.itgen.services.FamilyService;

public class TrFamilyHelper {

  private final FamilyService familyService = new FamilyService();

  public void newFamily(String idFamily, Boolean trialBonus, String tierId) {
    FamilyData family =
        new FamilyData().withId(idFamily).withTrialBonusOff(trialBonus).withTierId(tierId);
    familyService.save(family);
  }
}
