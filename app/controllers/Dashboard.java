package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import java.util.List;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("RENDER_DASHBOARD_PENDING");
    List<Station> stations = Station.findAll();
    //System.out.println(Station.getLatestReading());
    Logger.info("RENDER_DASHBOARD_SUCCESSFUL");
    render("dashboard.html", stations);
  }

  public static void addStation(String name) {
    Logger.info ("ACTION_CREATE_STATION_NAME(" + name + ")");
    Station station = new Station(name);
    station.save();
    Logger.info("ACTION_CREATE_STATION_SUCCESSFUL");
    redirect("/dashboard");
  }

  public static void deleteStation(Long id) {
    Logger.info ("ACTION_DELETE_STATION_PENDING_ID(" + id + ")");
    Station station = Station.findById(id);
    station.delete();
    Logger.info("ACTION_CREATE_STATION_SUCCESSFUL");
    redirect("/dashboard");
  }
}
