const express = require('express');
const routes = express.Router();

const JogoController = require('./controllers/JogoController');

routes.get('/jogo', JogoController.index);

routes.post('/jogo', JogoController.create);

module.exports = routes;