import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-cadastrar-ambiente',
  templateUrl: './cadastrar-ambiente.component.html',
  styleUrls: ['./cadastrar-ambiente.component.scss']
})
export class CadastrarAmbienteComponent {
  ambientesAdicionados: any[] = [];
  blocosSelecionados: any[] = [];
  nomeAmbiente: string = '';
  bloco: any = {};

  @Output() close = new EventEmitter<void>();
  isModalOpen = false;

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }

  removerServico(index: number) {
    this.ambientesAdicionados.splice(index, 1);
  }

  adicionarAmbiente() {
    this.ambientesAdicionados.push({
      nomeAmbiente: this.nomeAmbiente,
      bloco: this.bloco,
      patrimonio: '',
      descricao: ''
    });
  }
}