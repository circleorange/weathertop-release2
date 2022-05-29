package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {
  public void index() {
    Logger.info("RENDER_SETTINGS_PAGE");
    Member member = Accounts.getLoggedInUser();
    boolean updateSuccessful = false;
    boolean updateFailed = false;
    render("settings.html", member, updateSuccessful, updateFailed);
  }
  public static void signup() {
    Logger.info("RENDER_SIGNUP_PAGE");
    render("signup.html");
  }
  public static void login() {
    Logger.info("RENDER_LOGIN_PAGE");
    boolean registrationSuccessful = false;
    render("login.html");
  }
  public static void logout() {
    Logger.info("ACTION_LOG_OUT");
    session.clear();
    redirect("/");
  }
  public static void register(String firstname, String lastname, String email, String password) {
    Logger.info("REGISTER_NEW_USER(" + email + ":" + password + ")");
    if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty()) {
      Logger.info("REGISTER_NEW_USER_FAILED");
      boolean registrationFailed = true;
      render("signup.html", registrationFailed);
    } else {
      Logger.info("REGISTER_NEW_USER_SUCCESSFUL");
      Member member = new Member(firstname, lastname, email, password);
      member.save();
      boolean registrationSuccessful = true;
      render("login.html", registrationSuccessful);
    }
  }
  public static void authenticate(String email, String password) {
    Logger.info("LOGIN_AUTHENTICATION_CHECK("+ email + ":" + password + ")");
    Member member = Member.findByEmail(email);
    if ((member != null) && (member.checkPassword(password))) {
      Logger.info("LOGIN_AUTHENTICATION_SUCCESSFUL");
      session.put("LOGGED_IN_USER_ID", member.id); // Assign cookie to user
      redirect("/dashboard");
    } else {
      Logger.info("LOGIN_AUTHENTICATION_FAILED");
      redirect("/");
    }
  }
  public static Member getLoggedInUser() {
    Member member = null;
    if (session.contains("LOGGED_IN_USER_ID")) {
      String memberId = session.get("LOGGED_IN_USER_ID");
      member = Member.findById(Long.parseLong(memberId));
    } else { login(); }
    return member;
  }
  public void updateMember(String firstname, String lastname, String email, String password) {
    Logger.info("ACTION_UPDATE_MEMBER_PENDING");
    Member member = Accounts.getLoggedInUser();
    if (!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
      member.firstname = firstname;
      member.lastname = lastname;
      member.email = email;
      member.password = password;
    } else if (firstname.isEmpty() && lastname.isEmpty() && !email.isEmpty() && password.isEmpty()) {
      member.email = email;
    } else if (firstname.isEmpty() && lastname.isEmpty() && email.isEmpty() && !password.isEmpty()) {
      member.password = password;
    } else if (!firstname.isEmpty() && !lastname.isEmpty() && email.isEmpty() && password.isEmpty()) {
      member.firstname = firstname;
      member.lastname = lastname;
    } else {
      Logger.info("ACTION_UPDATE_MEMBER_FAILED");
      boolean updateFailed = true;
      render("settings.html", member, updateFailed);
    }
    member.save();
    boolean updateSuccessful = true;
    Logger.info("ACTION_UPDATE_MEMBER_SUCCESSFUL");
    render("settings.html", member, updateSuccessful);
  }
}
