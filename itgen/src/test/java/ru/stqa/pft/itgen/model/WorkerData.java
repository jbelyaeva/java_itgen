package ru.stqa.pft.itgen.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class WorkerData {
  @Id
  @Column(name = "_id")
  private String id;

  @Expose
  @Column(name = "firstName")
  private String firstName;

  @Expose
  @Column(name = "lastName")
  private String lastName;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "langs")
  private List<WorkerData.Langs> langs = new ArrayList<WorkerData.Langs>();

  @Embeddable
  public static class Langs {
    private String langs;

    public WorkerData.Langs withLangs(String type) {
      this.langs = type;
      return this;
    }

    public String getLangs() {
      return langs;
    }

    @Override
    public String toString() {
      return "" +
              langs;
    }
  }


  @Expose
  private String emailUI;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "emails")
  private List<WorkerData.Emails> emails = new ArrayList<WorkerData.Emails>();

  @Embeddable
  public static class Emails {
    private String address;
    private Boolean verified;

    public WorkerData.Emails withAddress(String address) {
      this.address = address;
      return this;
    }

    public WorkerData.Emails withVerified(Boolean verified) {
      this.verified = verified;
      return this;
    }

    public String getAddress() {
      return address;
    }

    public Boolean getVerified() {
      return verified;
    }

    @Override
    public String toString() {
      return "Emails{" +
              "address='" + address + '\'' +
              ", verified='" + verified + '\'' +
              '}';
    }
  }


  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "roles")
  private List<WorkerData.Roles> roles = new ArrayList<WorkerData.Roles>();

  @Embeddable
  public static class Roles {
    private String roles;

    public WorkerData.Roles withRoles(String type) {
      this.roles = type;
      return this;
    }

    public String getRoles() {
      return roles;
    }

    @Override
    public String toString() {
      return "" +
              roles;
    }
  }


  @Expose
  private String roleUi;

  @Expose
  @Column(name = "startWorkAt")
  @Temporal(TemporalType.DATE)
  private Date startDay;

  @Expose
  private String startDayUi;

  @Expose
  @Transient
  @Column(name = "birthday")
  @Temporal(TemporalType.DATE)
  private Date birthDay;

  @Expose
  private String birthDayUi;

  @Expose
  @Transient
  private String gender;

  @Expose
  private String country;

  @Expose
  private String city;

  @Expose
  private String timeZone;

  @Expose
  private String locate;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "contacts")
  private List<WorkerData.Contacts> contacts = new ArrayList<WorkerData.Contacts>();

  @Embeddable
  public static class Contacts {
    private String type;
    private String val;

    public WorkerData.Contacts withType(String type) {
      this.type = type;
      return this;
    }

    public WorkerData.Contacts withVal(String val) {
      this.val = val;
      return this;
    }

    public String getType() {
      return type;
    }

    public String getVal() {
      return val;
    }

    @Override
    public String toString() {
      return "Contacts{" +
              "type='" + type + '\'' +
              ", val='" + val + '\'' +
              '}';
    }
  }

  @Expose
  private String phone;

  @Expose
  private String skype;

  @Expose
  private String viber;

  @Expose
  private String whatsapp;

  @Expose
  private String tg;

  @Expose
  private String fb;

  @Expose
  private String vk;

  @Expose
  private String ok;

  @Expose
  private String inst;

  /* setters */

  public WorkerData withId(String id) {
    this.id = id;
    return this;
  }

  public WorkerData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public WorkerData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public WorkerData withLangs(List<WorkerData.Langs> langs) {
    this.langs = langs;
    return this;
  }

  public WorkerData withEmailUI(String emailUI) {
    this.emailUI = emailUI;
    return this;
  }

  public WorkerData withEmails(List<WorkerData.Emails> emails) {
    this.emails = emails;
    return this;
  }

  public WorkerData withRoles(List<WorkerData.Roles> roles) {
    this.roles = roles;
    return this;
  }

  public WorkerData withRoleUi(String roleUi) {
    this.roleUi = roleUi;
    return this;
  }

  public WorkerData withStartWork(Date startDay) {
    this.startDay = startDay;
    return this;
  }

  public WorkerData withStartWorkUi(String startDayUi) {
    this.startDayUi = startDayUi;
    return this;
  }

  public WorkerData withBirthday(Date birthDay) {
    this.birthDay = birthDay;
    return this;
  }

  public WorkerData withBirthdayUi(String birthDayUi) {
    this.birthDayUi = birthDayUi;
    return this;
  }

  public WorkerData withGender(String gender) {
    this.gender = gender;
    return this;
  }

  public WorkerData withCountry(String country) {
    this.country = country;
    return this;
  }

  public WorkerData withCity(String city) {
    this.city = city;
    return this;
  }

  public WorkerData withTimeZone(String timeZone) {
    this.timeZone = timeZone;
    return this;
  }

  public WorkerData withLocate(String locate) {
    this.locate = locate;
    return this;
  }

  public WorkerData withContacts(List<WorkerData.Contacts> contacts) {
    this.contacts = contacts;
    return this;
  }

  public WorkerData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public WorkerData withSkype(String skype) {
    this.skype = skype;
    return this;
  }

  public WorkerData withViber(String viber) {
    this.viber = viber;
    return this;
  }

  public WorkerData withWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
    return this;
  }

  public WorkerData withTg(String tg) {
    this.tg = tg;
    return this;
  }

  public WorkerData withFb(String fb) {
    this.fb = fb;
    return this;
  }

  public WorkerData withVk(String vk) {
    this.vk = vk;
    return this;
  }

  public WorkerData withOk(String ok) {
    this.ok = ok;
    return this;
  }

  public WorkerData withInst(String inst) {
    this.inst = inst;
    return this;
  }

  /* getters */

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public List<WorkerData.Langs> getLangs() {
    return langs;
  }

  public String getEmailUI() {
    return emailUI;
  }

  public List<WorkerData.Emails> getEmails() {
    return emails;
  }

  public List<WorkerData.Roles> getRoles() {
    return roles;
  }

  public String getRoleUi() {
    return roleUi;
  }

  public Date getStartDay() {
    return startDay;
  }

  public String getStartDayUi() {
    return startDayUi;
  }

  public Date getBirthDay() {
    return birthDay;
  }

  public String getBirthDayUi() {
    return birthDayUi;
  }

  public String getGender() {
    return gender;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public String getLocate() {
    return locate;
  }

  public List<WorkerData.Contacts> getContacts() {
    return contacts;
  }

  public String getPhone() {
    return phone;
  }

  public String getSkype() {
    return skype;
  }

  public String getViber() {
    return viber;
  }

  public String getWhatsapp() {
    return whatsapp;
  }

  public String getTg() {
    return tg;
  }

  public String getFb() {
    return fb;
  }

  public String getVk() {
    return vk;
  }

  public String getOk() {
    return ok;
  }

  public String getInst() {
    return inst;
  }

  /* toString(), hashCode() & equals() */

  @Override
  public String toString() {
    return "WorkerData{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WorkerData that = (WorkerData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName);
  }

}
