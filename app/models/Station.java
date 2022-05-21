package models;

import play.Logger;
import play.db.jpa.Model;
import utils.Conversions;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class Station extends Model {
  public String name;
  public double latitude;
  public double longitude;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();


  // Constructor
  public Station(String stationName, double latitude, double longitude) {
    this.name = stationName;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  // Get the latest readings
  public Reading getLatestReadings() {
    Reading latestReading = null;
    if (readings.size() != 0) {
      latestReading = readings.get(readings.size() - 1);
      Reading.tempFahrenheit = Conversions.toFahrenheit(latestReading.temperature);
      Reading.weatherType = Conversions.toWeatherLabel(latestReading.code);
      Reading.beaufortScale = Conversions.toBeaufortScale(latestReading.windSpeed);
      Reading.beaufortLabel = Conversions.toBeaufortLabel(Reading.beaufortScale);
      Reading.compassDirection = Conversions.toCompassDirection(latestReading.windDirection);
      Reading.windChill = Conversions.toWindChill(latestReading.temperature, latestReading.windSpeed);
    }
    return latestReading;
  }

  double maxTemp = 0.0;
  
  public void getMaxReadings() {
    Reading maxTemp = Collections.max(readings, Comparator.comparing(reading -> reading.temperature));
    Reading
  }
  /*public double maxTemp() {
    return Reading.maxTemp = Collections.max(readings, Comparator.comparing(reading -> reading.temperature));
  }*/
}
