import React from 'react';
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
  return (
    <Box
      as="form"
      p={6}
      borderRadius="md"
      bg="gray.50"
      boxShadow="md"
    >
      <Heading as="h2" fontSize="2xl" mb={4}>
        Reportar un problema
      </Heading>

      <FormControl mb={4}>
        <FormLabel htmlFor="fridge">Selecciona una heladera</FormLabel>
        <Select id="fridge" placeholder="Selecciona una heladera" bg="white">
          {fridges.map((fridge) => (
            <option key={fridge.id} value={fridge.id}>
              {fridge.name}
            </option>
          ))}
        </Select>
      </FormControl>

      <FormControl mb={4}>
        <FormLabel htmlFor="issue">Descripción del problema</FormLabel>
        <Textarea id="issue" placeholder="Describe el problema..." bg="white" />
      </FormControl>

      <FormControl mb={4}>
        <FormLabel htmlFor="email">Correo electrónico</FormLabel>
        <Input id="email" type="email" placeholder="Tu correo electrónico" bg="white" />
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
