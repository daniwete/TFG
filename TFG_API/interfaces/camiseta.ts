import { Document, Schema } from "mongoose";

export default interface ICamiseta extends Document {
  nombre: Schema.Types.String;
  descripcion: Schema.Types.String;
  precio: Schema.Types.Number;
  imagen: Schema.Types.String;
}
