package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {
  public void index() {
    Logger.info("RENDER_SETTINGS_PAGE");
    Member member = Accounts.getLoggedInUser();
    boolean updateSuccessful = false;
    render("settings.html", member, updateSuccessful);
  }
  public static void signup() {
    Logger.info("RENDER_SIGNUP_PAGE");
    render("signup.html");
  }
  public static void login() {
    Logger.info("RENDER_LOGIN_PAGE");
    render("login.html");
  }
  public static void logout() {
    Logger.info("ACTION_LOG_OUT");
    session.clear();
    redirect("/");
  }
  public static void register(String firstname, String lastname, String email, String password) {
    Logger.info("REGISTER_NEW_USER(" + email + ":" + password + ")");
    Member member = new Member(firstname, lastname, email, password);
    member.save();
    redirect("/");
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
    // new page Settings with users fields
    // allow user to change field and Save
    // member must already exist, Save posts new information
    // Validation
    Logger.info("ACTION_UPDATE_MEMBER_PENDING");
    Member member = Accounts.getLoggedInUser();
    if (!firstname.equals(member.firstname) && (firstname != null)) {
      member.firstname = firstname;
    }
    if (!lastname.equals(member.lastname) && (lastname != null)) {
      member.lastname = lastname;
    }
    if (!email.equals(member.email) && (email != null)) {
      member.email = email;
    }
    if (!password.equals(member.password) && (password != null)) {
      member.password = password;
    }
    member.save();
    boolean updateSuccessful = true;
    Logger.info("ACTION_UPDATE_MEMBER_SUCCESSFUL");
    render("settings.html", member, updateSuccessful);
  }
}
