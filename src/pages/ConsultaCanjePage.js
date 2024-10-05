import React from 'react';
import {
    Box,
    Heading,
    Container,
    Flex,
    useBreakpointValue
} from '@chakra-ui/react';
import ConsultaCanjeForm from '../components/ConsultaCanjeForm';

function ConsultaCanjePage() {
    const isMobile = useBreakpointValue({ base: true, md: false });

    return (
        <Container 
            maxW="container.lg" 
            mb={10} 
            mx="auto"
            bg="transparent"
            pt="80px"  // Ajuste del padding-top para evitar que el navbar tape el contenido
            minH="100vh"  // Aseguramos que el contenedor tenga al menos la altura de la pantalla
        >
            <Flex
                direction="column"
                align="center"
                justify="center"
                minH={isMobile ? 'auto' : '70vh'}  // Ajuste de altura para pantallas pequeñas
                gap={6}
                borderRadius="lg"
                p={8}
                bg="gray.100" 
                overflow="hidden"  // Evita que el contenido se desborde
            >
                <Box textAlign="center">
                    <Heading size="2xl" color="black">
                        Canje de Productos
                    </Heading>
                </Box>
                <ConsultaCanjeForm />
            </Flex>
        </Container>
    );
}

export default ConsultaCanjePage;
