package data.services;

import data.dao.SkillsDao;
import data.model.skills.SkillsData;

public class SkillsService {

  private final SkillsDao skillsDao = new SkillsDao();

  public SkillsService() {}

  public void save(SkillsData skill) {
    skillsDao.save(skill);
  }

  public void drop() {
    skillsDao.drop();
  }

  public void updateField(String idSkill, String nameFiled, String data) {
    skillsDao.updateField(idSkill, nameFiled, data);
  }

  public void deleteField(String idSkills, String nameField) {
    skillsDao.deleteField(idSkills, nameField);
  }
}
