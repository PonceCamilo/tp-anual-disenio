import React from 'react';
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalCloseButton,
  Box,
  SimpleGrid,
  Image,
  Text,
} from '@chakra-ui/react';
import { useNavigate } from 'react-router-dom'; // Importa useNavigate
import LaDonate from '../assets/iconos/LaDonate.svg';
import Form from '../assets/iconos/Form.svg';
import Vianda from '../assets/iconos/Heladera.svg';
import Suscripcion from '../assets/iconos/Suscripcion.svg';
import FallaTecnica from '../assets/iconos/FallaTecnica.svg';
import Canje from '../assets/iconos/Canje.svg';
import Config from '../assets/iconos/Config.svg';
import Report from '../assets/iconos/Report.svg';
import Product from '../assets/iconos/Product.svg';

function UserProfileModal({ isOpen, onClose }) {
  const navigate = useNavigate();

  const handleNavigate = (path) => {
    navigate(path);
    onClose();
  };

  return (
    <Modal isOpen={isOpen} onClose={onClose} size="xl">
      <ModalOverlay />
      <ModalContent>
        <ModalHeader textAlign="center">Opciones de Usuario</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <SimpleGrid columns={4} spacing={6}>
            {[
              { path: '/donacion-dinero', img: LaDonate, label: 'Donar Dinero' },
              { path: '/donacion-vianda', img: Vianda, label: 'Donar Vianda' },
              { path: '/registro-vulnerable', img: Form, label: 'Registrar Persona Vulnerable' },
              { path: '/suscripcion-heladera', img: Suscripcion, label: 'Suscribirse' },
              { path: '/report-issue', img: FallaTecnica, label: 'Reportar Falla Técnica' },
              { path: '/consulta-canje', img: Canje, label: 'Consultar o Canjear productos' },
              { path: '/recomendar-puntos', img: Config, label: 'Configuración' },
              { path: '/reportes', img: Report, label: 'Reportes' },
              { path: '/publicar-producto', img: Product, label: 'Publicar Producto/Servicio' },
              { path: '/cargar-heladera', img: Product, label: 'Cargar Heladera' },
              { path: '/distribucion-viandas', img: Product, label: 'Distribucion Viandas' },
            ].map((item, index) => (
              <Box
                key={index}
                onClick={() => handleNavigate(item.path)}
                cursor="pointer"
                display="flex"
                flexDirection="column"
                alignItems="center"
                justifyContent="center"
                p={4}
                borderWidth={1}
                borderRadius="md"
                _hover={{ bg: 'gray.100' }}
                width="130px"
                minHeight="120px" // Altura mínima para evitar que el texto sobresalga
              >
                <Image src={item.img} alt={item.label} boxSize="60px" />
                <Text mt={2} textAlign="center">{item.label}</Text> {/* Centrar el texto */}
              </Box>
            ))}
          </SimpleGrid>
        </ModalBody>
      </ModalContent>
    </Modal>
  );
}

export default UserProfileModal;
