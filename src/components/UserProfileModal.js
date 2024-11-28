import React, { useState } from 'react';
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
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../config/authContext'; // Importa el hook de autenticación
import LaDonate from '../assets/iconos/LaDonate.svg';
import Form from '../assets/iconos/Form.svg';
import Vianda from '../assets/iconos/Heladera.svg';
import Suscripcion from '../assets/iconos/Suscripcion.svg';
import FallaTecnica from '../assets/iconos/FallaTecnica.svg';
import Canje from '../assets/iconos/Canje.svg';
import Config from '../assets/iconos/Config.svg';
import Report from '../assets/iconos/Report.svg';
import Product from '../assets/iconos/Product.svg';
import LoadCSVModal from './LoadCSVModal'; // Importa el modal para cargar CSV

function UserProfileModal({ isOpen, onClose }) {
  const { roles } = useAuth();
  const navigate = useNavigate();
  const [isCSVModalOpen, setIsCSVModalOpen] = useState(false); // Estado para controlar la apertura del modal

  const handleNavigate = (path) => {
    navigate(path);
    onClose();
  };

  const handleOpenCSVModal = () => {
    setIsCSVModalOpen(true); // Abre el modal para cargar CSV
    onClose(); // Cierra el modal de perfil de usuario
  };

  const options = [
    { path: '/donacion-dinero', img: LaDonate, label: 'Donar Dinero', allowedRoles: ['ROLE_ADMIN', 'ROLE_COLLABORATOR'] },
    { path: '/donacion-vianda', img: Vianda, label: 'Donar Vianda', allowedRoles: ['ROLE_ADMIN', 'ROLE_COLLABORATOR'] },
    { path: '/registro-vulnerable', img: Form, label: 'Registrar Persona Vulnerable', allowedRoles: ['ROLE_ADMIN', 'ROLE_COLLABORATOR'] },
    { path: '/suscripcion-heladera', img: Suscripcion, label: 'Suscribirse', allowedRoles: ['ROLE_ADMIN', 'ROLE_COLLABORATOR'] },
    { path: '/report-issue', img: FallaTecnica, label: 'Reportar Falla Técnica', allowedRoles: ['ROLE_ADMIN', 'ROLE_COLLABORATOR'] },
    { path: '/consulta-canje', img: Canje, label: 'Consultar o Canjear productos', allowedRoles: ['ROLE_ADMIN', 'ROLE_COLLABORATOR'] },
    { path: '/recomendar-puntos', img: Config, label: 'Configuración', allowedRoles: ['ROLE_ADMIN', 'ROLE_COLLABORATOR'] },
    { path: '/reportes', img: Report, label: 'Reportes', allowedRoles: ['ROLE_ADMIN'] },
    { path: '/publicar-producto', img: Product, label: 'Publicar Producto/Servicio', allowedRoles: ['ROLE_ADMIN', 'ROLE_COLLABORATOR'] },
    { path: '/cargar-heladera', img: Product, label: 'Cargar Heladera', allowedRoles: ['ROLE_ADMIN'] },
    { path: '/distribucion-viandas', img: Product, label: 'Distribución Viandas', allowedRoles: ['ROLE_ADMIN', 'ROLE_COLLABORATOR'] },
    {
      path: '/visita-tecnico', img: Config, label: 'Visita Tecnico', allowedRoles: ['ROLE_ADMIN', 'ROLE_TECHNICAL']
    },
    {
      path: '/reconocimientos-extra', img: Form, label: 'Reconocimientos Extra', allowedRoles: ['ROLE_ADMIN', 'ROLE_TECHNICAL']
    },
    { path: '', img: Product, label: 'Cargar CSV', allowedRoles: ['ROLE_ADMIN'], action: handleOpenCSVModal },
  ];

  // Filtra las opciones basándote en los roles del usuario
  const filteredOptions = options.filter(option =>
    option.allowedRoles.some(role => roles.includes(role))
  );

  return (
    <>
      <Modal isOpen={isOpen} onClose={onClose} size="xl">
        <ModalOverlay />
        <ModalContent>
          <ModalHeader textAlign="center">Opciones de Usuario</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <SimpleGrid columns={4} spacing={6}>
              {filteredOptions.map((item, index) => (
                <Box
                  key={index}
                  onClick={() => item.action ? item.action() : handleNavigate(item.path)}
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
                  minHeight="120px"
                >
                  <Image src={item.img} alt={item.label} boxSize="60px" />
                  <Text mt={2} textAlign="center">{item.label}</Text>
                </Box>
              ))}
            </SimpleGrid>
          </ModalBody>
        </ModalContent>
      </Modal>

      {/* Modal para cargar CSV */}
      < LoadCSVModal isOpen={isCSVModalOpen} onClose={() => setIsCSVModalOpen(false)
      } />
    </>
  );
}

export default UserProfileModal;
