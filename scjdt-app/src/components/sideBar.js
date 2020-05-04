import React from 'react'
import style from 'styled-components'

import history from '../main/history'

import SplitButtonFinal from '../components/splitButtonMenu'

import { Button } from 'primereact/button'

const StyledSidNav = style.div`
    position: fixed;
    height: 100%;
    width: 200px;
    z-index: 1;
    top: 0em;
    background-color: #222;
    overflow-x: hidden;
    padding-top: 10ox
`;

function SideBar() {

    return (

        <div className="form-group" style={{ position: 'fixed', height: '100%', width: '140px', zIndex: '1', top: '0em', backgroundColor: '	#F5F5F5', overflowX: 'hidden', paddingTop: '10px', border: '1px solid #FAFAD2' }}>
            <SplitButtonFinal />
        </div>
    )
}

export default SideBar