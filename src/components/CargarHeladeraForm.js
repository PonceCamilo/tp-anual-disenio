import React, { useState } from 'react';
import { Box, Button, FormControl, FormLabel, Heading, Input } from '@chakra-ui/react';

const CargarHeladeraForm = () => {
    const [capacidad, setCapacidad] = useState('');
    const [temperaturaMinima, setTemperaturaMinima] = useState('');
    const [temperaturaMaxima, setTemperaturaMaxima] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = {
            capacidad: parseInt(capacidad, 10),
            temperaturaMinima: parseFloat(temperaturaMinima),
            temperaturaMaxima: parseFloat(temperaturaMaxima),
        };

        try {
            const response = await fetch('/api/add-fridge', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });
            if (response.ok) {
                console.log('Heladera añadida con éxito');
            } else {
                console.log('Error al añadir la heladera');
            }
        } catch (error) {
            console.error('Error:', error);
        }

        setCapacidad('');
        setTemperaturaMinima('');
        setTemperaturaMaxima('');
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
            bg="rgba(255, 255, 255)" 
            backdropFilter="blur(10px)"
        >
            <Heading as="h2" size="lg" textAlign="center" mb={4}>
                Cargar Heladera
            </Heading>

            <FormControl mb={4}>
                <FormLabel>Capacidad (cantidad viandas):</FormLabel>
                <Input
                    type="number"
                    value={capacidad}
                    onChange={(e) => setCapacidad(e.target.value)}
                    required
                />
            </FormControl>

            <FormControl mb={4}>
                <FormLabel>Temperatura Mínima (°C):</FormLabel>
                <Input
                    type="number"
                    step="0.1"
                    value={temperaturaMinima}
                    onChange={(e) => setTemperaturaMinima(e.target.value)}
                    required
                />
            </FormControl>

            <FormControl mb={4}>
                <FormLabel>Temperatura Máxima (°C):</FormLabel>
                <Input
                    type="number"
                    step="0.1"
                    value={temperaturaMaxima}
                    onChange={(e) => setTemperaturaMaxima(e.target.value)}
                    required
                />
            </FormControl>

            <Button type="submit" colorScheme="green" width="full">
                Añadir Heladera
            </Button>
        </Box>
    );
};

export default CargarHeladeraForm;
