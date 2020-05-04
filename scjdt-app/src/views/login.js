import React from 'react'
import Card from '../components/card'
import FormGroup from '../components/form-group'

import axios from 'axios'


class Login extends React.Component {

    state = {
        nomeUsuario: '',
        senha: '',
        mensagemErro: null
    }

    entrar = () => {

        axios.post('http://localhost:8080/scjdtws/usuario/autenticar/', {
            nomeUsuario: this.state.nomeUsuario,
            senha: this.state.senha
        }).then(response => {
            console.log(response.data)
            this.props.history.push('/consulta-usuarios')
        }).catch(erro => {
            this.setState({ mensagemErro: erro.response.data })
        })

    }

    render() {
        return (
            <div className="row">
                <div className="col-md-6" style={{ position: 'relative', left: '300px' }}>
                    <div className="bs-docs-section">
                        <Card title="Login">
                            <div className="row">
                                <span>{this.state.mensagemErro}</span>
                            </div>
                            <div className="col-lg-12">
                                <div className="bs-component">
                                    <fieldset>
                                        <FormGroup label="Usuário: *" htmlFor="exampleInputUsuario">
                                            <input type="text"
                                                value={this.state.nomeUsuario}
                                                onChange={e => this.setState({ nomeUsuario: e.target.value })}
                                                className="form-control"
                                                id="exampleInputUsuario"
                                                aria-describedby="usuarioHelp"
                                                placeholder="Digite o Usuario"
                                            />
                                        </FormGroup>
                                        <FormGroup label="Senha: *" htmlFor="">
                                            <input type="password"
                                                value={this.state.senha}
                                                onChange={e => this.setState({ senha: e.target.value })}
                                                className="form-control"
                                                id="exampleInputPassword1"
                                                placeholder="Password" />
                                        </FormGroup>
                                        <button onClick={this.entrar} className="btn btn-success">Entrar</button>
                                    </fieldset>
                                </div>
                            </div>
                        </Card>
                    </div>
                </div>
            </div>
        )
    }
}

export default Login;