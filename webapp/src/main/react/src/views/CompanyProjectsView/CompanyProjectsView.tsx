import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './CompanyProjectsView.css';
import { Company } from '@nirahtech/erp';
interface Properties {
    company: Company|null;
}

function CompanyProjectsView({company}: Properties) {
    const [myCompany, setMyCompany] = useState<Company>();
    const navigate = useNavigate();

    useEffect(() => {
        if (!company) {
            navigate("/");
        } else {
            setMyCompany(company);
        }
    }, []);

    if (!myCompany) {
        navigate("/");
        return (null);
    }
    return (
        <section className='CompanyProjectsView-Component' id='home'>
            <h1>Projets</h1>
            <ul>
                {Array.from(myCompany.getProjectsRegistry().getProjects()).map((project) => (
                    <li>
                        <div>
                            <h2>{project.name}</h2>
                            <button>Sélectionner</button>
                        </div>
                    </li>
                ))}
            </ul>

        </section>
    );
}

export default CompanyProjectsView;
