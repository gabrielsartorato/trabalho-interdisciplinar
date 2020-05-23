import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { useHistory } from 'react-router-dom'

import { FiArrowLeft } from 'react-icons/fi'

import api from '../../../services/api'
import './style.css'

export default function UserCreate(props) {

    const functionUser = props.match.params.id

    const history = useHistory()

    const [name, setName ] = useState('')
    const [salary, setSalary ] = useState('')
    const [description, setDescription ] = useState('')

    useEffect(() => {
        api.get(`categoria/buscar/${functionUser}`).then(response => {
            setName(response.data.nomeCategoria)
            setSalary(response.data.salarioCategoria)
            setDescription(response.data.descricaoCategoria)
        })
    }, [])

    async function handleEdit(e) {
        e.preventDefault();

        const data = {
            nomeCategoria: name,
            salarioCategoria: salary,
            descricaoCategoria: description
        }

        try {
            await api.put(`categoria/edit/${functionUser}`, data)

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
                    <h1>Edição de função</h1>
                    <p>Faça a alteração da função</p>

                    <Link className="back-link" to="/dashboard">
                        <FiArrowLeft size={16} color="#E02041"/>
                        Voltar
                    </Link>
                </section>
                <form onSubmit={handleEdit}>
                    <input 
                        placeholder="Nome da função"
                        value={name}
                        onChange={e => setName(e.target.value)}
                    />
                    <input 
                        placeholder="Salário"
                        value={salary}
                        onChange={e => setSalary(e.target.value)}
                    />
                    <textarea
                        placeholder="Descrição" 
                        value={description}
                        onChange={e => setDescription(e.target.value)}
                    />
                    <button className="button">Editar</button>
                </form>
            </div>
        </div>
        
    );
}