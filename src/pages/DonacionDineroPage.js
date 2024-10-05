import React from 'react';
import {
    Box,
    Heading,
    Text,
    Flex,
    Container,
    useBreakpointValue
} from '@chakra-ui/react';
import DonacionDineroForm from '../components/DonacionDineroForm';

function DonacionDineroPage() {
    const isMobile = useBreakpointValue({ base: true, md: false });

    return (
        <Container 
            maxW="container.lg" 
            mb={10} 
            mx="auto"  
            pt="80px"  // Ajuste para que no tape el nav
            minH="100vh"  // Aseguramos que el contenedor tenga al menos el tamaño de la pantalla
        >
            <Flex
                direction={isMobile ? 'column' : 'row'}
                align="center"
                justify="center"
                minH={isMobile ? 'auto' : '80vh'}  // Ajuste de la altura mínima en móviles
                bg="gray.100"
                borderRadius="lg"
                boxShadow="md"
                p={8}
                overflow="hidden"  // Asegura que el contenido no se desborde
            >
                <Box 
                    flex="1" 
                    p={6} 
                    textAlign="center" 
                    pr={isMobile ? 0 : 10}
                >
                    <Heading size="xl" mb={4} color="#8DC77E">
                        ¡Donar Dinero!
                    </Heading>
                    <Text fontSize="lg" color="#8DC77E" mb={6}>
                        Con tu ayuda, podemos marcar una diferencia. Cualquier cantidad que puedas donar es invaluable para nuestro esfuerzo continuo.
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
                    id="donacion-form"
                    maxW={isMobile ? "100%" : "50%"}  // Para que el formulario se ajuste en móvil
                >
                    <DonacionDineroForm />
                </Box>
            </Flex>
        </Container>
    );
}

export default DonacionDineroPage;
