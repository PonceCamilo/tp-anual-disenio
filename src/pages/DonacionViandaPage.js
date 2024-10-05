import React from 'react';
import {
    Box,
    Heading,
    Text,
    Flex,
    Container,
    useBreakpointValue
} from '@chakra-ui/react';
import DonacionViandaForm from '../components/DonacionViandaForm';

const DonacionViandaPage = () => {
    const isMobile = useBreakpointValue({ base: true, md: false });

    return (
        <Container 
            maxW="container.lg" 
            mb={10} 
            mx="auto" 
            pt="80px"  // Ajuste del padding-top para que no se tape por el navbar
            minH="100vh"  // Aseguramos que el contenedor tenga al menos la altura de la pantalla
        >
            <Flex
                direction={isMobile ? 'column' : 'row'}
                align="center"
                justify="center"
                minH={isMobile ? 'auto' : '80vh'}  // Ajustamos el comportamiento en pantallas pequeñas
                bg="gray.100"
                borderRadius="lg"
                boxShadow="md"
                p={8}
                overflow="hidden"  // Evita que el contenido se desborde
            >
                <Box flex="1" p={6} textAlign="center" pr={isMobile ? 0 : 10}>
                    <Heading size="xl" mb={4} color="#8DC77E">¡Dona una Vianda!</Heading>
                    <Text fontSize="lg" color="#8DC77E" mb={6}>
                        Con tu ayuda, podemos alimentar a quienes más lo necesitan. Completa el formulario para donar comida saludable y fresca.
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
                    maxW={isMobile ? "100%" : "50%"}  // Aseguramos que el formulario se ajuste en móviles
                >
                    <DonacionViandaForm />
                </Box>
            </Flex>
        </Container>
    );
};

export default DonacionViandaPage;
