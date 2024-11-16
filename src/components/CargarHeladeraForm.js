import React, { useState } from 'react';
import { Box, Button, FormControl, FormLabel, Heading, Input, Text, Link } from '@chakra-ui/react';
import { useAuth } from '../config/authContext';

const CargarHeladeraForm = () => {
    const [direccion, setDireccion] = useState('');
    const colaboradorUUID = localStorage.getItem('sub'); // Obtiene el UUID del usuario
    const { accessToken } = useAuth();

    const handleSubmit = async (e) => {
        e.preventDefault();
    
        try {
            const response = await fetch('http://localhost:8080/colaboraciones/obtencion-heladera', {
                method: 'POST',
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    "Authorization": `Bearer ${accessToken}`, 
                },
                body: new URLSearchParams({
                    direccion,
                    colaboradorUUID,
                }),
            });
    
            if (response.ok) {
                const message = await response.text();
                console.log('Respuesta del servidor:', message);
                setDireccion(''); // Limpia el formulario
            } else if (response.status === 404) {
                const errorMessage = await response.text();
                console.error('Error 404:', errorMessage);
            } else {
                const errorMessage = await response.text();
                console.error('Error al consumir el endpoint:', errorMessage);
            }
        } catch (error) {
            console.error('Error inesperado:', error);
        }
    };
    

    return (
        <Box
            as="form"
            onSubmit={handleSubmit}
            maxWidth="400px"
            width="100%"
            margin="0 auto"
            padding={6}
            borderRadius="lg"
            boxShadow="lg"
            bg="rgba(255, 255, 255, 0.8)"
            backdropFilter="blur(10px)"
        >
            <Heading as="h2" size="lg" textAlign="center" mb={4}>
                Registrar Dirección de Heladera
            </Heading>

            <FormControl mb={4}>
                <FormLabel>Dirección:</FormLabel>
                <Input
                    type="text"
                    value={direccion}
                    onChange={(e) => setDireccion(e.target.value)}
                    placeholder="Ingrese la dirección"
                    required
                />
            </FormControl>

            <Button type="submit" colorScheme="green" width="full" mb={4}>
                Enviar
            </Button>

            <Text textAlign="center" fontSize="sm">
                ¿No contas con el espacio físico?{' '}
                <Link href="/recomendar-puntos" color="teal.500" fontWeight="bold">
                    Recomendar un punto
                </Link>
            </Text>
        </Box>
    );
};

export default CargarHeladeraForm;
