import React from 'react';
import { Link, useHistory } from 'react-router-dom'
import { adjustName } from '../../libs/utils'

import { FiPower } from 'react-icons/fi'
import timerSvg from '../../assets/clock.svg'

import './style.css'

export default function Dashboard() {
    const userName = localStorage.getItem('nomeUsuario')
    const history = useHistory()

    const user = adjustName(userName)

    function handleLogout() {
        localStorage.clear();
        history.push('/')
    }

    return (
        <div className="dashboard-container">
            <header>
                <img src={timerSvg} alt="timer"/>
                <span>Bem vindo, {user} </span>
                <button onClick={() => handleLogout()} type="button">
                    <FiPower size={18} color="#e02041"/>
                </button>
            </header>

            <h1 className="dashboard-title">Dashboard</h1>

            <div className="dashboard-itens">
                <div className="itens">
                    <h2>Usuários</h2>
                    <div className="button-group">
                        <Link className="button" to="/user-create">Inserir</Link>
                        <Link className="button button-color" to="/user-list">Consultar</Link>
                    </div>
                </div>
                <div className="itens">
                    <h2>Categoria de Funções</h2>
                    <div className="button-group">
                        <Link className="button" to="/category">Inserir</Link>
                        <Link className="button button-color" to="/category-list">Consultar</Link>
                    </div>
                </div>
                <div className="itens">
                    <h2>Funções</h2>
                    <div className="button-group">
                        <Link className="button" to="/function-create">Inserir</Link>
                        <Link className="button button-color" to="/function-list">Consultar</Link>
                    </div>
                </div>
                <div className="itens">
                    <h2>Locais de trabalho</h2>
                    <div className="button-group">
                        <Link className="button" to="/local-create">Inserir</Link>
                        <Link className="button button-color" to="/local-list">Consultar</Link>
                    </div>
                </div>
                <div className="itens">
                    <h2>Colaborador</h2>
                    <div className="button-group">
                        <Link className="button" to="/collaborator-create">Inserir</Link>
                        <Link className="button button-color" to="/collaborator-list">Consultar</Link>
                    </div>
                </div>
                <div className="itens">
                    <h2>Programação Horária</h2>
                    <div className="button-group">
                        <Link className="button" to="/hourly-create">Inserir</Link>
                        <Link className="button button-color" to="/hourly-list">Consultar</Link>
                    </div>
                </div>
            </div>
        </div>
    );
}
