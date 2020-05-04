import React from 'react'

function SideBarItem(props) {


    return (
        <li className="nav-item">
            <a className="nav-link active" href={props.href} style={{ color: 'Black' }}>{props.label}
            </a>
        </li >
    )
}

export default SideBarItem