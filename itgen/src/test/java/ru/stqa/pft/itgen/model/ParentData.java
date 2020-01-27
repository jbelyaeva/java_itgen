package ru.stqa.pft.itgen.model;

public class ParentData {
  private final String firstName;
  private final String lastName;
  private String country;
  private String city;
  private String timeZone;
  private final String phone;
  private final String skype;
  private final String email;
  private final String c2d;
  private final String viber;
  private final String whatsapp;
  private final String telegram;
  private final String fb;
  private final String vk;
  private final String ok;
  private final String inst;

  public ParentData(String firstName, String lastName, String country, String city, String timeZone, String phone, String skype, String email, String c2d, String viber, String whatsapp, String telegram, String fb, String vk, String ok, String inst) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.city = city;
    this.timeZone = timeZone;
    this.phone = phone;
    this.skype = skype;
    this.email = email;
    this.c2d = c2d;
    this.viber = viber;
    this.whatsapp = whatsapp;
    this.telegram = telegram;
    this.fb = fb;
    this.vk = vk;
    this.ok = ok;
    this.inst = inst;
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


}
