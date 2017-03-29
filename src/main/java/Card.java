import org.sql2o.*;
import java.util.List;

public class Card {
  private int c_id;
  private String c_name;
  private int cardfaction_id;
  private int deck_quantity;
  private int combat;
  private int cost_to_buy;
  private int trade;
  private String user_notes;

  public Card(String c_name, int cardfaction_id, int deck_quantity, int combat, int cost_to_buy, int trade) {
    this.c_name = c_name;
    this.cardfaction_id = cardfaction_id;
    this.deck_quantity = deck_quantity;
    this.combat = combat;
    this.trade = trade;
    this.cost_to_buy = cost_to_buy;
    user_notes = "none";
  }
  public int getCardId(){
    return c_id;
  }
  public String getCardName(){
    return c_name;
  }
  public int getFactionId(){
    return cardfaction_id;
  }
  public int getDeckQuantity(){
    return deck_quantity;
  }
  public int getCombat(){
    return combat;
  }
  public int getCostToBuy(){
    return cost_to_buy;
  }
  public int getTrade(){
    return trade;
  }
  public String getUserNotes(){
    return user_notes;
  }
  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO cards (c_name, cardfaction_id, deck_quantity, combat,trade, cost_to_buy, user_notes) VALUES (:c_name, :cardfaction_id, :deck_quantity, :combat, :trade, :cost_to_buy, :user_notes)";
      this.c_id = (int) con.createQuery(sql, true)
        .addParameter("c_name", this.c_name)
        .addParameter("cardfaction_id", this.cardfaction_id)
        .addParameter("deck_quantity", this.deck_quantity)
        .addParameter("combat", this.combat)
        .addParameter("trade", this.trade)
        .addParameter("cost_to_buy", this.cost_to_buy)
        .addParameter("user_notes", this.user_notes)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Card> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cards;";
      return con.createQuery(sql).executeAndFetch(Card.class);
    }
  }

  @Override
  public boolean equals(Object otherCard){
    if(!(otherCard instanceof Card)){
      return false;
    } else{
      Card newCard = (Card) otherCard;
      return this.getCardName().equals(newCard.getCardName()) && this.getCardId() == newCard.getCardId();
    }
  }



}
