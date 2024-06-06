import express, {Application} from "express"
import mongoose from "mongoose"
import rutasUsuarios from "../rutas/usuario"
import rutasCamiseta from "../rutas/camiseta";
import cors from "cors"

class Servidor{
    //atributos
    private app: Application
    private port: string
    private rutas = {
        usuarios: "/usuarios/",
        camisetas: "/camisetas/"
    }
    constructor(){
        this.app = express()
        this.port = "3000"
        //iniciar bd sin que la tengas que inicar tu(API inicia automaticamente la BD)
        this.establecerConexion()
        this.middlewares()
        this.routes()
    }
    //async= inicia constantemente hasta que devuelva algo
    async establecerConexion(){
    mongoose.connect(
        process.env.mongoo!
    )
    .then(()=>{console.log("se inició")})
    .catch((ERROR)=>{console.log("da" + ERROR)})
    //cada vez que haga una peticion a mongoo va a imprimir las funciones
    mongoose.set("debug", true)
    }
    //parsea datos
    middlewares(){
    //Parseo del body
    this.app.use(express.json())
    //CORS
    this.app.use(cors())
    //Codificar url
    this.app.use(express.urlencoded({extended: true}))
    }
    routes(){
        this.app.use(this.rutas.usuarios, rutasUsuarios);
        this.app.use(this.rutas.camisetas, rutasCamiseta);
    }
    listen(){
        this.app.listen(this.port,()=>{
            console.log("El servidor esta activo"+ this.port)
        })
    }

}

export default Servidor