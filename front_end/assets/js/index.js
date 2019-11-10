const URL = 'http://localhost:9090/api';

function atualizaTela() {


	$.get(URL + '/jogador').done(function (jogadores) {
		atualizarJogadores(jogadores);
	});

	$.get(URL + '/estado').done(function (estados) {
		atualizarEstados(estados);
	})

	$.get(URL + '/atribuicao', function (atribuicoes) {
		atualizarAtribuicoes(atribuicoes);
	})

	$.get(URL + '/jogador/1/jogadas', function (jogadas) {
		atualizarJogadas(jogadas);
	})
	/*atualizarAtribuicoes(atribuicoes);
	atualizarJogadas(jogadas);
	*/
}

function atualizarJogadores(jogadores) {


	$('#pontos')[0].innerHTML = jogadores[0].pontos;

}

function atualizarEstados(estados) {

	for (let a = 0; a < estados.length; a++) {


		estadoAncora = document.getElementsByName("" + estados[a].nome);
		estado = estadoAncora[0].children[0];
		estadoValor = estadoAncora[0].children[1];

		estadoValor.innerHTML = estados[a].valor;

		if (estados[a].jogador == "player") {
			estado.style.fill = "blue";
		} else if (estados[a].jogador == "computador") {
			estado.style.fill = "red";
		} else {
			estado.style.fill = "gray";
		}
	}

}
var aberto = true;
function abreFecha() {
	if (aberto) {
		document.getElementById("ordens").style.width = "0px";

		document.getElementById("abreFecha").style.position = "absolute";

		document.getElementById("abreFecha").style.left = "0px";
		aberto = false;
	} else {
		document.getElementById("ordens").style.width = "250px";

		document.getElementById("abreFecha").style.position = "absolute";

		document.getElementById("abreFecha").style.left = "270px";

		aberto = true;
	}
}

var listaDeAtribuicoes = [];

function atualizarAtribuicoes(atribuicoes) {
	ordemAtribuicoes = $("#ordens");
	listaDeAtribuicoes = atribuicoes;
	console.log(atribuicoes);


	$("#ordens").append(`
		${listaDeAtribuicoes.map((atribuicao) =>  `
			<div class="atribuicaoStyle">
				<a class='btn btn-success btn-little'><span class='glyphicon glyphicon-plus'></span> </a>
				<a class='btn btn-danger btn-little'><span class='glyphicon glyphicon-trash'></span> </a>
				<h1> Deploy  ${atribuicao.pontos} 
				to ${atribuicao.nomeEstado}  </h1> 
			</div>`
		).join('')}
		
	`)
	/*
	for (var a = 0; a < atribuicoes.length; a++) {
		$("#ordens").append(`<h1> 
            <div class='btn btn-success btn-little '><span class='glyphicon glyphicon-plus'></span> </div>
            <div class='btn btn-danger btn-little'><span class='glyphicon glyphicon-trash'></span> </div>
            Deploy `+ atribuicoes[a].pontos +
			` to ` + atribuicoes[a].nomeEstado + `  </h1>`);
	}*/
}

function atualizarJogadas(jogadas) {
	ordemJogadas = $("#ordens");
	ordemData = jogadas;
	$('#ordens')[0].innerHTML = "";
	for (var a = 0; a < jogadas.length; a++) {
		$("#ordens").append(`<h1> 
            <div class='btn btn-success btn-little '><span class='glyphicon glyphicon-plus'></span> </div>
            <div class='btn btn-danger btn-little'><span class='glyphicon glyphicon-trash'></span> </div>
            `+ jogadas[a].estadoEnviaNome + ` attack/transfer` + jogadas[a].valor +
			` to ` + jogadas[a].estadoRecebeNome + `  </h1>`);
	}
}

var grupo;
$('a').click(function (evento) {
	let pontosDoJogador = parseInt($("#pontos")[0].innerHTML);
	if (pontosDoJogador > 0) {
		adicionarPontos(evento.currentTarget.children[0].id);
		return;
	}

	limpaAgrupamentoSetasAcionados();
	acionaSetas(evento.currentTarget.children[0].id);
});

function acionaSetas(sigla) {

	$("#agrupadorSetas_" + sigla)[0].style.display = "inline";
	for (var a = 0; a < $("#agrupadorSetas_" + sigla)[0].children.length; a++) {
		$("#agrupadorSetas_" + sigla)[0].children[a].style.display = "inline";
	}
}


function limpaAgrupamentoSetasAcionados() {
	for (estado in objetos) {

		$("#agrupadorSetas_" + estado)[0].style.display = "none";
	}
}


function adicionarPontos(estado) {

	$.ajax({
		url: URL + '/jogador/' + estado,
		type: 'PUT'
	})

	atualizaTela();
}

var agrup;

function limpaSetasDoEstado(agrupamento, setaEncontrada) {
	agrupamento.style.display = "inline";

	for (var a = 0; a < agrupamento.children.length; a++) {
		agrupamento.children[a].style.display = "none";
	}
	setaEncontrada.style.display = "inline";
}

var set;
function adicionarJogadas(estadoEnvia, estadoRecebe, valor) {

	setaEncontrada = $(`div:contains(${estadoEnvia[0].id} to ${estadoRecebe[0].id})`);
	set = setaEncontrada;
	limpaSetasDoEstado(setaEncontrada[0], setaEncontrada[1]);
	/*
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
			url:"/jogador/1/jogadas",
			data: JSON.stringify(jogada),
			contentType: "application/json",
			dataType: 'json'
		})
	*/
	atualizaTela();
}


