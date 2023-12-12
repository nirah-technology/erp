import React, { useState } from 'react';
import './Footer.css';

interface Properties {
    company: string;
}

function Footer({company}: Properties) {
  return (
    <footer className='Footer-Component'>
        
        <p>&copy; {company} {(new Date()).getFullYear()}</p>
    </footer>
  );
}

export default Footer;
