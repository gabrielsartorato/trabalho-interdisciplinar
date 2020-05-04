import React from 'react'

import { Route, Switch, Router } from 'react-router-dom'

import Login from '../views/login'
import CadastroUsuario from '../views/usuarios/cadastroUsuario'
import ConsultaUsuarios from '../views/usuarios/consulta-usuarios'

import history from '../main/history'

function Rotas() {
    return (
        <Router history={history}>
            <Switch>
                <Route path="/login" component={Login} />
                <Route path="/consulta-usuarios" component={ConsultaUsuarios} />
                <Route path="/cadastro-usuario/:id?" component={CadastroUsuario} />
            </Switch>
        </Router>
    )
}

export default Rotas