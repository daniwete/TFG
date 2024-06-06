"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const camiseta_1 = require("../controlador/camiseta");
const router = (0, express_1.Router)();
//Establecemos los endpoints de cada solicitud
router.get("/todos", camiseta_1.getCamisetas);
router.get("/esp/:id", camiseta_1.getCamiseta);
router.post("/agregar", camiseta_1.postCamiseta);
exports.default = router;
