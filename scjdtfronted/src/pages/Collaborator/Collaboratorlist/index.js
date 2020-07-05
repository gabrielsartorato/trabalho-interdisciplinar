import React, { useEffect, useState } from 'react'

import { useHistory } from 'react-router-dom'

import './style.css'

import { FiPower, FiEdit, FiHome } from 'react-icons/fi'
import timerSvg from '../../../assets/clock.svg'
import { adjustName } from '../../../libs/utils'

import api from '../../../services/api'


export default function Categorylist() {
  const userName = localStorage.getItem('nomeUsuario')
  const history = useHistory()
  const [collaboratorList, setCollaboratorList] = useState([]);

  const user = adjustName(userName)

  function handleLogout() {
    localStorage.clear();
    history.push('/')
  }

  function handleDashboard() {
    history.push('/dashboard')
  }

  useEffect(() => {
    api.get('colaborador/list').then(response => {
      setCollaboratorList(response.data)
    })
  }, [])

  return (
    <div className="dashboard-container">
      <header>
        <img src={timerSvg} alt="timer" />
        <span>Bem vindo, {user} </span>
        <div className="button-group">
          <button onClick={() => handleDashboard()}><FiHome size={18} /></button>
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
              <th>Ativo</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {collaboratorList.map(collaborator => (
              <tr key={collaborator.id_colaborador} className="tbody">
                <td>{collaborator.nome_colaborador}</td>
                {collaborator.ativo === 1 ?
                  (<td>Ativo</td>)
                  : collaborator.ativo === 0 ?
                    (<td>Inativo</td>)
                    :
                    (<td>Afastado</td>)
                }
                <td className="action-collaborator">
                  <a id="ref" href={`/collaborator-edit/${collaborator.id_colaborador}`}><FiEdit className="image" /></a>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}