
var resposta = $.get("http://localhost:9090/api/estado").done(function (data) {
    atualizarEstado(data);
    
});

var response = $.get("http://localhost:9090/api/jogador").done(function (data){
    $("#pontos")[0].innerHTML = data[0].pontos
});

//var estado = $("#" + resposta.responseJSON);

function atualizaJogador(data) {
    
    $("#pontos")[0].innerHTML = data[0].pontos;
}

function atualizarEstado(data) {

    for (let a = 0; a < 3; a++) {

        estadoAncora = document.getElementsByName(""+data[a].nome);
        estado = estadoAncora[0].children[0];
        estadoValor = estadoAncora[0].children[1];
        estadoValor.innerHTML = data[a].valor;

        if (data[a].jogador == "adriano") {
            estado.style.fill = "blue";
        } else if (data[a].jogador == "computador") {
            estado.style.fill = "red";
        } else {
            estado.style.fill = "gray";
        }
    }

    //var estado = $("#"+data.uf)[0];

    /*  if(data.jogador == "adriano"){
          estado.style.fill = "red";
      } else if(data.jogador == "computador") {
          estado.style.fill = "red";
      } else {
          estado.style.fill = "gray";
      }*/
}
var e;
var ordem;
var ordemData;
var array;

$("a").click(function(evento){
    adicionarPontos(evento.currentTarget);
});

function adicionarPontos(target) {
    $.ajax({
        url: 'http://localhost:9090/api/jogador/'+target.children[0].id,
        type: 'PUT',
        success: function(result) {
            
        }
    }).then($.get('http://localhost:9090/api/atribuicao',function(data){
        ordem = $("#ordens");
        ordemData = data;
        $('#ordens')[0].innerHTML = "";
        for(var a = 0;a < data.length;a++){
            $("#ordens").append(`<h1> 
            <div class='btn btn-success btn-little '><span class='glyphicon glyphicon-plus'></span> </div>
            <div class='btn btn-danger btn-little'><span class='glyphicon glyphicon-trash'></span> </div>
            Deploy `+data[a].pontos+
            ` to `+ data[a].nomeEstado +`  </h1>`);
        }
        /*; */
    }));

    $.get("http://localhost:9090/api/jogador").then(function(data){atualizaJogador(data)});
  
}

