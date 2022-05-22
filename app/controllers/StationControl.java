package controllers;

import models.Reading;
import play.Logger;
import play.mvc.Controller;
import models.Station;

public class StationControl extends Controller {
  public static void index(Long id) {
    Logger.info ("RENDER_STATION_PENDING_ID(" + id + ")");
    Station station = Station.findById(id);
    Logger.info ("RENDER_STATION_SUCCESSFUL");
    boolean addReadingError = false;
    render("station.html", station, addReadingError);
  }

  public static void addReading(Long id, int code, double temp, double windSpd, int windDir, int pressure) {
    Logger.info ("ACTION_CREATE_READING_PENDING");
    Station station = Station.findById(id);
    if (code == 0 || temp == 0.0 || windSpd == 0.0 || windDir == 0 || pressure == 0) {
      boolean addReadingError = true;
      Logger.info ("ACTION_CREATE_READING_REJECTED");
      render("station.html", station, addReadingError);
    } else {
      Reading reading = new Reading(code, temp, windSpd, windDir, pressure);
      station.readings.add(reading);
      station.save();
      Logger.info("ACTION_CREATE_READING_SUCCESSFUL");
      redirect ("/stations/" + id);
    }
  }

  public static void deleteReading(Long id, Long readingid) {
    Logger.info("ACTION_REMOVE_READING_ID(" + readingid + ")");
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    station.readings.remove(reading);
    station.save();
    reading.delete();
    Logger.info("ACTION_REMOVE_READING_SUCCESSFUL");
    render("station.html", station);
  }
}
