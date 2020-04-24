package ru.stqa.pft.itgen.model;

import com.google.gson.annotations.Expose;

public class FamilyDataUI {
  private String id;
  private String familyId;

  // атрибуты ученика
  @Expose
  private String firstnameStudent;
  @Expose
  private String lastnameStudent;
  @Expose
  private Integer genderStudent;
  @Expose
  private String birthdayUiStudent;
  @Expose
  private String pclevelStudent;
  @Expose
  private String countryStudent;
  @Expose
  private String cityStudent;
  @Expose
  private String timezoneStudent;
  @Expose
  private String phoneStudent;
  @Expose
  private String telegramStudent;
  @Expose
  private String viberStudent;
  @Expose
  private String c2dStudent;
  @Expose
  private String skypeStudent;
  @Expose
  private String whatsappStudent;
  @Expose
  private String fbStudent;
  @Expose
  private String vkStudent;
  @Expose
  private String okStudent;
  @Expose
  private String instStudent;

  // атрибуты родителя
  @Expose
  private String firstnameParent;
  @Expose
  private String lastnameParent;
  @Expose
  private String countryParent;
  @Expose
  private String cityParent;
  @Expose
  private String timezoneParent;
  @Expose
  private String phoneParent;
  @Expose
  private String emailParent;
  @Expose
  private String telegramParent;
  @Expose
  private String viberParent;
  @Expose
  private String c2dParent;
  @Expose
  private String skypeParent;
  @Expose
  private String whatsappParent;
  @Expose
  private String fbParent;
  @Expose
  private String vkParent;
  @Expose
  private String okParent;
  @Expose
  private String instParent;

  /* setters */

  public FamilyDataUI withFamilyId(String familyId) {
    this.familyId = familyId;
    return this;
  }

  public FamilyDataUI withId(String id) {
    this.id = id;
    return this;
  }

  public FamilyDataUI withFirstnameStudent(String firstnameStudent) {
    this.firstnameStudent = firstnameStudent;
    return this;
  }

  public FamilyDataUI withLastnameStudent(String lastnameStudent) {
    this.lastnameStudent = lastnameStudent;
    return this;
  }

  public FamilyDataUI withGenderStudent(Integer genderStudent) {
    this.genderStudent = genderStudent;
    return this;
  }

  public FamilyDataUI withBirthdayUiStudent(String birthdayUiStudent) {
    this.birthdayUiStudent = birthdayUiStudent;
    return this;
  }

  public FamilyDataUI withPclevelStudent(String pclevelStudent) {
    this.pclevelStudent = pclevelStudent;
    return this;
  }

  public FamilyDataUI withCountryStudent(String countryStudent) {
    this.countryStudent = countryStudent;
    return this;
  }

  public FamilyDataUI withCityStudent(String cityStudent) {
    this.cityStudent = cityStudent;
    return this;
  }

  public FamilyDataUI withTimezoneStudent(String timezoneStudent) {
    this.timezoneStudent = timezoneStudent;
    return this;
  }

  public FamilyDataUI withPhoneStudent(String phoneStudent) {
    this.phoneStudent = phoneStudent;
    return this;
  }

  public FamilyDataUI withTelegramStudent(String telegramStudent) {
    this.telegramStudent = telegramStudent;
    return this;
  }

  public FamilyDataUI withViberStudent(String viberStudent) {
    this.viberStudent = viberStudent;
    return this;
  }

  public FamilyDataUI withC2dStudent(String c2dStudent) {
    this.c2dStudent = c2dStudent;
    return this;
  }

  public FamilyDataUI withSkypeStudent(String skypeStudent) {
    this.skypeStudent = skypeStudent;
    return this;
  }

  public FamilyDataUI withWhatsappStudent(String whatsappStudent) {
    this.whatsappStudent = whatsappStudent;
    return this;
  }

  public FamilyDataUI withFbStudent(String fbStudent) {
    this.fbStudent = fbStudent;
    return this;
  }

  public FamilyDataUI withVkStudent(String vkStudent) {
    this.vkStudent = vkStudent;
    return this;
  }

  public FamilyDataUI withOkStudent(String okStudent) {
    this.okStudent = okStudent;
    return this;
  }

  public FamilyDataUI withInstStudent(String instStudent) {
    this.instStudent = instStudent;
    return this;
  }

