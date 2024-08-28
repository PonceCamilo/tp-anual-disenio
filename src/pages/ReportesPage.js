import React, { useState } from 'react';
import { Button, Collapse } from 'react-bootstrap';
import "../assets/styles/Reportes.css"

function ReportesPage() {
    const [openSection, setOpenSection] = useState(null);
    {/*const [listaReporte, setListaReporte] = useState([]);*/}

    {/* TEST */}
    const listaReporte = [
        {
            nombreHeladera: "Heladera de Buenos Aires",
            fallas: 4
        },
        {
            nombreHeladera: "Heladera de Córdoba",
            fallas: 2
        },
        {
            nombreHeladera: "Heladera de Mendoza",
            fallas: 3
        },
        {
            nombreHeladera: "Heladera de Rosario",
            fallas: 1
        },
        {
            nombreHeladera: "Heladera de Salta",
            fallas: 5
        }
    ];
    {/* TEST */}

    const toggleSection = (section) => {
        setOpenSection(prevSection => (prevSection === section ? null : section));
    };

    return (
        <div className="reportes-page">
            <div className='reportes-container'>
            <div className="botones">
                    <Button className="mt-1"
                        onClick={() => toggleSection('fallas')}
                        aria-controls="collapse-fallas"
                        aria-expanded={openSection === 'fallas'}>
                        Fallas por heladera
                    </Button>

                    <Button
                        className="mt-1"
                        onClick={() => toggleSection('viandasHeladera')}
                        aria-controls="collapse-viandas-heladera"
                        aria-expanded={openSection === 'viandasHeladera'}>
                        Viandas por heladera
                    </Button>
                    
                    <Button
                        className="mt-1"
                        onClick={() => toggleSection('viandasColaborador')}
                        aria-controls="collapse-viandas-colaborador"
                        aria-expanded={openSection === 'viandasColaborador'}>
                        Viandas por colaborador
                    </Button>
                </div>

                <Collapse in={openSection === 'fallas'}>
                    <div id="collapse-fallas">
                        <div className='fallas'> 
                            <p>Heladera</p>
                            <p>Fallas</p>
                        </div>
                        {listaReporte.map((heladera, index) => (
                            <div className="fallas" key={index}>
                                <p>{heladera.nombreHeladera}</p>
                                <p>{heladera.fallas}</p>
                            </div>
                        ))}
                    </div>
                </Collapse>

                <Collapse in={openSection === 'viandasHeladera'}>
                    <div id="collapse-viandas-heladera">
                        Contenido para Viandas por heladera
                    </div>
                </Collapse>

                <Collapse in={openSection === 'viandasColaborador'}>
                    <div id="collapse-viandas-colaborador">
                        Contenido para Viandas por colaborador
                    </div>
                </Collapse>

            </div>
        </div>
    );
}

export default ReportesPage;