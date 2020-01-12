import React, { Component } from 'react';

const RenderizaBotoes = props => {

    console.log(props);

    const territorios = props.territorios.map((territorio, index) => {
        return (
            <div className="col s3 bloco" key={index}>
                <button 
                    disabled={(territorio.dono !== "jogador")}
                    className="btnTerritorio"
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
        const { territorios } = this.props;

        return (
            <RenderizaBotoes territorios={territorios} />
        )
    }
}

export default Botao;