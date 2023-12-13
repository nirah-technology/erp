import React from 'react';
import './WorkTimeWeekSheet.css';

interface Properties {
    
}

function WorkTimeWeekSheet({ }: Properties) {

    return (
        <div className='WorkTimeWeekSheet-Component'>
            <table>
                <thead>
                    <tr>
                        <th>Day Of Week</th>
                        <th>Working Times</th>
                        <th>Total Wroked Hours</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>Lundi (31/12/2023)</th>
                        <td>
                            <ul>
                                <li>
                                    <div>from: <span>07:00</span> to: <span>12:00</span></div>
                                    <div>from: <span>16:00</span> to: <span>19:00</span></div>
                                </li>
                            </ul>
                        </td>
                        <td>10</td>
                    </tr>
                    <tr>
                        <th>Mardi (31/12/2023)</th>
                        <td>
                            <ul>
                                <li>
                                    <div>from: <span>09:30</span> to: <span>11:45</span></div>
                                    <div>from: <span>16:00</span> to: <span>19:00</span></div>
                                </li>
                            </ul>
                        </td>
                        <td>7</td>
                    </tr>
                    <tr>
                        <th>Mercredi (31/12/2023)</th>
                        <td></td>
                        <td>0</td>
                    </tr>
                    <tr>
                        <th>Jeudi (31/12/2023)</th>
                        <td>
                            <ul>
                                <li>
                                    <div>from: <span>07:30</span> to: <span>10:30</span></div>
                                    <div>from: <span>16:00</span> to: <span>19:00</span></div>
                                    <div>from: <span>21:00</span> to: <span>22:00</span></div>
                                </li>
                            </ul>
                        </td>
                        <td>12</td>
                    </tr>
                    <tr>
                        <th>Vendredi (31/12/2023)</th>
                        <td>
                            <ul>
                                <li>
                                    <div>from: <span>16:00</span> to: <span>19:00</span></div>
                                </li>
                            </ul>
                        </td>
                        <td>3</td>
                    </tr>
                    <tr>
                        <th>Samedi (31/12/2023)</th>
                        <td>
                            <ul>
                                <li>
                                    <div>from: <span>09:00</span> to: <span>17:00</span></div>
                                </li>
                            </ul>
                        </td>
                        <td>1</td>
                    </tr>
                    <tr>
                        <th>Dimanche (31/12/2023)</th>
                        <td></td>
                        <td>0</td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}

export default WorkTimeWeekSheet;