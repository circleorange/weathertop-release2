package controllers;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import javax.persistence.OrderBy;
import java.util.List;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("RENDER_DASHBOARD_PENDING");
    Member member = Accounts.getLoggedInUser();
    List<Station> stations = member.stations ;
    boolean addStationError = false;
    boolean addStationSuccessful = false;
    Logger.info("RENDER_DASHBOARD_SUCCESSFUL");
    render("dashboard.html", stations, addStationError, addStationSuccessful);
  }
  public static void addStation(String name, double latitude, double longitude) {
    Logger.info ("ACTION_CREATE_STATION(" + name + ")");
    Member member = Accounts.getLoggedInUser();
    List<Station> stations = member.stations;
    if (name.isEmpty()) {
      Logger.info("ACTION_CREATE_STATION_REJECTED");
      boolean addStationError = true;
      render("dashboard.html", stations, addStationError);
    } else {
      Station station = new Station(name, latitude, longitude);
      member.stations.add(station);
      member.save();
      boolean addStationSuccessful = true;
      Logger.info("ACTION_CREATE_STATION_SUCCESSFUL");
      render("dashboard.html", stations, addStationSuccessful);
    }
  }
  public static void deleteStation(Long id) {
    Logger.info ("ACTION_DELETE_STATION_PENDING_ID(" + id + ")");
    Member member = Accounts.getLoggedInUser();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    Logger.info("ACTION_CREATE_STATION_SUCCESSFUL");
    redirect("/dashboard");
  }
}
