import React, { useState, useEffect } from 'react'

import { FiArrowLeft } from 'react-icons/fi'
import { Link, useHistory } from 'react-router-dom'

import InputMask from 'react-input-mask';

import './style.css'
import api from '../../../services/api'
import axios from 'axios';

export default function collaborator() {
  const history = useHistory()

  const [name, setName] = useState('')
  const [birthDate, setBirthDate] = useState('')
  const [rg, setRg] = useState('')
  const [cpf, setCpf] = useState('')
  const [workload, setWorkload] = useState('')
  const [hometype, setHomeType] = useState('')
  const [email, setEmail] = useState('')
  const [cep, setCep] = useState('')
  const [address, setAddress] = useState('')
  const [number, setNumber] = useState('')
  const [complement, setComplement] = useState('')
  const [neighborhood, setNeighborhood] = useState('')
  const [city, setCity] = useState('')
  const [state, setState] = useState('')
  const [functionId, setFunctionId] = useState(0)
  const [functions, setFunctions] = useState([])

  useEffect(() => {
    api.get('funcao/list').then(response => {
      setFunctions(response.data)
    })
  }, [])

  async function getCep(e) {
    const cepFormat = e.replace(/\D/g, "")

    if (cepFormat.length < 8) {
      alert('Cep não pode ter menos que 8 digitos!')
      setTimeout(function () {
        document.getElementById('cep').focus()
      }, 1)

    }
    const cep = await axios.get(`https://viacep.com.br/ws/${cepFormat}/json`)
    if (cep.data.erro) {

      setAddress('')
      setCity('')
      setState('')
      setNeighborhood('')

      let address = document.querySelectorAll("#address")
      address.forEach(add => {

        add.removeAttribute("disabled", "true")
      })
      return alert('Cep inválido, digite novamente ou continua manualmente!')
    }
    else {
      setCepInput(cep)
    }

  }

  function setCepInput(cep) {
    setAddress(cep.data.logradouro)
    setCity(cep.data.localidade)
    setState(cep.data.uf)
    setNeighborhood(cep.data.bairro)

    let address = document.querySelectorAll("#address")
    address.forEach(add => {
      add.setAttribute("disabled", "true")
    })
  }

  function validade(e) {
    e.preventDefault()

    if (functionId === 0) {
      return alert('Selecione uma função!')
    }

    if (name === "" || birthDate === "" || rg === "" || cpf === "") {
      return alert('Preencha os campos obrigatórios que contem *')
    }

    handleRegisterCollaborator(e)
  }

  async function handleRegisterCollaborator(e) {

    let dateArange = birthDate.split("/")

    let date = dateArange[2] + '-' + dateArange[1] + '-' + dateArange[0]

    const cpfFormated = cpf.replace(/\D/g, "")
    const rgFormated = rg.replace(/\D/g, "")
    const cepFormated = cep.replace(/\D/g, "")

    const data = {
      nome_colaborador: name,
      data_nascimento: date,
      rg: rgFormated,
      cpf: cpfFormated,
      email,
      carga_horaria: workload,
      tipo_moradia: hometype,
      cep: cepFormated,
      rua: address,
      numero: number,
      complemento: complement,
      bairro: neighborhood,
      cidade: city,
      estado: state,
      id_funcao: functionId,
      ativo: 1
    }

    console.log(data)

    try {
      const response = await api.post('colaborador/add', data)

      alert(response.data)
      history.push('/dashboard')
    }
    catch (err) {
      alert(err.response.data)
    }

  }

  return (
    <div className="register-collaborator">
      <div className="collaborator-instruction">
        <section>
          <h1>Cadastro de colaboradores</h1>
          <p>Faça o cadastro de um novo colaborador</p>

          <Link className="back-link" to="/dashboard">
            <FiArrowLeft size={16} color="#E02041" />
                            Voltar
                    </Link>
        </section>

        <form onSubmit={validade}>
          <input
            placeholder="Nome do Colaborador *"
            value={name}
            onChange={e => setName(e.target.value)}
          />
          <InputMask
            mask="99/99/9999"
            placeholder="Data Nascimento *"
            value={birthDate}
            onChange={e => setBirthDate(e.target.value)}
          />
          <div className="input-group">
            <InputMask
              mask="99.999.999-9"
              placeholder="Rg *"
              value={rg}
              onChange={e => setRg(e.target.value)}
            />
            <InputMask
              mask="999.999.999-99"
              placeholder="Cpf *"
              value={cpf}
              onChange={e => setCpf(e.target.value)}
            />
          </div>
          <InputMask
            mask="99:99"
            placeholder="Carga Horária"
            value={workload}
            onChange={e => setWorkload(e.target.value)}
          />
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={e => setEmail(e.target.value)}
          />
          <div className="input-group cep-home">
            <InputMask
              mask="99.999-999"
              id="cep"
              placeholder="Cep"
              value={cep}
              onChange={e => setCep(e.target.value)}
              onBlur={e => getCep(e.target.value)}
            />
            <select
              style={{ width: 180 }}
              onChange={e => setHomeType(e.target.value)}
            >
              <option value="apartamento">Apartamento</option>
              <option value="casa">Casa</option>
              <option value="sobrado">Sobrado</option>
              <option value="edicula">Edícula</option>
              <option value="loft">Loft</option>
              <option value="mansao">Mansão</option>
              <option value="bangalo">Bangalô</option>
            </select>
          </div>
          <input
            id="address"
            placeholder="Endereço"
            value={address}
            onChange={e => setAddress(e.target.value)}
          />
          <input
            id="address"
            placeholder="Bairro"
            value={neighborhood}
            onChange={e => setNeighborhood(e.target.value)}
          />
          <div className="input-group city-state">
            <input
              id="address"
              placeholder="Cidade"
              value={city}
              onChange={e => setCity(e.target.value)}
            />
            {address ?
              (
                <input id="address" style={{ width: 80 }} placeholder="UF" value={state}
                  onChange={e => setState(e.target.value)} />
              ) :

              (
                <select id="address" style={{ width: 100 }} onChange={e => setState(e.target.value)}>
                  <option value="AC">AC</option>
                  <option value="AL">AL</option>
                  <option value="AL">AP</option>
                  <option value="AM">AM</option>
                  <option value="BA">BA</option>
                  <option value="CE">CE</option>
                  <option value="DF">DF</option>
                  <option value="ES">ES</option>
                  <option value="GO">GO</option>
                  <option value="MA">MA</option>
                  <option value="MT">MT</option>
                  <option value="MS">MS</option>
                  <option value="PA">PA</option>
                  <option value="PB">PB</option>
                  <option value="PR">PR</option>
                  <option value="PE">PE</option>
                  <option value="PI">PI</option>
                  <option value="RJ">RJ</option>
                  <option value="RN">RN</option>
                  <option value="RO">RO</option>
                  <option value="RR">RR</option>
                  <option value="SC">SC</option>
                  <option value="SP">SP</option>
                  <option value="SE">SE</option>
                  <option value="TO">TO</option>
                </select>
              )
            }

          </div>
          <div className="input-group">
            <input
              style={{ width: 140 }}
              placeholder="Numero"
              value={number}
              onChange={e => setNumber(e.target.value)}
            />
            <input
              placeholder="Complemento"
              value={complement}
              onChange={e => setComplement(e.target.value)}
            />
          </div>
          <select onChange={e => setFunctionId(e.target.value)}>
            <option>Selecione a função</option>
            {functions.map(func => (
              <option
                key={func.id_funcao}
                value={func.id_funcao}
              >{func.nome_funcao}
              </option>
            ))}
          </select>
          <button className="button">Salvar</button>
        </form>
      </div>
    </div>
  );
}