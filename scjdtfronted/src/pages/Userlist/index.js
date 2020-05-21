import React, { useEffect, useState } from 'react'

import { useHistory } from 'react-router-dom'

import { FiPower, FiTrash, FiEdit, FiHome } from 'react-icons/fi'
import timerSvg from '../../assets/clock.svg'

import api from '../../services/api'

import './style.css'

export default function Userlist() {
    const userName = localStorage.getItem('nomeUsuario')
    const history = useHistory()
    const [userList, setUserList] = useState([]);

    let user = userName.slice(0, 1).toUpperCase()

    user = user + userName.substring(1, userName.length)

    function handleLogout() {
        localStorage.clear();
        history.push('/')
    }

    function handleDashboard() {
        history.push('/dashboard')
    }

    useEffect(() => {
        api.get('usuario/list').then(response => {
            setUserList(response.data)
        })
    }, [])

    async function handleDeleteUser(id) {
        try {
            await api.delete(`usuario/delete/${id}`)
            setUserList(userList.filter(user => user.idUsuario !== id))
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
                            <th>Usuário</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {userList.map(user => (
                            <tr key={user.idUsuario} className="tbody">
                                <td>{user.nomeUsuario}</td>
                                <td className="action">
                                    <a href={`/user-edit/${user.idUsuario}`}><FiEdit /></a>
                                    <button onClick={() => handleDeleteUser(user.idUsuario)}><FiTrash /></button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}