package models;

import play.db.jpa.Model;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public int windDirection;
  public int pressure;
  public String date;

  public static String weatherLabel;
  public static double tempFahrenheit;
  public static int beaufortScale;
  public static String beaufortLabel;
  public static String compassDirection;
  public static double windChill;

  // Constructor
  public Reading(int code, double temp, double windSpeed, int windDirection, int pressure) {
    this.code = code;
    this.temperature = temp;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.pressure = pressure;
    dateToString();
  }

  public void dateToString() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime ldt = LocalDateTime.now();
    this.date = dtf.format(ldt);
  }

}
