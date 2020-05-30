import React, { useState, useEffect } from 'react'

import { FiArrowLeft } from 'react-icons/fi'
import { Link, useHistory } from 'react-router-dom'

import InputMask from 'react-input-mask';

import './style.css'
import api from '../../../services/api'

export default function Category() {    
    const history = useHistory()
    const [name, setName] = useState('')
    const [initProg, setInitProg] = useState('')
    const [finishProg, setFinishProg] = useState('')
    const [description, setDescription] = useState('')
    const [idLocal, setIdLocal] = useState(0)
    const [listLocal, setListLocal] = useState([])
    const [statusProg, setStatusProg] = useState(0)


    useEffect(() => {
        getLocalList()
    }, [])

    function getLocalList() {
        api.get('localtrabalho/list').then(response => {
            setListLocal(response.data)
        })
    }
    
    async function handleRegisterPrograming(e) {
        e.preventDefault()

        if(idLocal === 0) {
            return alert('Selecione uma categoria de função!');
        }

        if(name === "" || initProg === "" || finishProg === "" || statusProg === "") {
            return alert('Todos os campos devem ser preenchidos!')
        }


        const data = {
            nome_programacao: name,
            inicio_horario: initProg,
            fim_horario: finishProg,
            descricao: description,
            id_local_inicio: idLocal,
            status_programacao: statusProg
        }

        try {
            const response = await api.post('programacao/add', data)

            alert(response.data)
            history.push('/dashboard')
        }
        catch (err) {
            alert(err.reponse.data)
        }
        
    }
    
    return (
        <div className="register-category">
            <div className="category-instruction">
                <section>
                    <h1>Cadastro de programação horária</h1>
                    <p>Faça o cadastro de uma nova programação</p>

                    <Link className="back-link" to="/dashboard">
                            <FiArrowLeft size={16} color="#E02041"/>
                            Voltar
                    </Link>
                </section>

                <form onSubmit={handleRegisterPrograming}>
                    <input 
                        placeholder="Nome da programação *"
                        value={name}
                        onChange={e => setName(e.target.value)}
                    />
                    <div className="input-group">
                        <InputMask
                            mask="99:99" 
                            placeholder="Horário Ínicio *"
                            value={initProg}
                            onChange={e => setInitProg(e.target.value)}
                        />
                        <InputMask
                            mask="99:99" 
                            placeholder="Horário final *"
                            value={finishProg}
                            onChange={e => setFinishProg(e.target.value)}
                        />
                    </div>
                    <textarea 
                        placeholder="Descrição"
                        value={description}
                        onChange={e => setDescription(e.target.value)}
                    /> 
                    <div className="input-group"> 
                        <select onChange={e => setIdLocal(e.target.value)}>
                            <option value="0">Selecione o local de inicio</option>
                            {listLocal.map(local => (
                                <option key={local.id_local_trabalho} value={local.id_local_trabalho}>{local.nome_local_trabalho}</option>
                            ))}
                        </select>
                        <select onChange={e => setStatusProg(e.target.value)} style={{ width: 130, marginLeft: 8}}>
                            <option disabled selected>...</option>
                            <option value="1">Ativo</option>
                            <option value="0">Inativo</option>
                        </select>
                    </div>
                    <button className="button">Salvar</button>
                </form>
            </div>
        </div>
    );
}