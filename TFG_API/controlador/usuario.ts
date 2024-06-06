import {Request, Response}  from "express"
import mongoose from "mongoose"
import { UsuarioModel } from "../modelo/usuario"
//No permite ver la contraseña en la base de datos
import bcrypt from "bcrypt";

const getUsuarios = async(req: Request,res: Response)=>{
//esperar a la funcion
    await UsuarioModel.find()
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

}

const getUsuario = async (req: Request, res: Response) => {
  const { id } = req.params;

  await UsuarioModel.findById(id)
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
};
const authUsuario = async (req: Request, res: Response) => {
  const { correo, contraseña } = req.body;

  console.log("este es el cuerpo =" + req.body.toString());

  if(contraseña){
    
    await UsuarioModel.findOne({
      correo: correo,
    })
    .populate("camisetas")
      .exec()
      .then((resultado) => {
        if (bcrypt.compareSync(contraseña, resultado!.contraseña.toString())) {
          console.log("Correcto " + resultado);
          return res.json({
            exito: true,
            datos: resultado,
          });
        } else {
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
};

const postUsuarios = async (req: Request, res: Response) => {
  const {
    nombre,
    apellidos,
    correo,
    contraseña,
  } = req.body;

  let hashContraseña = bcrypt.hashSync(contraseña, 10);

  console.log(req.body);

  const nuevoUsuario = new UsuarioModel({
    _id: new mongoose.Types.ObjectId(),
    nombre,
    apellidos,
    correo,
    contraseña: hashContraseña,
    camisetas : Array()
  });

  await nuevoUsuario
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
};


const putUsuarios = async (req: Request, res: Response) => {
  const { id } = req.params;
  const { nombre, apellidos, correo, contraseña} =
    req.body;

  // Verificaciones de atributos nulos
  const object: any = {};
  if (nombre !== "" && nombre !== undefined) {
    object.nombre = nombre;
  }
  if (apellidos !== "" && apellidos !== undefined) {
    object.apellidos = apellidos;
  }
  if (correo !== "" && correo !== undefined) {
    object.correo = correo;
  }

  let hashContraseña = bcrypt.hashSync(contraseña, 10);
    object.contraseña = hashContraseña

  await UsuarioModel.findOneAndUpdate({ _id: id }, object)
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
};

const putShopping = async (req: Request, res: Response) => {
  const { id,idC } = req.params;

  await UsuarioModel
    .findByIdAndUpdate(
      id,
      {
        $push: { camisetas: idC },
      },
      { new: true } 
    )
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
};

const deleteUsuarios = async(req: Request,res: Response)=>{

  const {id} = req.params
  
  await UsuarioModel.findByIdAndDelete({ _id:id })

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
}

export {
  getUsuarios, 
  getUsuario, 
  authUsuario,
  postUsuarios, 
  putUsuarios, 
  putShopping, 
  deleteUsuarios
}