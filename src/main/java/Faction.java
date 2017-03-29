import org.sql2o.*;

public class Faction {
  private String f_name;
  private int f_id;

  public Faction(String f_name) {
    this.f_name = f_name;
  }

  public String getFactionName() {
    return f_name;
  }

  public int getFactionId() {
    return f_id;
  }

}
