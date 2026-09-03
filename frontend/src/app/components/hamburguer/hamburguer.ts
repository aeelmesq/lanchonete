import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HamburguerService } from '../../services/hamburguer';
import { IngredienteService } from '../../services/ingrediente';
import { Hamburguer } from '../../models/hamburguer';
import { Ingrediente } from '../../models/ingrediente';

@Component({
  imports: [FormsModule],
  selector: 'app-hamburguer',
  styleUrl: './hamburguer.css',
  templateUrl: './hamburguer.html',
})
export class HamburguerComponent {
  private service = inject(HamburguerService);
  hamburgueres = this.service.hamburgueres;

  private ingredienteService = inject(IngredienteService);
  ingredientes = this.ingredienteService.disponiveis;

  // Hamburguer

  form: Hamburguer = { codigo: '', descricao: '', valor: 0, ingredientes: [] };

  busca = '';

  pesquisar() {
    this.service.termoBusca.set(this.busca);
  }

  editar(hamburguer: Hamburguer) {
    this.form = { ...hamburguer };
  }

  salvar() {
    const acao = this.form.id
      ? this.service.atualizar(this.form.id, this.form)
      : this.service.criar(this.form);

    acao.subscribe(() => {
      this.limpar();
      this.hamburgueres.reload();
    });
  }

  excluir(id: number) {
    this.service.deletar(id).subscribe(() => this.hamburgueres.reload());
  }

  limpar() {
    this.form = { codigo: '', descricao: '', valor: 0, ingredientes: [] };
  }

  // Ingrediente

  estaSelecionado(ingrediente: Ingrediente): boolean
  {
    return this.form.ingredientes.some(i => i.id === ingrediente.id)
  }

  alternar(ingrediente: Ingrediente)
  {
    if (this.estaSelecionado(ingrediente))
    {
      this.form.ingredientes = this.form.ingredientes.filter(i => i.id !== ingrediente.id)
    } else
    {
      this.form.ingredientes = [...this.form.ingredientes, ingrediente]
    }
  }
}
