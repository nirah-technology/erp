import React from 'react';

import { NavLink } from "react-router-dom";
import Logo from '../Logo/Logo';
import './MainMenu.css';

interface Properties {
    company: string;
}

function MainMenu({company}: Properties) {
  return (
    <nav className='MainMenu-Component'>
        <ul id='left'>
            <li>
                <Logo company={company} />
            </li>
        </ul>
        <ul id='right'>
            <li>
                <NavLink to="/me" className={({ isActive, isPending }) => isPending ? "pending" : isActive ? "active" : ""}>Moi</NavLink>
            </li>
            <li>
                <NavLink to="/my-companies" className={({ isActive, isPending }) => isPending ? "pending" : isActive ? "active" : ""}>Entreprises</NavLink>
            </li>
        </ul>
        <ul>
            
        </ul>
    </nav>
  );
}

export default MainMenu;
