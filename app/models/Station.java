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


  public Station(String stationName, double latitude, double longitude) {
    this.name = stationName;
    this.latitude = latitude;
    this.longitude = longitude;
  }

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

  public boolean isNotEmpty() {
    if (readings.size() == 0) {
      return false;
    } else {
      return true;
    }
  }

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

  public String getTemperatureTrend(){
    if (readings.size() >= 3) {
      int counter = 0;
      for (int i = 1; i < 3; i++) {
        Reading rNext = readings.get(readings.size() - i);
        Reading rPrev = readings.get(readings.size() - 1 - i);
        if (rNext.temperature > rPrev.temperature) { counter++; }
        if (rNext.temperature < rPrev.temperature) { counter--; }
      }
      if (counter == 2) { return "Increasing"; }
      else if (counter == -2) { return "Decreasing"; }
      else { return "No trend"; }
    } else { return "No trend"; }
  }
  public String getWindTrend(){
    if (readings.size() >= 3) {
      int counter = 0;
      for (int i = 1; i < 3; i++) {
        Reading rNext = readings.get(readings.size() - i);
        Reading rPrev = readings.get(readings.size() - 1 - i);
        if (rNext.windSpeed > rPrev.windSpeed) { counter++; }
        if (rNext.windSpeed < rPrev.windSpeed) { counter--; }
      }
      if (counter == 2) { return "Increasing"; }
      else if (counter == -2) { return "Decreasing"; }
      else { return "No trend"; }
    } else { return "No trend"; }
  }
  public String getPressureTrend(){
    if (readings.size() >= 3) {
      int counter = 0;
      for (int i = 1; i < 3; i++) {
        Reading rNext = readings.get(readings.size() - i);
        Reading rPrev = readings.get(readings.size() - 1 - i);
        if (rNext.pressure > rPrev.pressure) { counter++; }
        if (rNext.pressure < rPrev.pressure) { counter--; }
      }
      if (counter == 2) { return "Increasing"; }
      else if (counter == -2) { return "Decreasing"; }
      else { return "No trend"; }
    } else { return "No trend"; }
  }
}
