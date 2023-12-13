import { useState } from 'react';

import { Routes, Route, Outlet, Link } from "react-router-dom";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import Company from '../../data/Company';

interface Properties {
    company: string;
    selectedCompany: Company|null;
}

function Main({ company, selectedCompany }: Properties) {
    return (<>
        <Header company={company} selectedCompany={selectedCompany} />
        <main>
            <Outlet />
        </main>
        <Footer company={company} />
    </>);
};

export default Main;