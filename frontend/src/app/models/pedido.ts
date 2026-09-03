import { Bebida } from "./bebida";
import { Hamburguer } from "./hamburguer";
import { Ingrediente } from "./ingrediente";

export interface Pedido {
  id?: number;
  codigo: string;
  descricao: string;
  dataPedido?: string;
  observacoes: string[];
  clienteNome: string;
  clienteEndereco: string;
  clienteTelefone: string;
  hamburgueres: Hamburguer[];
  bebidas: Bebida[];
  adicionais: Ingrediente[];
  total?: number;
}
