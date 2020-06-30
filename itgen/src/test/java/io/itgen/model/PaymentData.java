package io.itgen.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;

import java.util.Date;
import java.util.Objects;

@Entity("payments")
public class PaymentData {
  @Id
  @Property("_id")
  private String id;

  @Property("createAt")
  private Date createAt;

  @Property("fId")
  private String fId;

  @Property("creator")
  private String creator;

  @Property("val")
  private int val ;

  @Property("t")
  private int t ;

  @Property("desc")
  private String desc;

  @Property("approved")
  private Boolean approved;

  public PaymentData() {
  }

  //setters

  public PaymentData withId(String id) {
    this.id = id;
    return this;
  }

  public PaymentData withCreateAt(Date createAt) {
    this.createAt = createAt;
    return this;
  }

  public PaymentData withfId(String fId) {
    this.fId = fId;
    return this;
  }

  public PaymentData withCreator(String creator) {
    this.creator = creator;
    return this;
  }

  public PaymentData withVal(int val) {
    this.val = val;
    return this;
  }

  public PaymentData withT(int t) {
    this.t = t;
    return this;
  }

  public PaymentData withDesc(String desc) {
    this.desc = desc;
    return this;
  }

  public PaymentData withApproved(Boolean approved) {
    this.approved = approved;
    return this;
  }
  //getters

  public String getId() {
    return id;
  }

  public Date getCreateAt() {
    return createAt;
  }

  public String getfId() {
    return fId;
  }

  public String getCreator() {
    return creator;
  }

  public int getVal() {
    return val;
  }

  public int getT() {
    return t;
  }

  public String getDesc() {
    return desc;
  }

  public Boolean getApproved() {
    return approved;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PaymentData payments = (PaymentData) o;
    return val == payments.val &&
            t == payments.t &&
            Objects.equals(id, payments.id) &&
            Objects.equals(fId, payments.fId) &&
            Objects.equals(creator, payments.creator) &&
            Objects.equals(desc, payments.desc) &&
            Objects.equals(approved, payments.approved);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fId, creator, val, t, desc, approved);
  }

  @Override
  public String toString() {
    return "Payments{" +
            "id='" + id + '\'' +
            ", fId='" + fId + '\'' +
            ", creator='" + creator + '\'' +
            ", val=" + val +
            ", t=" + t +
            ", desc='" + desc + '\'' +
            ", approved=" + approved +
            '}';
  }
}