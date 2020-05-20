import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { FiArrowLeft } from 'react-icons/fi'

import api from '../../services/api'
import './style.css'

export default function UserCreate() {

    const [name, setName ] = useState('')
    const [password, setPassword ] = useState('')

    async function handleRegister(e) {
        e.preventDefault();

        const data = {
            nomeUsuario: name,
            senha: password
        }

        try {
            const response = await api.post('usuario/add', data)

        alert(`${response.data}`)
        }
        catch(err) {
            alert('Erro no cadastro, tente novamente!')
        }
    }

    return (
        <div className="register-container">
            <div className="content">
                <section>
                    <h1>Cadastro de Usuário</h1>
                    <p>Faça o cadastro de um novo usuário</p>

                    <Link className="back-link" to="/dashboard">
                        <FiArrowLeft size={16} color="#E02041"/>
                        Voltar
                    </Link>
                </section>
                <form onSubmit={handleRegister}>
                    <input 
                        placeholder="Nome do Usuário"
                        value={name}
                        onChange={e => setName(e.target.value)}
                    />
                    <input 
                        type="password" 
                        placeholder="Senha"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                    />
                    <button className="button">Cadastrar</button>
                </form>
            </div>
        </div>
        
    );
}