import React, { useState } from 'react';
import './HomeView.css';
import { Link } from 'react-router-dom';

interface Properties {
    company: string;
}

function HomeView({company}: Properties) {
    
    return (
        <section className='Home-Component' id='home'>
            
        </section>
    );
}

export default HomeView;
