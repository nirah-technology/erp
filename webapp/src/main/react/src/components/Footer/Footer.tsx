import React, { useState } from 'react';
import './Footer.css';
import { LocalDateTime } from '@nirahtech/datetime';

interface Properties {
    company: string;
}

function Footer({company}: Properties) {
  return (
    <footer className='Footer-Component'>
        
        <p>&copy; {company} {LocalDateTime.now().getYear()}</p>
    </footer>
  );
}

export default Footer;