  public FamilyDataUI withFirstnameParent(String firstnameParent) {
    this.firstnameParent = firstnameParent;
    return this;
  }

  public FamilyDataUI withLastnameParent(String lastnameParent) {
    this.lastnameParent = lastnameParent;
    return this;
  }

  public FamilyDataUI withCountryParent(String countryParent) {
    this.countryParent = countryParent;
    return this;
  }

  public FamilyDataUI withCityParent(String cityParent) {
    this.cityParent = cityParent;
    return this;
  }

  public FamilyDataUI withTimezoneParent(String timezoneParent) {
    this.timezoneParent = timezoneParent;
    return this;
  }

  public FamilyDataUI withPhoneParent(String phoneParent) {
    this.phoneParent = phoneParent;
    return this;
  }

  public FamilyDataUI withEmailParent(String emailParent) {
    this.emailParent = emailParent;
    return this;
  }

  public FamilyDataUI withTelegramParent(String telegramParent) {
    this.telegramParent = telegramParent;
    return this;
  }

  public FamilyDataUI withViberParent(String viberParent) {
    this.viberParent = viberParent;
    return this;
  }

  public FamilyDataUI withC2dParent(String c2dParent) {
    this.c2dParent = c2dParent;
    return this;
  }

  public FamilyDataUI withSkypeParent(String skypeParent) {
    this.skypeParent = skypeParent;
    return this;
  }

  public FamilyDataUI withWhatsappParent(String whatsappParent) {
    this.whatsappParent = whatsappParent;
    return this;
  }

  public FamilyDataUI withFbParent(String fbParent) {
    this.fbParent = fbParent;
    return this;
  }

  public FamilyDataUI withVkParent(String vkParent) {
    this.vkParent = vkParent;
    return this;
  }

  public FamilyDataUI withOkParent(String okParent) {
    this.okParent = okParent;
    return this;
  }

  public FamilyDataUI withInstParent(String instParent) {
    this.instParent = instParent;
    return this;
  }

  /* getters */

  public String getFamilyId() {
    return familyId;
  }

  public String getId() {
    return id;
  }

  public String getFirstnameStudent() {
    return firstnameStudent;
  }

  public String getLastnameStudent() {
    return lastnameStudent;
  }

  public Integer getGenderStudent() {
    return genderStudent;
  }

  public String getBirthdayUiStudent() {
    return birthdayUiStudent;
  }

  public String getPclevelStudent() {
    return pclevelStudent;
  }

  public String getCountryStudent() {
    return countryStudent;
  }

  public String getCityStudent() {
    return cityStudent;
  }

  public String getTimezoneStudent() {
    return timezoneStudent;
  }

  public String getPhoneStudent() {
    return phoneStudent;
  }

  public String getTelegramStudent() {
    return telegramStudent;
  }

  public String getViberStudent() {
    return viberStudent;
  }

  public String getC2dStudent() {
    return c2dStudent;
  }

  public String getSkypeStudent() {
    return skypeStudent;
  }

  public String getWhatsappStudent() {
    return whatsappStudent;
  }

  public String getFbStudent() {
    return fbStudent;
  }

  public String getVkStudent() {
    return vkStudent;
  }

  public String getOkStudent() {
    return okStudent;
  }

  public String getInstStudent() {
    return instStudent;
  }

  public String getFirstnameParent() {
    return firstnameParent;
  }

  public String getLastnameParent() {
    return lastnameParent;
  }

  public String getCountryParent() {
    return countryParent;
  }

  public String getCityParent() {
    return cityParent;
  }

  public String getTimezoneParent() {
    return timezoneParent;
  }

  public String getPhoneParent() {
    return phoneParent;
  }

  public String getEmailParent() {
    return emailParent;
  }

  public String getTelegramParent() {
    return telegramParent;
  }

  public String getViberParent() {
    return viberParent;
  }

  public String getC2dParent() {
    return c2dParent;
  }

  public String getSkypeParent() {
    return skypeParent;
  }

  public String getWhatsappParent() {
    return whatsappParent;
  }

  public String getFbParent() {
    return fbParent;
  }

  public String getVkParent() {
    return vkParent;
  }

  public String getOkParent() {
    return okParent;
  }

  public String getInstParent() {
    return instParent;
  }

  /* toString(), hashCode() & equals() */


}
