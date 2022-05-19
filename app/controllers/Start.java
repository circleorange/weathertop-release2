package controllers;

import play.Logger;
import play.mvc.Controller;

public class Start extends Controller
{
  public static void index()
  {
    Logger.info("RENDER_START_PAGE");
    render("start.html");
  }
}
