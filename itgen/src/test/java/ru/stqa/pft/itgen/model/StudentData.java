package ru.stqa.pft.itgen.model;

public class StudentData {
  private final String firsname;
  private final String lastname;
  private final String gender;
  private final String birthday;
  private final String country;
  private final String city;
  private final String timezone;
  private final String locate;
  private final String studyLang;
  private final String duration;
  private final String phone;
  private final String skype;
  private final String c2d;
  private final String viber;
  private final String whatsapp;
  private final String telegram;
  private final String fb;
  private final String vk;
  private final String ok;
  private final String inst;
  private final String familyId;
  private final String note;

  public StudentData(String firsname, String lastname, String gender, String birthday, String country, String city, String timezone, String locate, String studyLang, String duration, String phone, String skype, String c2d, String viber, String whatsapp, String telegram, String fb, String vk, String ok, String inst, String familyId, String note) {
    this.firsname = firsname;
    this.lastname = lastname;
    this.gender = gender;
    this.birthday = birthday;
    this.country = country;
    this.city = city;
    this.timezone = timezone;
    this.locate = locate;
    this.studyLang = studyLang;
    this.duration = duration;
    this.phone = phone;
    this.skype = skype;
    this.c2d = c2d;
    this.viber = viber;
    this.whatsapp = whatsapp;
    this.telegram = telegram;
    this.fb = fb;
    this.vk = vk;
    this.ok = ok;
    this.inst = inst;
    this.familyId = familyId;
    this.note = note;
  }

  public String getFirsname() {
    return firsname;
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
