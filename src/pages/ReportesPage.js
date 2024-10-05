import React, { useState } from 'react';
import {
  Menu,
  MenuButton,
  MenuList,
  MenuItem,
  Button,
  Box,
  Collapse,
  Heading,
  Text,
} from '@chakra-ui/react';
import { ChevronDownIcon } from '@chakra-ui/icons';

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
  ],
};

const reportTypes = [
  { label: 'Fallas por heladera', section: 'fallas' },
  { label: 'Viandas por heladera', section: 'viandas-heladeras' },
  { label: 'Viandas por colaborador', section: 'viandas-colaboradores' },
  // Agrega más tipos de reportes aquí si es necesario
];

const SeccionReporte = ({ clase, columnas, data, openSection }) => (
  <Collapse in={openSection === clase} animateOpacity>
    <Box className={clase} bg="lightblue" p={4} mt={4} borderRadius="md">
      <Box fontWeight="bold" display="grid" gridTemplateColumns={`repeat(${columnas.length}, 1fr)`} gap={2}>
        {columnas.map((columna) => (
          <Box key={columna}>{columna}</Box>
        ))}
      </Box>
      {data.map((elemento, index) => (
        <Box key={index} display="grid" gridTemplateColumns={`repeat(${columnas.length}, 1fr)`} gap={2} mt={2}>
          {Object.values(elemento).map((value, i) => (
            <Box key={i}>{value}</Box>
          ))}
        </Box>
      ))}
    </Box>
  </Collapse>
);

function ReportesPage() {
  const [openSection, setOpenSection] = useState(null);
  const [selectedReport, setSelectedReport] = useState(null);

  const handleMenuClick = (section, label) => {
    setOpenSection(prevSection => (prevSection === section ? null : section));
    setSelectedReport(prevSection => (prevSection === label ? null : label));
  };

  return (
    <Box display="flex" minHeight="100vh" alignItems="center" justifyContent="center" bg="transparent">
      <Box bg="white" p={8} borderRadius="lg" boxShadow="lg" width="80vw" height="80vh" overflow="auto">
        <Menu>
          <MenuButton as={Button} rightIcon={<ChevronDownIcon />}>
            Seleccionar tipo de reporte
          </MenuButton>
          <MenuList>
            {reportTypes.map((report) => (
              <MenuItem key={report.section} onClick={() => handleMenuClick(report.section, report.label)}>
                {report.label}
              </MenuItem>
            ))}
          </MenuList>
        </Menu>

        {selectedReport ? (
          <>
            <Heading as="h2" size="lg" mt={4} mb={4} textAlign="center">
              {`Reporte: ${selectedReport}`}
            </Heading>

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
          </>
        ) : (
          <Box display="flex" justifyContent="center" alignItems="center" height="60vh">
            <Text fontSize="2xl" color="gray.500">
              Seleccione un tipo de reporte a mostrar
            </Text>
          </Box>
        )}
      </Box>
    </Box>
  );
}

export default ReportesPage;
