import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class CardTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void cardCreated_true () {
    Card newCard = new Card("Scout", 1, 16, 0, 0, 1);
    assertTrue(newCard instanceof Card);
  }
  @Test
  public void getCardName_scout () {
    Card newCard = new Card("Scout", 1, 16, 0, 0, 1);
    assertEquals("Scout", newCard.getCardName());
  }
  @Test
  public void getFactionId_1 () {
    Card newCard = new Card("Scout", 1, 16, 0, 0, 1);
    assertEquals(1, newCard.getFactionId());
  }
  @Test
  public void getDeckQuantity_16 () {
    Card newCard = new Card("Scout", 1, 16, 0, 0, 1);
    assertEquals(16, newCard.getDeckQuantity());
  }
  @Test
  public void getCombat_0 () {
    Card newCard = new Card("Scout", 1, 16, 0, 0, 1);
    assertEquals(0, newCard.getCombat());
  }
  @Test
  public void getTrade_1() {
    Card newCard = new Card("Scout", 1, 16, 0, 0, 1);
    assertEquals(1, newCard.getTrade());
  }
  @Test
  public void getCostToBuy_0() {
    Card newCard = new Card("Scout", 1, 16, 0, 0, 1);
    assertEquals(0, newCard.getCostToBuy());
  }
  @Test
  public void getUserNotes_none() {
    Card newCard = new Card("Scout", 1, 16, 0, 0, 1);
    assertEquals("none", newCard.getUserNotes());
  }
  @Test
  public void getAllCards_true(){
    Card newCard1 = new Card("Scout", 1, 16, 0, 0, 1);
    Card newCard2 = new Card("Explorer", 1, 12, 0, 0, 2);
    newCard1.save();
    newCard2.save();
    System.out.println("newCard before Equals ---" + newCard2.getCardId());

    assertTrue(newCard2.equals(Card.all().get(1)));
  }
  @Test
  public void getCardId_true(){
    Card newCard1 = new Card("Scout", 1, 16, 0, 0, 1);
    newCard1.save();
    assertTrue(newCard1.getCardId()>0);
  }
  @Test
  public void findCard_true(){
    Card newCard1 = new Card("Scout", 1, 16, 0, 0, 1);
    newCard1.save();
    assertTrue(newCard1.equals(Card.find(newCard1.getCardId())));
  }

  @Test
  public void deletesCard_true() {
    Card newCard1 = new Card("Scout", 1, 16, 0, 0, 1);
    newCard1.save();
    int c_id = newCard1.getCardId();
    newCard1.delete();
    assertEquals(null, Card.find(c_id));
  }

  @Test
  public void updateCardName_true(){
    Card newCard1 = new Card("Scot", 1, 16, 0, 0, 1);
    newCard1.save();
    newCard1.updateCardName("Scout");
    assertEquals("Scout", Card.find(newCard1.getCardId()).getCardName());
  }
}
