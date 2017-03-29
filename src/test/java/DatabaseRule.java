import org.junit.rules.ExternalResource;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/star_realms_test", null, null);
  }

  @Override
  protected void after() {
    try (Connection con = DB.sql2o.open()) {
      String sqlF = "DELETE FROM factions *;";
      String sqlC = "DELETE FROM cards *;";
      con.createQuery(sqlF).executeUpdate();
      con.createQuery(sqlC).executeUpdate();

    }
  }

}
