import React from 'react';
import MainMenu from '../MainMenu/MainMenu';
import './Header.css';

interface Properties {
    company: string;
}

function Header({company}: Properties) {
  return (
    <header className='Header-Component'>
        <MainMenu company={company}  />
    </header>
  );
}

export default Header;