function criarJogadas(estadoEnvia, estadoRecebe) {
	$("#modal").append(
		`<h1>Estado Envia</h1>
    <h2>${estadoEnvia}</h2>
    <h2>${10}</h2>

    <h1>Estado Recebe</h1>
    <h2>${estadoRecebe}</h2>
    <h2>${60}</h2>

    <label for="valor">Valor</label>
    <input type="number" name="valor">
    <button onclick="adicionarJogadas(${estadoEnvia},${estadoRecebe},$('#valor').val())">Enviar</button>
  `)

	/*
	let jogada = {
		estadoEnvia: {
			id: 3
		},
		estadoRecebe: {
			id: 2
		},
		valor: parseInt(valor)
	}*/
	/*
		$.ajax({
			type:"POST",
			url:"/jogador/1/jogadas",
			data: JSON.stringify(jogada),
			contentType: "application/json",
			dataType: 'json'
		})
	
		atualizaTela();*/
}

var objetos = {
	// 1
	"AC": [
		"AM", "RO"
	],
	// 2
	"AL": [
		"PE", "SE", "BA"
	],
	// 3
	"AP": [
		"PA"
	],
	// 4
	"AM": [
		"PA", "MT", "RO", "RR", "AC"
	],
	// 5
	"BA": [
		"MG", "ES", "GO", "TO", "PI", "PE", "AL", "SE"
	],
	// 6
	"CE": [
		"RN", "PB", "PE", "PI"
	],
	// 7
	"DF": [
		"GO", "MG"
	],
	// 8
	"ES": [
		"BA", "MG", "RJ"
	],
	// 9
	"GO": [
		"MS", "MT", "TO", "BA", "MG", "DF"
	],
	// 10
	"MA": [
		"PI", "TO", "PA"
	],
	// 11
	"MT": [
		"AM", "PA", "TO", "GO", "MS", "RO"
	],
	// 12
	"MS": [
		"MT", "GO", "MG", "SP", "PR"
	],
	// 13
	"MG": [
		"SP", "RJ", "ES", "GO", "BA", "DF", "MS"
	],
	// 14
	"PA": [
		"AP", "RR", "AM", "MS", "TO", "MA"
	],
	// 15
	"PB": [
		"RN", "PE", "CE"
	],
	// 16
	"PR": [
		"MS", "SC", "SP"
	],
	// 17
	"PE": [
		"PB", "CE", "AL", "BA", "PI"
	],
	// 18
	"PI": [
		"PE", "CE", "TO", "BA", "MA"
	],
	// 19
	"RJ": [
		"MG", "ES", "SP"
	],
	// 20
	"RN": [
		"PB", "CE"
	],
	// 21
	"RS": [
		"SC"
	],
	// 22
	"RO": [
		"MT", "AM", "AC"
	],
	// 23
	"RR": [
		"PA", "AM"
	],
	// 24
	"SC": [
		"PR", "RS"
	],
	// 25
	"SP": [
		"MG", "PR", "RJ", "MS"
	],
	// 26
	"SE": [
		"BA", "AL"
	],
	// 27
	"TO": [
		"GO", "MT", "PA", "MA", "PI", "BA"
	]
}

var csseta;
function criarSetas(estados) {
	var indice = 0;
	var rotacao;
	for (estado in estados) {
		var agrupadorSetas = document.createElement("div");
		agrupadorSetas.style.display = "none";
		agrupadorSetas.id = "agrupadorSetas_" + estado;
		
		for (adjacencia of estados[estado]) {
			var seta = document.createElement("div");
			seta.id = `${estado}`;
			seta.name = `${adjacencia}`;
			seta.className = "setinha";
			seta.className = `setinha${estado}to${adjacencia}`;
			var altura = $(".setinha").css("height");

			var largura = $(".setinha").css("width");

			seta.style.position = "absolute"

			console.log(estado);

			var	top = $("#"+estado+"_TEXT")[0].transform.animVal[0].matrix.f - 20;
			var	left = $("#"+estado+"_TEXT")[0].transform.animVal[0].matrix.e; 
			
			seta.style.top = top + "px";
			seta.style.left = left + "px";



			const e = parseInt($("#"+adjacencia+"_TEXT")[0].transform.animVal[0].matrix.e);
			const f = parseInt($("#"+adjacencia+"_TEXT")[0].transform.animVal[0].matrix.f);


			calculaSeta();
			
			console.log(rotacao);


			seta.style.transform = `rotate(${rotacao}deg)`;

			seta.style.transformOrigin = "left";
			console.log("Origin " + seta.style.transformOrigin);



			/*seta.style.background = "yellow";
			seta.style.width = "50px";
			seta.style.height = "10px";
			seta.style.color = "white";
			*/
			seta.innerHTML = `${estado} to ${adjacencia}`;

			seta.onclick = function () {
				//this.className = "setinha animated bounceOutRight";
				criarJogadas(this.id, this.name);

			}
			agrupadorSetas.appendChild(seta);
			
		}
		indice++;
		document.body.appendChild(agrupadorSetas);
	}
}

function calculaSeta() {

}


criarSetas(objetos);
