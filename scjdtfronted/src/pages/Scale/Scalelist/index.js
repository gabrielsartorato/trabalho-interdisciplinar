import React, { useEffect, useState } from 'react'

import { useHistory } from 'react-router-dom'

import { FiPower, FiEdit, FiHome } from 'react-icons/fi'
import timerSvg from '../../../assets/clock.svg'
import { adjustName } from '../../../libs/utils'

import api from '../../../services/api'

import './style.css'

export default function Categorylist() {
    const userName = localStorage.getItem('nomeUsuario')
    const history = useHistory()
    const [scalePatternList, setScalePatternList] = useState([]);

    const user = adjustName(userName)

    function handleLogout() {
        localStorage.clear();
        history.push('/')
    }

    function handleDashboard() {
        history.push('/dashboard')
    }

    useEffect(() => {
        api.get('escala/listarTodos').then(response => {
          setScalePatternList(response.data)
        })
    }, [])

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
                            <th>Nome do Colaborador</th>
                            <th>Nome da Programação</th>
                            <th>Status Programação</th>
                            <th>Horário Ínicio</th>
                            <th>Horário Fim</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {scalePatternList.map(scale => (
                            <tr key={scale.id_escala} className="tbody">
                                <td>{scale.nome_colaborador}</td>
                                <td>{scale.nome_programacao}</td>
                                <td>{scale.inicio_horario}</td>
                                <td>{scale.fim_horario}</td>
                                <td>{scale.status === 0 ? "Inativo" : "Ativo"}</td>
                                <td className="action">
                                    <a href={`/scale-edit/${scale.id_escala}`}><FiEdit /></a>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}