import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

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
  public void findsFactionByName() {
    Faction newFaction = new Faction("Mercs");
    newFaction.save();
    assertTrue(newFaction.equals(Faction.findByName("Mercs")));
  }

  @Test
  public void getFactionId_true(){
    Faction newFaction = new Faction("Mercs");
    newFaction.save();
    assertTrue(newFaction.getFactionId()>0);
  }

  @Test
  public void findAllcardsofcertainFaction_cardlist() {
    Faction newFaction = new Faction("Mercs");
    Card newCard1 = new Card("Scout", newFaction.getFactionId(), 16, 0, 0, 1);
    Card newCard2 = new Card("Explorer", newFaction.getFactionId(), 12, 0, 0, 2);
    newCard1.save();
    newCard2.save();
    Card [] cards = new Card [] {newCard1, newCard2};
    assertTrue(newFaction.getCardsOfFaction().containsAll(Arrays.asList(cards)));

  }

  @Test
  public void deletesFaction_true() {
    Faction newFaction1 = new Faction("Mercs");
    newFaction1.save();
    int f_id = newFaction1.getFactionId();
    newFaction1.delete();
    assertEquals(null, Faction.find(f_id));
  }
  @Test
  public void updateFactionName_true(){
    Faction newFaction1 = new Faction("Merk");
    newFaction1.save();
    newFaction1.updateFactionName("Mercs");
    assertEquals("Mercs", Faction.find(newFaction1.getFactionId()).getFactionName());
  }


}
