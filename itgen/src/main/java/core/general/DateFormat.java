package core.general;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

//28 февраля
public class DateFormat {

  public static String formatDDMMMM(Date date) {
    SimpleDateFormat dateFormat = null;
    dateFormat = new SimpleDateFormat("dd MMMM", myDateFormatSymbols);
    return dateFormat.format(date);
  }

  public static String formatDD(Date date) {
    SimpleDateFormat dateFormat = null;
    dateFormat = new SimpleDateFormat("dd");
    return dateFormat.format(date);
  }

  public static String formatMMMM(Date date) {
    SimpleDateFormat dateFormat = null;
    dateFormat = new SimpleDateFormat("MMMM", myDateFormat);
    return dateFormat.format(date);
  }

  private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {

    @Override
    public String[] getMonths() {
      return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
          "июля", "августа", "сентября", "октября", "ноября", "декабря"};
    }
  };

  private static DateFormatSymbols myDateFormat = new DateFormatSymbols() {

    @Override
    public String[] getMonths() {
      return new String[]{"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
          "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    }
  };
}