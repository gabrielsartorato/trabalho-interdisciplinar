import React, { useState, useEffect } from 'react'

import { FiArrowLeft } from 'react-icons/fi'
import { Link, useHistory } from 'react-router-dom'

import NumberFormat from 'react-number-format'

import './style.css'
import api from '../../../services/api'

export default function Category() {    
    const history = useHistory()
    const [name, setName] = useState('')
    const [salary, setSalary] = useState('')
    const [description, setDescription] = useState('')
    const [categoryList, setCategoryList] = useState([])
    const [idCategory, setIdCategory] = useState(0)

    useEffect(() => {
        getCategory()
    }, [])

    function getCategory() {
        api.get('categoria/list').then(response => {
            setCategoryList(response.data)
        })
    }
    
    async function handleRegisterCategory(e) {
        e.preventDefault()

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

        try {
            const response = await api.post('funcao/add', data)

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
                    <h1>Cadastro de funções</h1>
                    <p>Faça o cadastro de uma nova função</p>

                    <Link className="back-link" to="/dashboard">
                            <FiArrowLeft size={16} color="#E02041"/>
                            Voltar
                    </Link>
                </section>

                <form onSubmit={handleRegisterCategory}>
                    <input 
                        placeholder="Nome da categoria"
                        value={name}
                        onChange={e => setName(e.target.value)}
                    />
                    <NumberFormat
                        placeholder="Salário"
                        prefix="R$"
                        fixedDecimalScale={true}
                        value={salary}
                        onChange={e => setSalary(e.target.value)}
                    />
                    <textarea 
                        placeholder="Descrição"
                        value={description}
                        onChange={e => setDescription(e.target.value)}
                    />
                    <select onChange={e => setIdCategory(e.target.value)}>
                        <option value="0">Selecione</option>
                        {categoryList.map(cat => (
                            <option key={cat.id_categoria} value={cat.id_categoria}>{cat.nome_categoria}</option>
                        ))}
                    </select>
                    <button className="button">Salvar</button>
                </form>
            </div>
        </div>
    );
}