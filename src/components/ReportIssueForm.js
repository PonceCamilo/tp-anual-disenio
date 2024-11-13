import React, { useState } from 'react';
import { useToast } from '@chakra-ui/react';
import { useAuth } from '../config/authContext';
import {
  Box,
  FormControl,
  FormLabel,
  Input,
  Select,
  Textarea,
  Button,
  Heading
} from '@chakra-ui/react';

const ReportIssueForm = ({ fridges }) => {
  const { accessToken } = useAuth();
  const toast = useToast();
  
  const [heladeraId, setHeladeraId] = useState("");
  const [descripcion, setDescripcion] = useState("");
  const [email, setEmail] = useState("");
  const [file, setFile] = useState(null); // Estado para almacenar el archivo
  const colaboradorUUID = localStorage.getItem('sub');

  const handleFileChange = (event) => {
    setFile(event.target.files[0]); // Guardar el archivo en el estado, pero sin enviarlo
  };

  const handleDonation = async (event) => {
    event.preventDefault();

    // Obtener la fecha actual en el formato "YYYY-MM-DD"
    const today = new Date().toISOString().split("T")[0];

    try {
      const response = await fetch(`http://localhost:8080/incidentes/reportarFallaTecnica?colaboradorUUID=${colaboradorUUID}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${accessToken}`,
        },
        body: JSON.stringify({
          heladeraId,
          descripcion,
          fecha: today,
          email,
        }),
      });

      if (response.ok) {
        const data = await response.text();
        toast({
          title: "Reporte Exitoso",
          description: data || "Reporte exitoso, en breve se atenderá.",
          status: "success",
          duration: 5000,
          isClosable: true,
        });
      } else {
        const errorText = await response.text();
        toast({
          title: "Error",
          description: errorText || "Ocurrió un error al procesar el reporte.",
          status: "error",
          duration: 5000,
          isClosable: true,
        });
      }
    } catch (error) {
      console.error("Error al realizar el reporte:", error);
      toast({
        title: "Error de conexión",
        description: "No se pudo conectar con el servidor para procesar el reporte.",
        status: "error",
        duration: 5000,
        isClosable: true,
      });
    }
  };

  return (
    <Box
      as="form"
      p={6}
      borderRadius="md"
      bg="gray.50"
      boxShadow="md"
      onSubmit={handleDonation}
    >
      <Heading as="h2" fontSize="2xl" mb={4}>
        Reportar un problema
      </Heading>

      <FormControl mb={4}>
        <FormLabel htmlFor="fridge">Selecciona una heladera</FormLabel>
        <Select
          id="fridge"
          placeholder="Selecciona una heladera"
          bg="white"
          value={heladeraId}
          onChange={(e) => setHeladeraId(e.target.value)}
        >
          {fridges.map((fridge) => (
            <option key={fridge.id} value={fridge.id}>
              {fridge.name}
            </option>
          ))}
        </Select>
      </FormControl>

      <FormControl mb={4}>
        <FormLabel htmlFor="issue">Descripción del problema</FormLabel>
        <Textarea
          id="issue"
          placeholder="Describe el problema..."
          bg="white"
          value={descripcion}
          onChange={(e) => setDescripcion(e.target.value)}
        />
      </FormControl>

      <FormControl mb={4}>
        <FormLabel htmlFor="email">Correo electrónico</FormLabel>
        <Input
          id="email"
          type="email"
          placeholder="Tu correo electrónico"
          bg="white"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </FormControl>

      {/* Input para cargar una foto */}
      <FormControl mb={4}>
        <FormLabel htmlFor="photo">Subir una foto</FormLabel>
        <Input
          id="photo"
          type="file"
          accept="image/*"
          onChange={handleFileChange} // Guardar el archivo, pero no enviarlo
        />
      </FormControl>

      <Button
        type="submit"
        colorScheme="green"
        width="100%"
      >
        Enviar Reporte
      </Button>
    </Box>
  );
};

export default ReportIssueForm;
