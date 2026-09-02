import { Component, inject, OnInit, signal } from '@angular/core';
import { BebidaService } from '../../services/bebida';
import { Bebida } from '../../models/bebida';
import { FormsModule } from '@angular/forms';

@Component({
  imports: [FormsModule],
  selector: 'app-bebida',
  styleUrl: './bebida.css',
  templateUrl: './bebida.html',
})
export class BebidaComponent implements OnInit {
  private service = inject(BebidaService);
  bebidas = signal<Bebida[]>([])

  form: Bebida = { codigo: '', descricao: '', precoUnitario: 0, contemAcucar: false}

  ngOnInit()
  {
    this.carregar();
  }

  carregar()
  {
    this.service.listar().subscribe((dados) => this.bebidas.set(dados));
  }

  salvar()
  {
    this.service.criar(this.form).subscribe(() => {
      this.limpar();
      this.carregar;
    })
  }

  limpar()
  {
    this.form = { codigo: '', descricao: '', precoUnitario: 0, contemAcucar: false }
  }
}
