
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

function atualizarAtribuicoes(atribuicoes) {
	ordemAtribuicoes = $("#ordens");
	ordemData = atribuicoes;
	console.log(atribuicoes);


	$("#ordens").append(`
		${atribuicoes.map((atribuicao) =>  `
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
	"AC": [
		"AM", "RO"
	],
	"AL": [
		"PE", "SE", "BA"
	],
	"AP": [
		"PA"
	],
	"AM": [
		"PA", "MT", "RO", "RR", "AC"
	],
	"BA": [
		"MG", "ES", "GO", "TO", "PI", "PE", "AL", "SE"
	],
	"CE": [
		"RN", "PB", "PE", "PI"
	],
	"DF": [
		"GO", "MG"
	],
	"ES": [
		"BA", "MG", "RJ"
	],
	"GO": [
		"MS", "MT", "TO", "BA", "MG", "DF"
	],
	"MA": [
		"PI", "TO", "PA"
	],
	"MT": [
		"AM", "PA", "TO", "GO", "MS", "RO"
	],
	"MS": [
		"MT", "GO", "MG", "SP", "PR"
	],
	"MG": [
		"SP", "RJ", "ES", "GO", "BA", "DF", "MS"
	],
	"PA": [
		"AP", "RR", "AM", "MS", "TO", "MA"
	],
	"PB": [
		"RN", "PE", "CE"
	],
	"PR": [
		"MS", "SC", "SP"
	],
	"PE": [
		"PB", "CE", "AL", "BA", "PI"
	],
	"PI": [
		"PE", "CE", "TO", "BA", "MA"
	],
	"RJ": [
		"MG", "ES", "SP"
	],
	"RN": [
		"PB", "CE"
	],
	"RS": [
		"SC"
	],
	"RO": [
		"MT", "AM", "AC"
	],
	"RR": [
		"PA", "AM"
	],
	"SC": [
		"PR", "RS"
	],
	"SP": [
		"MG", "PR", "RJ", "MS"
	],
	"SE": [
		"BA", "AL"
	],
	"TO": [
		"GO", "MT", "PA", "MA", "PI", "BA"
	]
}

var csseta;
function criarSetas(estados) {
	var indice = 0;
	for (estado in estados) {
		var agrupadorSetas = document.createElement("div");
		agrupadorSetas.style.display = "none";
		agrupadorSetas.id = "agrupadorSetas_" + estado;
		var rotacao = 0;
		for (adjacencia of estados[estado]) {
			var seta = document.createElement("div");
			seta.id = `${estado}`;
			seta.name = `${adjacencia}`;
			seta.className = "setinha";
			var altura = $(".setinha").css("height");

			var largura = $(".setinha").css("width");

			seta.style.position = "absolute"

			seta.style.top = `calc(${$("text")[indice].transform.animVal[0].matrix.f}px - ${(typeof altura == "string") ? altura : "1px"})`;
			seta.style.left = `calc(${$("text")[indice].transform.animVal[0].matrix.e}px - ${(typeof largura == "string") ? largura : "1px"})`;

			seta.style.transform = `rotate(${rotacao}deg)`;

			/*seta.style.background = "yellow";
			seta.style.width = "50px";
			seta.style.height = "10px";
			seta.style.color = "white";
			*/
			seta.innerHTML = `${estado} to ${adjacencia}`;

			seta.onclick = function () {
				this.className = "setinha animated bounceOutRight";
				criarJogadas(this.id, this.name);

			}
			agrupadorSetas.appendChild(seta);
			rotacao = rotacao + 60;
		}
		indice++;
		document.body.appendChild(agrupadorSetas);
	}
}

criarSetas(objetos);
