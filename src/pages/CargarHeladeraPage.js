import React from 'react';
import CargarHeladeraForm from '../components/CargarHeladeraForm';
import { Box, Heading } from '@chakra-ui/react';

function CargarHeladeraPage() {
    return (
        <Box 
            display="flex" 
            flexDirection="column" 
            alignItems="center" 
            justifyContent="center" 
            minHeight="100vh" 
            padding={8} 
            bg="transparent" // Cambia esto al color que desees
        >
            <Heading as="h1" size="2xl" color="#fff" textShadow="2px 2px 4px rgba(0, 0, 0, 0.5)" mb={6}>
                Añadir Nueva Heladera
            </Heading>
            <CargarHeladeraForm />
        </Box>
    );
}

export default CargarHeladeraPage;
