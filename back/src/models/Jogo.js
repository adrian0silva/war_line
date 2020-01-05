const mongoose = require('mongoose');

const JogoSchema = new mongoose.Schema({
    rodada: {
        type: Number,
        required: true
    }
});

mongoose.model("Jogo", JogoSchema);
