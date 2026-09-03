import { Routes } from '@angular/router';
import { BebidaComponent } from './components/bebida/bebida';
import { IngredienteComponent } from './components/ingrediente/ingrediente';

export const routes: Routes = [
  { path: 'bebidas', component: BebidaComponent },
  { path: 'ingredientes', component: IngredienteComponent },
  { path: '', redirectTo: 'bebidas', pathMatch: 'full' },
];
