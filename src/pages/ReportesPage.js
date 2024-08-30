import React, { useState } from 'react';
import { Button, Collapse } from 'react-bootstrap';
import "../assets/styles/Reportes.css"

const BotonReporte = ({ texto, section, handleClick, openSection }) => (
    <Button
        className="mt-1"
        onClick={() => handleClick(section)}
        aria-controls={`collapse-${section}`}
        aria-expanded={openSection === section}>
        {texto}
    </Button>
);

const SeccionReporte = ({ clase, columnas, data, openSection }) => (
    <Collapse in={openSection === clase}>
        <div id={`collapse-${clase}`} className='collapse-content'>
            <div className={`${clase}`}> 
                {columnas.map((columna) => (
                    <p>{columna}</p>
                ))}
            </div>
            {data.map((elemento) => (
                <div className={`${clase}`}>
                    {Object.values(elemento).map((value) => (
                        <p>{value}</p>
                    ))}
                </div>
            ))}
        </div>
    </Collapse>
);

function ReportesPage() {
    const [openSection, setOpenSection] = useState(null);
    const [reportes, setReportes] = useState([]);


    {/* Data para probar */}
    const mockData = {
            "viandas-heladeras": [
                { nombreHeladera: "Heladera de Buenos Aires", viandasColocadas: 5, viandasRetiradas: 3 },
                { nombreHeladera: "Heladera de Córdoba", viandasColocadas: 7, viandasRetiradas: 6 },
                { nombreHeladera: "Heladera de Mendoza", viandasColocadas: 4, viandasRetiradas: 4 },
            ],
            "fallas": [
                { nombreHeladera: "Heladera de Buenos Aires", fallas: 2 },
                { nombreHeladera: "Heladera de Córdoba", fallas: 1 },
                { nombreHeladera: "Heladera de Mendoza", fallas: 3 },
            ],
            "viandas-colaboradores": [
                { nombreColaborador: "Juan Pérez", viandasDonadas: 10 },
                { nombreColaborador: "María López", viandasDonadas: 8 },
                { nombreColaborador: "Carlos Gómez", viandasDonadas: 15 },
            ]
        };


    const handleButtonClick = (section) => {
        // Falta implementar la llamada a la API
        setOpenSection(prevSection => (prevSection === section ? null : section));      
    }

    return (
        <div className="reportes-page">
            <div className='reportes-container'>
                <div className="botones">
                    <BotonReporte texto="Fallas por heladera" section="fallas" handleClick={handleButtonClick} openSection={openSection} />
                    <BotonReporte texto="Viandas por heladera" section="viandas-heladeras" handleClick={handleButtonClick} openSection={openSection} />
                    <BotonReporte texto="Viandas por colaborador" section="viandas-colaboradores" handleClick={handleButtonClick} openSection={openSection} />
                </div>

                <SeccionReporte 
                    clase="fallas" 
                    columnas={['Heladera', 'Fallas']} 
                    data={mockData['fallas']} 
                    openSection={openSection} 
                />

                <SeccionReporte 
                    clase="viandas-heladeras" 
                    columnas={['Heladera', 'Viandas retiradas', 'Viandas colocadas']} 
                    data={mockData['viandas-heladeras']} 
                    openSection={openSection} 
                />

                <SeccionReporte 
                    clase="viandas-colaboradores"
                    columnas={['Colaborador', 'Viandas donadas']} 
                    data={mockData['viandas-colaboradores']} 
                    openSection={openSection} 
                />
            </div>
        </div>
    );
}

export default ReportesPage;