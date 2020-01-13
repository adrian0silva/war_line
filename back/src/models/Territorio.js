const mongoose = require('mongoose');

const TerritorioSchema = new mongoose.Schema({
    nome: {
        type: String,
        required: true
    },
    poder: {
        type: Number,
        required: true
    }
});

mongoose.model("Territorio", TerritorioSchema);