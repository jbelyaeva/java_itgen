package ru.stqa.pft.itgen.model;


import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "leads")
public class LeadData {

  @Id
  @Column(name = "_id")
  private String id;

  @Expose
  @Column(name = "firstName")
  private String firstname;

  @Expose
  @Column(name = "lastName")
  private String lastname;

  @Expose
  @Column(name = "country")
  private String country;

  @Expose
  @Column(name = "city")
  private String city;

  @Expose
  @Column(name = "tz")
  private String timezone;

  @Expose
  @Column(name = "locale")
  private String locate;


  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "contacts")
  private List<Contacts> contacts = new ArrayList<Contacts>();

  @Embeddable
  public static class Contacts {
    private String type;
    private String val;

    public Contacts withType(String type) {
      this.type = type;
      return this;
    }

    public Contacts withVal(String val) {
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
  @Transient
  private String phone;

  @Expose
  @Transient
  private String skype;

  @Expose
  @Transient
  private String c2d;

  @Expose
  @Transient
  private String viber;

  @Expose
  @Transient
  private String whatsapp;

  @Expose
  @Transient
  private String telegram;

  @Expose
  @Transient
  private String fb;

  @Expose
  @Transient
  private String vk;

  @Expose
  @Transient
  private String ok;

  @Expose
  @Transient
  private String inst;

  @Expose
  private String roleUi;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "roles")
  private List<Roles> roles = new ArrayList<Roles>();

  @Embeddable
  public static class Roles {
    private String roles;

    public Roles withRoles(String type) {
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

  @Column(name = "status")
  private String status;

  public LeadData withId(String id) {
    this.id = id;
    return this;
  }

  public LeadData withFirstName(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public LeadData withLastName(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public LeadData withCountry(String country) {
    this.country = country;
    return this;
  }

  public LeadData withCity(String city) {
    this.city = city;
    return this;
  }

  public LeadData withTimeZone(String timezone) {
    this.timezone = timezone;
    return this;
  }

  public LeadData withLocate(String locate) {
    this.locate = locate;
    return this;
  }

  public LeadData withContacts(List<Contacts> contacts) {
    this.contacts = contacts;
    return this;
  }

  public LeadData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public LeadData withSkype(String skype) {
    this.skype = skype;
    return this;
  }

  public LeadData withC2d(String c2d) {
    this.c2d = c2d;
    return this;
  }

  public LeadData withViber(String viber) {
    this.viber = viber;
    return this;
  }

  public LeadData withWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
    return this;
  }

  public LeadData withTelegram(String telegram) {
    this.telegram = telegram;
    return this;
  }

  public LeadData withFb(String fb) {
    this.fb = fb;
    return this;
  }

  public LeadData withVk(String vk) {
    this.vk = vk;
    return this;
  }

  public LeadData withOk(String ok) {
    this.ok = ok;
    return this;
  }

  public LeadData withInst(String inst) {
    this.inst = inst;
    return this;
  }

  public LeadData withRoleUi(String roleUi) {
    this.roleUi = roleUi;
    return this;
  }

  public LeadData withRoles(List<Roles> roles) {
    this.roles = roles;
    return this;
  }


  public LeadData withStatus(String status) {
    this.status = status;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getStatus() {
    return status;
  }

  public List<Roles> getRoles() {
    return roles;
  }

  public String getInst() {
    return inst;
  }

  public String getOk() {
    return ok;
  }

  public String getVk() {
    return vk;
  }

  public String getFb() {
    return fb;
  }

  public String getTelegram() {
    return telegram;
  }

  public String getWhatsapp() {
    return whatsapp;
  }

  public String getViber() {
    return viber;
  }

  public String getC2d() {
    return c2d;
  }

  public String getSkype() {
    return skype;
  }

  public String getPhone() {
    return phone;
  }

  public List<Contacts> getContacts() {
    return contacts;
  }

  public String getLocate() {
    return locate;
  }

  public String getTimezone() {
    return timezone;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getLastname() {
    return lastname;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getRoleUi() {
    return roleUi;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LeadData leadData = (LeadData) o;
    return Objects.equals(id, leadData.id) &&
            Objects.equals(firstname, leadData.firstname) &&
            Objects.equals(lastname, leadData.lastname) &&
            Objects.equals(country, leadData.country) &&
            Objects.equals(timezone, leadData.timezone) &&
            Objects.equals(locate, leadData.locate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, country, timezone, locate);
  }
}
