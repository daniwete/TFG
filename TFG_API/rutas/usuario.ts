import {Router} from "express"
import {getUsuario, postUsuarios, authUsuario, putUsuarios, deleteUsuarios, putShopping} from "../controlador/usuario"

const router = Router()
router.get("/get/:id", getUsuario)
router.post("/auth", authUsuario);
router.post("/post", postUsuarios)
router.put("/put/:id", putUsuarios)
router.delete("/delete/:id", deleteUsuarios)
router.put("/agregarCarrito/:id/ins/:idC",putShopping)

export default router