package controllers;

import models.Reading;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Admin extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Admin");
    List<Reading> todolist = Reading.findAll();
    render("admin.html", todolist);
  }
}
