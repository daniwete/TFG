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
exports.deleteUsuarios = exports.putShopping = exports.putUsuarios = exports.postUsuarios = exports.authUsuario = exports.getUsuario = exports.getUsuarios = void 0;
const mongoose_1 = __importDefault(require("mongoose"));
const usuario_1 = require("../modelo/usuario");
//No permite ver la contraseña en la base de datos
const bcrypt_1 = __importDefault(require("bcrypt"));
const getUsuarios = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    //esperar a la funcion
    yield usuario_1.UsuarioModel.find()
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
exports.getUsuarios = getUsuarios;
const getUsuario = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    yield usuario_1.UsuarioModel.findById(id)
        .populate("camisetas")
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
exports.getUsuario = getUsuario;
const authUsuario = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { correo, contraseña } = req.body;
    console.log("este es el cuerpo =" + req.body.toString());
    if (contraseña) {
        yield usuario_1.UsuarioModel.findOne({
            correo: correo,
        })
            .populate("camisetas")
            .exec()
            .then((resultado) => {
            if (bcrypt_1.default.compareSync(contraseña, resultado.contraseña.toString())) {
                console.log("Correcto " + resultado);
                return res.json({
                    exito: true,
                    datos: resultado,
                });
            }
            else {
                console.log("Incorrecto " + resultado);
                return res.status(500).json({
                    exito: false,
                    error: "Contraseña incorrecta",
                });
            }
        })
            .catch((error) => {
            console.log("Error: " + error);
            return res.status(500).json({
                exito: false,
                error,
            });
        });
    }
});
exports.authUsuario = authUsuario;
const postUsuarios = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { nombre, apellidos, correo, contraseña, } = req.body;
    let hashContraseña = bcrypt_1.default.hashSync(contraseña, 10);
    console.log(req.body);
    const nuevoUsuario = new usuario_1.UsuarioModel({
        _id: new mongoose_1.default.Types.ObjectId(),
        nombre,
        apellidos,
        correo,
        contraseña: hashContraseña,
        camisetas: Array()
    });
    yield nuevoUsuario
        .save()
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
exports.postUsuarios = postUsuarios;
const putUsuarios = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    const { nombre, apellidos, correo, contraseña } = req.body;
    // Verificaciones de atributos nulos
    const object = {};
    if (nombre !== "" && nombre !== undefined) {
        object.nombre = nombre;
    }
    if (apellidos !== "" && apellidos !== undefined) {
        object.apellidos = apellidos;
    }
    if (correo !== "" && correo !== undefined) {
        object.correo = correo;
    }
    let hashContraseña = bcrypt_1.default.hashSync(contraseña, 10);
    object.contraseña = hashContraseña;
    yield usuario_1.UsuarioModel.findOneAndUpdate({ _id: id }, object)
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
exports.putUsuarios = putUsuarios;
const putShopping = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id, idC } = req.params;
    yield usuario_1.UsuarioModel
        .findByIdAndUpdate(id, {
        $push: { camisetas: idC },
    }, { new: true })
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
exports.putShopping = putShopping;
const deleteUsuarios = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    yield usuario_1.UsuarioModel.findByIdAndDelete({ _id: id })
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
exports.deleteUsuarios = deleteUsuarios;
