import React, { useState } from "react";
import {
  Box,
  FormControl,
  FormLabel,
  Input,
  Select,
  Textarea,
  Button,
  VStack,
  Heading,
  useToast,
} from "@chakra-ui/react";
import ContactMethod from "./ContactMethod";

function LegalForm({ onBack }) {
  const [formData, setFormData] = useState({
    companyName: "",
    organizationType: "",
    category: "",
    contactMethod: "",
    contactInfo: "",
    address: "",
  });

  const [contactMethod, setContactMethod] = useState("");
  const toast = useToast();

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleContactInfoChange = (newData) => {
    setFormData((prevData) => ({
      ...prevData,
      contactInfo: newData,
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    // Validación básica
    if (!formData.companyName || !formData.organizationType || !contactMethod || !formData.contactInfo) {
      toast({
        title: "Error",
        description: "Por favor, completa todos los campos obligatorios.",
        status: "error",
        duration: 5000,
        isClosable: true,
      });
      return;
    }

    try {
      const response = await fetch("/api/legal-registrations", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        toast({
          title: "¡Registro exitoso!",
          description: "La información ha sido enviada correctamente.",
          status: "success",
          duration: 5000,
          isClosable: true,
        });
        setFormData({
          companyName: "",
          organizationType: "",
          category: "",
          contactMethod: "",
          contactInfo: "",
          address: "",
        });
        setContactMethod("");
      } else {
        const errorData = await response.json();
        console.error("Error al registrar:", errorData);
        toast({
          title: "Error",
          description: "Hubo un problema al registrar. Por favor, inténtalo nuevamente.",
          status: "error",
          duration: 5000,
          isClosable: true,
        });
      }
    } catch (error) {
      console.error("Error al enviar los datos:", error);
      toast({
        title: "Error",
        description: "Hubo un problema al registrar. Por favor, inténtalo nuevamente.",
        status: "error",
        duration: 5000,
        isClosable: true,
      });
    }
  };

  return (
    <Box as="form" onSubmit={handleSubmit} p={6} borderWidth={1} borderRadius="md" boxShadow="md">
      <VStack spacing={4} align="stretch">
        <Heading size="lg" textAlign="center" mb={4}>
          Registro Persona Jurídica
        </Heading>
        <FormControl isRequired>
          <FormLabel>Razón Social</FormLabel>
          <Input
            type="text"
            placeholder="Razón Social"
            name="companyName"
            value={formData.companyName}
            onChange={handleInputChange}
          />
        </FormControl>

        <FormControl isRequired>
          <FormLabel>Tipo</FormLabel>
          <Select
            placeholder="Seleccione el tipo de organización"
            name="organizationType"
            value={formData.organizationType}
            onChange={handleInputChange}
          >
            <option value="Gubernamental">Gubernamental</option>
            <option value="ONG">ONG</option>
            <option value="Empresa">Empresa</option>
            <option value="Institución">Institución</option>
          </Select>
        </FormControl>

        <FormControl>
          <FormLabel>Rubro</FormLabel>
          <Input
            type="text"
            placeholder="Rubro"
            name="category"
            value={formData.category}
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
            <option value="email">Email</option>
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
          <FormLabel>Dirección (opcional)</FormLabel>
          <Textarea
            placeholder="Ingrese su dirección"
            name="address"
            value={formData.address}
            onChange={handleInputChange}
          />
        </FormControl>

        <Button type="submit" colorScheme="green" size="lg" width="full">
          Registrar
        </Button>
        <Button
            type="button"
            colorScheme="blue"
            onClick={onBack} // Redirigir a la página de selección
          >
            Volver atrás
          </Button>
      </VStack>
    </Box>
  );
}

export default LegalForm;
