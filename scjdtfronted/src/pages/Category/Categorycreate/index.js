import React, { useState } from 'react'

import { FiArrowLeft } from 'react-icons/fi'
import { Link, useHistory } from 'react-router-dom'

import './style.css'
import api from '../../../services/api'

export default function Category() {    
    const history = useHistory()
    const [name, setName] = useState('')

    async function handleRegisterCategory(e) {
        e.preventDefault()

        const data = {
            nome_categoria: name,
        }

        if(name === "") {
            return alert('Todos os campos devem ser preenchidos!')
        }
        
        try {
            const response = await api.post('categoria/add', data)

            alert(response.data)
            history.push('/dashboard')
        }
        catch (err) {
            alert(err.reponse.data)
        }
    }

    return (
        <div className="register-category">
            <div className="category-instruction">
                <section>
                    <h1>Cadastro de categoria de funções</h1>
                    <p>Faça o cadastro de uma nova categoria de função</p>

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
                    <button className="button">Salvar</button>
                </form>
            </div>
        </div>
    );
}