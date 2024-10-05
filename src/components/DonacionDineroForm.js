import React from "react";
import { FormControl, FormLabel, Input, Button, Heading, Box, Text,Flex } from "@chakra-ui/react";
import MercadoPago from "../assets/iconos/MercadoPago.svg";
import PayPal from "../assets/iconos/PayPal.svg";
import CreditCard from "../assets/iconos/CreditCard.svg";

function DonacionDineroForm() {
  return (
    <Box
      backgroundColor="white"
      padding={6}
      borderRadius="md"
      boxShadow="lg"
      maxWidth="500px"
      width="100%"
    >
      <Heading as="h2" size="lg" textAlign="center" mb={4}>
        Donación de dinero
      </Heading>

      <FormControl id="monto" mb={4}>
        <FormLabel>Monto:</FormLabel>
        <Input type="number" placeholder="Ingrese el monto a donar" />
      </FormControl>

      <Text fontSize="lg" mb={2}>
        Doná con los siguientes métodos de pago:
      </Text>
      <Flex justifyContent="space-around" mb={6}>
        <img src={MercadoPago} alt="Mercado Pago" width="50" />
        <img src={PayPal} alt="PayPal" width="50" />
        <img src={CreditCard} alt="Tarjeta de Crédito" width="50" />
      </Flex>

      <Button variant="solid" colorScheme="green" width="full">
        Donar
      </Button>
    </Box>
  );
}

export default DonacionDineroForm;
