import org.sql2o.*;
import java.util.List;

public class Faction {
  private String f_name;
  private int f_id;
  private String f_image_url;

  public Faction(String f_name) {
    this.f_name = f_name;
  }

  public String getFactionName() {
    return f_name;
  }
  public String getFactionImgUrl() {
    return f_image_url;
  }
  public void setFactionImgUrl(String url) {
    f_image_url = url;
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
      String sql = "INSERT INTO factions (f_name, f_image_url) VALUES (:f_name, :f_image_url);";
      this.f_id = (int) con.createQuery(sql, true)
        .addParameter("f_name", this.f_name)
        .addParameter("f_image_url", this.f_image_url)
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

    public static Faction findByName(String name) {
      try (Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM factions WHERE f_name = :f_name;";
        Faction foundFaction = con.createQuery(sql)
          .addParameter("f_name", name)
          .executeAndFetchFirst(Faction.class);
          return foundFaction;
        }
      }

  public List<Card> getCardsOfFaction(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cards WHERE cardfaction_id=:f_id;";
      return con.createQuery(sql)
        .addParameter("f_id", this.f_id)
        .executeAndFetch(Card.class);

    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM factions WHERE f_id = :f_id;";
      con.createQuery(sql)
        .addParameter("f_id", this.f_id)
        .executeUpdate();
    }
  }

  public void updateFactionName(String factionName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE factions SET f_name = :f_name WHERE f_id=:f_id;";
      con.createQuery(sql)
        .addParameter("f_id", this.f_id)
        .addParameter("f_name", factionName)
        .executeUpdate();
    }
  }
}
