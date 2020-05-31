import React, { useEffect, useState } from 'react'

import { useHistory } from 'react-router-dom'

import { FiPower, FiTrash, FiEdit, FiHome } from 'react-icons/fi'
import timerSvg from '../../../assets/clock.svg'
import { adjustName } from '../../../libs/utils'

import api from '../../../services/api'

import './style.css'

export default function Categorylist() {
    const userName = localStorage.getItem('nomeUsuario')
    const history = useHistory()
    const [hourList, setHourList] = useState([]);

    const user = adjustName(userName)

    function handleLogout() {
        localStorage.clear();
        history.push('/')
    }

    function handleDashboard() {
        history.push('/dashboard')
    }

    useEffect(() => {
        api.get('programacao/list').then(response => {
            setHourList(response.data)
        })
    }, [])

    function handleConfirm (hour) {
        const confirmation = window.confirm(`Deseja realmente deletar o usuário ${hour.nome_funcao}`)

        if(confirmation)
        handleDeleteProg(hour.id_programacao)

        window.event.preventDefault()
    }


    async function handleDeleteProg(id) {
        try {
            await api.delete(`programacao/delete/${id}`)
            setHourList(hourList.filter(hour => hour.id_programacao !== id))
        }
        catch (err) {
            alert('Erro ao deletar a categoria, tente novamente!')
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
                            <th>Funções</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {hourList.map(hour => (
                            <tr key={hour.id_programacao} className="tbody">
                                <td>{hour.nome_programacao}</td>
                                <td className="action-hour">
                                    <a href={`/hourly-edit/${hour.id_programacao}`}><FiEdit /></a>
                                    <button onClick={() => handleConfirm(hour)}><FiTrash /></button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}