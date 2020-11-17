package data.model.communities;

import com.google.gson.annotations.Expose;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("communities_post_comments")
public class CommunitiesPostCommentData {

  @Expose
  @Id
  @Property("_id")
  private String id;

  @Property("text")
  private String text;

  @Property("postId")
  private String postId;

  @Expose
  @Property("communityId")
  private String communityId;

  @Expose
  @Property("fromCommunity")
  private Boolean fromCommunity;

  @Expose
  @Property("createAt")
  private Date createAt;

  @Expose
  @Property("updatedAt")
  private Date updatedAt;

  @Property("likes")
  private List<String> likes = new ArrayList<>();

  @Property("likesCount")
  private int likesCount;

  @Property("attachments")
  private List<String> attachments = new ArrayList<>();

  @Property("creatorId")
  private String creatorId;

  public CommunitiesPostCommentData() {
  }

  public CommunitiesPostCommentData withId(String id) {
    this.id = id;
    return this;
  }

  public CommunitiesPostCommentData withText(String text) {
    this.text = text;
    return this;
  }

  public CommunitiesPostCommentData withPostId(String postId) {
    this.postId = postId;
    return this;
  }

  public CommunitiesPostCommentData withCommunityId(String communityId) {
    this.communityId = communityId;
    return this;
  }

  public CommunitiesPostCommentData withFromCommunity(Boolean fromCommunity) {
    this.fromCommunity = fromCommunity;
    return this;
  }

  public CommunitiesPostCommentData withCreateAt(Date createAt) {
    this.createAt = createAt;
    return this;
  }

  public CommunitiesPostCommentData withUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  public CommunitiesPostCommentData withLikes(List<String> likes) {
    this.likes = likes;
    return this;
  }

  public CommunitiesPostCommentData withLikesCount(int likesCount) {
    this.likesCount = likesCount;
    return this;
  }

  public CommunitiesPostCommentData withAttachments(List<String> attachments) {
    this.attachments = attachments;
    return this;
  }

  public CommunitiesPostCommentData withCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public String getPostId() {
    return postId;
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

  public Date getUpdatedAt() {
    return updatedAt;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommunitiesPostCommentData that = (CommunitiesPostCommentData) o;
    return likesCount == that.likesCount &&
        Objects.equals(id, that.id) &&
        Objects.equals(text, that.text) &&
        Objects.equals(postId, that.postId) &&
        Objects.equals(communityId, that.communityId) &&
        Objects.equals(fromCommunity, that.fromCommunity) &&
        Objects.equals(createAt, that.createAt) &&
        Objects.equals(likes, that.likes) &&
        Objects.equals(attachments, that.attachments) &&
        Objects.equals(creatorId, that.creatorId);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id, text, postId, communityId, fromCommunity, createAt, likes, likesCount,
            attachments,
            creatorId);
  }

  @Override
  public String toString() {
    return "CommunitiesPostCommentData{" +
        "id='" + id + '\'' +
        ", text='" + text + '\'' +
        ", postId='" + postId + '\'' +
        ", communityId='" + communityId + '\'' +
        ", fromCommunity=" + fromCommunity +
        ", createAt=" + createAt +
        ", updatedAt=" + updatedAt +
        ", likes=" + likes +
        ", likesCount=" + likesCount +
        ", attachments=" + attachments +
        ", creatorId='" + creatorId + '\'' +
        '}';
  }
}
