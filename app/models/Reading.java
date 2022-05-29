package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public int windDirection;
  public int pressure;
  public Date date;
  private String dateYear;
  private String dateHour;

  public static String weatherLabel;
  public static double fahrenheit;
  public static int beaufortScale;
  public static String beaufortLabel;
  public static String compassDirection;
  public static double windChill;

  public Reading(int code, double temp, double windSpeed, int windDirection, int pressure) {
    this.code = code;
    this.temperature = temp;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.pressure = pressure;
    Date date = new Date();
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
    this.dateYear = sdf1.format(date);
    this.dateHour = sdf2.format(date);
  }
}
