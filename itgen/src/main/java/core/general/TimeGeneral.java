package core.general;

import static app.appmanager.ApplicationManager.properties;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class TimeGeneral {

  public TimeGeneral() {
    super();
  }

  // установка времени на начало дня ( на 00:00 )по UTC
  public long getMsLocalTime() {
    LocalDate date = LocalDate.now();
    return date.atStartOfDay().toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
  }

  private long getMsLocalTimeYesterday() {
    LocalDate date = LocalDate.now().minusDays(1);
    return date.atStartOfDay().toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
  }

  private long getMsLocalTimeTomorrow() {
    LocalDate date = LocalDate.now().plusDays(1);
    return date.atStartOfDay().toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
  }

  // только время
  public long getTimeNow() {
    long dataWithTime = new Date().getTime();
    int popravka = 0;
    if (diffTz() < 100) {
      popravka = 10800000;
    }
    return dataWithTime - getMsLocalTime() + popravka;
  }

  // поправка на часовой пояс, заданный в properties
  public long diffTz() {
    long timeUTC = DateTime.now(DateTimeZone.UTC).getMillisOfDay();
    long timeTZ = DateTime.now(DateTimeZone.forID(properties.getProperty("tz"))).getMillisOfDay();
    long diff = timeTZ - timeUTC;
    return (long) (Math.floor(diff / 1000) * 1000);
  }

  // сегодня 00:00 в мс
  public Double date() {
    long nowTime = getMsLocalTime();
    return (nowTime + diffTz()) * 1.0;
  }

  public Double dateYesterday() {
    long nowTime = getMsLocalTimeYesterday();
    return (nowTime + diffTz()) * 1.0;
  }

  public Double dateTomorrow() {
    long nowTime = getMsLocalTimeTomorrow();
    return (nowTime + diffTz()) * 1.0;
  }

  public Double Stime(String period) {
    long nowTime = getMsLocalTime();
    return (nowTime + diffTz() + start(period)) * 1.0;
  }

  public Double Etime(String period) {
    long nowTime = getMsLocalTime();
    return (nowTime + diffTz() + finish(period)) * 1.0;
  }

  public Double StimeYesterday(String period) {
    long nowTime = getMsLocalTimeYesterday();
    return (nowTime + diffTz() + start(period)) * 1.0;
  }

  public Double EtimeYesterday(String period) {
    long nowTime = getMsLocalTimeYesterday();
    return (nowTime + diffTz() + finish(period)) * 1.0;
  }

  public Double StimeTomorrow(String period) {
    long nowTime = getMsLocalTimeTomorrow();
    return (nowTime + diffTz() + start(period)) * 1.0;
  }

  public Double EtimeTomorrow(String period) {
    long nowTime = getMsLocalTimeTomorrow();
    return (nowTime + diffTz() + finish(period)) * 1.0;
  }

  public int start(String period) {
    int hours = Integer.parseInt(period.substring(0, 2));
    int minutes = Integer.parseInt(period.substring(3, 5));
    int startValue = hours * 3600000;

    if ((hours < 3) && (minutes != 30)) {
      startValue += 75600000;
    }
    if ((hours < 3) && (minutes == 30)) {
      startValue += 77400000;
    }
    if ((hours >= 3) && (minutes != 30)) {
      startValue -= 10800000;
    }
    if ((hours >= 3) && (minutes == 30)) {
      startValue -= 9000000;
    }

    return startValue;
  }

  public int finish(String period) {
    int hours = Integer.parseInt(period.substring(0, 2));
    int minutes = Integer.parseInt(period.substring(3, 5));
    int finishValue = hours * 3600000;

    if ((hours < 3) && (minutes != 30)) {
      finishValue += 82800000;
    }
    if ((hours < 3) && (minutes == 30)) {
      finishValue -= 84600000;
    }
    if ((hours >= 3) && (minutes != 30)) {
      finishValue -= 3600000;
    }
    if ((hours >= 3) && (minutes == 30)) {
      finishValue -= 1800000;
    }

    return finishValue;
  }

  // для определения ближайшего занятия, в зависимости от того, сколько сейчас времени
  public String getPeriod(long time) {
    int index = (int) (time / 1800000);
    int minutes = 0;
    int hours = index / 2;

    if (index % 2 != 0) {
      minutes = 30;
    }
    return String.format("%02d:%02d - $02d:%02d", hours, minutes, hours + 2, minutes);
  }
}
