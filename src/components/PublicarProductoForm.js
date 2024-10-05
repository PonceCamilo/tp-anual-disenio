import React, { useState } from 'react';
import { Box, Button, FormControl, FormLabel, Input, Textarea, VStack } from '@chakra-ui/react';

function PublicarProductoForm() {
    const [product, setProduct] = useState({
        name: '',
        description: '',
        points: 0,
        image: null
    });

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
                image: file
            });
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('name', product.name);
        formData.append('description', product.description);
        formData.append('points', product.points);
        if (product.image) {
            formData.append('image', product.image);
        }

        fetch('/api/publicar-producto', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                console.log('Producto publicado:', data);
            })
            .catch(error => {
                console.error('Error al publicar el producto:', error);
            });
    };

    return (
        <Box as="form" onSubmit={handleSubmit} width="100%">
            <VStack spacing={4} align="stretch">
                <FormControl isRequired>
                    <FormLabel htmlFor="name">Nombre:</FormLabel>
                    <Input
                        type="text"
                        id="name"
                        name="name"
                        value={product.name}
                        onChange={handleChange}
                    />
                </FormControl>
                <FormControl isRequired>
                    <FormLabel htmlFor="description">Descripción:</FormLabel>
                    <Textarea
                        id="description"
                        name="description"
                        value={product.description}
                        onChange={handleChange}
                    />
                </FormControl>
                <FormControl isRequired>
                    <FormLabel htmlFor="points">Puntos necesarios:</FormLabel>
                    <Input
                        type="number"
                        id="points"
                        name="points"
                        value={product.points}
                        onChange={handleChange}
                    />
                </FormControl>
                <FormControl>
                    <FormLabel htmlFor="image">Selecciona una imagen:</FormLabel>
                    <Input
                        type="file"
                        id="image"
                        name="image"
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
