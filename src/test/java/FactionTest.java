import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class FactionTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void createsNewFaction_True() {
    Faction newFaction = new Faction("Mercs");
    assertTrue(newFaction instanceof Faction);
  }

  @Test
  public void returnsFactionName_Mercs() {
    Faction newFaction = new Faction("Mercs");
    assertEquals("Mercs", newFaction.getFactionName());
  }

  @Test
  public void getsAllFactions_True() {
    Faction newFaction1 = new Faction("Mercs");
    Faction newFaction2 = new Faction("Blob");
    newFaction1.save();
    newFaction2.save();
    assertTrue(newFaction2.equals(Faction.all().get(1)));
  }

  @Test
  public void findsFactionByID() {
    Faction newFaction = new Faction("Mercs");
    newFaction.save();
    assertTrue(newFaction.equals(Faction.find(newFaction.getFactionId())));
  }

  @Test
  public void getFactionId_true(){
    Faction newFaction = new Faction("Mercs");
    newFaction.save();
    assertTrue(newFaction.getFactionId()>0);
  }



}
