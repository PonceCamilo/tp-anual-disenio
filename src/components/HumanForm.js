import React, { useState } from "react";
import {
  Box,
  FormControl,
  FormLabel,
  Input,
  Select,
  Checkbox,
  VStack,
  ButtonGroup,
  Button,
  Heading,
  HStack,
  useToast,
} from "@chakra-ui/react";
import { useNavigate } from "react-router-dom"; // Para redirigir
import ContactMethod from "./ContactMethod";

function HumanForm({ onBack }) {
  const [formData, setFormData] = useState({
    name: "",
    lastName: "",
    contactMethod: "",
    contactInfo: "",
    donationChecked: false,
    foodDonationChecked: false,
    mealDistributionChecked: false,
    birthdate: "",
    address: "",
  });

  const [contactMethod, setContactMethod] = useState("");
  const toast = useToast();
  const navigate = useNavigate(); // Hook para navegar

  const handleInputChange = (event) => {
    const { name, value, type, checked } = event.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleContactInfoChange = (newValue) => {
    setFormData((prevData) => ({
      ...prevData,
      contactInfo: newValue,
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // Validación básica
    if (!formData.name || !formData.lastName || !contactMethod || !formData.contactInfo) {
      toast({
        title: "Error",
        description: "Por favor, completa todos los campos obligatorios.",
        status: "error",
        duration: 5000,
        isClosable: true,
      });
      return;
    }

    toast({
      title: "Formulario enviado",
      description: "Los datos se han registrado correctamente.",
      status: "success",
      duration: 5000,
      isClosable: true,
    });

    // Resetear datos
    setFormData({
      name: "",
      lastName: "",
      contactMethod: "",
      contactInfo: "",
      donationChecked: false,
      foodDonationChecked: false,
      mealDistributionChecked: false,
      birthdate: "",
      address: "",
    });
    setContactMethod("");
  };

  return (
    <Box as="form" onSubmit={handleSubmit} p={6} borderWidth={1} borderRadius="md" boxShadow="md">
      <VStack spacing={4} align="stretch">
        <Heading size="lg" textAlign="center" mb={4}>
          Registrar Persona Humana
        </Heading>

        <FormControl isRequired>
          <FormLabel>Nombre</FormLabel>
          <Input
            type="text"
            placeholder="Ingrese su nombre"
            name="name"
            value={formData.name}
            onChange={handleInputChange}
          />
        </FormControl>

        <FormControl isRequired>
          <FormLabel>Apellido</FormLabel>
          <Input
            type="text"
            placeholder="Ingrese su apellido"
            name="lastName"
            value={formData.lastName}
            onChange={handleInputChange}
          />
        </FormControl>

        <FormControl isRequired>
          <FormLabel>Método de Contacto</FormLabel>
          <Select
            placeholder="Seleccione un método de contacto"
            name="contactMethod"
            value={contactMethod}
            onChange={(e) => {
              handleInputChange(e);
              setContactMethod(e.target.value);
            }}
          >
            <option value="emailContact">Email</option>
            <option value="telefono">Teléfono</option>
            <option value="whatsapp">WhatsApp</option>
          </Select>
        </FormControl>

        {contactMethod && (
          <FormControl>
            <FormLabel>Información de Contacto</FormLabel>
            <ContactMethod
              type={contactMethod}
              value={formData.contactInfo}
              onChange={handleContactInfoChange}
            />
          </FormControl>
        )}

        <FormControl>
          <FormLabel>Formas de Contribuir</FormLabel>
          <HStack spacing={4}>
            <Checkbox
              name="donationChecked"
              isChecked={formData.donationChecked}
              onChange={handleInputChange}
            >
              Donación de dinero
            </Checkbox>
            <Checkbox
              name="foodDonationChecked"
              isChecked={formData.foodDonationChecked}
              onChange={handleInputChange}
            >
              Donación de vianda
            </Checkbox>
            <Checkbox
              name="mealDistributionChecked"
              isChecked={formData.mealDistributionChecked}
              onChange={handleInputChange}
            >
              Distribución de "viandas"
            </Checkbox>
          </HStack>
        </FormControl>

        <FormControl>
          <FormLabel>Fecha de Nacimiento (opcional)</FormLabel>
          <Input
            type="date"
            name="birthdate"
            value={formData.birthdate}
            onChange={handleInputChange}
          />
        </FormControl>

        <FormControl>
          <FormLabel>Dirección (opcional)</FormLabel>
          <Input
            type="text"
            placeholder="Ingrese su dirección"
            name="address"
            value={formData.address}
            onChange={handleInputChange}
          />
        </FormControl>

        <ButtonGroup justifyContent="center" mt={4}>
          <Button type="submit" colorScheme="green">
            Enviar
          </Button>
          <Button
            type="button"
            colorScheme="blue"
            onClick={onBack}
          >
            Volver atrás
          </Button>
          
        </ButtonGroup>
      </VStack>
    </Box>
  );
}

export default HumanForm;
