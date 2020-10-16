package io.itgen.services;

import io.itgen.dao.SkillsDao;
import io.itgen.dao.TestDao;
import io.itgen.model.skills.SkillsData;
import io.itgen.model.typeform.TestData;

public class SkillsService {

  private final SkillsDao skillsDao = new SkillsDao();

  public SkillsService() {
  }

  public void save(SkillsData skill) {
    skillsDao.save(skill);
  }

  public void drop() {
    skillsDao.drop();
  }

  public void updateField(String idSkill,String nameFiled, String data) {
    skillsDao.updateField(idSkill, nameFiled, data);
  }

  public void deleteField(String idSkills, String nameField) {
    skillsDao.deleteField(idSkills, nameField);
  }

}
