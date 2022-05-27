package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model {
  public String firstname;
  public String lastname;
  public String email;
  public String password;

  @OneToMany(cascade = CascadeType.ALL)
  @OrderBy("name ASC")
  public List<Station> stations = new ArrayList<Station>();

  public Member(String fname, String lname, String email, String pwd) {
    this.firstname = fname;
    this.lastname = lname;
    this.email = email;
    this.password = pwd;
  }

  // find method from play framework
  public static Member findByEmail(String email) { return find("email", email).first(); }
  public boolean checkPassword(String pwd) { return this.password.equals(pwd); }
}
