const mongoose = require('mongoose');

const Jogo = mongoose.model('Jogo');

module.exports = {
    async index(req, res) {
        const jogos = await Jogo.find();

        return res.json(jogos);
    },

    create() {
        Jogo.create({"rodada": 1});
    }
}