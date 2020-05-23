import React, { useState } from 'react'

import { FiArrowLeft } from 'react-icons/fi'
import { Link, useHistory } from 'react-router-dom'

import './style.css'
import api from '../../../services/api'

export default function Category() {    
    const history = useHistory()
    const [name, setName] = useState('')
    const [salary, setSalary] = useState('')
    const [description, setDescription] = useState('')

    async function handleRegisterCategory(e) {
        e.preventDefault()

        const data = {
            nomeCategoria: name,
            salarioCategoria: salary,
            descricaoCategoria: description
        }
        
        try {
            await api.post('categoria/add', data)

            history.push('/dashboard')
        }
        catch (err) {
            alert('Erro ao tentar inserir, tente novamente')
        }
    }


    return (
        <div className="register-category">
            <div className="category-instruction">
                <section>
                    <h1>Cadastro de funções</h1>
                    <p>Faça o cadastro de uma nova função</p>

                    <Link className="back-link" to="/dashboard">
                            <FiArrowLeft size={16} color="#E02041"/>
                            Voltar
                    </Link>
                </section>

                <form onSubmit={handleRegisterCategory}>
                    <input 
                        placeholder="Nome da categoria"
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
                    <button className="button">Salvar</button>
                </form>
            </div>
        </div>
    );
}