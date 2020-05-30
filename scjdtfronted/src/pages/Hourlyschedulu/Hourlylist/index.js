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
    const [functionList, setFunctionList] = useState([]);

    const user = adjustName(userName)

    function handleLogout() {
        localStorage.clear();
        history.push('/')
    }

    function handleDashboard() {
        history.push('/dashboard')
    }

    useEffect(() => {
        api.get('funcao/list').then(response => {
            setFunctionList(response.data)
        })
    }, [])

    function handleConfirm (func) {
        const confirmation = window.confirm(`Deseja realmente deletar o usuário ${func.nome_funcao}`)

        if(confirmation)
            handleDeleteCategory(func.id_funcao)

        window.event.preventDefault()
    }


    async function handleDeleteCategory(id) {
        try {
            await api.delete(`funcao/delete/${id}`)
            setFunctionList(functionList.filter(func => func.id_funcao !== id))
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
                        {functionList.map(func => (
                            <tr key={func.id_funcao} className="tbody">
                                <td>{func.nome_funcao}</td>
                                <td className="action">
                                    <a href={`/function-edit/${func.id_funcao}`}><FiEdit /></a>
                                    <button onClick={() => handleConfirm(func)}><FiTrash /></button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}