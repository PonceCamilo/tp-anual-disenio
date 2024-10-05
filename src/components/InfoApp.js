import React from "react";
import {
  Box,
  Text,
  Container,
  SimpleGrid,
  Heading,
  VStack,
} from '@chakra-ui/react';
import FormasColaborarGrid from './FormasColaborarGrid';

function InfoApp() {
  return (
    <Container 
      maxW="container.lg" 
      my={10} 
      centerContent
      minH="70vh" // Ajuste para la altura mínima
      display="flex"
      flexDirection="column"
      justifyContent="center"
       
    >
      <Box
        textAlign="center"
        mb={5}
        bg="white"
        p={5}
        borderRadius="md"
        boxShadow="md"
        width="100%"
      >
        <Text fontSize="lg" fontWeight="normal">
          Más del 10% de los argentinos viven en situación de calle, y muchos de ellos no pueden cumplir con sus necesidades alimenticias. Te invitamos a ayudar a estas personas de la siguiente manera:
        </Text>
      </Box>
      <FormasColaborarGrid />
    </Container>
  );
}

export default InfoApp;
