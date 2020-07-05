import React, { useState } from 'react'

import { FiArrowLeft } from 'react-icons/fi'
import { Link, useHistory } from 'react-router-dom'

import InputMask from 'react-input-mask';
import { adjustBirthDateToBackEnd } from '../../../libs/utils'

import './style.css'
import api from '../../../services/api'
import { useEffect } from 'react'

export default function vacation() {
  const history = useHistory()
  const [collaboratorList, setCollaboratorList] = useState([])
  const [collaboratorId, setCollaboratorId] = useState(0)
  const [dateStart, setDateStart] = useState('')
  const [dateEnd, setDateEnd] = useState('')

  useEffect(() => {
    api.get('colaborador/list').then(response => {
      setCollaboratorList(response.data)
    })
  }, [])

  async function handleRegisterCategory(e) {
    e.preventDefault()

    console.log(adjustBirthDateToBackEnd(dateStart))
    console.log(adjustBirthDateToBackEnd(dateEnd))

    const data = {
      id_colaborador: collaboratorId,
      data_inicio: adjustBirthDateToBackEnd(dateStart),
      data_fim: adjustBirthDateToBackEnd(dateEnd)
    }

    if(collaboratorId === 0) {
      alert('Selecione um colaborador')
      return
    }

    if(dateStart === "" || dateEnd === "") {
      alert('Data de ínicio e data fim devem ser preenchidas')
    }

    try {
      const response = await api.post('ferias/add', data)

      alert(response.data)
      history.push('/dashboard')
    }
    catch (err) {
      alert(err.response.data)
    }
  }

  return (
    <div className="register-vacation">
      <div className="vacation-instruction">
        <section>
          <h1>Cadastro de férias</h1>
          <p>Faça o cadastro de uma nova programação de férias</p>

          <Link className="back-link" to="/dashboard">
            <FiArrowLeft size={16} color="#E02041" />
                            Voltar
          </Link>
        </section>

        <form onSubmit={handleRegisterCategory}>
          <select onChange={e => setCollaboratorId(e.target.value)} style={{marginBottom: 8}}>
            <option selected disabled value="0">Selecione um Colaborador</option>
            {collaboratorList.map(collaborator => (
              <option 
                key={collaborator.id_colaborador} 
                value={collaborator.id_colaborador}>
                  {collaborator.nome_colaborador}
              </option>
            ))}
          </select>
          <div className="input-group">
            <InputMask
              mask="99/99/9999"
              placeholder="Data Inicio"
              value={dateStart}
              onChange={e => setDateStart(e.target.value)}
            />
            <InputMask
              mask="99/99/9999"
              placeholder="Data Fim"
              value={dateEnd}
              onChange={e => setDateEnd(e.target.value)}
            />
          </div>
          <button className="button">Salvar</button>
        </form>
      </div>
    </div>
  );
}