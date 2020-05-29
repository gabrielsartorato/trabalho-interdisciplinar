import React, { useState } from 'react'
import { useHistory } from 'react-router-dom'

import './style.css';

import api from '../../services/api'


import timerImg from '../../assets/clock.png';

export default function Login() {
    const [user, setUser ] = useState('')
    const [password, setPassword] = useState('')
    const history = useHistory()

    async function handleLogin(e) {
        e.preventDefault()

        const data = {
            nome_usuario: user.toUpperCase(),
	        senha: password
        }
        try {
            const response = await api.post('usuario/autenticar', data)

            localStorage.setItem('userId', response.data.id_usuario)
            localStorage.setItem('nomeUsuario', response.data.nome_usuario)

            history.push('/dashboard')
        }
        catch(err) {
            alert('Usuário ou senha incorreto')
        }
    }

    return (
        <div className="login-container">
            <section className="form">
            <form onSubmit={handleLogin}>
                <h1>Faça seu Login</h1>
                <input
                    value={user}
                    onChange={e => setUser(e.target.value)} 
                    placeholder="Usuário"
                />
                <input 
                    type="password"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                    placeholder="Senha"
                />
                <button className="button">Entrar</button>
            </form>
            </section>
            <img src={timerImg} alt="timer"/>
        </div>
    );
}