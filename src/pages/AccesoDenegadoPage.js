import React from 'react';
import { useNavigate } from 'react-router-dom';
import {
  Box,
  Heading,
  Text,
  Button,
  Flex,
  VStack,
} from '@chakra-ui/react';

const AccesoDenegadoPage = () => {
  const navigate = useNavigate();

  return (
    <Flex
      className="denied-page"
      height="100vh"
      justify="center"
      align="center"
      bg="transparent" // Fondo gris claro
      textAlign="center"
    >
      <VStack spacing={5}>
        <Heading as="h1" fontSize="3rem" color="blue">
          ¿Te perdiste compañero?
        </Heading>
        <Text fontWeight="bold" fontSize="1.5rem" marginTop="1rem">
          Parece que estás tratando de entrar a un lugar al que no tienes acceso...
        </Text>
        <Text fontSize="4rem" marginY={2}>
          🚫🙅‍♂️🚪
        </Text>
        <Text fontWeight="bold" fontSize="1.5rem">
          Dejame que te muestre la salida, por acá porfavor 👇
        </Text>
        <Button
          colorScheme="blue"
          onClick={() => navigate('/')}
          padding="0.75rem 2rem"
          marginTop="1.5rem"
          _hover={{ bg: '#215fef' }} // Color de fondo en hover
        >
          Volver a lo seguro
        </Button>
      </VStack>
    </Flex>
  );
};

export default AccesoDenegadoPage;
