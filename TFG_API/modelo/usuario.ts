import mongoose, { Schema, Document } from 'mongoose';
import IUser from '../interfaces/usuario';

// Define el esquema de usuario
const SchemaUsuario: Schema = new Schema<IUser>(
  {
    nombre: { type: Schema.Types.String, required: true },
    apellidos: { type: Schema.Types.String, required: true },
    correo: { type: Schema.Types.String, required: true },
    contraseña: { type: Schema.Types.String, required: true },
    camisetas: {
      type: [Schema.Types.ObjectId],
      ref: "camisetas",
    },
  },
  { timestamps: false, versionKey: false }
);

export const UsuarioModel = mongoose.model<IUser & Document>(
  "usuarios",
  SchemaUsuario
);
