import { Component, EventEmitter, Output } from '@angular/core';
import { UsuarioRequest } from '../../../dtos/requests/usuario.request';
import { AddUsuario, UsuarioResponse } from '../../../dtos/responses/usuario.response';

@Component({
  selector: 'app-cadastrar-usuario',
  templateUrl: './cadastrar-usuario.component.html',
  styleUrls: ['./cadastrar-usuario.component.scss']
})
export class CadastrarUsuarioComponent {
  isModalOpen = false;
  username: string = '';
  senha :string = '';
  permissoes: string[] = [];
  usuarioResponse : UsuarioResponse[] = [];
  permissoesDisponiveis = ['ADMINISTRADOR', 'SUPER', 'OPERADOR'];

  constructor(private usuario: UsuarioRequest) {
    this.loadUsuarios();
  }
  @Output() usuarioAdicionado = new EventEmitter<void>();
  onSubmit( event: Event): void {

  }
  loadUsuarios() {
    this.usuario.getUsuario().subscribe((data: any[]) => {
      this.usuarioResponse = data;
    });
  }

  adicionarUsuario() {
    const permissoesArray = Array.isArray(this.permissoes) ? this.permissoes : [this.permissoes];
    const usuario: AddUsuario = {
      username: this.username,
      password:  this.senha,
      permissoes: permissoesArray
    };

    this.usuario.setUsuario(this.usuarioResponse).subscribe(() => {
      this.usuarioAdicionado.emit();
    });
  }
  @Output() close = new EventEmitter<void>();
  closeModal() {
    this.close.emit();
  }
}
