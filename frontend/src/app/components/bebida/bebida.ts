import { Component, inject, OnInit, signal } from '@angular/core';
import { BebidaService } from '../../services/bebida';
import { Bebida } from '../../models/bebida';

@Component({
  imports: [],
  selector: 'app-bebida',
  styleUrl: './bebida.css',
  templateUrl: './bebida.html',
})
export class BebidaComponent implements OnInit {
  private service = inject(BebidaService);
  bebidas = signal<Bebida[]>([])

  ngOnInit()
  {
    this.service.listar().subscribe(dados => this.bebidas.set(dados))
  }
}
