var e;
var ordem;
var ordemData;
var array;

function adicionarJogada(event ,envia, recebe, valor){
    event.preventDefault();
    let jogada = {
        estadoEnvia:{
            id: parseInt(envia)
        },
        estadoRecebe: {
            id: parseInt(recebe)
        },
        valor: parseInt(valor)
    }
    var jogadaNova = JSON.stringify(jogada);  
    
    console.log(jogadaNova);

    $.ajax({
        type: 'POST',
        url: 'http://localhost:9090/api/jogador/1/jogadas',
        data: jogadaNova,
        success: function(data) { alert('data: ' + data); },
        contentType: "application/json",
        dataType: 'json'
    }).then($.get("http://localhost:9090/api/jogador/1/jogadas",function(data){
        ordem = $("#ordens");
        ordemData = data;
        $('#ordens')[0].innerHTML = "";
        for(var a = 0;a < data.length;a++){
            $("#ordens").append(`<h1> 
            <div class='btn btn-success btn-little '><span class='glyphicon glyphicon-plus'></span> </div>
            <div class='btn btn-danger btn-little'><span class='glyphicon glyphicon-trash'></span> </div>
            `+data[a].estadoEnviaNome+` attack/transfer` + data[a].valor+ 
            ` to `+ data[a].estadoRecebeNome +`  </h1>`);
        }
    }));

}
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

    for (let a = 0; a < 4; a++) {

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

function commitar() {
    // chamar metodo que randomiza computador

    $.post("http://localhost:9090/api/jogador");
}

