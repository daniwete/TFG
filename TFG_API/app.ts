//cuando tenga que llamar al servidor este archivo lo iniciará
//dotenv= me pertmite trabajar con variables de entorno(.env), que es
import dotenv from "dotenv"
import Servidor  from "./modelo/servidor"

//cargar variables de entorno
dotenv.config()

const server = new Servidor(

)
server.listen()