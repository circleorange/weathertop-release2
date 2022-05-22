package controllers;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import java.util.List;

public class Dashboard extends Controller {
  public static boolean containsReadings;

  public static void index() {
    Logger.info("RENDER_DASHBOARD_PENDING");
    Member member = Accounts.getLoggedInUser();
    List<Station> stations = member.stations;
    Logger.info("RENDER_DASHBOARD_SUCCESSFUL");
    render("dashboard.html", stations, containsReadings);
  }

  public static void addStation(String name, double latitude, double longitude) {
    Logger.info ("ACTION_CREATE_STATION_NAME(" + name + ")");
    Member member = Accounts.getLoggedInUser();
    Station station = new Station(name, latitude, longitude);
    member.stations.add(station);
    member.save();
    Logger.info("ACTION_CREATE_STATION_SUCCESSFUL");
    redirect("/dashboard", containsReadings);
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
