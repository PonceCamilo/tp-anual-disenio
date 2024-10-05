import React, { useState } from 'react';
import { Box, Button, FormControl, FormLabel, Input, VStack } from '@chakra-ui/react';

function DistribucionViandasForm() {
    const [vianda, setVianda] = useState({
        comida: '',
        fechaDeCaducidad: '',
        calorias: '',
        peso: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setVianda({
            ...vianda,
            [name]: value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const formData = {
            ...vianda,
            calorias: parseFloat(vianda.calorias),
            peso: parseFloat(vianda.peso),
        };

        fetch('/api/distribuir-vianda', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Distribución de vianda registrada:', data);
            })
            .catch(error => {
                console.error('Error en la distribución:', error);
            });
    };

    return (
        <Box as="form" onSubmit={handleSubmit} width="100%">
            <VStack spacing={4} align="stretch">
                <FormControl isRequired>
                    <FormLabel htmlFor="comida">Comida:</FormLabel>
                    <Input
                        type="text"
                        id="comida"
                        name="comida"
                        value={vianda.comida}
                        onChange={handleChange}
                    />
                </FormControl>
                <FormControl isRequired>
                    <FormLabel htmlFor="fechaDeCaducidad">Fecha de Caducidad:</FormLabel>
                    <Input
                        type="date"
                        id="fechaDeCaducidad"
                        name="fechaDeCaducidad"
                        value={vianda.fechaDeCaducidad}
                        onChange={handleChange}
                    />
                </FormControl>
                <FormControl isRequired>
                    <FormLabel htmlFor="calorias">Calorías:</FormLabel>
                    <Input
                        type="number"
                        step="0.01"
                        id="calorias"
                        name="calorias"
                        value={vianda.calorias}
                        onChange={handleChange}
                    />
                </FormControl>
                <FormControl isRequired>
                    <FormLabel htmlFor="peso">Peso (en gramos):</FormLabel>
                    <Input
                        type="number"
                        step="0.01"
                        id="peso"
                        name="peso"
                        value={vianda.peso}
                        onChange={handleChange}
                    />
                </FormControl>
                <Button colorScheme="green" type="submit">Registrar Distribución</Button>
            </VStack>
        </Box>
    );
}

export default DistribucionViandasForm;
