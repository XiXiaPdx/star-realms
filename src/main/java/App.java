import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("factions", Faction.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/factions/:faction-name", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Faction thisFaction = Faction.findByName(request.params(":faction-name"));
      model.put("faction", thisFaction);
      model.put("cards", thisFaction.getCardsOfFaction());
      model.put("template", "templates/faction.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/factions/:faction-name/:card-name", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Faction thisFaction = Faction.findByName(request.params(":faction-name"));
      Card thisCard = Card.findByName(request.params(":card-name"));
      model.put("faction", thisFaction);
      model.put("card", thisCard);
      model.put("template", "templates/card.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:faction-name", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Faction thisFaction = Faction.findByName(request.params(":faction-name"));
      int thisFactionId = thisFaction.getFactionId();
      String cardName = request.queryParams("cardName");
      int deckQuantity = Integer.parseInt(request.queryParams("deckQuantity"));
      int combat= Integer.parseInt(request.queryParams("combat"));
      int costToBuy = Integer.parseInt(request.queryParams("costToBuy"));
      int trade = Integer.parseInt(request.queryParams("trade"));
      String cardImageUrl = request.queryParams("cardImageUrl");
      String userNotes = request.queryParams("userNotes");
      Card newCard = new Card(cardName, thisFactionId, deckQuantity, combat, costToBuy, trade);
      newCard.setCardImgUrl(cardImageUrl);
      newCard.save();
      String url = String.format("/factions/"+thisFaction.getFactionName());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String factionName = request.queryParams("factionName");
      String factionImgUrl = request.queryParams("factionImgUrl");
      Faction newFaction = new Faction(factionName);
      newFaction.setFactionImgUrl(factionImgUrl);
      newFaction.save();
      String url = String.format("/");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/factions/:faction-name/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String updateFactionName = request.queryParams("updateFactionName");
      String updateFactionImg = request.queryParams("updateFactionImg");

      Faction thisFaction = Faction.findByName(request.params(":faction-name"));
      thisFaction.updateFaction(updateFactionName, updateFactionImg);
      String url = String.format("/factions/"+updateFactionName);
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/factions/:faction-name/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Faction thisFaction = Faction.findByName(request.params(":faction-name"));
      thisFaction.deleteFaction();
      String url = String.format("/");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/factions/:faction-name/:card-name", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Faction thisFaction = Faction.findByName(request.params(":faction-name"));
      Card thisCard = Card.findByName(request.params(":card-name"));
      System.out.println("VCard name----"+thisCard.getCardName());
      String url = String.format("/");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
