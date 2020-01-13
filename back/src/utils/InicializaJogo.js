const jogo = require('../controllers/JogoController');
jogo.create(null, { "rodada": 1 });

const territorio = require('../controllers/TerritorioController');

territorio.create({"nome":"brazil","poder":1});

module.exports = jogo, territorio;