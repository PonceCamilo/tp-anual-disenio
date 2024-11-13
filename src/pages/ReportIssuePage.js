import React from 'react';
import {
    Box,
    Heading,
    Text,
    Flex,
    Container,
    useBreakpointValue
} from '@chakra-ui/react';
import ReportIssueForm from '../components/ReportIssueForm';

const fridges = [
    { id: '1', name: 'Heladera 1' },
    { id: '2', name: 'Heladera 2' },
    
];

const ReportIssuePage = () => {
    const isMobile = useBreakpointValue({ base: true, md: false });

    return (
        <Container 
            maxW="container.lg" 
            mb={10} 
            mx="auto"
            pt="80px"  // Ajuste del padding-top para evitar que el navbar tape el contenido
            minH="100vh"  // Aseguramos que el contenedor tenga al menos la altura de la pantalla
        >
            <Flex
                direction={isMobile ? 'column' : 'row'}
                align="center"
                justify="center"
                minH={isMobile ? 'auto' : '80vh'}  // Ajuste de altura para pantallas pequeñas
                bg="gray.100"
                borderRadius="lg"
                boxShadow="md"
                p={8}
                overflow="hidden"  // Evita que el contenido se desborde
            >
                <Box flex="1" p={6} textAlign="center" pr={isMobile ? 0 : 10}>
                    <Heading size="xl" mb={4} color="#8DC77E">
                        Reportar Falla Técnica
                    </Heading>
                    <Text fontSize="lg" color="#8DC77E" mb={6}>
                        Con tu ayuda, podemos mantener nuestras heladeras en óptimas condiciones. 
                        Reporta cualquier problema técnico para que podamos solucionarlo rápidamente.
                    </Text>
                </Box>
                <Box
                    flex="1"
                    p={6}
                    display="flex"
                    alignItems="center"
                    justifyContent="center"
                    bg="white"
                    borderRadius="lg"
                    boxShadow="lg"
                    id="report-issue-form"
                    maxW={isMobile ? "100%" : "50%"}  // Ajuste para que el formulario se vea bien en móviles
                >
                    <ReportIssueForm fridges={fridges} />
                </Box>
            </Flex>
        </Container>
    );
};

export default ReportIssuePage;
