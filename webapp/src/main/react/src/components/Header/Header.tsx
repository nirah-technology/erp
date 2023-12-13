import React from 'react';
import MainMenu from '../MainMenu/MainMenu';
import './Header.css';
import Company from '../../data/Company';

interface Properties {
    company: string;
    selectedCompany: Company|null
}

function Header({company, selectedCompany}: Properties) {
  return (
    <header className='Header-Component'>
        <MainMenu company={company} selectedCompany={selectedCompany} />
    </header>
  );
}

export default Header;
