import { Component, inject } from '@angular/core';
import { BebidaService } from '../../services/bebida';
import { Bebida } from '../../models/bebida';
import { FormsModule } from '@angular/forms';

@Component({
  imports: [FormsModule],
  selector: 'app-bebida',
  styleUrl: './bebida.css',
  templateUrl: './bebida.html',
})
export class BebidaComponent {
  private service = inject(BebidaService);
  bebidas = this.service.bebidas;

  form: Bebida = { codigo: '', descricao: '', precoUnitario: 0, contemAcucar: false}

  busca = '';
  pesquisar()
  {
    this.service.termoBusca.set(this.busca);
  }

  editar(bebida: Bebida)
  {
    this.form = { ...bebida }
  }

  salvar()
  {
    const acao = this.form.id
      ? this.service.atualizar(this.form.id, this.form)
      : this.service.criar(this.form);

    acao.subscribe(() => {
      this.limpar();
      this.bebidas.reload();
    })
  }

  excluir(id: number)
  {
    this.service.deletar(id).subscribe(() => this.bebidas.reload());
  }

  limpar()
  {
    this.form = { codigo: '', descricao: '', precoUnitario: 0, contemAcucar: false }
  }
}
