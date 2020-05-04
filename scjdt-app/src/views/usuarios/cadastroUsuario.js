import React from 'react'

import Card from '../../components/card'
import FormGroup from '../../components/form-group'

import UsuarioService from '../../app/service/usuarioService'

import * as messages from '../../components/toastr'

class CadastroUsuario extends React.Component {

    constructor() {
        super();
        this.service = new UsuarioService();
    }

    state = {
        idUsuario: null,
        nomeUsuario: '',
        senha: '',
        atualizando: false
    }


    cadastrarUsuario = () => {

        const { nomeUsuario, senha } = this.state

        const user = { nomeUsuario, senha }

        try {
            this.service.validar(user)
        } catch (erro) {
            const msgs = erro.mensagens;
            msgs.forEach(msg => messages.mensagemErro(msg))
            return false;
        }

        this.service.save(user)
            .then(response => {
                messages.mensagemSucesso('Usuário cadastrado com sucesso')
                this.props.history.push('/consulta-usuarios')
            }).catch(err => {
                messages.mensagemErro(err.response.data)
            })

    }

    atualizarUsuario = () => {

        const { nomeUsuario, senha, idUsuario } = this.state
        const usuario = { idUsuario, nomeUsuario, senha }

        this.service.atualizar(usuario)
            .then(response => {
                messages.mensagemSucesso('Usuário atualizado com sucesso')
                this.props.history.push('/consulta-usuarios')
            }).catch(err => {
                messages.mensagemErro(err.response.data)
            })

    }

    componentDidMount() {
        const params = this.props.match.params

        if (params.id) {
            this.service
                .obterPorId(params.id)
                .then(response => {
                    this.setState({ ...response.data, atualizando: true })
                }).catch(err => {
                    messages.mensagemErro(err.response.data)
                })
        }
    }

    handleChange = (event) => {

        const value = event.target.value;
        const name = event.target.name;

        this.setState({ [name]: value })

    }

    render() {

        return (
            <div className="col-md-6" style={{ position: 'relative', left: '300px' }}>
                <Card title={this.state.atualizando ? 'Atualização de Usuário' : 'Cadastro de Usuários'}>
                    <div className="row">
                        <div className="col-lg-12">
                            <div className="bs-component">
                                <FormGroup id="inputUsuario" label="Usuário: *" htmlFor="inputUsuario">
                                    <input type="text"
                                        id="inputUsuario"
                                        className="form-control"
                                        name="nomeUsuario"
                                        value={this.state.nomeUsuario}
                                        onChange={this.handleChange} />
                                </FormGroup>
                                <FormGroup label="Senha: *" htmlFor="inputSenha">
                                    <input type="password"
                                        id="inputSenha"
                                        className="form-control"
                                        name="senha"
                                        value={this.state.senha}
                                        onChange={this.handleChange} />
                                </FormGroup>
                                <div className="row">
                                    <div className="col-md-6">
                                        {this.state.atualizando ?
                                            (
                                                <button onClick={this.atualizarUsuario} type="button" className="btn btn-primary">Atualizar</button>
                                            ) : (
                                                <button onClick={this.cadastrarUsuario} type="button" className="btn btn-success">Salvar</button>
                                            )
                                        }
                                        <button onClick={e => this.props.history.push('/consulta-usuarios')} className="btn btn-danger">Cancelar</button>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </Card>
            </div>
        )
    }
}

export default CadastroUsuario