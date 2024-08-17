import React from 'react';
import ReportIssueForm from '../components/ReportIssueForm';
import '../assets/styles/ReportIssuePage.css';


const fridges = [
    { id: 'a', name: 'Heladera A' },
    { id: 'b', name: 'Heladera B' },
    // Agrega más heladeras según sea necesario
];

const ReportIssuePage = () => {
    return (
        <div className="report-issue-page">
            <ReportIssueForm fridges={fridges} />
        </div>
    );
};

export default ReportIssuePage;