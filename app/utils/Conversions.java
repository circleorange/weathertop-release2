package utils;

import models.Reading;

public class Conversions {
  public static String toWeatherLabel(int weatherCode) {
    String weatherType = "";
    if (weatherCode == 100) { return weatherType = "Clear"; }
    else if (weatherCode == 200) { return weatherType = "Partial Clouds"; }
    else if (weatherCode == 300) { return weatherType = "Cloudy"; }
    else if (weatherCode == 400) { return weatherType = "Light Showers"; }
    else if (weatherCode == 500) { return weatherType = "Heavy Showers"; }
    else if (weatherCode == 600) { return weatherType = "Rain"; }
    else if (weatherCode == 700) { return weatherType = "Snow"; }
    else if (weatherCode == 800) { return weatherType = "Thunder"; }
    else { return weatherType = "INVALID_WEATHER_CODE"; }
  }

  public static double toFahrenheit(double celsius) {
    double fahrenheit = celsius * (9/5) + 32;
    return fahrenheit;
  }

  public static int toBeaufortScale(double windSpeed) {
    int beaufort = 0;
    if (windSpeed == 1) { return beaufort = 1; }
    else if (windSpeed > 1 && windSpeed <= 5) { return beaufort = 1; }
    else if (windSpeed >= 6 && windSpeed <= 11) { return beaufort = 2; }
    else if (windSpeed >= 12 && windSpeed <= 19) { return beaufort = 3; }
    else if (windSpeed >= 20 && windSpeed <= 28) { return beaufort = 4; }
    else if (windSpeed >= 29 && windSpeed <= 38) { return beaufort = 5; }
    else if (windSpeed >= 39 && windSpeed <= 49) { return beaufort = 6; }
    else if (windSpeed >= 50 && windSpeed <= 61) { return beaufort = 7; }
    else if (windSpeed >= 62 && windSpeed <= 74) { return beaufort = 8; }
    else if (windSpeed >= 75 && windSpeed <= 88) { return beaufort = 9; }
    else if (windSpeed >= 86 && windSpeed <= 102) { return beaufort = 10; }
    else if (windSpeed >= 103 && windSpeed <= 117) { return beaufort = 11; }
    else { return beaufort = 404; }
  }

  public static String toBeaufortLabel(int beaufort) {
    int beaufortScale = Reading.beaufortScale;
    //beaufortScale = Reading.windBeaufort;
    if (beaufortScale  == 0) { return "Calm"; }
    else if (beaufortScale  == 1) { return "Light Air"; }
    else if (beaufortScale  == 2) { return "Light Breeze"; }
    else if (beaufortScale  == 3) { return "Gentle Breeze"; }
    else if (beaufortScale  == 4) { return "Moderate Breeze"; }
    else if (beaufortScale  == 5) { return "Fresh Breeze"; }
    else if (beaufortScale  == 6) { return "Strong Breeze"; }
    else if (beaufortScale  == 7) { return "Near Gale"; }
    else if (beaufortScale  == 8) { return "Gale"; }
    else if (beaufortScale  == 9) { return "Severe Gale"; }
    else if (beaufortScale  == 10) { return "Strong storm"; }
    else if (beaufortScale  == 11) { return "Violent Storm"; }
    else { return "OUT_OF_RANGE_BEAUFORT_SCALE"; }
  }

  public static String toCompassDirection(double windDirection) {
    double windDir = windDirection;
    if (windDir > 348.75 && windDir <= 11.25) { return "N"; }
    else if (windDir > 11.25 && windDir <= 33.75) { return "NNE"; }
    else if (windDir > 33.75 && windDir <= 56.25) { return "NE"; }
    else if (windDir > 56.25 && windDir <= 78.75) { return "ENE"; }
    else if (windDir > 75.75 && windDir <= 101.25) { return "E"; }
    else if (windDir > 101.25 && windDir <= 123.75) { return "ESE"; }
    else if (windDir > 123.75 && windDir <= 146.25) { return "SE"; }
    else if (windDir > 146.25 && windDir <= 168.75) { return "SSE"; }
    else if (windDir > 168.75 && windDir <= 191.25) { return "S"; }
    else if (windDir > 191.25 && windDir <= 213.75) { return "SSW"; }
    else if (windDir > 213.75 && windDir <= 236.25) { return "SW"; }
    else if (windDir > 236.25 && windDir <= 258.75) { return "WSW"; }
    else if (windDir > 258.75 && windDir <= 281.25) { return "W"; }
    else if (windDir > 281.25 && windDir <= 303.75) { return "WNW"; }
    else if (windDir > 303.75 && windDir <= 326.25) { return "NW"; }
    else if (windDir > 326.25 && windDir <= 348.75) { return "NNW"; }
    else { return "INVALID_COMPASS_DIRECTION"; }
  }

  public static double toWindChill(double tempC, double windKM) {
    double windChill = 13.12 + 0.6215*tempC - 11.37*(Math.pow(windKM, 0.16)) + 0.3965*tempC*(Math.pow(windKM, 0.16));
    return windChill;
  }
}
