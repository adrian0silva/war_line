const mongoose = require('mongoose');

const Territorio = mongoose.model("Territorio");

module.exports = {
    async index(req, res) {
        const territorios = await Territorio.find();
        return res.json(territorios);
    },
    async create(req, res) {
        console.log("O que tem na req?");
        
        console.log(req);
        
        console.log("O qutem no corpo?");
        console.log(req.body);
        

        const territorio = await Territorio.create(req);
        return res.json(territorio);
    }
}