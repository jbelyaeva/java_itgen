package data.model.achievemets;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AchievementsUsers extends ForwardingSet<AchievementsUsersData> {

  private final Set<AchievementsUsersData> delegate;

  public AchievementsUsers(AchievementsUsers achievements) {
    this.delegate = new HashSet<AchievementsUsersData>(achievements.delegate);
  }

  public AchievementsUsers() { // конструктор без параметров
    this.delegate = new HashSet<AchievementsUsersData>();
  }

  public AchievementsUsers(Collection<AchievementsUsersData> achievements) {
    this.delegate = new HashSet<AchievementsUsersData>(achievements);
  }

  public AchievementsUsers withAdded(
      AchievementsUsersData achievement) { // объект в который добавлена группа
    AchievementsUsers achievements = new AchievementsUsers(this);
    achievements.add(achievement);
    return achievements;
  }

  public AchievementsUsers without(
      AchievementsUsersData achievement) { // объекта, из которго удалена группа
    AchievementsUsers achievements = new AchievementsUsers(this);
    achievements.remove(achievement);
    return achievements;
  }

  @Override
  protected Set<AchievementsUsersData> delegate() {
    return delegate;
  }
}
