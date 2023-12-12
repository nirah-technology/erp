import React from 'react';
import './Logo.css';
import icon from './Logo.png';

interface Properties {
    company: string
}

function Logo({company}: Properties) {
  
  return (
    <div className='Logo-Component'>
        <a href={process.env.REACT_APP_ROUTER_BASE || '/'}>
            <img src={icon} alt={company} />
            <span>{company}</span>
        </a>
    </div>
  );
}

export default Logo;