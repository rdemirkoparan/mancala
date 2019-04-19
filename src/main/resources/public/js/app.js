function startNewGame() {
    for(i=1;i<15;i++){
        for(j=1;j<3;j++){
          $("#Pit"+j+"_" + i).empty();
          $("#Pit"+j+"_" + i).append(6);
        }
    }

    for(i=1;i<3;i++){
        for(j=7;j<15;j+=7){
          $("#Pit"+i+"_"+j).empty();
          $("#Pit"+i+"_"+j).append(0);
        }
    }

    $("#nextPlayer").empty();
    $("#nextPlayer").append(1);
}

function move(pit,player,otherplayer) {
    if(player != $("#nextPlayer").text()){
        return;
    }
    var pits = document.getElementById("Pit" + player + "_1").innerHTML;
    for(i=2;i<15;i++){
        pits = pits + "," + document.getElementById("Pit" + player + "_" + i).innerHTML;
    }

    var data = {
        "current":pit.id,
        "player":player,
        "pits":pits.split(",")
    }

    $.ajax({
        async: false,
        url: 'move',
        type: 'POST',
        data: JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            $("#nextPlayer").empty();
            $("#nextPlayer").append(data.nextPlayer);

            var otherplayerindex = 7;
            $.each(data.pits, function (index, value) {
                $("#Pit" + player + "_" + (index + 1)).empty();
                $("#Pit" + player + "_" + (index + 1)).append(value);
                $("#Pit" + otherplayer + "_" + (otherplayerindex + 1)).empty();
                $("#Pit" + otherplayer + "_" + (otherplayerindex + 1)).append(value);

                if(otherplayerindex == 13){
                    otherplayerindex = 0;
                } else {
                    otherplayerindex++;
                }
            });

            if(data.winner){
                alert("Player" + data.winner + " wins!");
            }
        }
    });
}