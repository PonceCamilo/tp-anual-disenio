import React, { useState } from 'react';
import { Box, Button, FormControl, FormLabel, Input, Textarea, VStack, useToast } from '@chakra-ui/react';
import { useAuth } from '../config/authContext';

function PublicarProductoForm() {
    const [product, setProduct] = useState({
        rubro: '', // Cambié 'name' por 'rubro' porque el backend lo espera
        nombre: '', // Cambié 'name' por 'nombre' porque el backend lo espera
        cantidadPuntosNec: 0, // Cambié 'points' por 'cantidadPuntosNec' porque el backend lo espera
        imagen: null // Cambié 'image' por 'imagen'
    });
    const toast = useToast(); // Hook de Chakra UI para mostrar mensajes
    const colaboradorUUID = localStorage.getItem('sub'); // Obtengo el UUID del colaborador del localStorage
    const { accessToken } = useAuth();
    console.log('AccessToken:', accessToken); // Verifica que el token esté presente

    const handleChange = (e) => {
        const { name, value } = e.target;
        setProduct({
            ...product,
            [name]: value
        });
    };

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setProduct({
                ...product,
                imagen: file
            });
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('rubro', product.rubro); // 'rubro' en vez de 'name'
        formData.append('nombre', product.nombre); // 'nombre' en vez de 'name'
        formData.append('cantidadPuntosNec', product.cantidadPuntosNec); // 'cantidadPuntosNec' en vez de 'points'

        // Si la imagen es válida, la agregamos al formData
        if (product.imagen) {
            formData.append('imagen', product.imagen);
        }

        // URL del endpoint
        const url = `http://localhost:8080/colaboraciones/oferta?colaboradorUUID=${colaboradorUUID}`;

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${accessToken}`, // Bearer token agregado correctamente
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                const data = await response.json();
                toast({
                    title: 'Oferta registrada con éxito',
                    description: data.message || 'La oferta fue procesada exitosamente.',
                    status: 'success',
                    duration: 5000,
                    isClosable: true,
                });
            } else {
                const errorText = await response.text();
                toast({
                    title: 'Error',
                    description: errorText || 'Ocurrió un error al procesar la oferta.',
                    status: 'error',
                    duration: 5000,
                    isClosable: true,
                });
            }
        } catch (error) {
            console.error('Error:', error);
            toast({
                title: 'Error de conexión',
                description: 'No se pudo conectar con el servidor para procesar la oferta.',
                status: 'error',
                duration: 5000,
                isClosable: true,
            });
        }
    };

    return (
        <Box as="form" onSubmit={handleSubmit} width="100%">
            <VStack spacing={4} align="stretch">
                <FormControl isRequired>
                    <FormLabel htmlFor="rubro">Rubro:</FormLabel>
                    <Input
                        type="text"
                        id="rubro"
                        name="rubro"
                        value={product.rubro}
                        onChange={handleChange}
                    />
                </FormControl>

                <FormControl isRequired>
                    <FormLabel htmlFor="nombre">Nombre del Producto:</FormLabel>
                    <Input
                        type="text"
                        id="nombre"
                        name="nombre"
                        value={product.nombre}
                        onChange={handleChange}
                    />
                </FormControl>

                <FormControl isRequired>
                    <FormLabel htmlFor="cantidadPuntosNec">Puntos necesarios:</FormLabel>
                    <Input
                        type="number"
                        id="cantidadPuntosNec"
                        name="cantidadPuntosNec"
                        value={product.cantidadPuntosNec}
                        onChange={handleChange}
                    />
                </FormControl>

                <FormControl>
                    <FormLabel htmlFor="imagen">Selecciona una imagen:</FormLabel>
                    <Input
                        type="file"
                        id="imagen"
                        name="imagen"
                        accept="image/*"
                        onChange={handleImageChange}
                    />
                </FormControl>

                <Button colorScheme="green" type="submit">Publicar</Button>
            </VStack>
        </Box>
    );
}

export default PublicarProductoForm;
