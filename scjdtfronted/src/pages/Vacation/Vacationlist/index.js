import React, { useEffect, useState } from 'react'

import { useHistory } from 'react-router-dom'

import { FiPower, FiTrash, FiEdit, FiHome } from 'react-icons/fi'
import timerSvg from '../../../assets/clock.svg'
import { adjustName, adjustBirthDateToForm } from '../../../libs/utils'

import api from '../../../services/api'

import './style.css'

export default function Categorylist() {
  const userName = localStorage.getItem('nomeUsuario')
  const history = useHistory()
  const [vacationList, setVacationList] = useState([]);
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
    api.get('ferias/list').then(response => {
      setVacationList(response.data)
    })
  }, [])

  useEffect(() => {
    api.get('colaborador/list').then(response =>
      setCollaboratorList(response.data)
    )
  }, [])

  function handleConfirm(vacation) {

    const confirmation = window.confirm(`Deseja realmente deletar às férias ${vacation.id_ferias}`)

    if (confirmation)
      handleDeleteVacation(vacation.id_ferias)

    window.event.preventDefault()
  }

  async function handleDeleteVacation(id) {
    try {
      await api.delete(`ferias/delete/${id}`)
      setVacationList(vacationList.filter(vacation => vacation.id_ferias !== id))
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
              <th>Nome Colaborador</th>
              <th>Data Inicio</th>
              <th>Data Fim</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {vacationList.map(vacation => (
              <tr key={vacation.id_categoria} className="tbody">
                {collaboratorList.map(collaborator => {
                  if (collaborator.id_colaborador === vacation.id_colaborador) {
                    vacation.nome_colaborador = collaborator.nome_colaborador
                  }
                })}
                <td>{vacation.nome_colaborador}</td>
                <td>{adjustBirthDateToForm(vacation.data_inicio)}</td>
                <td>{adjustBirthDateToForm(vacation.data_fim)}</td>
                <td className="action">
                  <a href={`/category-edit/${vacation.id_ferias}`}><FiEdit /></a>
                  <button onClick={() => handleConfirm(vacation)}><FiTrash /></button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}