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
  private String c_image_url;

  public Card(String c_name, int cardfaction_id, int deck_quantity, int combat, int cost_to_buy, int trade) {
    this.c_name = c_name;
    this.cardfaction_id = cardfaction_id;
    this.deck_quantity = deck_quantity;
    this.combat = combat;
    this.trade = trade;
    this.cost_to_buy = cost_to_buy;
    user_notes = "none";
  }
  public String getCardImgUrl(){
    return c_image_url;
  }
  public void setCardImgUrl( String url){
    c_image_url = url;
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
      String sql = "INSERT INTO cards (c_name, cardfaction_id, deck_quantity, combat,trade, cost_to_buy, user_notes, c_image_url) VALUES (:c_name, :cardfaction_id, :deck_quantity, :combat, :trade, :cost_to_buy, :user_notes, :c_image_url)";
      this.c_id = (int) con.createQuery(sql, true)
        .addParameter("c_name", this.c_name)
        .addParameter("cardfaction_id", this.cardfaction_id)
        .addParameter("deck_quantity", this.deck_quantity)
        .addParameter("combat", this.combat)
        .addParameter("trade", this.trade)
        .addParameter("cost_to_buy", this.cost_to_buy)
        .addParameter("user_notes", this.user_notes)
        .addParameter("c_image_url", this.c_image_url)
        .executeUpdate()
        .getKey();
    }
  }
  public static Card find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cards WHERE c_id=:c_id;";
      Card foundCard = con.createQuery(sql)
        .addParameter("c_id", id)
        .executeAndFetchFirst(Card.class);
      return foundCard;
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
      System.out.println("newCard ---" + newCard.getCardId());
      System.out.println("this---" + this.getCardId());
      return this.getCardName().equals(newCard.getCardName()) && this.getCardId() == newCard.getCardId();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM cards WHERE c_id = :c_id;";
      con.createQuery(sql)
        .addParameter("c_id", this.c_id)
        .executeUpdate();
    }
  }

  public void updateCardName(String cardName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE cards SET c_name = :c_name WHERE c_id=:c_id;";
      con.createQuery(sql)
        .addParameter("c_id", this.c_id)
        .addParameter("c_name", cardName)
        .executeUpdate();
    }
  }



}
