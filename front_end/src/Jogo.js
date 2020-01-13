import React, { Component } from 'react';
import 'materialize-css/dist/css/materialize.min.css';
import Botao from './Botao';
import './App.css';

class Jogo extends Component {

  constructor(props) {
    super(props);
  }

  state = {
    rodada: 96959,
    meusPontos: 5,
    territorios: [
      {
        nome: "aston",
        valor: 20,
        dono: "computador"
      },
      {
        nome: "villa",
        valor: 5,
        dono: "jogador"
      },
      {
        nome: "arsenal",
        valor: 23,
        dono: "neutro"
      }
    ]
  }

  acao = (event, index) => {

    if (this.state.meusPontos > 0) {
      return this.adicionarPontos(index);
    }
    return this.transferirPontos(index);
  }

  adicionarPontos = index => {
    const { meusPontos } = this.state;

    this.setState({
      meusPontos: meusPontos - 1
    })

    this.setState({
      territorios: this.state.territorios.filter((territorio, posAtual) => {
        if (posAtual === index) {
          territorio.valor++;
        }
        return territorio;
      })
    })
  }

  transferirPontos = index => {
    console.log("Aciona setas!");

    console.log("Abre modal!");

  }

  async obtemRodada() {
    const resposta = await fetch('http://localhost:3001/api/jogo');
    const dados = await resposta.json();
    this.setState({ 
      rodada: dados[0].rodada
     });

  }

  componentWillMount() {
    this.obtemRodada();
  }

  render() {
    return (
      <div>
        <h5>Rodada atual: {this.state.rodada}</h5>
        <div className="container">

          <Botao territorios={this.state.territorios} acao={this.acao} />
        </div>
      </div>
    )
  }
}

export default Jogo;
