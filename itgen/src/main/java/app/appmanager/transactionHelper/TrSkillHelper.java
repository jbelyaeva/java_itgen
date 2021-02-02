package app.appmanager.transactionHelper;

import data.model.skills.Advanced;
import data.model.skills.AmountOfProjects;
import data.model.skills.Beginner;
import data.model.skills.Expert;
import data.model.skills.Requirements;
import data.model.skills.SkillsData;
import data.model.skills.Usual;
import data.services.SkillsService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrSkillHelper {

  SkillsService skillsService = new SkillsService();

  public TrSkillHelper() {
  }

  public void updateSkill(
      String idSkill,
      String lang,
      String title,
      String visibility,
      String desc,
      int minAge,
      int[] age,
      String[] state,
      String skillId,
      String link,
      Boolean needRemind,
      int payCoefficient,
      int duration
  ) {
    SkillsData skill = new SkillsData()
        .withId(idSkill)
        .withLang(lang)
        .withTitle(title)
        .withVisibility(visibility)
        .withDesc(desc)
        .withMinAge(minAge)
        .withRequirements(new Requirements()
            .withtBeginner(getBeginner(age, state))
            .withAdvanced(getAdvanced(age, state))
            .withUsual(getUsual(age, state))
            .withExpert(getExpert(age, state)))
        .withSkillId(skillId)
        .withDownloadLink(link)
        .withNeedRemindAboutLesson(needRemind)
        .withPayCoefficient(payCoefficient)
        .withtTrialDuration(duration);
    skillsService.save(skill);
  }

  public void updateSkillWithFormatLessons(
      String idSkill,
      String lang,
      String title,
      String visibility,
      String desc,
      int minAge,
      int[] age,
      String[] state,
      String skillId,
      String link,
      Boolean needRemind,
      int payCoefficient,
      int duration,
      Integer[] lessonFormat
  ) {
    SkillsData skill = new SkillsData()
        .withId(idSkill)
        .withLang(lang)
        .withTitle(title)
        .withVisibility(visibility)
        .withDesc(desc)
        .withMinAge(minAge)
        .withRequirements(new Requirements()
            .withtBeginner(getBeginner(age, state))
            .withAdvanced(getAdvanced(age, state))
            .withUsual(getUsual(age, state))
            .withExpert(getExpert(age, state)))
        .withSkillId(skillId)
        .withDownloadLink(link)
        .withNeedRemindAboutLesson(needRemind)
        .withPayCoefficient(payCoefficient)
        .withtTrialDuration(duration)
        .withLessonsFormats(Arrays.asList(lessonFormat))
        .withAmountOfProjects(new AmountOfProjects().withP25(90).withP50(50).withP75(15));
    skillsService.save(skill);
  }

  public void updateSkillWithTest(
      String idSkill,
      String lang,
      String title,
      String visibility,
      String desc,
      int minAge,
      int[] age,
      String[] state,
      String skillId,
      String link,
      Boolean needRemind,
      int payCoefficient,
      int duration,
      String testId
  ) {
    SkillsData skill = new SkillsData()
        .withId(idSkill)
        .withLang(lang)
        .withTitle(title)
        .withVisibility(visibility)
        .withDesc(desc)
        .withMinAge(minAge)
        .withRequirements(new Requirements()
            .withtBeginner(getBeginner(age, state))
            .withAdvanced(getAdvanced(age, state))
            .withUsual(getUsual(age, state))
            .withExpert(getExpert(age, state)))
        .withMinAgeWithoutTest(10)
        .withSkillId(skillId)
        .withDownloadLink(link)
        .withNeedRemindAboutLesson(needRemind)
        .withPayCoefficient(payCoefficient)
        .withtTrialDuration(duration)
        .withTestId(testId);
    skillsService.save(skill);
  }

  private List<Beginner> getBeginner(int[] age, String[] state) {
    List<Beginner> mas = new ArrayList<Beginner>();
    for (int i = 0; i < age.length; i++) {
      mas.add(new Beginner()
          .withAge(age[i])
          .withState(state[i]));
    }
    return mas;
  }

  private List<Advanced> getAdvanced(int[] age, String[] state) {
    List<Advanced> mas = new ArrayList<Advanced>();
    for (int i = 0; i < age.length; i++) {
      mas.add(new Advanced()
          .withAge(age[i])
          .withState(state[i]));
    }
    return mas;
  }

  private List<Usual> getUsual(int[] age, String[] state) {
    List<Usual> mas = new ArrayList<Usual>();
    for (int i = 0; i < age.length; i++) {
      mas.add(new Usual()
          .withAge(age[i])
          .withState(state[i]));
    }
    return mas;
  }

  private List<Expert> getExpert(int[] age, String[] state) {
    List<Expert> mas = new ArrayList<Expert>();
    for (int i = 0; i < age.length; i++) {
      mas.add(new Expert()
          .withAge(age[i])
          .withState(state[i]));
    }
    return mas;
  }
}
