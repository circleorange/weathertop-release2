package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

@Entity
public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public int windDirection;
  public int pressure;

  public static String weatherType;
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
  }
}
