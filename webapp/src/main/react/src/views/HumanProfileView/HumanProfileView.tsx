import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './HumanProfileView.css';
import Human from '../../data/Human';
import HumanIdentityCard from '../../components/HumanIdentityCard/HumanIdentityCard';

interface Properties {
    human: Human|null;
}

function HumanProfileView({human}: Properties) {
    const [meAsHuman, setMeAsHuman] = useState<Human>();
    const navigate = useNavigate();

    useEffect(() => {
        if (!human) {
            navigate("/");
        } else {
            setMeAsHuman(human);
        }
    }, []);
    
    
    if (!meAsHuman) {
        navigate("/");
        return (null);
    }

    return (
        <section className='HumanProfileView-Component' id='home'>
            <h1>Mon Profil Particulier</h1>
            <HumanIdentityCard human={meAsHuman} />
        </section>
    );
}

export default HumanProfileView;
