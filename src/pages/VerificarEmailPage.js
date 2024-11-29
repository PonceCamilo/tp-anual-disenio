import React from 'react';
import { useNavigate } from 'react-router-dom';
import {
  Heading,
  Text,
  Button,
  Flex,
  VStack,
} from '@chakra-ui/react';

const EmailNoVerificadoPage = () => {
  const navigate = useNavigate();

  return (
    <Flex
      className="email-unverified-page"
      height="100vh"
      justify="center"
      align="center"
      bg="transparent" // Fondo gris claro
      textAlign="center"
    >
      <VStack spacing={5}>
        <Heading as="h1" fontSize="3rem" color="black">
          ¡Espera un momento! 📧
        </Heading>
        <Text fontWeight="bold" fontSize="1.5rem" marginTop="1rem">
          Parece que tu correo aún no está verificado...
        </Text>
        <Text fontSize="4rem" marginY={2}>
          🔒📨🕒
        </Text>
        <Text fontWeight="bold" fontSize="1.5rem">
          Por favor, revisa tu bandeja de entrada y sigue el enlace para verificarlo.
        </Text>
        <Button
          colorScheme="green"
          onClick={() => navigate('/')}
          padding="0.75rem 2rem"
          marginTop="1.5rem"
          _hover={{ bg: 'green.600' }} // Color de fondo en hover
        >
          Volver al inicio
        </Button>
      </VStack>
    </Flex>
  );
};

export default EmailNoVerificadoPage;
