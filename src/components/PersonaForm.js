import React from 'react';
import { useNavigate } from 'react-router-dom';
import {
  Heading,
  Text,
  Button,
  Flex,
  VStack,
} from '@chakra-ui/react';

const PersonaForm = () => {
    const navigate = useNavigate();
    
    return (
        <Flex
        height="100vh"
        justify="center"
        align="center"
        bg="transparent"
        textAlign="center"
        >
        <VStack spacing={5}>
            <Heading as="h1" fontSize="3rem" color="blue">
            Usted es una persona:
            </Heading>
            
            <Text fontWeight="bold" fontSize="1.5rem" marginTop="1rem">
            ¡Tu email ha sido verificado con éxito!
            </Text>
            <Text fontSize="4rem" marginY={2}>
            🎉🎉🎉
            </Text>
            <Text fontWeight="bold" fontSize="1.5rem">
            ¡Ya puedes acceder a todas las funcionalidades de la aplicación!
            </Text>
            <Button
            colorScheme="blue"
            onClick={() => navigate('/')}
            padding="0.75rem 2rem"
            marginTop="1.5rem"
            _hover={{ bg: '#215fef' }} // Color de fondo en hover
            >
            Volver al inicio
            </Button>
        </VStack>
        </Flex>
    );
    };