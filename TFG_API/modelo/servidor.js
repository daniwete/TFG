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
const express_1 = __importDefault(require("express"));
const mongoose_1 = __importDefault(require("mongoose"));
const usuario_1 = __importDefault(require("../rutas/usuario"));
const camiseta_1 = __importDefault(require("../rutas/camiseta"));
const cors_1 = __importDefault(require("cors"));
class Servidor {
    constructor() {
        this.rutas = {
            usuarios: "/usuarios/",
            camisetas: "/camisetas/"
        };
        this.app = (0, express_1.default)();
        this.port = "3000";
        //iniciar bd sin que la tengas que inicar tu(API inicia automaticamente la BD)
        this.establecerConexion();
        this.middlewares();
        this.routes();
    }
    //async= inicia constantemente hasta que devuelva algo
    establecerConexion() {
        return __awaiter(this, void 0, void 0, function* () {
            mongoose_1.default.connect(process.env.mongoo)
                .then(() => { console.log("se inició"); })
                .catch((ERROR) => { console.log("da" + ERROR); });
            //cada vez que haga una peticion a mongoo va a imprimir las funciones
            mongoose_1.default.set("debug", true);
        });
    }
    //parsea datos
    middlewares() {
        //Parseo del body
        this.app.use(express_1.default.json());
        //CORS
        this.app.use((0, cors_1.default)());
        //Codificar url
        this.app.use(express_1.default.urlencoded({ extended: true }));
    }
    routes() {
        this.app.use(this.rutas.usuarios, usuario_1.default);
        this.app.use(this.rutas.camisetas, camiseta_1.default);
    }
    listen() {
        this.app.listen(this.port, () => {
            console.log("El servidor esta activo" + this.port);
        });
    }
}
exports.default = Servidor;
