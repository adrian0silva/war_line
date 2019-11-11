const URL = 'http://localhost:9090/api';
let aberto = false;

obtemDadosDosEstados();
obtemPontosDoJogador();
obtemAtribuicoes();
atualizaRound();

function atualizaCorDosEstados(estados) {

    for (let a = 0; a < estados.length; a++) {
        let estadoAncora = document.getElementsByName("" + estados[a].nome);
        let estado = estadoAncora[0].children[0];
        let estadoValor = estadoAncora[0].children[1];

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

function abreFecha() {
    if (!aberto) {
        document.getElementById("ordens").style.width = "250px";
        document.getElementById("abreFecha").style.position = "absolute";
        document.getElementById("abreFecha").style.left = "270px";
        aberto = true;
    } else {
        document.getElementById("ordens").style.width = "0px";
        document.getElementById("abreFecha").style.position = "absolute";
        document.getElementById("abreFecha").style.left = "0px";
        aberto = false;
    }
}

$("a").click(function (evento) {
    let estadoSelecionado = evento.currentTarget.children[0].id;
    validaSeEstadoEhDoJogador(estadoSelecionado);
})

function validaSeEstadoEhDoJogador(estadoSelecionado) {
    $.get(URL + `/estado/valida-estado-jogador-uf/${estadoSelecionado}`)
        .then((estadoEhDoJogador) => {
            return estadoEhDoJogador ? processaAcoesDoJogador(estadoSelecionado) : null
        })
        .catch((error) => console.log(error));
}

function processaAcoesDoJogador(estadoSelecionado) {
    if ($("#pontos")[0].innerHTML > 0) {
        adicionarPontosNoEstado(estadoSelecionado);
    } else {
        acionarSetas(estadoSelecionado);
    }
}

function adicionarPontosNoEstado(estadoSelecionado) {
    $.ajax({
        url: URL + '/jogador/' + estadoSelecionado,
        type: 'PUT'
    })
    $.get(URL + "/atribuicoes", function (atribuicoes) {
        adicionarAtribuicoesNaOrdem(atribuicoes);
    });
}

function adicionarAtribuicoesNaOrdem(atribuicoes) {
    $("#ordens")[0].innerHTML = "";
    $("#ordens").append(`
		${atribuicoes.map((atribuicao) => `
			<div class="atribuicaoStyle">
				<a class='btn btn-success btn-little'><span class='glyphicon glyphicon-plus'></span> </a>
				<a class='btn btn-danger btn-little'><span class='glyphicon glyphicon-trash'></span> </a>
				<h1> Deploy  ${atribuicao.pontos} 
				to ${atribuicao.nomeEstado}  </h1> 
			</div>`
    ).join('')}
    `);
    obtemDadosDosEstados();
    $.get(URL + '/estado').done(function (estados) {
        atualizaCorDosEstados(estados);
    })
    obtemPontosDoJogador();
}

function limpaAgrupamentoSetasAcionados() {
    for (estado in objetos) {
        $("#agrupadorSetas_" + estado)[0].style.display = "none";
    }
}

function acionarSetas(estadoSelecionado) {
    limpaAgrupamentoSetasAcionados();
    $("#agrupadorSetas_" + estadoSelecionado)[0].style.display = "inline";
}

function obtemDadosDosEstados() {
    $.get(URL + '/estado').done(function (estados) {
        atualizaCorDosEstados(estados);
    })
}

function obtemPontosDoJogador() {
    $.get(URL + "/jogador/pontos", function (pontos) {
        atualizaPontosDoJogador(pontos);
    });
}

function obtemAtribuicoes() {

}

function atualizaPontosDoJogador(pontos) {
    $("#pontos")[0].innerHTML = pontos;
}

/****************************************************/

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

criarSetas(objetos);
function criarSetas(estados) {
    for (estado in estados) {
        var agrupadorSetas = document.createElement("div");
        agrupadorSetas.style.display = "none";
        agrupadorSetas.id = "agrupadorSetas_" + estado;
        for (adjacencia of estados[estado]) {
            var seta = document.createElement("div");
            seta.id = `${estado}`;
            seta.name = `${adjacencia}`;
            //seta.className = "setinha";
            seta.className = `setinha${estado}to${adjacencia}`;

            seta.style.position = "absolute"

            var top = $("#" + estado + "_TEXT")[0].transform.animVal[0].matrix.f;
            var left = $("#" + estado + "_TEXT")[0].transform.animVal[0].matrix.e + 161;
            seta.style.zoom = "1.3";
            seta.style.top = top + "px";
            seta.style.left = left + "px";

            seta.style.transformOrigin = "left";

            seta.innerHTML = `${estado} to ${adjacencia}`;


            seta.onclick = function () {
                criarJogadas(this.id, this.name);
                limpaSetasDoEstado(this);
                //criarJogadas(this.id, this.name);
            }

            agrupadorSetas.appendChild(seta);
            
        }
        document.body.appendChild(agrupadorSetas);
    }
}
function limpaSetasDoEstado(seta) {
    var agrupadorSetas = seta.parentNode;
    for (var a = 0; a < agrupadorSetas.childElementCount; a++) {
        agrupadorSetas.children[a].style.display = "none";
    }
    seta.style.display = "inline";
}

function criarJogadas(estadoEnvia, estadoRecebe) {
    $.get(URL + "/estado", function (estados) {
        pegarEstadosParaOModal(estados, estadoEnvia, estadoRecebe);
    })
}

function pegarEstadosParaOModal(estados, estadoEnvia, estadoRecebe) {
    for (var a = 0; a < estados.length; a++) {
        if (estados[a].uf == estadoEnvia) {
            estadoEnvia = estados[a];
        }
        if (estados[a].uf == estadoRecebe) {
            estadoRecebe = estados[a];
        }
    }
    abreModal(estadoEnvia, estadoRecebe);
}

function fechaModal() {
    $("#modal")[0].innerHTML = "";
}

function abreModal(estadoEnvia, estadoRecebe) {

    $("#modal")[0].innerHTML = "";
    $("#modal").append(
        `   <span class="glyphicon glyphicon-remove btn btn-danger" onclick="fechaModal()"></span>
        <h1>Estado Envia</h1>
        <h2>${estadoEnvia.nome}</h2>
        <h2>${estadoEnvia.valor}</h2>

        <h1>Estado Recebe</h1>
        <h2>${estadoRecebe.nome}</h2>
        <h2>${estadoRecebe.valor}</h2>

        <label for="valor">Valor</label>
        <input id="valor" type="number" name="valor" min="1" max="${estadoEnvia.valor}" required="true" />
        <input type="submit" onclick="adicionarJogadas(${estadoEnvia.id},${estadoRecebe.id},$('#valor')[0].value)" />
    `
    );
}

function adicionarJogadas(estadoEnviaId, estadoRecebeId, valor) {

    let jogada = {
        estadoEnvia: {
            id: estadoEnviaId
        },
        estadoRecebe: {
            id: estadoRecebeId
        },
        valor: parseInt(valor)
    }


    $.ajax({
        type: "POST",
        url: URL + "/jogador/1/jogadas",
        data: JSON.stringify(jogada),
        contentType: "application/json",
        dataType: 'json'
    })

    obtemDadosDosEstados();
    fechaModal();

}


function commitar() {
    var pontosDoJogador = $("#pontos")[0].innerHTML;

    if(pontosDoJogador > 0) {
        alert("Voce deve distribuir seus pontos!");
        return;
    }

    $.ajax({
        type: "POST",
        url: URL + "/jogador",
        contentType: "application/json",
        dataType: 'json'
    }).done((objRetorno) => animaSeta(objRetorno))

}

function animaSeta(jogada) {
    
    congelaTempo(jogada,0);
    limparAnimacoes(jogada);

    obtemDadosDosEstados();
    obtemPontosDoJogador();
    atualizaRound();
    //var setaParaAnimar = $(`.setinhaRJtoSP`);
}

function limparAnimacoes(jogada) {
    for(var a = 0;a < jogada.length;a++ ) {
        var setaParaAnimar = $(`.setinha${jogada[a].estadoEnviaNomeSigla}to${jogada[a].estadoRecebeNomeSigla}`);
        setaParaAnimar[0].style.animation = "";
    }
}

function congelaTempo(jogada,jogadaIndice) {
    console.log(jogada);
    console.log(jogadaIndice);
    if(jogadaIndice == (jogada.length - 1)) {
        console.log("EH PRA PARAR");
        return;
    }
   
    var setaParaAnimar = $(`.setinha${jogada[jogadaIndice].estadoEnviaNomeSigla}to${jogada[jogadaIndice].estadoRecebeNomeSigla}`);

    setTimeout((algo) => {
        setaParaAnimar[0].style.animation = "moverSeta"+jogada[jogadaIndice].estadoEnviaNomeSigla+"to"+jogada[jogadaIndice].estadoRecebeNomeSigla+" 3s";
        console.log(algo);
        jogadaIndice = jogadaIndice + 1;
        congelaTempo(jogada,jogadaIndice);
    }, 2000);
}

$(".sul-valor").hover(function() {
    $(".sul")[0].style.stroke = "green";
    $(".sul")[0].style.strokeWidth = "4";
    $(".sul")[1].style.stroke = "green";
    $(".sul")[1].style.strokeWidth = "4";
    $(".sul")[2].style.stroke = "green";
    $(".sul")[2].style.strokeWidth = "4";
},function() {
    $(".sul")[0].style.stroke = "green";
    $(".sul")[0].style.strokeWidth = "2";
    $(".sul")[1].style.stroke = "green";
    $(".sul")[1].style.strokeWidth = "2";
    $(".sul")[2].style.stroke = "green";
    $(".sul")[2].style.strokeWidth = "2";
})


$(".sudeste-valor").hover(function() {
    $(".sudeste")[0].style.stroke = "orange";
    $(".sudeste")[0].style.strokeWidth = "4";
    $(".sudeste")[1].style.stroke = "orange";
    $(".sudeste")[1].style.strokeWidth = "4";
    $(".sudeste")[2].style.stroke = "orange";
    $(".sudeste")[2].style.strokeWidth = "4";
    $(".sudeste")[3].style.stroke = "orange";
    $(".sudeste")[3].style.strokeWidth = "4";
},function() {
    $(".sudeste")[0].style.stroke = "orange";
    $(".sudeste")[0].style.strokeWidth = "2";
    $(".sudeste")[1].style.stroke = "orange";
    $(".sudeste")[1].style.strokeWidth = "2";
    $(".sudeste")[2].style.stroke = "orange";
    $(".sudeste")[2].style.strokeWidth = "2";
    $(".sudeste")[3].style.stroke = "orange";
    $(".sudeste")[3].style.strokeWidth = "4";
})

function atualizaRound() {
    $.get(URL+"/jogo",function(rodada) {
        $("#rodada")[0].innerHTML = rodada;
    })
}