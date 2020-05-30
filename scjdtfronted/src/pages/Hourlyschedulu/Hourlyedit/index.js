import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { useHistory } from 'react-router-dom'

import { FiArrowLeft } from 'react-icons/fi'

import NumberFormat from 'react-number-format'


import api from '../../../services/api'
import './style.css'

export default function UserCreate(props) {

    const functionUser = props.match.params.id

    const history = useHistory()

    const [name, setName ] = useState('')
    const [salary, setSalary ] = useState('')
    const [description, setDescription ] = useState('')
    const [categoryList, setCategoryList] = useState([])
    const [idCategory, setIdCategory] = useState(0)


    useEffect(() => {
        api.get(`funcao/buscar/${functionUser}`).then(response => {
            setName(response.data.nome_funcao)
            setSalary(response.data.salario_funcao)
            setDescription(response.data.descricao_funcao)
            setIdCategory(response.data.id_categoria)
        })
        api.get('categoria/list').then(response => {
            setCategoryList(response.data)
        })
    }, [])

    async function handleEdit(e) {
        e.preventDefault();

        if(idCategory === 0) {
            return alert('Selecione uma categoria de função!');
        }

        if(name === "" || salary === "" || description === "") {
            return alert('Todos os campos devem ser preenchidos!')
        }

        let salaryFormat = salary.replace("R$", "")

        const data = {
            nome_funcao: name,
            salario_funcao: salaryFormat,
            descricao_funcao: description,
            id_categoria: idCategory
        }
        console.log(data)
        
        try {
            await api.put(`funcao/edit/${functionUser}`, data)

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
                    <h1>Edição de função</h1>
                    <p>Faça a alteração da função</p>

                    <Link className="back-link" to="/dashboard">
                        <FiArrowLeft size={16} color="#E02041"/>
                        Voltar
                    </Link>
                </section>
                <form onSubmit={handleEdit}>
                    <input 
                        placeholder="Nome da função"
                        value={name}
                        onChange={e => setName(e.target.value)}
                    />
                    <NumberFormat
                        prefix="R$" 
                        placeholder="Salário"
                        value={salary}
                        onChange={e => setSalary(e.target.value)}
                    />
                    <textarea
                        placeholder="Descrição" 
                        value={description}
                        onChange={e => setDescription(e.target.value)}
                    />
                    <select onChange={e => setIdCategory(e.target.value)}>
                        {categoryList.map(cat => (
                            <option key={cat.id_categoria} selected={cat.id_categoria === idCategory} value={cat.id_categoria}>{cat.nome_categoria}</option>
                        ))}
                    </select>
                    <button className="button">Editar</button>
                </form>
            </div>
        </div>
        
    );
}