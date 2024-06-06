import {Document, Schema} from "mongoose"
//interfaz es como un contrato, Document es una clase estática.
export default interface IUser extends Document{

    nombre: Schema.Types.String
    apellidos: Schema.Types.String;
    correo: Schema.Types.String;
    contraseña: Schema.Types.String
    camisetas : Schema.Types.Array;
}