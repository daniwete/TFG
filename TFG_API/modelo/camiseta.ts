import mongoose, { Schema, Document } from "mongoose";
import ICamiseta from "../interfaces/camiseta";

const SchemaCamiseta: Schema = new Schema<ICamiseta>(
  {
    nombre: { type: Schema.Types.String, required: true },
    descripcion: { type: Schema.Types.String, required: true },
    precio: {type: Schema.Types.Number ,required: true},
    imagen: { type: Schema.Types.String, required: true },
  },
  { timestamps: false, versionKey: false }
);

export const CamisetaModel = mongoose.model<ICamiseta & Document>(
  "camisetas",
  SchemaCamiseta
);