import React, { useState } from "react";
import RecomendarPuntosApp from "../components/RecomendarPuntosApp";
import {
  Box,
  Input,
  Button,
  Flex,
  Text,
} from '@chakra-ui/react';

function RecomendarPuntosPage() {
  const [puntos, setPuntos] = useState([]);
  const [selectedLocation, setSelectedLocation] = useState(null);
  const [radius, setRadius] = useState(0);
  const [direccion, setDireccion] = useState("");

  const handleDireccionChange = (e) => {
    setDireccion(e.target.value);
  };

  const handleBuscarDireccion = () => {
    console.log("Buscar dirección:", direccion);
    // Aquí puedes agregar la lógica de búsqueda de dirección
  };

  const handleSubmit = async () => {
    try {
      const response = await fetch('http://localhost:8080/heladeras/recomendarPuntos', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          latitud: selectedLocation.lat,
          longitud: selectedLocation.lng,
          radio: radius,
        }),
      });
      const data = await response.json();
      console.log(data);
      setPuntos(data);
    } catch (error) {
      console.error('Error al obtener las recomendaciones:', error);
    }
  };

  return (
    <Flex
      direction="column"
      minHeight="100vh"
      alignItems="center"
      justifyContent="center"
      bg="transparent"
    >
      <Box
        width="80vw"
        mt={12}
        mb={4}
        px={[2, 4, 16]}
        display="flex"
        alignItems="center"
        justifyContent="center"
      >
        <Input
          flex={5}
          placeholder="Ingrese una dirección"
          value={direccion}
          onChange={handleDireccionChange}
          mr={2}
          borderColor="gray.300"
          bg={"white"}
        />
        <Button flex={1} onClick={handleBuscarDireccion} colorScheme="green">
          Aceptar
        </Button>
      </Box>

      <Flex
        width="80vw"
        height="80vh"
        bg="white"
        boxShadow="lg"
        borderRadius="lg"
        p={4}
        overflow="hidden"
      >
        <Box flex={1} bg="white" p={4}>
          <Text fontSize="lg" mb={4}>
            Seleccione un área en el mapa y presione <b>Obtener recomendación</b>
          </Text>

          <Box overflowY="auto" maxHeight="60vh">
            {puntos.map((punto) => (
              <Box
                key={punto.nombre}
                bg="teal.50"
                p={4}
                borderRadius="md"
                mb={2}
              >
                <Text>{punto.nombre}</Text>
                <Text>Lat: {punto.latitud}</Text>
                <Text>Long: {punto.longitud}</Text>
              </Box>
            ))}
          </Box>

          <Button mt={4} colorScheme="green" onClick={handleSubmit}>
            Obtener recomendación
          </Button>
        </Box>

        <Box flex={5} ml={4} borderRadius="md" overflow="hidden">
          <RecomendarPuntosApp
            radius={radius}
            selectedLocation={selectedLocation}
            setRadius={setRadius}
            setSelectedLocation={setSelectedLocation}
            puntos={puntos}
            setPuntos={setPuntos}
          />
        </Box>
      </Flex>
    </Flex>
  );
}

export default RecomendarPuntosPage;
