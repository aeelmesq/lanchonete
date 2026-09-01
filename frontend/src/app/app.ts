import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BebidaComponent } from './components/bebida/bebida';

@Component({
  imports: [RouterOutlet, BebidaComponent],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('frontend');
}
