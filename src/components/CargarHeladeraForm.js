import React, { useState } from 'react';
import { Box, Button, FormControl, FormLabel, Heading, Input, Text, Link, useToast } from '@chakra-ui/react';
import { useAuth } from '../config/authContext';

const CargarHeladeraForm = () => {
    const [direccion, setDireccion] = useState('');
    const colaboradorUUID = localStorage.getItem('sub'); // Obtiene el UUID del usuario
    const { accessToken } = useAuth();
    const toast = useToast();
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
                toast({
                    title: "Heladera Registrada con éxito",
                    description:"Tu heladera fue procesada exitosamente.",
                    status: "success",
                    duration: 5000,
                    isClosable: true,
                  });
                setDireccion(''); // Limpia el formulario
            } 
        } catch (error) {
            toast({
                title: "Ocurrió un error al procesar la solicitud.",
                description: error,
                status: "error",
                duration: 5000,
                isClosable: true,
              });
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
