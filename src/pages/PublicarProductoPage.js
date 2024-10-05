import React from 'react';
import {
    Box,
    Heading,
    Text,
    Flex,
    Container,
    useBreakpointValue
} from '@chakra-ui/react';
import PublicarProductoForm from '../components/PublicarProductoForm';

function PublicarProductoPage() {
    const isMobile = useBreakpointValue({ base: true, md: false });

    return (
        <Container 
            maxW="container.lg" 
            pt="80px"  // Ajuste del padding-top para evitar que el navbar tape el contenido
            mb={10} 
            mx="auto"  
            minH="100vh"  // Aseguramos que el contenedor tenga al menos la altura de la pantalla
        >
            <Flex
                direction={isMobile ? 'column' : 'row'}
                align="center"
                justify="center"
                minH={isMobile ? 'auto' : '80vh'}  // Ajuste dinámico de la altura para pantallas pequeñas
                bg="gray.100"
                borderRadius="lg"
                boxShadow="md"
                p={8}
            >
                <Box flex="1" p={6} textAlign="center" pr={isMobile ? 0 : 10}>
                    <Heading size="xl" mb={4} color="#8DC77E">¡Publica tu Producto/Servicio!</Heading>
                    <Text fontSize="lg" color="#8DC77E" mb={6}>
                        Contribuye a nuestra comunidad ofreciendo tus productos o servicios. Completa el formulario para compartir tu oferta.
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
                    id="publicar-form"
                >
                    <PublicarProductoForm />
                </Box>
            </Flex>
        </Container>
    );
}

export default PublicarProductoPage;
