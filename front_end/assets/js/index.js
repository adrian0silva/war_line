
const URL = 'http://localhost:9090/api';

function atualizaTela() {

	$("#modal").hide();

	$.get(URL+'/jogador').done(function(jogadores){
		atualizarJogadores(jogadores);	
	});

	$.get(URL+'/estado').done(function(estados){
		atualizarEstados(estados);
	})

	$.get(URL+'/atribuicao',function(atribuicoes) {
		atualizarAtribuicoes(atribuicoes);
	})

	$.get(URL+'/jogador/1/jogadas',function(jogadas){
		atualizarJogadas(jogadas);
	})
	/*atualizarAtribuicoes(atribuicoes);
	atualizarJogadas(jogadas);
	*/
}

function atualizarJogadores(jogadores) {

	console.log(jogadores);

	$('#pontos')[0].innerHTML = jogadores[0].pontos;

}

function atualizarEstados(estados) {
	console.log(estados);

	for(let a = 0;a < estados.length;a++) {
		estadoAncora = document.getElementsByName(""+estados[a].nome);
	 	estado = estadoAncora[0].children[0];
		estadoValor = estadoAncora[0].children[1];
		estadoValor.innerHTML = estados[a].valor;
		
		if(estados[a].jogador == "adriano") {
			estado.style.fill = "blue";
		} else if(estados[a].jogador == "computador") {
			estado.style.fill = "red";
		} else {
			estado.style.fill = "gray";
		}
	}

}

function atualizarAtribuicoes(atribuicoes) {
	console.log(atribuicoes);
	ordemAtribuicoes = $("#ordens");
	ordemData = atribuicoes;
	 $('#ordens')[0].innerHTML = "";
        for(var a = 0;a < atribuicoes.length;a++){
            $("#ordens").append(`<h1> 
            <div class='btn btn-success btn-little '><span class='glyphicon glyphicon-plus'></span> </div>
            <div class='btn btn-danger btn-little'><span class='glyphicon glyphicon-trash'></span> </div>
            Deploy `+atribuicoes[a].pontos+
            ` to `+ atribuicoes[a].nomeEstado +`  </h1>`);
        }
}

function atualizarJogadas(jogadas) {
	console.log(jogadas);
	ordemJogadas = $("#ordens");
	ordemData = jogadas;
	 $('#ordens')[0].innerHTML = "";
     for(var a = 0;a < jogadas.length;a++){
            $("#ordens").append(`<h1> 
            <div class='btn btn-success btn-little '><span class='glyphicon glyphicon-plus'></span> </div>
            <div class='btn btn-danger btn-little'><span class='glyphicon glyphicon-trash'></span> </div>
            `+jogadas[a].estadoEnviaNome+` attack/transfer` + jogadas[a].valor+ 
            ` to `+ jogadas[a].estadoRecebeNome +`  </h1>`);
        }	
}


$('a').click(function (evento) {
	let pontosDoJogador = parseInt($("#pontos")[0].innerHTML);
	if(pontosDoJogador > 0) {
		adicionarPontos(evento.currentTarget.children[0].id);
		return;
	}


	$.get(URL+"/estado/busca-pelo-uf/"+evento.currentTarget.children[0].id,function(estado){
		$("#estadoEnviaNome")[0].innerHTML = estado.nome;
		$("#estadoEnviaValor")[0].innerHTML = estado.valor;


		$("#estadoRecebeNome")[0].innerHTML = "santa catarina";
		$("#estadoRecebeValor")[0].innerHTML = "12";


		$("#modal").show();
	});
});

function adicionarPontos(estado) {
	console.log(estado);
	$.ajax({
		url: URL+'/jogador/'+estado,
		type:'PUT'
	})

	atualizaTela();
}


function adicionarJogadas(estadoEnvia,estadoRecebe,valor){
	
	let jogada = {
		estadoEnvia: {
			id: 3
		},
		estadoRecebe: {
			id: 2
		},
		valor: parseInt(valor)
	}

	$.ajax({
		type:"POST",
		url: URL+"/jogador/1/jogadas",
		data: JSON.stringify(jogada),
		contentType: "application/json",
		dataType: 'json'
	})

	atualizaTela();
}

var objetos = {
	"pr":{
		"top":356,
		"left":248
	}
}
