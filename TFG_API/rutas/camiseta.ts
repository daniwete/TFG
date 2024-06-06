import { Router } from "express";
import {
  getCamisetas,
  getCamiseta,
  postCamiseta,
} from "../controlador/camiseta";

const router = Router();

//Establecemos los endpoints de cada solicitud
router.get("/todos", getCamisetas);
router.get("/esp/:id", getCamiseta);
router.post("/agregar", postCamiseta);


export default router;
