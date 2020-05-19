import React from 'react';

import { FiPower } from 'react-icons/fi'
import timerSvg from '../../assets/clock.svg'

import './style.css'

export default function Dashboard() {
    return (
        <div className="dashborad-container">
            <header>
                <img src={timerSvg} alt="timer"/>
                <span>Bem vindo, Gabriel</span>
                <button type="button">
                    <FiPower size={18} color="#e02041"/>
                </button>
            </header>
        </div>
    );
}
