import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { useHistory } from 'react-router-dom'

import './style-edit.css'

import { FiArrowLeft } from 'react-icons/fi'
import { adjustBirthDateToForm, adjustBirthDateToBackEnd } from '../../../libs/utils'

import InputMask from 'react-input-mask';
import api from '../../../services/api'
import axios from 'axios'

export default function UserCreate(props) {

    const collaboratorId = props.match.params.id

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
    const [active, setActive] = useState(0)

    useEffect(() => {
        getCollaborator()
    }, [])

    async function getCollaborator() {
        const response = await api.get(`colaborador/buscar/${collaboratorId}`)
        if (!response.data) {
            alert('Colaborador não encontrado')

            return history.push('/dashboard')
        }
        setName(response.data.nome_colaborador)
        setBirthDate(adjustBirthDateToForm(response.data.data_nascimento))
        setRg(response.data.rg)
        setCpf(response.data.cpf)
        setWorkload(response.data.carga_horaria)
        setEmail(response.data.email)
        setCep(response.data.cep)
        setHomeType(response.data.tipo_moradia)
        setAddress(response.data.rua)
        setNeighborhood(response.data.bairro)
        setCity(response.data.cidade)
        setState(response.data.estado)
        setNumber(response.data.numero)
        setComplement(response.data.complemento)
        setFunctionId(response.data.id_funcao)
        setActive(response.data.ativo)

        await api.get('funcao/list').then(response => {
            setFunctions(response.data)
        })

    }

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

        handleEdit(e)
    }

    async function handleEdit(e) {
        e.preventDefault();

        let date = adjustBirthDateToBackEnd(birthDate)

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
            ativo: active
        }

        console.log(data)

        try {
            await api.put(`colaborador/edit/${collaboratorId}`, data)

            history.push('/dashboard')
        }
        catch (err) {
            alert('Erro ao editar, tente novamente!')
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
                            value={hometype}
                            style={{ width: 180 }}
                            onChange={e => setHomeType(e.target.value)}
                        >
                            <option selected={hometype === "apartamento"} value="apartamento">Apartamento</option>
                            <option selected={hometype === "casa"} value="casa">Casa</option>
                            <option selected={hometype === "sobrado"} value="sobrado">Sobrado</option>
                            <option selected={hometype === "edicula"} value="edicula">Edícula</option>
                            <option selected={hometype === "loft"} value="loft">Loft</option>
                            <option selected={hometype === "mansao"} value="mansao">Mansão</option>
                            <option selected={hometype === "bangalo"} value="bangalo">Bangalô</option>
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
                                <select value={state} id="address" style={{ width: 100 }} onChange={e => setState(e.target.value)}>
                                    <option selected={state === "AC"} value="AC">AC</option>
                                    <option selected={state === "AL"} value="AL">AL</option>
                                    <option selected={state === "AL"} value="AL">AP</option>
                                    <option selected={state === "AM"} value="AM">AM</option>
                                    <option selected={state === "BA"} value="BA">BA</option>
                                    <option selected={state === "CE"} value="CE">CE</option>
                                    <option selected={state === "DF"} value="DF">DF</option>
                                    <option selected={state === "ES"} value="ES">ES</option>
                                    <option selected={state === "GO"} value="GO">GO</option>
                                    <option selected={state === "MA"} value="MA">MA</option>
                                    <option selected={state === "MT"} value="MT">MT</option>
                                    <option selected={state === "MS"} value="MS">MS</option>
                                    <option selected={state === "PQ"} value="PA">PA</option>
                                    <option selected={state === "PB"} value="PB">PB</option>
                                    <option selected={state === "PR"} value="PR">PR</option>
                                    <option selected={state === "PE"} value="PE">PE</option>
                                    <option selected={state === "PI"} value="PI">PI</option>
                                    <option selected={state === "RJ"} value="RJ">RJ</option>
                                    <option selected={state === "RN"} value="RN">RN</option>
                                    <option selected={state === "RO"} value="RO">RO</option>
                                    <option selected={state === "RR"} value="RR">RR</option>
                                    <option selected={state === "SC"} value="SC">SC</option>
                                    <option selected={state === "SP"} value="SP">SP</option>
                                    <option selected={state === "SE"} value="SE">SE</option>
                                    <option selected={state === "TO"} value="TO">TO</option>
                                </select>
                            )
                            :
                            (
                                <input id="address" style={{ width: 80 }} placeholder="UF" value={state}
                                    onChange={e => setState(e.target.value)} />
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
                    <div className="input-group">
                        <select value={functionId} onChange={e => setFunctionId(e.target.value)}>
                            {functions.map(func => {
                                return (
                                    <option
                                        key={func.id_funcao}
                                        selected={functionId === func.id_funcao}
                                        value={func.id_funcao}
                                    >{func.nome_funcao}
                                    </option>

                                )
                            })
                            }
                        </select>
                        <select disabled={active === 0}  onChange={e => setActive(e.target.value)} className="active" style={{width: 100, marginLeft: 8}}>
                            <option selected={active === 0} value="0">I</option>
                            <option selected={active === 1} value="1">A</option>
                        </select>
                    </div>
                    <button className="button">Salvar</button>
                </form>
            </div>
        </div>

    );
}