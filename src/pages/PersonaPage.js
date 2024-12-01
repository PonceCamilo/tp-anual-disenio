import React, { useState, useEffect } from "react";
import {
  Box,
  Heading,
  Text,
  Flex,
  Button,
  VStack,
  Container,
} from "@chakra-ui/react";
import LegalForm from "../components/LegalForm"; // Importa tu componente LegalForm
import HumanForm from "../components/HumanForm"; // Importa tu componente HumanForm
import { useAuth } from "../config/authContext"; // Importa el hook useAuth0
import { Navigate } from 'react-router-dom';
const data = localStorage.getItem('cached_roles');

const PersonaPage = () => {
  const [personaType, setPersonaType] = useState(null); // Estado para tipo de persona seleccionada
  // Función para volver a la selección de tipo de persona
  const {isAuthenticated, login} = useAuth();
  if (!isAuthenticated){
    login(); // Redirige al usuario al inicio de sesión si no está autenticado
    return null
  }
  const handleBack = () => {
    setPersonaType(null);
  };
  if(data){
    return <Navigate to="/" replace />;
  }
  return (
    <Container
      maxW="container.md"
      mx="auto"
      pt={{ base: "60px", md: "80px" }}
      minH="100vh"
      pb={10}
    >
      <Flex
        direction="column"
        align="center"
        bgGradient="linear(to-r, #f6f8fc, #edf2f7)"
        borderRadius="lg"
        boxShadow="lg"
        p={10}
        gap={6}
      >
        {!personaType ? (
          <>
            <Heading
              size="lg"
              fontWeight="bold"
              color="#2C5282"
              textAlign="center"
            >
              Selecciona el tipo de colaborador
            </Heading>
            <Text fontSize="md" color="gray.700" textAlign="center">
              Elige si deseas registrarte como una persona humana o jurídica.
              Esto nos permitirá recopilar la información adecuada para tu
              perfil.
            </Text>
            <VStack spacing={4}>
              <Button
                colorScheme="blue"
                size="lg"
                onClick={() => setPersonaType("human")}
                width="200px"
              >
                Persona Humana
              </Button>
              <Button
                colorScheme="green"
                size="lg"
                onClick={() => setPersonaType("legal")}
                width="200px"
              >
                Persona Jurídica
              </Button>
            </VStack>
          </>
        ) : (
          <Box width="100%">
            {personaType === "human" && (
              <HumanForm onBack={handleBack} />
            )}
            {personaType === "legal" && (
              <LegalForm onBack={handleBack} />
            )}
          </Box>
        )}
      </Flex>
    </Container>
  );
};

export default PersonaPage;
