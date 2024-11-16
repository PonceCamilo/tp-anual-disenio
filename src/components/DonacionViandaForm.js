import React, { useState } from 'react';
import {
    Box,
    FormControl,
    FormLabel,
    Input,
    Button,
    VStack,
    NumberInput,
    NumberInputField,
    useToast,
    Select
} from '@chakra-ui/react';
import { useAuth } from '../config/authContext';

const DonacionViandaForm = () => {
    const [comida, setComida] = useState('');
    const [fechaDeCaducidad, setFechaDeCaducidad] = useState('');
    const [calorias, setCalorias] = useState('');
    const [peso, setPeso] = useState('');
    const [heladeraId, setHeladeraId] = useState(1); // Asignamos el valor inicial como número
    const toast = useToast(); // Usamos el hook useToast de Chakra UI
    const { accessToken } = useAuth(); // Asegurarse de tener el accessToken

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Asegurémonos de que la fecha esté bien formateada
        const fechaFormateada = new Date(fechaDeCaducidad).toISOString().split('T')[0];
        console.log('Fecha formateada:', fechaFormateada);

        const formData = {
            comida,
            fechaCaducidad: fechaFormateada,  // Asegurándonos de que sea la fecha correcta
            calorias: parseFloat(calorias),
            peso: parseFloat(peso),
            heladeraId: heladeraId,
        };

        const colaboradorUUID = localStorage.getItem('sub');
        const url = `http://localhost:8080/colaboraciones/donacion-vianda?colaboradorUUID=${colaboradorUUID}`;

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${accessToken}`,
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                const data = await response.text();
                toast({
                    title: 'Donación de Vianda realizada',
                    description: data || 'Tu donación de vianda fue procesada exitosamente.',
                    status: 'success',
                    duration: 5000,
                    isClosable: true,
                });
            } else {
                const errorText = await response.text();
                toast({
                    title: 'Error',
                    description: errorText || 'Ocurrió un error al procesar la donación.',
                    status: 'error',
                    duration: 5000,
                    isClosable: true,
                });
            }
        } catch (error) {
            console.error('Error:', error);
            toast({
                title: 'Error de conexión',
                description: 'No se pudo conectar con el servidor para procesar la donación.',
                status: 'error',
                duration: 5000,
                isClosable: true,
            });
        }

        // Limpiar los campos después de enviar el formulario
        setComida('');
        setFechaDeCaducidad('');
        setCalorias('');
        setPeso('');
        setHeladeraId(1); // Resetear heladeraId
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

                {/* Nuevo campo para seleccionar el id de la heladera */}
                <FormControl id="heladeraId" isRequired>
                    <FormLabel>Seleccionar Heladera</FormLabel>
                    <Select
                        value={heladeraId}
                        onChange={(e) => setHeladeraId(Number(e.target.value))} // Convertimos el valor a número
                    >
                        <option value="1">Heladera 1</option>
                        <option value="2">Heladera 2</option>
                        <option value="3">Heladera 3</option>
                    </Select>
                </FormControl>

                <Button type="submit" colorScheme="green" width="100%">
                    Enviar Donación
                </Button>
            </VStack>
        </Box>
    );
};

export default DonacionViandaForm;
