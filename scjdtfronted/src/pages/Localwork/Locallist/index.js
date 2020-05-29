import React, { useEffect, useState } from 'react'

import { useHistory } from 'react-router-dom'

import { FiPower, FiTrash, FiEdit, FiHome } from 'react-icons/fi'
import timerSvg from '../../../assets/clock.svg'
import { adjustName } from '../../../libs/utils'

import api from '../../../services/api'

import './style.css'

export default function Userlist() {
    const userName = localStorage.getItem('nomeUsuario')
    const history = useHistory()
    const [localList, setLocalList] = useState([]);

    const user = adjustName(userName)

    function handleLogout() {
        localStorage.clear();
        history.push('/')
    }

    function handleDashboard() {
        history.push('/dashboard')
    }

    function handleConfirm (local) {
        const confirmation = window.confirm(`Deseja realmente deletar o usuário ${local.nome_local_trabalho}`)

        if(confirmation)
            handleDeleteUser(local.id_local_trabalho)

        window.event.preventDefault()
    }

    useEffect(() => {
        api.get('localtrabalho/list').then(response => {
            setLocalList(response.data)
        })
    }, [])

    async function handleDeleteUser(id) {
        try {
            await api.delete(`localtrabalho/delete/${id}`)
            setLocalList(localList.filter(local => local.id_local_trabalho !== id))
        }
        catch (err) {
            alert('Erro ao deletar o caso, tete novamente!')
        }
    }

    return (
        <div className="dashboard-container">
            <header>
                <img src={timerSvg} alt="timer" />
                <span>Bem vindo, {user} </span>
                <div className="button-group">
                    <button onClick={() => handleDashboard()}><FiHome size={18}/></button>
                    <button onClick={() => handleLogout()} type="button">
                        <FiPower size={18} color="#e02041" />
                    </button>
                </div>
            </header>

            <div className="table-container">
                <table className="user-list">
                    <thead>
                        <tr>
                            <th>Local Trabalho</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {localList.map(local => (
                            <tr key={local.id_local_trabalho} className="tbody">
                                <td>{local.nome_local_trabalho}</td>
                                <td className="action">
                                    <a href={`/local-edit/${local.id_local_trabalho}`}><FiEdit /></a>
                                    <button onClick={() => handleConfirm(local)}><FiTrash /></button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}