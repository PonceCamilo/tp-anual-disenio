import React, { useState } from 'react';
import {
  Box,
  FormControl,
  FormLabel,
  Input,
  Select,
  Textarea,
  Button,
  Heading,
  useToast,
} from '@chakra-ui/react';
import { useAuth } from '../config/authContext';

const ReportIssueForm = ({ fridges }) => {
  const { accessToken } = useAuth();
  const toast = useToast();
  const colaboradorUUID = localStorage.getItem('sub');

  const [formData, setFormData] = useState({
    heladeraId: '',
    descripcion: '',
    email: '',
    file: null, // Archivo opcional
  });

  const handleChange = ({ target: { id, value, files } }) => {
    setFormData((prev) => ({
      ...prev,
      [id]: files ? files[0] : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const today = new Date().toISOString().split('T')[0];

    try {
      const response = await fetch(
        `http://localhost:8080/incidentes/reportarFallaTecnica?colaboradorUUID=${colaboradorUUID}`,
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${accessToken}`,
          },
          body: JSON.stringify({
            heladeraId: formData.heladeraId,
            descripcion: formData.descripcion,
            fecha: today,
            email: formData.email,
          }),
        }
      );

      const message = await response.text();
      toast({
        title: response.ok ? 'Reporte Exitoso' : 'Error',
        description: message || (response.ok ? 'En breve se atenderá.' : 'Ocurrió un error.'),
        status: response.ok ? 'success' : 'error',
        duration: 5000,
        isClosable: true,
      });
    } catch {
      toast({
        title: 'Error de conexión',
        description: 'No se pudo conectar con el servidor.',
        status: 'error',
        duration: 5000,
        isClosable: true,
      });
    }
  };

  return (
    <Box as="form" p={6} borderRadius="md" bg="gray.50" boxShadow="md" onSubmit={handleSubmit}>
      <Heading as="h2" fontSize="2xl" mb={4}>
        Reportar un problema
      </Heading>

      <FormControl mb={4}>
        <FormLabel htmlFor="heladeraId">Selecciona una heladera</FormLabel>
        <Select
          id="heladeraId"
          placeholder="Selecciona una heladera"
          bg="white"
          value={formData.heladeraId}
          onChange={handleChange}
        >
          {fridges.map(({ id, nombrePunto }) => (
            <option key={id} value={id}>
              {nombrePunto}
            </option>
          ))}
        </Select>
      </FormControl>

      <FormControl mb={4}>
        <FormLabel htmlFor="descripcion">Descripción del problema</FormLabel>
        <Textarea
          id="descripcion"
          placeholder="Describe el problema..."
          bg="white"
          value={formData.descripcion}
          onChange={handleChange}
        />
      </FormControl>

      <FormControl mb={4}>
        <FormLabel htmlFor="email">Correo electrónico</FormLabel>
        <Input
          id="email"
          type="email"
          placeholder="Tu correo electrónico"
          bg="white"
          value={formData.email}
          onChange={handleChange}
        />
      </FormControl>

      <FormControl mb={4}>
        <FormLabel htmlFor="file">Subir una foto</FormLabel>
        <Input id="file" type="file" accept="image/*" onChange={handleChange} />
      </FormControl>

      <Button type="submit" colorScheme="green" width="100%">
        Enviar Reporte
      </Button>
    </Box>
  );
};

export default ReportIssueForm;
