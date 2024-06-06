import { Request, Response } from "express";
import mongoose from "mongoose";
import { CamisetaModel } from "../modelo/camiseta";


const getCamisetas = async (req: Request, res: Response) => {
  await CamisetaModel.find()
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
};


const getCamiseta = async (req: Request, res: Response) => {
  const { id } = req.params;

  await CamisetaModel.findById(id)
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

const postCamiseta = async (req: Request, res: Response) => {
  const { nombre,descripcion,precio,imagen} =
    req.body;

  const Camiseta = new CamisetaModel({
    _id: new mongoose.Types.ObjectId(),
    nombre,
    descripcion,
    precio,
    imagen,
  });

  await Camiseta
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
};


export { getCamisetas, getCamiseta, postCamiseta };
