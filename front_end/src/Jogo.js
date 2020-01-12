import React, { Component } from 'react';
import 'materialize-css/dist/css/materialize.min.css';
import Botao from './Botao';
import './App.css';

class Jogo extends Component {

  state = {
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

  acao = (event,index) => {

    console.log(this.state);
    
    
    if(this.state.meusPontos > 0) {
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
        if(posAtual === index) {
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

  render() {
    return (
      <div className="container">
        <Botao territorios={this.state.territorios} acao={this.acao}/>
      </div>
    )
  }
}

export default Jogo;
