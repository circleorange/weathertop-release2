package models;

import play.Logger;
import play.db.jpa.Model;
import utils.Conversions;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Station extends Model {
  public String name;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();
  //public String weatherType;


  // Constructor
  public Station(String stationName) { this.name = stationName; }

  // Get the latest readings
  public Reading getLatestReading() {
    //reading = Reading.findAll();
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
}
