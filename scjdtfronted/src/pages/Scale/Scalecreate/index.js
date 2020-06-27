import React, { useState, useEffect } from 'react'

import { FiArrowLeft } from 'react-icons/fi'
import { Link, useHistory } from 'react-router-dom'

import './style.css'
import api from '../../../services/api'

export default function scale() {
  const history = useHistory()
  const [idProgramation, setIdProgramation] = useState(0)
  const [idCollaborator, setIdCollaborator] = useState(0)
  const [programationList, setProgramationList] = useState([])
  const [collaboratorList, setCollaboratorList] = useState([])
  const [collaboratorProgramationListId, setCollaboratorProgramationList] = useState([])

  useEffect(() => {
    api.get('colaborador/list').then(response => {
      setCollaboratorList(response.data)
    })
  }, [])

  useEffect(() => {
    api.get('programacao/list').then(response => {
      setProgramationList(response.data)
    })
  }, [])

  useEffect(() => {
    api.get(`escala/buscar/${idCollaborator}`).then(response => {
      setCollaboratorProgramationList(response.data)
    })
  }, [idCollaborator])

  async function handleRegisterCategory(e) {
    e.preventDefault()

    if (idProgramation === 0 || idCollaborator === 0) {
      alert('Você deve selecionar uma programação horária e um colaborador')
      return
    }

    // const data = {
    //   id_programacao: idProgramation,
    //   id_colaborador: idCollaborator
    // }

    // try {
    //     const response = await api.post('categoria/add', data)

    //     alert(response.data)
    //     history.push('/dashboard')
    // }
    // catch (err) {
    //     alert(err.reponse.data)
    // }
  }

  return (
    <div className="register-scale">
      <div className="scale-instruction">
        <div className="scale-test">
          <section>
            <h1>Cadastro de escala padrão</h1>
            <p>Faça o cadastro de uma nova escala padrão</p>

            <Link className="back-link" to="/dashboard">
              <FiArrowLeft size={16} color="#E02041" />
                        Voltar
                </Link>
          </section>

          <form onSubmit={handleRegisterCategory}>
            <select onChange={e => setIdCollaborator(e.target.value)}>
              <option disabled selected value="0">Selecione o Colaborador</option>
              {collaboratorList.map(collaborator => (
                <option
                  key={collaborator.id_colaborador}
                  value={collaborator.id_colaborador}>
                  {collaborator.nome_colaborador}
                </option>
              ))}
            </select>

            <select
              disabled={idCollaborator <= 0}
              onChange={e => setIdProgramation(e.target.value)}
              style={{ marginTop: 8 }}
            >
              <option disabled selected value="0">Selecione a Programação Horária</option>
              {programationList.map(programation => (
                <option 
                  key={programation.id_programacao} 
                  value={programation.id_programacao}>{programation.nome_programacao} - Horário: {programation.inicio_horario.slice(0,5)} até {programation.fim_horario.slice(0,5)}</option>
              ))}
            </select>
            <button className="button">Salvar</button>
          </form>
        </div>
        {idCollaborator ?
          (
            <div className="table-container">
              <table className="programation-list">
                <thead>
                  <tr>
                    <th>Nome Programação</th>
                    <th>Horário Inicio</th>
                    <th>Horário Fim</th>
                  </tr>
                </thead>
                <tbody>
                  {collaboratorProgramationListId.map(programation => (
                    <tr key={programation.id_escala}>
                      <td>{programation.nome_programacao}</td>
                      <td>{programation.inicio_horario.slice(0,5)}</td>
                      <td>{programation.fim_horario.slice(0,5)}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )
          :
          (
            <div></div>
          )}

      </div>
    </div>
  );
}