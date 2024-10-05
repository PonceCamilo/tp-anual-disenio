import React, { useState } from 'react';
import {
    Box,
    Input,
    Button,
    Text,
    Grid,
    Image,
    Heading,
    Flex,
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalBody,
    ModalCloseButton,
    VStack,
    FormControl,
    Checkbox,
    HStack,
    useDisclosure,
} from '@chakra-ui/react';

const heladeras = [
    { id: 1, nombre: 'Heladera 1', descripcion: 'Descripción de la Heladera 1', imagen: 'https://via.placeholder.com/150' },
    { id: 2, nombre: 'Heladera 2', descripcion: 'Descripción de la Heladera 2', imagen: 'https://via.placeholder.com/150' },
    { id: 3, nombre: 'Heladera 3', descripcion: 'Descripción de la Heladera 3', imagen: 'https://via.placeholder.com/150' },
    { id: 4, nombre: 'Heladera 4', descripcion: 'Descripción de la Heladera 4', imagen: 'https://via.placeholder.com/150' },
    { id: 5, nombre: 'Heladera 5', descripcion: 'Descripción de la Heladera 5', imagen: 'https://via.placeholder.com/150' },
    { id: 6, nombre: 'Heladera 6', descripcion: 'Descripción de la Heladera 6', imagen: 'https://via.placeholder.com/150' },
    { id: 1, nombre: 'Heladera 1', descripcion: 'Descripción de la Heladera 1', imagen: 'https://via.placeholder.com/150' },
    { id: 2, nombre: 'Heladera 2', descripcion: 'Descripción de la Heladera 2', imagen: 'https://via.placeholder.com/150' },
    { id: 3, nombre: 'Heladera 3', descripcion: 'Descripción de la Heladera 3', imagen: 'https://via.placeholder.com/150' },
    { id: 4, nombre: 'Heladera 4', descripcion: 'Descripción de la Heladera 4', imagen: 'https://via.placeholder.com/150' },
    { id: 5, nombre: 'Heladera 5', descripcion: 'Descripción de la Heladera 5', imagen: 'https://via.placeholder.com/150' },
    { id: 6, nombre: 'Heladera 6', descripcion: 'Descripción de la Heladera 6', imagen: 'https://via.placeholder.com/150' },
    
];

function SuscripcionHeladeraContent() {
    const [searchTerm, setSearchTerm] = useState('');
    const { isOpen, onOpen, onClose } = useDisclosure();
    const [selectedHeladera, setSelectedHeladera] = useState(null);
    const [notificationSettings, setNotificationSettings] = useState({
        case1: false,
        case2: false,
        case3: false,
    });
    const [viandasNumber, setViandasNumber] = useState({
        case1: '',
        case2: '',
    });

    const handleSuscribirse = (heladera) => {
        setSelectedHeladera(heladera);
        onOpen();
    };

    const handleCheckboxChange = (e) => {
        const { name, checked } = e.target;
        setNotificationSettings(prevState => ({
            ...prevState,
            [name]: checked,
        }));
    };

    const handleViandasChange = (e) => {
        const { name, value } = e.target;
        setViandasNumber(prevState => ({
            ...prevState,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        alert(`Configuración enviada para ${selectedHeladera?.nombre}`);
        onClose();
    };

    const filteredHeladeras = heladeras.filter(heladera =>
        heladera.nombre.toLowerCase().includes(searchTerm.toLowerCase())
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
                <Heading size="md" mb={1}>Suscripción a Heladeras</Heading>
            </Box>
            <Flex justifyContent="center" mb={6}>
                <Input
                    placeholder="Buscar heladera..."
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    width="250px"
                    mr={2}
                />
                <Button colorScheme="green" onClick={() => {}}>Buscar</Button>
            </Flex>
            <Grid templateColumns="repeat(auto-fill, minmax(180px, 1fr))" gap={4}>
                {filteredHeladeras.map(heladera => (
                    <Box
                        key={heladera.id}
                        p={3}
                        bg="gray.50"
                        borderRadius="lg"
                        boxShadow="md"
                        textAlign="center"
                        transition="transform 0.3s, box-shadow 0.3s"
                        _hover={{ transform: 'scale(1.05)', boxShadow: 'lg' }}
                    >
                        <Image src={heladera.imagen} alt={heladera.nombre} borderRadius="md" mb={3} />
                        <Heading size="sm" mb={2}>{heladera.nombre}</Heading>
                        <Text fontSize="md" color="gray.600">{heladera.descripcion}</Text>
                        <Button
                            mt={3}
                            size="sm"
                            colorScheme="green"
                            onClick={() => handleSuscribirse(heladera)}
                        >
                            Suscribirse
                        </Button>
                    </Box>
                ))}
            </Grid>

            <Modal isOpen={isOpen} onClose={onClose}>
                <ModalOverlay />
                <ModalContent>
                    <ModalHeader>Formulario de Suscripción para {selectedHeladera?.nombre}</ModalHeader>
                    <ModalCloseButton />
                    <ModalBody>
                        <form onSubmit={handleSubmit}>
                            <VStack spacing={4}>
                                <FormControl>
                                    <Checkbox
                                        name="case1"
                                        isChecked={notificationSettings.case1}
                                        onChange={handleCheckboxChange}
                                    >
                                        Viandas disponibles
                                        <Input
                                            type="number"
                                            name="case1"
                                            value={viandasNumber.case1}
                                            onChange={handleViandasChange}
                                            min="1"
                                            placeholder="Número de viandas"
                                            mt={2}
                                            borderColor="blue.400"
                                        />
                                    </Checkbox>
                                </FormControl>
                                <FormControl>
                                    <Checkbox
                                        name="case2"
                                        isChecked={notificationSettings.case2}
                                        onChange={handleCheckboxChange}
                                    >
                                        Faltan viandas para llenar la heladera
                                        <Input
                                            type="number"
                                            name="case2"
                                            value={viandasNumber.case2}
                                            onChange={handleViandasChange}
                                            min="1"
                                            placeholder="Número de viandas"
                                            mt={2}
                                            borderColor="blue.400"
                                        />
                                    </Checkbox>
                                </FormControl>
                                <FormControl>
                                    <Checkbox
                                        name="case3"
                                        isChecked={notificationSettings.case3}
                                        onChange={handleCheckboxChange}
                                    >
                                        La heladera sufrió un desperfecto
                                    </Checkbox>
                                </FormControl>
                                <HStack spacing={4}>
                                    <Button type="submit" colorScheme="green">Suscribirse</Button>
                                    <Button onClick={onClose} colorScheme="red">Cancelar</Button>
                                </HStack>
                            </VStack>
                        </form>
                    </ModalBody>
                </ModalContent>
            </Modal>
        </Box>
    );
}

export default SuscripcionHeladeraContent;
