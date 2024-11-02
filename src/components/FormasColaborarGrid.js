import React from 'react';
import { SimpleGrid, Box, Image, Text, Heading, useToast } from '@chakra-ui/react';
import { useNavigate } from 'react-router-dom';
import LaDonate from '../assets/iconos/LaDonate.svg';
import Food from '../assets/iconos/Heladera.svg';
import Heladera from '../assets/iconos/Food.svg';
import FallaTecnica from '../assets/iconos/FallaTecnica.svg';
function FormasColaborarGrid() {
  const navigate = useNavigate();

  const formasColaborar = [
    {
      id: 1,
      titulo: 'Donación de Dinero',
      descripcion: 'Contribuye con una donación monetaria.',
      imagen: LaDonate,
      link: '/donacion-dinero'
    },
    {
      id: 2,
      titulo: 'Donación de Viandas',
      descripcion: 'Prepara y dona viandas para quienes lo necesitan.',
      imagen: Food,
      link: '/donacion-vianda'
    },
    {
      id: 3,
      titulo: 'Distribución de Viandas',
      descripcion: 'Ayuda a distribuir viandas entre diferentes puntos.',
      imagen: Heladera,
      link: '/distribucion-viandas'
    },
    {
      id: 4,
      titulo: 'Reportar Falla Técnica',
      descripcion: 'Informa sobre problemas técnicos en las heladeras.',
      imagen: FallaTecnica,
      link: '/report-issue'
    },
  ];

  const handleCardClick = (link) => {
    navigate(link);
  };

  return (
    <SimpleGrid columns={{ base: 1, md: 2, lg: 4 }} spacing={4} mt={5}>
      {formasColaborar.map((forma) => (
        <Box
          key={forma.id}
          onClick={() => handleCardClick(forma.link)}
          cursor="pointer"
          textAlign="center"
          borderWidth={1}
          borderColor="gray.200"
          borderRadius="md"
          p={4}
          boxShadow="sm"
          _hover={{ boxShadow: 'md', borderColor: 'gray.400' }} // Efecto hover
        >
          <Image
            src={forma.imagen}
            alt={forma.titulo}
            boxSize="5rem"
            objectFit="contain"
            mx="auto"
            mb={4}
          />
          <Heading size="md" mb={2}>
            {forma.titulo}
          </Heading>
          <Text maxWidth="20rem" mx="auto">
            {forma.descripcion}
          </Text>
        </Box>
      ))}
    </SimpleGrid>
  );
}

export default FormasColaborarGrid;
