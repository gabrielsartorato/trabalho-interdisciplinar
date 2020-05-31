import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { useHistory } from 'react-router-dom'

import { FiArrowLeft } from 'react-icons/fi'

import InputMask from 'react-input-mask';

import api from '../../../services/api'
import './style.css'

export default function UserCreate(props) {

    const hourId = props.match.params.id

    const history = useHistory()

    const [name, setName ] = useState('')
    const [initProg, setInitProg] = useState('')
    const [finishProg, setFinishProg ] = useState('')
    const [description, setDescription ] = useState('')
    const [idLocal, setIdLocal ] = useState(0)
    const [statusProg, setStatusProg ] = useState(0)

    const [listLocal, setLocalList] = useState([])


    useEffect(() => {
        api.get(`programacao/buscar/${hourId}`).then(response => {
            setName(response.data.nome_programacao)
            setInitProg(response.data.inicio_horario)
            setFinishProg(response.data.fim_horario)
            setDescription(response.data.descricao)
            setIdLocal(response.data.id_local_inicio)
            setStatusProg(response.data.status_programacao)
        })
        api.get('localtrabalho/list').then(response => {
            setLocalList(response.data)
        })
    }, [])

    async function handleEdit(e) {
        e.preventDefault();

        if(idLocal === 0) {
            return alert('Selecione uma categoria de função!');
        }

        if(name === "" || initProg === "" || finishProg === "") {
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
        console.log(data)
        
        try {
            await api.put(`programacao/edit/${hourId}`, data)

            history.push('/dashboard')
        }
        catch(err) {
            alert('Erro ao editar, tente novamente!')
        }
        
    }

    return (
        <div className="register-container">
            <div className="content">
                <section>
                    <h1>Edição de programação horária</h1>
                    <p>Faça a alteração da programação horária</p>

                    <Link className="back-link" to="/dashboard">
                        <FiArrowLeft size={16} color="#E02041"/>
                        Voltar
                    </Link>
                </section>
                <form onSubmit={handleEdit}>
                    <input 
                        placeholder="Nome da programação"
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
                            style={{marginLeft: 8}} 
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
                            {listLocal.map(local => (
                                <option 
                                    key={local.id_local_trabalho} 
                                    selected={local.id_local_trabalho === idLocal} 
                                    value={local.id_local_trabalho}>{local.nome_local_trabalho}
                                </option>
                            ))}
                        </select>
                        <select onChange={e => setStatusProg(e.target.value)} style={{ width: 130, marginLeft: 8}}>
                            <option selected={statusProg === 1} value="1">Ativo</option>
                            <option selected={statusProg === 0} value="0">Inativo</option>
                        </select>
                    </div>
                    <button className="button">Editar</button>
                </form>
            </div>
        </div>
        
    );
}