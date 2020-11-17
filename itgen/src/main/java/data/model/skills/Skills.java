package data.model.skills;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Skills extends ForwardingSet<SkillsData> {
  private final Set<SkillsData> delegate;

  public Skills(Skills skills) {
    this.delegate = new HashSet<SkillsData>(skills.delegate);
  }

  public Skills() { // конструктор без параметров
    this.delegate = new HashSet<SkillsData>();
  }

  public Skills(Collection<SkillsData> skills) {
    this.delegate = new HashSet<SkillsData>(skills);
  }

  public Skills withAdded(SkillsData skill) {
    Skills skills = new Skills(this);
    skills.add(skill);
    return skills;
  }

  public Skills without(SkillsData skill) {
    Skills skills = new Skills(this);
    skills.remove(skill);
    return skills;
  }

  @Override
  protected Set<SkillsData> delegate() {
    return delegate;
  }
}
