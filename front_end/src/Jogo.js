import React, { Component } from 'react';
import 'materialize-css/dist/css/materialize.min.css';
import Botao from './Botao';
import './App.css';

class Jogo extends Component {

  state = {
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

  render() {
    return (
      <div className="container">
        <Botao territorios={this.state.territorios} />
      </div>
    )
  }
}

export default Jogo;
