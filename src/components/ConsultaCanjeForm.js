import React, { useState } from 'react';
import {
    Box,
    Input,
    Button,
    Text,
    Grid,
    Image,
    Heading,
    Flex
} from '@chakra-ui/react';

const productos = [
    { id: 1, nombre: 'Producto 1', puntos: 100, imagen: 'https://via.placeholder.com/150' },
    { id: 2, nombre: 'Producto 2', puntos: 200, imagen: 'https://via.placeholder.com/150' },
    { id: 3, nombre: 'Producto 3', puntos: 300, imagen: 'https://via.placeholder.com/150' },
    { id: 4, nombre: 'Producto 4', puntos: 400, imagen: 'https://via.placeholder.com/150' },
    { id: 5, nombre: 'Producto 5', puntos: 500, imagen: 'https://via.placeholder.com/150' },
    { id: 1, nombre: 'Producto 1', puntos: 100, imagen: 'https://via.placeholder.com/150' },
    { id: 2, nombre: 'Producto 2', puntos: 200, imagen: 'https://via.placeholder.com/150' },
    { id: 3, nombre: 'Producto 3', puntos: 300, imagen: 'https://via.placeholder.com/150' },
    { id: 4, nombre: 'Producto 4', puntos: 400, imagen: 'https://via.placeholder.com/150' },
    { id: 5, nombre: 'Producto 5', puntos: 500, imagen: 'https://via.placeholder.com/150' },
    { id: 1, nombre: 'Producto 1', puntos: 100, imagen: 'https://via.placeholder.com/150' },
    { id: 2, nombre: 'Producto 2', puntos: 200, imagen: 'https://via.placeholder.com/150' },
    { id: 3, nombre: 'Producto 3', puntos: 300, imagen: 'https://via.placeholder.com/150' },
    { id: 4, nombre: 'Producto 4', puntos: 400, imagen: 'https://via.placeholder.com/150' },
    { id: 5, nombre: 'Producto 5', puntos: 500, imagen: 'https://via.placeholder.com/150' },
    { id: 1, nombre: 'Producto 1', puntos: 100, imagen: 'https://via.placeholder.com/150' },
    { id: 2, nombre: 'Producto 2', puntos: 200, imagen: 'https://via.placeholder.com/150' },
    { id: 3, nombre: 'Producto 3', puntos: 300, imagen: 'https://via.placeholder.com/150' },
    { id: 4, nombre: 'Producto 4', puntos: 400, imagen: 'https://via.placeholder.com/150' },
    { id: 5, nombre: 'Producto 5', puntos: 500, imagen: 'https://via.placeholder.com/150' },
    { id: 1, nombre: 'Producto 1', puntos: 100, imagen: 'https://via.placeholder.com/150' },
    { id: 2, nombre: 'Producto 2', puntos: 200, imagen: 'https://via.placeholder.com/150' },
    { id: 3, nombre: 'Producto 3', puntos: 300, imagen: 'https://via.placeholder.com/150' },
    { id: 4, nombre: 'Producto 4', puntos: 400, imagen: 'https://via.placeholder.com/150' },
    { id: 5, nombre: 'Producto 5', puntos: 500, imagen: 'https://via.placeholder.com/150' },
];

function ConsultaCanjeForm() {
    const [searchTerm, setSearchTerm] = useState('');
    const puntosActuales = 2000;

    const handleCanjear = (productoId) => {
        const producto = productos.find(p => p.id === productoId);
        if (producto) {
            alert(`Has canjeado: ${producto.nombre}`);
        }
    };

    const filteredProducts = productos.filter(producto =>
        producto.nombre.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <Box
            width="100%"
            maxW="1300px"
            minW="400px"
            p={6}
            bg="white"
            borderRadius="lg"
            boxShadow="lg"
            overflowY="auto" 
            maxHeight="80vh" 
        >
            <Box textAlign="center" mb={6}>
                <Heading size="md" mb={1}>Puntos actuales:</Heading>
                <Text fontSize="2xl" fontWeight="bold" color="green.500">{puntosActuales}</Text>
            </Box>
            <Flex justifyContent="center" mb={6}>
                <Input
                    placeholder="Buscar producto..."
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    width="250px"
                    mr={2}
                />
                <Button colorScheme="green" onClick={() => {}}>Buscar</Button>
            </Flex>
            <Grid templateColumns="repeat(auto-fill, minmax(180px, 1fr))" gap={4}>
                {filteredProducts.map(producto => (
                    <Box
                        key={producto.id}
                        p={3}
                        bg="gray.50"
                        borderRadius="lg"
                        boxShadow="md"
                        textAlign="center"
                        transition="transform 0.3s, box-shadow 0.3s"
                        _hover={{ transform: 'scale(1.05)', boxShadow: 'lg' }}
                    >
                        <Image src={producto.imagen} alt={producto.nombre} borderRadius="md" mb={3} />
                        <Heading size="sm" mb={2}>{producto.nombre}</Heading>
                        <Text fontSize="md" color="gray.600">Puntos: {producto.puntos}</Text>
                        <Button
                            mt={3}
                            size="sm"
                            colorScheme="green"
                            onClick={() => handleCanjear(producto.id)}
                        >
                            Canjear
                        </Button>
                    </Box>
                ))}
            </Grid>
        </Box>
    );
}

export default ConsultaCanjeForm;
