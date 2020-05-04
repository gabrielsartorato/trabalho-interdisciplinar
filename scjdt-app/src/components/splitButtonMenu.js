import React, { Component } from 'react';
import { SplitButton } from 'primereact/splitbutton';

import history from '../main/history'

export class SplitButtonFinal extends Component {

    constructor() {
        super();
        this.state = {
            usuarios: [
                { label: 'Cadastrar', icon: 'pi pi-save', command: (e) => { history.push('/cadastro-usuario') } },
                { label: 'Consultar', icon: 'pi pi-list', command: (e) => { history.push('/consulta-usuarios') } },
            ]
        }
    }

    render() {
        return (
            <div>
                <div className="p-splitbutton">
                    <SplitButton label="Usuários" icon="pi pi-align-justify" model={this.state.usuarios} className="p-button-secondary" style={{ width: '300px' }}></SplitButton>
                </div>
            </div>
        )
    }
}

export default SplitButtonFinal