import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './CompaniesView.css';
import { Company } from '@nirahtech/erp';

interface Properties {
    companies: Array<Company>;
    onSelectCompany: Function;
}

function CompaniesView({companies, onSelectCompany}: Properties) {
    const [myCompanies] = useState<Array<Company>>(companies);
    
    const onLinkClickedHandler = (event: any, company: Company) => {
        if (onSelectCompany) {
            onSelectCompany(company);
        }
    }
    return (
        <section className='Home-Component' id='home'>
            <h2>Entreprises</h2>
                {myCompanies.map(company => <li>
                    <Link to={"/work/".concat(String(company.getSiren().code))} onClick={(e) => onLinkClickedHandler(e, company)}>
                        {company.getName()}
                    </Link>
                </li>)}
                <button type='button'>Join New Company</button>

        </section>
    );
}

export default CompaniesView;
