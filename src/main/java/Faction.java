import org.sql2o.*;
import java.util.List;

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

  @Override
  public boolean equals(Object otherFaction) {
    if (!(otherFaction instanceof Faction)) {
      return false;
    } else {
      Faction newFaction = (Faction) otherFaction;
      return this.getFactionName().equals(newFaction.getFactionName()) && this.getFactionId() == newFaction.getFactionId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO factions (f_name) VALUES (:f_name);";
      this.f_id = (int) con.createQuery(sql, true)
        .addParameter("f_name", this.f_name)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Faction> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM factions;";
      return con.createQuery(sql).executeAndFetch(Faction.class);
    }
  }

  public static Faction find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM factions WHERE f_id = :f_id;";
      Faction foundFaction = con.createQuery(sql)
        .addParameter("f_id", id)
        .executeAndFetchFirst(Faction.class);
        return foundFaction;
      }
    }
}
