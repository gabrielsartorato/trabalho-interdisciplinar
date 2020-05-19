import React from 'react'
import { Link } from 'react-router-dom'
import { FiArrowLeft } from 'react-icons/fi'

import './style.css'

export default function UserCreate() {
    return (
        <div className="register-container">
            <div className="content">
                <section>
                    <h1>Cadastro de Usuário</h1>
                    <p>Faça o cadastro de um novo usuário</p>

                    <Link className="back-link" to="/">
                        <FiArrowLeft size={16} color="#E02041"/>
                        Voltar
                    </Link>
                </section>
                <form>
                    <input placeholder="Nome do Usuário"/>
                    <input placeholder="Senha"/>
                    <button className="button">Cadastrar</button>
                </form>
            </div>
        </div>
        
    );
}