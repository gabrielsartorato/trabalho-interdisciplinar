import ApiService from '../apiservice'

import ErroValicadao from '../service/exception/erroValidacao'

class UsuarioService extends ApiService {

    constructor() {
        super('/usuario')
    }

    save(user) {
        return this.post('/add', user)
    }

    consultar() {
        return this.get('/list')
    }

    deletar(id) {
        return this.delete(`/delete/${id}`)
    }

    obterPorId(id) {
        return this.get(`/buscar/${id}`)
    }

    atualizar(usuario) {
        return this.put(`/edit/${usuario.idUsuario}`, usuario)
    }

    validar(usuario) {
        const erros = []

        if (!usuario.nomeUsuario) {
            erros.push('O campo Nome é obrigatório');
        }

        if (!usuario.senha) {
            erros.push('O campo senha é obrigatório')
        }

        if (erros && erros.length > 0) {
            throw new ErroValicadao(erros);
        }
    }

}

export default UsuarioService;