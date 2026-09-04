import { HttpClient, httpResource } from '@angular/common/http';
import { inject, Service, signal } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Ingrediente } from '../models/ingrediente';
import { Observable } from 'rxjs';

@Service()
export class IngredienteService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/ingredientes`;

  termoBusca = signal('');

  ingredientes = httpResource<Ingrediente[]>(() => {
    const termo = this.termoBusca();
    return termo ? `${this.url}?termo=${termo}` : this.url;
  });

  adicionais = httpResource<Ingrediente[]>(() => {
    return `${this.url}/adicionais`;
  });

  listar(): Observable<Ingrediente[]> {
    return this.http.get<Ingrediente[]>(this.url);
  }

  criar(ingrediente: Ingrediente): Observable<Ingrediente> {
    return this.http.post<Ingrediente>(this.url, ingrediente);
  }

  atualizar(id: number, ingrediente: Ingrediente): Observable<Ingrediente> {
    return this.http.put<Ingrediente>(`${this.url}/${id}`, ingrediente);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
