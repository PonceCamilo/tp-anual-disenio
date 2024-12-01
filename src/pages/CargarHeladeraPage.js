import React from 'react';
import CargarHeladeraForm from '../components/CargarHeladeraForm';
import { Box, Heading, Text, VStack } from '@chakra-ui/react';

function CargarHeladeraPage() {
    return (
        <Box
            display="flex"
            flexDirection="column"
            alignItems="center"
            justifyContent="center"
            minHeight="100vh"
            padding={{ base: 4, md: 8 }} // Padding responsivo
            bg="transparent"
        >
            <Heading
                as="h1"
                size={{ base: "xl", md: "2xl" }} // Tamaño responsivo
                color="#fff"
                textAlign="center" // Centrar texto en pantallas pequeñas
                textShadow="2px 2px 4px rgba(0, 0, 0, 0.5)"
                mb={6}
            >
                Obtener Heladera
            </Heading>

            <Box
                display="flex"
                flexDirection="column"
                textAlign="center" // Centrar texto en pantallas pequeñas
                maxWidth="800px" // Limitar el ancho
                marginX="auto" // Centrar horizontalmente
            >
                <Text
                    fontSize={{ base: "md", md: "lg" }} // Tamaño responsivo
                    fontWeight="bold"
                    display="flex"
                    flexDirection="column" // Garantiza que el texto respete el display
                >
                    Otra forma de colaborar con la comunidad es haciéndote responsable de una heladera.
                    Si no posees un espacio físico para instalarla, podes utilizar nuestro recomendador de puntos
                    para que sea instalada en un lugar público.
                </Text>
            </Box>

            <VStack spacing={4} mt={8} w="full" maxW="500px">
                <CargarHeladeraForm />
            </VStack>
        </Box>
    );
}

export default CargarHeladeraPage;
