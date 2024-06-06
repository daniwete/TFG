"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
//cuando tenga que llamar al servidor este archivo lo iniciará
//dotenv= me pertmite trabajar con variables de entorno(.env), que es
const dotenv_1 = __importDefault(require("dotenv"));
const servidor_1 = __importDefault(require("./modelo/servidor"));
//cargar variables de entorno
dotenv_1.default.config();
const server = new servidor_1.default();
server.listen();
