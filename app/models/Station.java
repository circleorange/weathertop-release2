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
      Reading.weatherLabel = Conversions.toWeatherLabel(latestReading.code);
      Reading.beaufortScale = Conversions.toBeaufortScale(latestReading.windSpeed);
      Reading.beaufortLabel = Conversions.toBeaufortLabel(Reading.beaufortScale);
      Reading.compassDirection = Conversions.toCompassDirection(latestReading.windDirection);
      Reading.windChill = Conversions.toWindChill(latestReading.temperature, latestReading.windSpeed);
    }
    return latestReading;
  }

  /*
  // Hash map to link weatherLabel with suitable icon
  public HashMap<String, String> toWeatherIcon;
  public void weatherIconMap() {
    toWeatherIcon.put("Rain", "umbrella icon");
  }

  public String getWeatherIcon() {
    weatherIconMap();
    return toWeatherIcon.get(Reading.weatherLabel);
  }*/

  public double maxTemperature() {
    if (readings.size() == 0) { return 999.99; }
    Reading maxTemperature = readings.get(0);
    for (Reading reading : readings) {
      if (reading.temperature > maxTemperature.temperature) { maxTemperature = reading; }
    }
    return maxTemperature.temperature;
  }

  public double minTemperature() {
    if (readings.size() == 0) { return 0.01; }
    Reading minTemperature = readings.get(0);
    for (Reading reading : readings) {
      if (reading.temperature < minTemperature.temperature) { minTemperature = reading; }
    }
    return minTemperature.temperature;
  }

  public double maxWindSpeed() {
    if (readings.size() == 0) { return 999.99; }
    Reading maxWindSpeed = readings.get(0);
    for (Reading reading : readings) {
      if (reading.windSpeed > maxWindSpeed.windSpeed) { maxWindSpeed = reading; }
    }
    return maxWindSpeed.windSpeed;
  }

  public double minWindSpeed() {
    if (readings.size() == 0) { return 0.01; }
    Reading minWindSpeed = readings.get(0);
    for (Reading reading : readings) {
      if (reading.windSpeed < minWindSpeed.windSpeed) { minWindSpeed = reading; }
    }
    return minWindSpeed.windSpeed;
  }

  public int maxPressure() {
    if (readings.size() == 0) { return 999; }
    Reading maxPressure = readings.get(0);
    for (Reading reading : readings) {
      if (reading.pressure > maxPressure.pressure) { maxPressure = reading; }
    }
    return maxPressure.pressure;
  }

  public int minPressure() {
    if (readings.size() == 0) { return 0; }
    Reading minPressure = readings.get(0);
    for (Reading reading : readings) {
      if (reading.pressure < minPressure.pressure) { minPressure = reading; }
    }
    return minPressure.pressure;
  }
}
