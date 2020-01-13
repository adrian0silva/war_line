const express = require('express');
const mongoose = require('mongoose');
const requireDir = require('require-dir');
const cors = require('cors');

const app = express();
app.use(express.json());
app.use(cors());

mongoose.connect('mongodb://127.0.0.1:27017/warline', (err, db) => {

    if (err) throw err;

    console.log("Connected to database!");

    db.dropDatabase((err, result) => {
        if (err) throw err;
    })
});
requireDir('./src/models');

app.use("/api", require('./src/routes'));

app.listen(3001);

require('./src/utils/InicializaJogo');