import React from 'react'

import './style.css';

import timerImg from '../../assets/clock.png';

export default function Login() {
    return (
        <div className="login-container">
            <section className="form">
            <form>
                <h1>Faça seu Login</h1>
                <input placeholder="Usuário"/>
                <input placeholder="Senha"/>
                <button className="button">Entrar</button>
            </form>
            </section>
            <img src={timerImg} alt="timer"/>
        </div>
    );
}