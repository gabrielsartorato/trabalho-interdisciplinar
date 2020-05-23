import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { useHistory } from 'react-router-dom'

import { FiArrowLeft } from 'react-icons/fi'

import api from '../../../services/api'
import './style.css'

export default function UserCreate(props) {

    const user = props.match.params.id

    const history = useHistory()

    const [name, setName ] = useState('')
    const [password, setPassword ] = useState('')

    useEffect(() => {
        api.get(`usuario/buscar/${user}`).then(response => {
            setName(response.data.nomeUsuario)
            setPassword(response.data.senha)
        })
    }, [])

    async function handleEdit(e) {
        e.preventDefault();

        const data = {
            nomeUsuario: name.toUpperCase(),
            senha: password
        }

        try {
            await api.put(`usuario/edit/${user}`, data)

            history.push('/dashboard')
        }
        catch(err) {
            alert('Erro ao editar, tente novamente!')
        }
    }

    return (
        <div className="register-container">
            <div className="content">
                <section>
                    <h1>Alteração de Usuário</h1>
                    <p>Faça a alteração do usuário {name}</p>

                    <Link className="back-link" to="/dashboard">
                        <FiArrowLeft size={16} color="#E02041"/>
                        Voltar
                    </Link>
                </section>
                <form onSubmit={handleEdit}>
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
                    <button className="button">Editar</button>
                </form>
            </div>
        </div>
        
    );
}