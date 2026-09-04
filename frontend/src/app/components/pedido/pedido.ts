import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PedidoService } from '../../services/pedido';
import { Pedido } from '../../models/pedido';
import { HamburguerService } from '../../services/hamburguer';
import { BebidaService } from '../../services/bebida';
import { IngredienteService } from '../../services/ingrediente';
import { Hamburguer } from '../../models/hamburguer';
import { Bebida } from '../../models/bebida';
import { Ingrediente } from '../../models/ingrediente';
import { CurrencyPipe, DatePipe } from '@angular/common';

@Component({
  imports: [FormsModule, DatePipe, CurrencyPipe],
  selector: 'app-pedido',
  styleUrl: './pedido.css',
  templateUrl: './pedido.html',
})
export class PedidoComponent {
  private service = inject(PedidoService);
  pedidos = this.service.pedidos;

  private hamburguerService = inject(HamburguerService);
  private bebidaService = inject(BebidaService);
  private ingredienteService = inject(IngredienteService);

  hamburgueres = this.hamburguerService.hamburgueres;
  bebidas = this.bebidaService.bebidas;
  adicionais = this.ingredienteService.adicionais;

  form: Pedido = {
    codigo: '',
    descricao: '',
    observacoes: [],
    clienteNome: '',
    clienteEndereco: '',
    clienteTelefone: '',
    hamburgueres: [],
    bebidas: [],
    adicionais: [],
  };

  busca = '';

  pesquisar() {
    this.service.termoBuscar.set(this.busca);
  }

  editar(pedido: Pedido) {
    this.form = { ...pedido };
  }

  erro = signal('')

  salvar() {
    const acao = this.form.id
      ? this.service.atualizar(this.form.id, this.form)
      : this.service.criar(this.form);

    acao.subscribe({
      next: () => {
        this.limpar();
        this.pedidos.reload();
      },
      error: (e) => this.erro.set(e.erro?.mensagem ?? 'Erro ao salvar'),
    });
  }

  excluir(id: number) {
    this.service.deletar(id).subscribe(() => this.pedidos.reload());
  }

  limpar() {
    this.form = {
      codigo: '',
      descricao: '',
      observacoes: [],
      clienteNome: '',
      clienteEndereco: '',
      clienteTelefone: '',
      hamburgueres: [],
      bebidas: [],
      adicionais: [],
    };
  }

  novaObservacao = '';

  adicionarObservacao() {
    const texto = this.novaObservacao.trim();
    if (!texto) return;

    this.form.observacoes = [...this.form.observacoes, texto];
    this.novaObservacao = '';
  }

  removerObservacao(index: number) {
    this.form.observacoes = this.form.observacoes.filter((_, i) => i !== index);
  }

  descricoes(itens: { descricao: string }[]): string {
    return itens.map((i) => i.descricao).join(', ');
  }

  // Hamburguer

  hamburguerSelecionado(hamburguer: Hamburguer): boolean {
    return this.form.hamburgueres.some((i) => i.id === hamburguer.id);
  }

  alternarHamburguer(hamburguer: Hamburguer) {
    this.form.hamburgueres = this.hamburguerSelecionado(hamburguer)
      ? this.form.hamburgueres.filter((i) => i.id !== hamburguer.id)
      : [...this.form.hamburgueres, hamburguer];
  }

  // Bebida

  bebidaSelecionada(bebida: Bebida): boolean {
    return this.form.bebidas.some((i) => i.id === bebida.id);
  }

  alternarBebida(bebida: Bebida) {
    this.form.bebidas = this.bebidaSelecionada(bebida)
      ? this.form.bebidas.filter((i) => i.id !== bebida.id)
      : [...this.form.bebidas, bebida];
  }

  // Adicionais

  adicionalSelecionado(adicional: Ingrediente): boolean {
    return this.form.adicionais.some((i) => i.id === adicional.id);
  }

  alternarAdicional(adicional: Ingrediente) {
    this.form.adicionais = this.adicionalSelecionado(adicional)
      ? this.form.adicionais.filter((i) => i.id !== adicional.id)
      : [...this.form.adicionais, adicional];
  }
}
