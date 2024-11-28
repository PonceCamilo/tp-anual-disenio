import React, { useState } from 'react';
import {
  Heading,
  Text,
  Button,
  Flex,
  VStack,
  RadioGroup,
  Stack,
  Radio,
  Box,
  Input,
  FormLabel,
} from '@chakra-ui/react';

const PersonaForm = () => {
  const [personaType, setPersonaType] = useState(null); // Estado para el tipo de persona
  const [formData, setFormData] = useState({}); // Estado para los datos del formulario

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = () => {
    // Aquí puedes manejar el envío del formulario, por ejemplo, enviarlo al backend.
    console.log('Datos enviados:', formData);
  };

  return (
    <Flex
      height="100vh"
      justify="center"
      align="center"
      bg="transparent"
      textAlign="center"
    >
      <VStack spacing={5} width="100%" maxWidth="500px">
        {!personaType ? (
          <>
            <Heading as="h1" fontSize="3rem" color="blue">
              ¿Qué tipo de persona eres?
            </Heading>
            <RadioGroup
              onChange={setPersonaType}
              value={personaType}
              size="lg"
              colorScheme="blue"
            >
              <Stack direction="column" spacing={4}>
                <Radio value="humana">Persona Humana</Radio>
                <Radio value="juridica">Persona Jurídica</Radio>
              </Stack>
            </RadioGroup>
          </>
        ) : (
          <Box width="100%">
            <Heading as="h2" fontSize="2xl" marginBottom={4}>
              Formulario para {personaType === 'humana' ? 'Persona Humana' : 'Persona Jurídica'}
            </Heading>
            {personaType === 'humana' ? (
              // Formulario para Persona Humana
              <VStack spacing={4} align="start">
                <FormLabel htmlFor="nombre">Nombre Completo</FormLabel>
                <Input
                  id="nombre"
                  name="nombre"
                  placeholder="Ingresa tu nombre completo"
                  onChange={handleInputChange}
                />
                <FormLabel htmlFor="dni">DNI</FormLabel>
                <Input
                  id="dni"
                  name="dni"
                  placeholder="Ingresa tu DNI"
                  onChange={handleInputChange}
                />
                <FormLabel htmlFor="telefono">Teléfono</FormLabel>
                <Input
                  id="telefono"
                  name="telefono"
                  placeholder="Ingresa tu teléfono"
                  onChange={handleInputChange}
                />
              </VStack>
            ) : (
              // Formulario para Persona Jurídica
              <VStack spacing={4} align="start">
                <FormLabel htmlFor="razonSocial">Razón Social</FormLabel>
                <Input
                  id="razonSocial"
                  name="razonSocial"
                  placeholder="Ingresa la razón social"
                  onChange={handleInputChange}
                />
                <FormLabel htmlFor="cuit">CUIT</FormLabel>
                <Input
                  id="cuit"
                  name="cuit"
                  placeholder="Ingresa el CUIT"
                  onChange={handleInputChange}
                />
                <FormLabel htmlFor="direccion">Dirección</FormLabel>
                <Input
                  id="direccion"
                  name="direccion"
                  placeholder="Ingresa la dirección"
                  onChange={handleInputChange}
                />
              </VStack>
            )}
            <Button
              colorScheme="blue"
              onClick={handleSubmit}
              marginTop="1.5rem"
              width="100%"
            >
              Enviar
            </Button>
            <Button
              variant="outline"
              colorScheme="blue"
              marginTop="1rem"
              width="100%"
              onClick={() => setPersonaType(null)}
            >
              Volver
            </Button>
          </Box>
        )}
      </VStack>
    </Flex>
  );
};

export default PersonaForm;
