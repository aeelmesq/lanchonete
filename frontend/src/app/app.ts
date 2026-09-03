import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BebidaComponent } from './components/bebida/bebida';
import { IngredienteComponent } from './components/ingrediente/ingrediente';

@Component({
  imports: [RouterOutlet, BebidaComponent, IngredienteComponent],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('frontend');
}
