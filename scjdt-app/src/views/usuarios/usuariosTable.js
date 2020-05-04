import React from 'react'

export default props => {

    const rows = props.usuarios.map(usuario => {
        return (
            <tr key={usuario.idUsuario}>
                <td>{usuario.nomeUsuario}</td>
                <td>
                    <button type="button" className="btn btn-primary"
                        onClick={e => props.editAction(usuario.idUsuario)}>
                        Editar
                    </button>
                    <button type="button"
                        className="btn btn-danger"
                        onClick={e => props.deleteAction(usuario)}>
                        Deletar
                    </button>
                </td>
            </tr>
        )
    })

    return (
        <table className="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </table>
    )
}