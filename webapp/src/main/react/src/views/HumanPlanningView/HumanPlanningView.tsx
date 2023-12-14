import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './HumanPlanningView.css';
import { Calendar } from '../../components/Calendar/Calendar';
import { Activity } from '../../entity/Activity';
import { CalendarViewType } from '../../common/CalendarViewType';
import { Day } from '../../entity/Day';
import { LocalDateTime } from '@nirahtech/datetime';
import { Company, Human } from '@nirahtech/erp';

interface Properties {
    human: Human|null;
    companies: Array<Company>;
}

function HumanPlanningView({human, companies}: Properties) {
    const [meAsHuman, setMeAsHuman] = useState<Human>();
    const [myCompanies] = useState<Array<Company>>(companies);
    const [activities, setActivities] = useState(new Array<Activity>());
    const [calendarView, setCalendarView] = useState<CalendarViewType>(CalendarViewType.WEAK);

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
        <section className='HumanPlanningView-Component' id='home'>
            <h1>Mon Planning</h1>
            <fieldset>
                <legend>Calendriers</legend>
                <div>
                    <input type="checkbox" name="" id="" />
                    <label htmlFor="">Jours Fériés</label>
                </div>
                <div>
                    <input type="checkbox" name="" id="" />
                    <label htmlFor="">{human?.firstName} {human?.lastName}</label>
                </div>
                {
                    companies.map((company) => (<div>
                        <input type="checkbox" name="" id="" />
                        <label htmlFor="">{company.getName()}</label>
                    </div>))
                }
                <div>
                </div>
            </fieldset>
            <Calendar events={activities} now={new Day(LocalDateTime.now())}  view={calendarView} />
        </section>
    );
}

export default HumanPlanningView;
