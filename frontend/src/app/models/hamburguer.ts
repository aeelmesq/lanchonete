import { Ingrediente } from './ingrediente';

export interface Hamburguer {
  id?: number;
  codigo: string;
  descricao: string;
  valor: number;
  ingredientes: Ingrediente[];
}
