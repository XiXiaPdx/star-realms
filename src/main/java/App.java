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

    get("/:faction-name", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Faction thisFaction = Faction.findByName(request.params(":faction-name"));
      System.out.println(thisFaction.getFactionName());
      model.put("faction", thisFaction);
      model.put("cards", thisFaction.getCardsOfFaction());
      model.put("template", "templates/faction.vtl");
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
      String url = String.format("/"+thisFaction.getFactionName());
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

  }
}
