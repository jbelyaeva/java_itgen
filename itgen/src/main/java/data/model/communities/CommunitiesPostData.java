package data.model.communities;

import com.google.gson.annotations.Expose;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("communities_posts")
public class CommunitiesPostData {

  @Expose
  @Id
  @Property("_id")
  private String id;

  @Property("text")
  private String text;

  @Expose
  @Property("communityId")
  private String communityId;

  @Expose
  @Property("fromCommunity")
  private Boolean fromCommunity;

  @Expose
  @Property("createAt")
  private Date createAt;

  @Property("likes")
  private List<String> likes = new ArrayList<>();

  @Property("likesCount")
  private int likesCount;

  @Property("attachments")
  private List<String> attachments = new ArrayList<>();

  @Property("creatorId")
  private String creatorId;

  @Property("updatedTime")
  private Double updatedTime;

  @Expose
  @Property("removedAt")
  private Date removedAt;

  public CommunitiesPostData() {
  }

  public CommunitiesPostData withId(String id) {
    this.id = id;
    return this;
  }

  public CommunitiesPostData withText(String text) {
    this.text = text;
    return this;
  }

  public CommunitiesPostData withCommunityId(String communityId) {
    this.communityId = communityId;
    return this;
  }

  public CommunitiesPostData withFromCommunity(Boolean fromCommunity) {
    this.fromCommunity = fromCommunity;
    return this;
  }

  public CommunitiesPostData withCreateAt(Date createAt) {
    this.createAt = createAt;
    return this;
  }

  public CommunitiesPostData withLikes(List<String> likes) {
    this.likes = likes;
    return this;
  }

  public CommunitiesPostData withLikesCount(int likesCount) {
    this.likesCount = likesCount;
    return this;
  }

  public CommunitiesPostData withAttachments(List<String> attachments) {
    this.attachments = attachments;
    return this;
  }

  public CommunitiesPostData withCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public CommunitiesPostData withUpdatedTime(Double updatedTime) {
    this.updatedTime = updatedTime;
    return this;
  }

  public CommunitiesPostData withRemovedAt(Date removedAt) {
    this.removedAt = removedAt;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public String getCommunityId() {
    return communityId;
  }

  public Boolean getFromCommunity() {
    return fromCommunity;
  }

  public Date getCreateAt() {
    return createAt;
  }

  public List<String> getLikes() {
    return likes;
  }

  public int getLikesCount() {
    return likesCount;
  }

  public List<String> getAttachments() {
    return attachments;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public Double getUpdatedTime() {
    return updatedTime;
  }

  public Date getRemovedAt() {
    return removedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommunitiesPostData that = (CommunitiesPostData) o;
    return likesCount == that.likesCount &&
        Objects.equals(id, that.id) &&
        Objects.equals(text, that.text) &&
        Objects.equals(communityId, that.communityId) &&
        Objects.equals(fromCommunity, that.fromCommunity) &&
        Objects.equals(likes, that.likes) &&
        Objects.equals(attachments, that.attachments) &&
        Objects.equals(creatorId, that.creatorId);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id, text, communityId, fromCommunity, likes, likesCount, attachments,
            creatorId);
  }

  @Override
  public String toString() {
    return "CommunitiesPostData{" +
        "id='" + id + '\'' +
        "text='" + text + '\'' +
        ", communityId='" + communityId + '\'' +
        ", fromCommunity=" + fromCommunity +
        ", createAt=" + createAt +
        ", likes=" + likes +
        ", likesCount=" + likesCount +
        ", attachments=" + attachments +
        ", creatorId='" + creatorId + '\'' +
        ", updatedTime=" + updatedTime +
        '}';
  }
}
