package data.services;

import data.dao.TierCountryDao;
import data.dao.TierDao;
import data.model.tier.TierCountryData;
import data.model.tier.TierData;

public class TierService {

  private final TierCountryDao tierCountryDao = new TierCountryDao();
  private final TierDao tierDao = new TierDao();

  public TierService() {
  }

  public void save(TierCountryData tierCountryData) {
    tierCountryDao.save(tierCountryData);
  }

  public void dropTierC() {
    tierCountryDao.drop();
  }

  public void dropTier() {
    tierDao.drop();
  }

  public void deleteField(String idProd, String nameField) {
    tierCountryDao.deleteField(idProd, nameField);
  }

  public void saveTier(TierData tierData) {
    tierDao.save(tierData);
  }

  public void deleteFieldTier(String idProd, String nameField) {
    tierDao.deleteField(idProd, nameField);
  }
}
