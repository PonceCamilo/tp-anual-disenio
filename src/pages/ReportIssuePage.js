import React from 'react';
import ReportIssueForm from '../components/ReportIssueForm';
import '../assets/styles/ReportIssuePage.css';
import '../assets/styles/App.css';

const fridges = [
    { id: 'a', name: 'Heladera A' },
    { id: 'b', name: 'Heladera B' },
    // Agrega más heladeras según sea necesario
];

const ReportIssuePage = () => {
    return (
        <div className="report-issue-page d-flex">
            <div className="left-side">
                <h1>Reportar Falla Tecnica</h1>
                <p>Con tu ayuda, podemos mantener nuestras heladeras en óptimas condiciones. </p>
            </div>
            <div className="right-side">
                <ReportIssueForm fridges={fridges} />
            </div>
        </div>
    );
};

export default ReportIssuePage;