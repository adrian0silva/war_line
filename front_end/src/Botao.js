import React, { Component } from 'react';

const RenderizaBotoes = props => {

    console.log(props);

    const territorios = props.territorios.map((territorio, index) => {
        return (
            <div className="col s4" key={index}>
                <button >{territorio.valor}</button>
            </div>
        )
    });

    return (
        <div className="row">
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