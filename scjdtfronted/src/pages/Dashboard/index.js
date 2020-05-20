import React from 'react';
import { Link, useHistory } from 'react-router-dom'

import { FiPower } from 'react-icons/fi'
import timerSvg from '../../assets/clock.svg'

import './style.css'

export default function Dashboard() {
    const userName = localStorage.getItem('nomeUsuario')
    const history = useHistory()
    
    let user = userName.slice(0, 1).toUpperCase()

    user = user + userName.substring(1, userName.length)

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
                        <Link className="button button-color" to="/">Consultar</Link>
                    </div>
                </div>
                <div className="itens">
                    <h2>Locais de trabalho</h2>
                    <div className="button-group">
                        <Link className="button" to="/">Inserir</Link>
                        <Link className="button button-color" to="/">Consultar</Link>
                    </div>
                </div>
            </div>
        </div>
    );
}
