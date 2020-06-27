import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { useHistory } from 'react-router-dom'

import { FiArrowLeft } from 'react-icons/fi'

import api from '../../../services/api'
import './style.css'

export default function UserCreate(props) {

  const scalePattern = props.match.params.id

  const history = useHistory()

  const [idProgramation, setIdProgramation] = useState(0)
  const [idCollaborator, setIdCollaborator] = useState(0)
  const [status, setStatus] = useState(0)
  const [nameProgramation, setNameProgramation] = useState('')
  const [nameCollaborator, setNameCollaborator] = useState('')
  const [startHour, setStartHour] = useState('')
  const [endHour, setEndHour] = useState('')

  useEffect(() => {
    api.get(`escala/buscarPorId/${scalePattern}`).then(response => {
      setIdProgramation(response.data.id_programacao)
      setIdCollaborator(response.data.id_colaborador)
      setStatus(response.data.status)
      setNameProgramation(response.data.nome_programacao)
      setNameCollaborator(response.data.nome_colaborador)
      setStartHour(response.data.inicio_horario)
      setEndHour(response.data.fim_horario)
    })
  }, [])

  async function handleEdit(e) {
    e.preventDefault();

    const data = {
      id_programacao: idProgramation,
      id_colaborador: idCollaborator,
      status
    }

    try {
      await api.put(`escala/edit/${scalePattern}`, data)

      history.push('/dashboard')
    }
    catch (err) {
      alert('Erro ao editar, tente novamente!')
    }
  }

  return (
    <div className="scale-container">
      <div className="content">
        <section>
          <h1>Edição de Escala Padrão</h1>
          <p>Faça a alteração da escala padrão</p>

          <Link className="back-link" to="/dashboard">
            <FiArrowLeft size={16} color="#E02041" />
                        Voltar
                    </Link>
        </section>
        <form onSubmit={handleEdit}>
          <input
            placeholder="Nome do colaborador"
            disabled
            value={nameCollaborator}
            onChange={e => setNameCollaborator(e.target.value)}
          />
          <input
            placeholder="Nome da programação"
            disabled
            value={nameProgramation}
            onChange={e => setNameProgramation(e.target.value)}
          />
          <div className="input-group">
            <input disabled value={startHour.slice(0, 5)} />
            <input disabled value={endHour.slice(0, 5)} />
            <select 
              onChange={e => setStatus(e.target.value)} 
              className="active" 
              style={{width: 100, marginLeft: 8, marginTop: 8}}
            >
              <option selected={status === 0} value="0">I</option>
              <option selected={status === 1} value="1">A</option>a
            </select>
          </div>
          <button className="button">Editar</button>
        </form>
      </div>
    </div>

  );
}