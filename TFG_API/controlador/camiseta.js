"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.postCamiseta = exports.getCamiseta = exports.getCamisetas = void 0;
const mongoose_1 = __importDefault(require("mongoose"));
const camiseta_1 = require("../modelo/camiseta");
const getCamisetas = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    yield camiseta_1.CamisetaModel.find()
        .exec()
        .then((resultados) => {
        return res.status(200).json({
            exito: true,
            datos: resultados,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.getCamisetas = getCamisetas;
const getCamiseta = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    yield camiseta_1.CamisetaModel.findById(id)
        .exec()
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
            datos: resultado,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.getCamiseta = getCamiseta;
const postCamiseta = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { nombre, descripcion, precio, imagen } = req.body;
    const Camiseta = new camiseta_1.CamisetaModel({
        _id: new mongoose_1.default.Types.ObjectId(),
        nombre,
        descripcion,
        precio,
        imagen,
    });
    yield Camiseta
        .save()
        .then((resultado) => {
        return res.status(200).json({
            exito: true,
            datos: resultado,
        });
    })
        .catch((error) => {
        return res.status(500).json({
            exito: false,
            error,
        });
    });
});
exports.postCamiseta = postCamiseta;
