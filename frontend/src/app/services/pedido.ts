import { HttpClient, httpResource } from '@angular/common/http';
import { inject, Service, signal } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Pedido } from '../models/pedido';
import { Observable } from 'rxjs';

@Service()
export class PedidoService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/pedidos`;

  termoBuscar = signal('');

  pedidos = httpResource<Pedido[]>(() => {
    const termo = this.termoBuscar();
    return termo ? `${this.url}?termo=${termo}` : this.url;
  });

  listar(): Observable<Pedido[]> {
    return this.http.get<Pedido[]>(this.url);
  }

  criar(pedido: Pedido): Observable<Pedido> {
    return this.http.post<Pedido>(this.url, pedido);
  }

  atualizar(id: number, pedido: Pedido): Observable<Pedido> {
    return this.http.put<Pedido>(`${this.url}/${id}`, pedido);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
