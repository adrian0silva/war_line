import React, { Component } from 'react';

const RenderizaBotoes = props => {

    console.log(props);

    const territorios = props.territorios.map((territorio, index) => {
        const resultado = () => {
            if(territorio.dono === "jogador") {
                return "jogador"
            } else if(territorio.dono === "computador") {
                return "inimigo"
            } 
            return "neutro";
        };
        return (
            <div className="col s3 bloco" key={index}>
                <button 
                    disabled={(territorio.dono !== "jogador")}
                    className={"btnTerritorio " + resultado()}
                    onClick={(event) => props.acao(event,index)}
                    >{territorio.valor}</button>
            </div>
        )
    });

    return (
        <div className="row meio">
            {territorios}
        </ div>
    )
}

class Botao extends Component {

    render() {
        const { territorios, acao } = this.props;

        return (
            <RenderizaBotoes territorios={territorios} acao={acao}/>
        )
    }
}

export default Botao;