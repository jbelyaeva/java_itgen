package data.services;

import data.dao.SkillsDao;
import data.dao.SkillsOrderDao;
import data.model.skills.SkillsData;
import data.model.skills.SkillsOrderData;

public class SkillsService {

  private final SkillsDao skillsDao = new SkillsDao();
  private final SkillsOrderDao skillsOrderDao = new SkillsOrderDao();

  public SkillsService() {
  }

  public void save(SkillsData skill) {
    skillsDao.save(skill);
  }

  public void saveOrder(SkillsOrderData skillsOrder) {
    skillsOrderDao.save(skillsOrder);
  }

  public void drop() {
    skillsDao.drop();
  }

  public void dropSkillOrder() {
    skillsOrderDao.drop();
  }

  public void updateField(String idSkill, String nameFiled, String data) {
    skillsDao.updateField(idSkill, nameFiled, data);
  }

  public void updateField(String idSkill, String nameFiled, Boolean data) {
    skillsDao.updateField(idSkill, nameFiled, data);
  }

  public void deleteField(String idSkills, String nameField) {
    skillsDao.deleteField(idSkills, nameField);
  }

  public void deleteFieldInOrder(String idSkills, String nameField) {
    skillsOrderDao.deleteField(idSkills, nameField);
  }

  public SkillsData findBySkillId(String skillId, String lang) {
    return skillsDao.findBySkillId(skillId, lang);
  }

  public SkillsData DeleteById(String id) {
    return skillsDao.deleteById(id);
  }
}
