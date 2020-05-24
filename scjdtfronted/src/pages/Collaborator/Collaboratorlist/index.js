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
    const [categoryList, setCategoryList] = useState([]);

    const user = adjustName(userName)

    function handleLogout() {
        localStorage.clear();
        history.push('/')
    }

    function handleDashboard() {
        history.push('/dashboard')
    }

    useEffect(() => {
        api.get('categoria/list').then(response => {
            setCategoryList(response.data)
        })
    }, [])

    function handleConfirm (category) {
        const confirmation = window.confirm(`Deseja realmente deletar o usuário ${category.nomeCategoria}`)

        if(confirmation)
            handleDeleteCategory(category.idCategoria)

        window.event.preventDefault()
    }


    async function handleDeleteCategory(id) {
        try {
            await api.delete(`categoria/delete/${id}`)
            setCategoryList(categoryList.filter(category => category.idCategoria !== id))
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
                        {categoryList.map(category => (
                            <tr key={category.idCategoria} className="tbody">
                                <td>{category.nomeCategoria}</td>
                                <td className="action">
                                    <a href={`/category-edit/${category.idCategoria}`}><FiEdit /></a>
                                    <button onClick={() => handleConfirm(category)}><FiTrash /></button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}