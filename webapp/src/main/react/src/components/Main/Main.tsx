import { useState } from 'react';

import { Routes, Route, Outlet, Link } from "react-router-dom";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';

interface Properties {
    company: string;
}

function Main({ company }: Properties) {
    return (<>
        <Header company={company} />
        <main>
            <Outlet />
        </main>
        <Footer company={company} />
    </>);
};

export default Main;