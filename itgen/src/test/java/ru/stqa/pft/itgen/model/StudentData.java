package ru.stqa.pft.itgen.model;

import com.google.gson.annotations.Expose;

public class StudentData {
  @Expose
  private String firstname;
  @Expose
  private String lastname;
  @Expose
  private String gender;
  @Expose
  private String birthday;
  @Expose
  private String pclevel;
  @Expose
  private String country;
  @Expose
  private String city;
  @Expose
  private String timezone;
  @Expose
  private String locate;
  @Expose
  private String studyLang;
  @Expose
  private String duration;
  @Expose
  private String phone;
  @Expose
  private String skype;
  @Expose
  private String c2d;
  @Expose
  private String viber;
  @Expose
  private String whatsapp;
  @Expose
  private String telegram;
  @Expose
  private String fb;
  @Expose
  private String vk;
  @Expose
  private String ok;
  @Expose
  private String inst;
  @Expose
  private String familyId;
  @Expose
  private String note;



  public StudentData withFirstName(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public StudentData withLastName(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public StudentData withGender(String gender) {
    this.gender = gender;
    return this;
  }

  public StudentData withBirthday(String birthday) {
    this.birthday = birthday;
    return this;
  }

  public StudentData withPclevel(String pclevel) {
    this.pclevel = pclevel;
    return this;
  }

  public StudentData withCountry(String country) {
    this.country = country;
    return this;
  }

  public StudentData withCity(String city) {
    this.city = city;
    return this;
  }

  public StudentData withTimeZone(String timezone) {
    this.timezone = timezone;
    return this;
  }

  public StudentData withLocate(String locate) {
    this.locate = locate;
    return this;
  }

  public StudentData withStudyLang(String studyLang) {
    this.studyLang = studyLang;
    return this;
  }

  public StudentData withDuration(String duration) {
    this.duration = duration;
    return this;
  }

  public StudentData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public StudentData withSkype(String skype) {
    this.skype = skype;
    return this;
  }

  public StudentData withC2d(String c2d) {
    this.c2d = c2d;
    return this;
  }

  public StudentData withViber(String viber) {
    this.viber = viber;
    return this;
  }

  public StudentData withWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
    return this;
  }

  public StudentData withTelegram(String telegram) {
    this.telegram = telegram;
    return this;
  }

  public StudentData withFb(String fb) {
    this.fb = fb;
    return this;
  }

  public StudentData withVk(String vk) {
    this.vk = vk;
    return this;
  }

  public StudentData withOk(String ok) {
    this.ok = ok;
    return this;
  }

  public StudentData withInst(String inst) {
    this.inst = inst;
    return this;
  }

  public StudentData withFamilyId(String familyId) {
    this.familyId = familyId;
    return this;
  }

  public StudentData withNote(String note) {
    this.note = note;
    return this;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getGender() {
    return gender;
  }

  public String getBirthday() {
    return birthday;
  }

  public String getPclevel() {
    return pclevel;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getTimezone() {
    return timezone;
  }

  public String getLocate() {
    return locate;
  }

  public String getStudyLang() {
    return studyLang;
  }

  public String getDuration() {
    return duration;
  }

  public String getPhone() {
    return phone;
  }

  public String getSkype() {
    return skype;
  }

  public String getC2d() {
    return c2d;
  }

  public String getViber() {
    return viber;
  }

  public String getWhatsapp() {
    return whatsapp;
  }

  public String getTelegram() {
    return telegram;
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

  public String getFamilyId() {
    return familyId;
  }

  public String getNote() {
    return note;
  }
}
