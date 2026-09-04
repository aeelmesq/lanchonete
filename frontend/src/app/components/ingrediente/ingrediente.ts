import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IngredienteService } from '../../services/ingrediente';
import { Ingrediente } from '../../models/ingrediente';

@Component({
  imports: [FormsModule],
  selector: 'app-ingrediente',
  styleUrl: './ingrediente.css',
  templateUrl: './ingrediente.html',
})
export class IngredienteComponent {
  private service = inject(IngredienteService);
  ingredientes = this.service.ingredientes;

  form: Ingrediente = { codigo: '', descricao: '', precoUnitario: 0, podeSerAdicional: false };

  busca = '';

  pesquisar() {
    this.service.termoBusca.set(this.busca);
  }

  editar(ingrediente: Ingrediente) {
    this.form = { ...ingrediente };
  }

  erro = signal('')

  salvar() {
    const acao = this.form.id
      ? this.service.atualizar(this.form.id, this.form)
      : this.service.criar(this.form);

    acao.subscribe({
      next: () => {
        this.limpar();
        this.ingredientes.reload();
      },
      error: (e) => this.erro.set(e.error?.mensagem ?? 'Erro ao salvar'),
    });
  }

  excluir(id: number) {
    this.service.deletar(id).subscribe(() => this.ingredientes.reload);
  }

  limpar() {
    this.form = { codigo: '', descricao: '', precoUnitario: 0, podeSerAdicional: false };
  }
}
