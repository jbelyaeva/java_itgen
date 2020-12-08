package data.model.communities;

import com.google.gson.annotations.Expose;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import dev.morphia.annotations.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("communities")
public class CommunityData {
  @Expose
  @Id
  @Property("_id")
  private String id;

  @Expose
  @Property("createdAt")
  private Date createAt;

  @Expose
  @Property("creatorId")
  private String creatorId;

  @Expose
  @Property("description")
  private String description;

  @Embedded
  private List<Managers> managers = new ArrayList<>();

  @Embedded
  private List<Subscribers> subscribers = new ArrayList<>();

  @Property("tagIds")
  private List<String> tagIds = new ArrayList<>();

  @Expose
  @Property("title")
  private String title;

  @Expose
  @Property("subscribersCount")
  private int subscribersCount;

  @Embedded private AvatarUrls avatarUrls;

  @Property("lang")
  private String lang;

  @Property("skills")
  private List<String> skills = new ArrayList<>();

  @Transient
  private List<String> tagUI = new ArrayList<>();

  public CommunityData() {}

  public CommunityData withId(String id) {
    this.id = id;
    return this;
  }

  public CommunityData withCreateAt(Date createAt) {
    this.createAt = createAt;
    return this;
  }

  public CommunityData withCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public CommunityData withDescription(String description) {
    this.description = description;
    return this;
  }

  public CommunityData withManagers(List<Managers> managers) {
    this.managers = managers;
    return this;
  }

  public CommunityData withSubscribers(List<Subscribers> subscribers) {
    this.subscribers = subscribers;
    return this;
  }

  public CommunityData withTagIds(List<String> tagIds) {
    this.tagIds = tagIds;
    return this;
  }

  public CommunityData withTitle(String title) {
    this.title = title;
    return this;
  }

  public CommunityData withSubscribersCount(int subscribersCount) {
    this.subscribersCount = subscribersCount;
    return this;
  }

  public CommunityData withAvatarUrls(AvatarUrls avatarUrls) {
    this.avatarUrls = avatarUrls;
    return this;
  }

  public CommunityData withTagUI(List<String> tagUI) {
    this.tagUI = tagUI;
    return this;
  }

  public CommunityData withLang(String lang) {
    this.lang = lang;
    return this;
  }

  public CommunityData withSkills(List<String> skills) {
    this.skills = skills;
    return this;
  }

  public String getId() {
    return id;
  }

  public Date getCreateAt() {
    return createAt;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public String getDescription() {
    return description;
  }

  public List<Managers> getManagers() {
    return managers;
  }

  public List<Subscribers> getSubscribers() {
    return subscribers;
  }

  public List<String> getTagIds() {
    return tagIds;
  }

  public String getTitle() {
    return title;
  }

  public int getSubscribersCount() {
    return subscribersCount;
  }

  public AvatarUrls getAvatarUrls() {
    return avatarUrls;
  }

  public List<String> getTagUI() {
    return tagUI;
  }

  public String getLang() {
    return lang;
  }

  public List<String> getSkills() {
    return skills;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommunityData that = (CommunityData) o;
    return subscribersCount == that.subscribersCount &&
        Objects.equals(id, that.id) &&
        Objects.equals(creatorId, that.creatorId) &&
        Objects.equals(description, that.description) &&
        Objects.equals(managers, that.managers) &&
        Objects.equals(title, that.title) &&
        Objects.equals(avatarUrls, that.avatarUrls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, creatorId, description, managers, title,
        subscribersCount, avatarUrls);
  }

  @Override
  public String toString() {
    return "CommunityData{" +
        "id='" + id + '\'' +
        ", createAt=" + createAt +
        ", creatorId='" + creatorId + '\'' +
        ", description='" + description + '\'' +
        ", managers=" + managers +
        ", subscribers=" + subscribers +
        ", tagIds=" + tagIds +
        ", title='" + title + '\'' +
        ", subscribersCount=" + subscribersCount +
        ", avatarUrls=" + avatarUrls +
        ", lang='" + lang + '\'' +
        ", skills=" + skills +
        ", tagUI=" + tagUI +
        '}';
  }
}
