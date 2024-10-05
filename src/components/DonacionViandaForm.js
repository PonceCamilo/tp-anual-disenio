import React, { useState } from 'react';
import {
    Box,
    FormControl,
    FormLabel,
    Input,
    Button,
    VStack,
    NumberInput,
    NumberInputField
} from '@chakra-ui/react';

const DonacionViandaForm = () => {
    const [comida, setComida] = useState('');
    const [fechaDeCaducidad, setFechaDeCaducidad] = useState('');
    const [calorias, setCalorias] = useState('');
    const [peso, setPeso] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = {
            comida,
            fechaDeCaducidad,
            calorias: parseFloat(calorias),
            peso: parseFloat(peso),
        };

        try {
            const response = await fetch('/api/donate-meal', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });
            if (response.ok) {
                console.log('Donación enviada con éxito');
            } else {
                console.log('Error al enviar la donación');
            }
        } catch (error) {
            console.error('Error:', error);
        }

        setComida('');
        setFechaDeCaducidad('');
        setCalorias('');
        setPeso('');
    };

    return (
        <Box
            bg="white"
            p={8}
            borderRadius="lg"
            boxShadow="xl"
            width="100%"
            maxW="400px"
        >
            <VStack spacing={4} as="form" onSubmit={handleSubmit}>
                <FormControl id="comida" isRequired>
                    <FormLabel>Comida</FormLabel>
                    <Input
                        type="text"
                        value={comida}
                        onChange={(e) => setComida(e.target.value)}
                    />
                </FormControl>

                <FormControl id="fechaDeCaducidad" isRequired>
                    <FormLabel>Fecha de Caducidad</FormLabel>
                    <Input
                        type="date"
                        value={fechaDeCaducidad}
                        onChange={(e) => setFechaDeCaducidad(e.target.value)}
                    />
                </FormControl>

                <FormControl id="calorias" isRequired>
                    <FormLabel>Calorías</FormLabel>
                    <NumberInput
                        value={calorias}
                        onChange={(value) => setCalorias(value)}
                        step={0.01}
                        min={0}
                    >
                        <NumberInputField />
                    </NumberInput>
                </FormControl>

                <FormControl id="peso" isRequired>
                    <FormLabel>Peso (en gramos)</FormLabel>
                    <NumberInput
                        value={peso}
                        onChange={(value) => setPeso(value)}
                        step={0.01}
                        min={0}
                    >
                        <NumberInputField />
                    </NumberInput>
                </FormControl>

                <Button type="submit" colorScheme="green" width="100%">
                    Enviar Donación
                </Button>
            </VStack>
        </Box>
    );
};

export default DonacionViandaForm;
