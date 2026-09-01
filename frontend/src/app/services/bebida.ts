import { HttpClient } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { Observable } from 'rxjs';
import { Bebida } from '../models/bebida';

@Service()
export class BebidaService {
  private http = inject(HttpClient);
  private url = 'http://localhost:8080/bebidas';

  listar(): Observable<Bebida[]>
  {
    return this.http.get<Bebida[]>(this.url)
  }

  criar(bebida: Bebida): Observable<Bebida>
  {
    return this.http.post<Bebida>(this.url, bebida)
  }

  atualizar(id: number, bebida: Bebida): Observable<Bebida>
  {
    return this.http.put<Bebida>(`${this.url}/${id}`, bebida)
  }

  deletar(id: number): Observable<void>
  {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
