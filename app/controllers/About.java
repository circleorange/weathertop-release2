package controllers;

import play.*;
import play.mvc.*;

public class About extends Controller {
  public static void index() {
    Logger.info("RENDER_ABOUT_PAGE");
    render("about.html");
  }
}
