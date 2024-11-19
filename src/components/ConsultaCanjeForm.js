import React, { useState, useEffect } from 'react';
import {
    Box,
    Input,
    Button,
    Text,
    Grid,
    Image,
    Heading,
    Flex,
    useToast,
    Spinner,
} from '@chakra-ui/react';
import { useAuth } from '../config/authContext';

function ConsultaCanjeForm() {
    const [productos, setProductos] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [loadingCanje, setLoadingCanje] = useState(null);
    const [loadingProductos, setLoadingProductos] = useState(true);
    const [loadingPuntos, setLoadingPuntos] = useState(true);
    const [error, setError] = useState(null);
    const [puntosActuales, setPuntosActuales] = useState(0);
    const { accessToken } = useAuth();
    const colaboradorUUID = localStorage.getItem('sub');
    const toast = useToast();

    // Fetch de productos disponibles
    useEffect(() => {
        const fetchProductos = async () => {
            try {
                setLoadingProductos(true);
                const response = await fetch('http://localhost:8080/canjes/ofertasDisponibles', {
                    method: 'GET',
                    headers: {
                        Authorization: `Bearer ${accessToken}`,
                    },
                });
                if (!response.ok) throw new Error('Error al obtener las ofertas.');

                const data = await response.json();
                setProductos(
                    data.map((oferta, index) => ({
                        id: index, // Generar un ID único si el backend no lo proporciona
                        ofertaID: oferta.id,
                        nombre: oferta.nombre,
                        puntos: oferta.cantidadPuntosNecesarios,
                        imagen: oferta.imagen || 'https://via.placeholder.com/150',
                        rubro: oferta.rubro,
                    }))
                );
            } catch (err) {
                setError('Error al cargar las ofertas.');
            } finally {
                setLoadingProductos(false);
            }
        };

        fetchProductos();
    }, [accessToken]);

    // Fetch de puntos del colaborador
    useEffect(() => {
        const fetchPuntos = async () => {
            try {
                setLoadingPuntos(true);
                const response = await fetch(
                    `http://localhost:8080/canjes/calcularPuntos?colaboradorUUID=${colaboradorUUID}`,
                    {
                        method: 'GET',
                        headers: {
                            Authorization: `Bearer ${accessToken}`,
                        },
                    }
                );
                if (!response.ok) throw new Error('Error al obtener los puntos del colaborador.');

                const puntos = await response.json();
                setPuntosActuales(puntos);
            } catch (err) {
                setError('Error al cargar los puntos.');
            } finally {
                setLoadingPuntos(false);
            }
        };

        fetchPuntos();
    }, [accessToken, colaboradorUUID]);

    // Canje de un producto
    const handleCanjear = async (productoId) => {
        const producto = productos.find((p) => p.id === productoId);
        
        if (!producto) {
            toast({
                title: 'Producto no encontrado',
                description: 'El producto seleccionado no está disponible.',
                status: 'error',
                duration: 5000,
                isClosable: true,
            });
            return;
        }

        if (puntosActuales < producto.puntos) {
            toast({
                title: 'Puntos insuficientes',
                description: 'No tienes suficientes puntos para canjear esta oferta.',
                status: 'warning',
                duration: 5000,
                isClosable: true,
            });
            return;
        }

        try {
            setLoadingCanje(productoId);
            const response = await fetch(
                `http://localhost:8080/canjes/canjear?colaboradorUUID=${colaboradorUUID}&ofertaId=${producto.ofertaID}`,
                {
                    method: 'POST',
                    headers: {
                        Authorization: `Bearer ${accessToken}`,
                    },
                }
            );

            if (!response.ok) throw new Error('Error al canjear la oferta.');

            toast({
                title: 'Oferta canjeada',
                description: 'Tu canje fue procesado exitosamente.',
                status: 'success',
                duration: 5000,
                isClosable: true,
            });

            // Actualiza los puntos restantes
            setPuntosActuales((prev) => prev - producto.puntos);
        } catch (err) {
            toast({
                title: 'Error al canjear',
                description: 'Ocurrió un problema, inténtalo más tarde.',
                status: 'error',
                duration: 5000,
                isClosable: true,
            });
        }finally {
            setLoadingCanje(null);
        }
    };

    const filteredProducts = productos.filter((producto) =>
        (producto.nombre ||'').toLowerCase().includes(searchTerm.toLowerCase())
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
                {loadingPuntos ? (
                    <Text>Cargando...</Text>
                ) : (
                    <Text fontSize="2xl" fontWeight="bold" color="green.500">
                        {puntosActuales}
                    </Text>
                )}
            </Box>
    
            <Flex justifyContent="center" mb={6}>
                <Input
                    placeholder="Buscar producto..."
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    width="250px"
                    mr={2}
                />
                <Button colorScheme="green">Buscar</Button>
            </Flex>
    
            {loadingProductos && <Text textAlign="center">Cargando ofertas...</Text>}
            {error && <Text textAlign="center" color="red.500">{error}</Text>}
    
            {!loadingProductos && !error && (
                <Grid templateColumns="repeat(auto-fill, minmax(180px, 1fr))" gap={4}>
                    {filteredProducts.map((producto) => (
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
                            <Text fontSize="sm" color="gray.500">{producto.rubro}</Text>
                            <Button
                                mt={3}
                                size="sm"
                                colorScheme="green"
                                onClick={() => handleCanjear(producto.id)}
                                disabled={loadingCanje === producto.id} // Desactiva el botón si está procesando
                            >
                                {loadingCanje === producto.id ? (
                                    <Spinner size="sm" /> // Muestra el Spinner mientras está en progreso
                                ) : (
                                    'Canjear'
                                )}
                            </Button>
                        </Box>
                    ))}
                </Grid>
            )}
        </Box>
    );    
}

export default ConsultaCanjeForm;
