package ru.stqa.pft.itgen.model;

import com.google.gson.annotations.Expose;

public class ParentData {
  @Expose
  private String firstName;
  @Expose
  private String lastName;
  @Expose
  private String country;
  @Expose
  private String city;
  @Expose
  private String timeZone;
  @Expose
  private String locate;
  @Expose
  private String phone;
  @Expose
  private String skype;
  @Expose
  private String email;
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

  public ParentData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ParentData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ParentData withCountry(String country) {
    this.country = country;
    return this;
  }

  public ParentData withCity(String city) {
    this.city = city;
    return this;
  }

  public ParentData withTimeZone(String timeZone) {
    this.timeZone = timeZone;
    return this;
  }

  public ParentData withLocate(String locate) {
    this.locate = locate;
    return this;
  }

  public ParentData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public ParentData withSkype(String skype) {
    this.skype = skype;
    return this;
  }

  public ParentData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ParentData withC2d(String c2d) {
    this.c2d = c2d;
    return this;
  }

  public ParentData withViber(String viber) {
    this.viber = viber;
    return this;
  }

  public ParentData withWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
    return this;
  }

  public ParentData withTelegram(String telegram) {
    this.telegram = telegram;
    return this;
  }

  public ParentData withFb(String fb) {
    this.fb = fb;
    return this;
  }

  public ParentData withVk(String vk) {
    this.vk = vk;
    return this;
  }

  public ParentData withOk(String ok) {
    this.ok = ok;
    return this;
  }

  public ParentData withInst(String inst) {
    this.inst = inst;
    return this;
  }

  public ParentData withFamilyId(String familyId) {
    this.familyId = familyId;
    return this;
  }

  public ParentData withNote(String note) {
    this.note = note;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
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

  public String getPhone() {
    return phone;
  }

  public String getSkype() {
    return skype;
  }

  public String getEmail() {
    return email;
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
