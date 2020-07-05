import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { useHistory } from 'react-router-dom'

import { FiArrowLeft } from 'react-icons/fi'

import api from '../../../services/api'
import './style.css'

export default function UserCreate(props) {

  const idVacation = props.match.params.id

  const history = useHistory()

  const [collaboratoList, setCollaboratorList] = useState([])
  const [dateStart, setDateStart] = useState('')
  const [dateEnd, setDateEnd] = useState('')
  const [collaboratoId, setCollaboratorId] = useState('')
  const [collaboratorName, setCollaboratorName] = useState('')

  useEffect(() => {
    api.get(`ferias/buscar/${idVacation}`).then(response => {
      setCollaboratorId(response.data.id_colaborador)
      setDateStart(response.data.data_inicio)
      setDateEnd(response.data.data_fim)
    })
  }, [])

  // useEffect(() => {
  //   api.get('colaborador/list').then(response => {
  //     setCollaboratorList(response.data)
  //   })
  // }, [])

  async function handleEdit(e) {
    e.preventDefault();

    const data = {
      id_colaborador: collaboratoId,
      data_inicio: dateStart,
      data_fima: dateEnd
    }

    try {
      await api.put(`ferias/edit/${idVacation}`, data)

      history.push('/dashboard')
    }
    catch (err) {
      alert('Erro ao editar, tente novamente!')
    }
  }

  return (
    <div className="register-container">
      <div className="content">
        <section>
          <h1>Edição de função</h1>
          <p>Faça a alteração da função</p>

          <Link className="back-link" to="/dashboard">
            <FiArrowLeft size={16} color="#E02041" />
                        Voltar
                    </Link>
        </section>
        <form onSubmit={handleEdit}>
          <input
            placeholder="Nome do colaborador"
            {...collaboratoList.map(collaborator => {
              if(collaborator.id_colaborador === collaboratoId) {
                setCollaboratorName(collaborator.nome_colaborador)
              }
            })}
            value={collaboratorName}
            disabled
          />
          <button className="button">Editar</button>
        </form>
      </div>
    </div>

  );
}