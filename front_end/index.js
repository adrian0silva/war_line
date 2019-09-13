var paraiba = document.getElementsByName("Paraíba");

paraiba[0].children[0].style.fill = "yellow";

var resposta = $.get("http://localhost:8080/api/estado").done(function (data) {
    atualizarEstado(data);
});

//var estado = $("#" + resposta.responseJSON);

function atualizarEstado(data) {

    for (let a = 0; a < (data.length - 1); a++) {

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


