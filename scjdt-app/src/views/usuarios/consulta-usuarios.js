import React from 'react'
import { withRouter } from 'react-router-dom'

import UsuarioService from '../../app/service/usuarioService'

import * as messages from '../../components/toastr'
import UsuariosTable from './usuariosTable'
import CardTable from '../../components/cardTable'
import { Dialog } from 'primereact/dialog';
import { Button } from 'primereact/button';

class ConsultaUsuarios extends React.Component {

    state = {

        nomeUsuario: '',
        senha: '',
        usuarios: [],
        showConfirmDialog: false,
        usuarioDeletar: {}

    }

    constructor() {
        super();
        this.service = new UsuarioService();
    }

    editar = (id) => {
        this.props.history.push(`/cadastro-usuario/${id}`)

    }

    abrirConfirmacao = (usuario) => {
        this.setState({ showConfirmDialog: true, usuarioDeletar: usuario })
    }

    cancelarDelecao = () => {
        this.setState({ showConfirmDialog: false, usuarioDeletar: {} })
    }

    deletar = () => {
        this.service
            .deletar(this.state.usuarioDeletar.idUsuario)
            .then(response => {
                const usuarios = this.state.usuarios
                const index = usuarios.indexOf(this.state.usuarioDeletar)
                usuarios.splice(index, 1);
                this.setState({ usuarios: usuarios, showConfirmDialog: false })
                messages.mensagemSucesso('Usuário deletado com sucesso')
            }).catch(errr => {
                messages.mensagemErro('Ocorreu um erro ao deletar o Usuário')
            })
    }

    componentDidMount() {

        this.service.consultar()
            .then(response => {
                this.setState({ usuarios: response.data })
            }).catch(err => {
                console.log(err.response)
            })

    }

    render() {

        const confirmDialogFooter = (
            <div>
                <Button label="Confirma" icon="pi pi-check" onClick={this.deletar} />
                <Button label="Cancelar" icon="pi pi-times" onClick={this.cancelarDelecao}
                    className="p-button-secondary" />
            </div>
        )

        return (
            <div className="row">
                <div className="col-md-4" style={{ position: 'relative', left: '300px' }}>
                    <CardTable title="Consulta Usuarios">
                        <div className="row">
                            <div className="col-md-12">
                                <div className="bs-component">
                                    <UsuariosTable usuarios={this.state.usuarios} deleteAction={this.abrirConfirmacao} editAction={this.editar} />
                                </div>
                            </div>
                        </div>

                        <div>
                            <Dialog header="Confirmação"
                                visible={this.state.showConfirmDialog}
                                style={{ width: '50vw' }}
                                footer={confirmDialogFooter}
                                modal={true}
                                onHide={() => this.setState({ showConfirmDialog: false })}>
                                Confirma a exclusão deste Usuário?
                            </Dialog>
                        </div>
                    </CardTable>
                </div>
            </div>
        )
    }
}

export default withRouter(ConsultaUsuarios)