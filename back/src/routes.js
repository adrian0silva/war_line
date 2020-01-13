const express = require('express');
const routes = express.Router();

const JogoController = require('./controllers/JogoController');

routes.get('/jogo', JogoController.index);

const TerritorioController = require('./controllers/TerritorioController');

routes.get('/territorios', TerritorioController.index);
routes.post('/territorios', TerritorioController.create);

module.exports = routes;