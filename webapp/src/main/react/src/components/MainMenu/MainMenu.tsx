import React from 'react';

import { Link, NavLink } from "react-router-dom";
import Logo from '../Logo/Logo';
import './MainMenu.css';
import Company from '../../data/Company';

interface Properties {
    company: string;
    selectedCompany: Company|null;
}

function MainMenu({ company, selectedCompany }: Properties) {
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
                    <ul id='human'>
                        <li>
                            <Link to={"/me/profile"}>Profile</Link>
                        </li>
                        <li>
                            <Link to={"/me/planning"}>Calendrier</Link>
                        </li>
                        <li>
                            <Link to={"/me/jobs"}>Jobs</Link>
                        </li>
                    </ul>
                </li>
                {
                    (selectedCompany) ? (
                        <li>
                            <NavLink to="/work" className={({ isActive, isPending }) => isPending ? "pending" : isActive ? "active" : ""}>{selectedCompany.getName()}</NavLink>
                            <ul id='company'>
                                <li><Link to={"/work/profile"}>Profile</Link></li>
                                <li><Link to={"/work/client"}>Clients</Link></li>
                                <li><Link to={"/work/projects"}>Projets</Link></li>
                            </ul>
                        </li>
                    ) : (null)
                }
            </ul>
            <ul>

            </ul>
        </nav>
    );
}

export default MainMenu;
