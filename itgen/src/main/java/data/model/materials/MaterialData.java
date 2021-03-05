package data.model.materials;

import data.model.general.Activity;
import data.model.general.Comments;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("materials")
public class MaterialData {
  @Id
  @Property("_id")
  private String id;

  @Property("createAt")
  private Date createAt;

  @Property("creator")
  private String creator;

  @Property("title")
  private String title;

  @Property("status")
  private String status;

  @Property("skill")
  private String skill;

  @Property("branch")
  private String branch;

  @Property("type")
  private String type;

  @Property("lang")
  private String lang;

  @Property("materialLink")
  private String materialLink;

  @Property("tags")
  private List<String> tags = new ArrayList<>();

  @Embedded("comments")
  private List<Comments> comments = new ArrayList<>();

  @Embedded("activity")
  private List<Activity> activity = new ArrayList<>();

  @Embedded("linkedMaterials")
  private List<LinkedMaterials> linkedMaterials = new ArrayList<LinkedMaterials>();

  @Property("originality")
  private String originality;

  @Property("level")
  private String level;

  @Property("sourceLink")
  private String sourceLink;

  @Property("projectLink")
  private String projectLink;

  @Property("desc")
  private String desc;

  @Property("verifier")
  private String verifier;

  @Property("removed")
  private Boolean removed;

  public MaterialData() {}

  // setters

  public MaterialData withId(String id) {
    this.id = id;
    return this;
  }

  public MaterialData withCreateAt(Date createAt) {
    this.createAt = createAt;
    return this;
  }

  public MaterialData withCreator(String creator) {
    this.creator = creator;
    return this;
  }

  public MaterialData withTitle(String title) {
    this.title = title;
    return this;
  }

  public MaterialData withStatus(String status) {
    this.status = status;
    return this;
  }

  public MaterialData withSkill(String skill) {
    this.skill = skill;
    return this;
  }

  public MaterialData withBranch(String branch) {
    this.branch = branch;
    return this;
  }

  public MaterialData withType(String type) {
    this.type = type;
    return this;
  }

  public MaterialData withLang(String lang) {
    this.lang = lang;
    return this;
  }

  public MaterialData withMaterialLink(String materialLink) {
    this.materialLink = materialLink;
    return this;
  }

  public MaterialData withLinkedMaterials(List<LinkedMaterials> materialLink) {
    this.linkedMaterials = materialLink;
    return this;
  }

  public MaterialData withTags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public MaterialData withComments(List<Comments> comments) {
    this.comments = comments;
    return this;
  }

  public MaterialData withActivity(List<Activity> activity) {
    this.activity = activity;
    return this;
  }

  public MaterialData withOriginality(String originality) {
    this.originality = originality;
    return this;
  }

  public MaterialData withLevel(String level) {
    this.level = level;
    return this;
  }

  public MaterialData withSourceLink(String sourceLink) {
    this.sourceLink = sourceLink;
    return this;
  }

  public MaterialData withProjectLink(String projectLink) {
    this.projectLink = projectLink;
    return this;
  }

  public MaterialData withDesc(String desc) {
    this.desc = desc;
    return this;
  }

  public MaterialData withVerifier(String verifier) {
    this.verifier = verifier;
    return this;
  }

  public MaterialData withRemoved(Boolean removed) {
    this.removed = removed;
    return this;
  }
  // getters

  public String getId() {
    return id;
  }

  public Date getCreateAt() {
    return createAt;
  }

  public String getCreator() {
    return creator;
  }

  public String getTitle() {
    return title;
  }

  public String getStatus() {
    return status;
  }

  public String getSkill() {
    return skill;
  }

  public String getBranch() {
    return branch;
  }

  public String getType() {
    return type;
  }

  public String getLang() {
    return lang;
  }

  public String getMaterialLink() {
    return materialLink;
  }

  public List<String> getTags() {
    return tags;
  }

  public List<Comments> getComments() {
    return comments;
  }

  public List<Activity> getActivity() {
    return activity;
  }

  public String getOriginality() {
    return originality;
  }

  public String getLevel() {
    return level;
  }

  public String getSourceLink() {
    return sourceLink;
  }

  public String getProjectLink() {
    return projectLink;
  }

  public String getDesc() {
    return desc;
  }

  public String getVerifier() {
    return verifier;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MaterialData that = (MaterialData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(creator, that.creator)
        && Objects.equals(title, that.title)
        && Objects.equals(status, that.status)
        && Objects.equals(skill, that.skill)
        && Objects.equals(branch, that.branch)
        && Objects.equals(type, that.type)
        && Objects.equals(lang, that.lang)
        && Objects.equals(materialLink, that.materialLink)
        && Objects.equals(tags, that.tags)
        && Objects.equals(originality, that.originality)
        && Objects.equals(level, that.level)
        && Objects.equals(sourceLink, that.sourceLink)
        && Objects.equals(projectLink, that.projectLink)
        && Objects.equals(desc, that.desc)
        && Objects.equals(verifier, that.verifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        creator,
        title,
        status,
        skill,
        branch,
        type,
        lang,
        materialLink,
        tags,
        originality,
        level,
        sourceLink,
        projectLink,
        desc,
        verifier);
  }

  @Override
  public String toString() {
    return "MaterialData{" +
        "id='" + id + '\'' +
        ", createAt=" + createAt +
        ", creator='" + creator + '\'' +
        ", title='" + title + '\'' +
        ", status='" + status + '\'' +
        ", skill='" + skill + '\'' +
        ", branch='" + branch + '\'' +
        ", type='" + type + '\'' +
        ", lang='" + lang + '\'' +
        ", materialLink='" + materialLink + '\'' +
        ", tags=" + tags +
        ", comments=" + comments +
        ", activity=" + activity +
        ", linkedMaterials=" + linkedMaterials +
        ", originality='" + originality + '\'' +
        ", level='" + level + '\'' +
        ", sourceLink='" + sourceLink + '\'' +
        ", projectLink='" + projectLink + '\'' +
        ", desc='" + desc + '\'' +
        ", verifier='" + verifier + '\'' +
        ", removed=" + removed +
        '}';
  }
}
