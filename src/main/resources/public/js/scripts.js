$(function() {
  $(".updateCard").click(function(){
    var factionName = $("#factionTitle").text();
    $(this).before("<form action='/factions/"+factionName+"/"+this.id+"'"+
    " method='post'>"+
    "<button type='submit' class='btn'>Update Card</button>"
    +"</form>");
  })
})
