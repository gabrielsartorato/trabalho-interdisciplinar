import React, { useState } from 'react'

import { FiArrowLeft } from 'react-icons/fi'
import { Link, useHistory } from 'react-router-dom'

import './style.css'
import api from '../../../services/api'

export default function Localcreate() {
    const history = useHistory()
    const [name, setName] = useState('')

    async function handleRegisterCategory(e) {
        e.preventDefault()

        if(name === "") {
            return alert('Todos os campos devem ser preenchidos!')
        }

        const data = {
            nomeLocalTrabalho: name,
        }
        
        try {
           const response = await api.post('localtrabalho/add', data)

           alert(response.data)

            history.push('/dashboard')
        }
        catch (err) {
            alert(err.response.data)
        }
    }

    return (

        <div className="register-category">
            <div className="category-instruction">
                <section>
                    <h1>Cadastro de Locais de trabalho</h1>
                    <p>Faça o cadastro de um novo local de trablho</p>

                    <Link className="back-link" to="/dashboard">
                            <FiArrowLeft size={16} color="#E02041"/>
                            Voltar
                    </Link>
                </section>

                <form onSubmit={handleRegisterCategory}>
                    <input 
                        placeholder="Nome do local"
                        value={name}
                        onChange={e => setName(e.target.value)}
                    />
                    <button className="button">Salvar</button>
                </form>
            </div>
        </div>
    );
}