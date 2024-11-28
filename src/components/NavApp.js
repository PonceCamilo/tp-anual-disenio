import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import {
  Box,
  Flex,
  Image,
  Text,
  IconButton,
  useDisclosure,
  useMediaQuery,
  Drawer,
  DrawerOverlay,
  DrawerContent,
  DrawerCloseButton,
  DrawerHeader,
  DrawerBody,
} from '@chakra-ui/react';
import { HamburgerIcon } from '@chakra-ui/icons';
import UTNlogo from '../assets/logos/utn.svg';
import IcoProfile from '../assets/iconos/IcoProfile.svg';
import IcoHeladera from '../assets/iconos/IcoHeladera.svg';
import Logout from '../assets/iconos/Logout.svg';
import { useAuth } from '../config/authContext';
import UserProfileModal from './UserProfileModal';

function NavApp({ className }) {
  const { logout, login, isAuthenticated, user, accessToken } = useAuth();

  // useDisclosure para las opciones de usuario
  const { isOpen: isUserModalOpen, onOpen: onUserModalOpen, onClose: onUserModalClose } = useDisclosure();

  // useDisclosure para el menú hamburguesa
  const { isOpen: isHamburgerOpen, onOpen: onHamburgerOpen, onClose: onHamburgerClose } = useDisclosure();

  // Comprobación del tamaño de la pantalla
  const [isLargerThan992] = useMediaQuery('(min-width: 992px)');

  const handleNavLinkClick = () => {
    onHamburgerClose(); // Cerrar el menú hamburguesa al hacer clic
  };

  // Función para abrir el modal de usuario y cerrar el menú hamburguesa
  const handleUserClick = () => {
    onUserModalOpen(); // Abrir el modal de usuario
    onHamburgerClose(); // Cerrar el menú hamburguesa
  };

  useEffect(() => {
    if (isAuthenticated && user) {
      console.log("Usuario logueado:", {
        nombre: user.name,
        email: user.email,
        sub: user.sub,
        emailVerificado: user.email_verified,
        nickname: user.nickname,
        
      });

      console.log("Token:", {
        accessToken
      });
    }
  }, [isAuthenticated, user]);

  return (
    <>
      <Flex
        as="nav"
        className={`Nav-Bar ${className}`}
        bg="transparent"
        backdropFilter="blur(5px)"
        p={4}
        position="fixed"
        width="100%"
        zIndex="1050"
        justify="space-between"
        align="center"
      >
        <Flex align="center">
          <Image src={UTNlogo} alt="" width="30px" height="30px" mr={2} />
          <Link to="/" fontSize="2xl">Heladeras DDS</Link>
        </Flex>
        {/* Botón de menú hamburguesa solo en pantallas pequeñas */}
        {!isLargerThan992 && (
          <IconButton
            aria-label="Toggle Navigation"
            icon={<HamburgerIcon />}
            variant="outline"
            onClick={onHamburgerOpen} // Abrir menú hamburguesa
            display={{ base: 'block', md: 'none' }}
          />
        )}
        {/* Menú de navegación para pantallas grandes */}
        <Flex
          as="ul"
          listStyleType="none"
          display={{ base: 'none', md: 'flex' }}
          flexDirection="row"
          alignItems="center"
          justify="flex-end"
          flexGrow={1}
        >
          {isAuthenticated ? (
            <>
              <Box
                as="li"
                onClick={handleUserClick} // Cambiado para abrir el modal de usuario
                cursor="pointer"
                display="flex"
                alignItems="center"
                mr={4}
              >
                <Image
                  src={user.picture || IcoProfile}
                  alt=""
                  width="25px"
                  height="25px"
                  borderRadius="full"
                  mr={2}
                />
                <Text fontSize="lg">{user.name}</Text>
              </Box>
            </>
          ) : (
            <Box onClick={login} fontSize="lg" mr={4} cursor="pointer" display="flex" alignItems="center">
              <Image src={IcoProfile} alt="" width="30px" height="30px" mr={2} />
              <Text>Ingresar</Text>
            </Box>
          )}
          <Link to="/map" onClick={handleNavLinkClick}>
            <Box fontSize="lg" mr={4} cursor="pointer" display="flex" alignItems="center">
              <Image src={IcoHeladera} alt="" width="30px" height="30px" mr={2} />
              <Text>Heladeras</Text>
            </Box>
          </Link>
          {isAuthenticated && (
            <Box
              fontSize="lg"
              onClick={logout}
              cursor="pointer"
              display="flex"
              alignItems="center"
            >
              <Image src={Logout} alt="" width="30px" height="30px" mr={2} />
              <Text>Cerrar sesión</Text>
            </Box>
          )}
        </Flex>
      </Flex>

      {/* Drawer para el menú hamburguesa */}
      <Drawer placement="right" onClose={onHamburgerClose} isOpen={isHamburgerOpen}>
        <DrawerOverlay />
        <DrawerContent>
          <DrawerCloseButton />
          <DrawerHeader>Menu</DrawerHeader>
          <DrawerBody>
            <Flex
              direction="column"
              align="flex-start"
              justify="center"
              p={4}
            >
              {isAuthenticated ? (
                <>
                  <Box
                    onClick={handleUserClick} // Usar la función handleUserClick para abrir el modal
                    cursor="pointer"
                    display="flex"
                    alignItems="center"
                    mb={4}
                  >
                    <Image
                      src={user.picture || IcoProfile}
                      alt=""
                      width="25px"
                      height="25px"
                      borderRadius="full"
                      mr={2}
                    />
                    <Text fontSize="lg">{user.name}</Text>
                  </Box>
                </>
              ) : (
                <Box onClick={login} fontSize="lg" cursor="pointer" display="flex" alignItems="center" mb={4}>
                  <Image src={IcoProfile} alt="" width="30px" height="30px" mr={2} />
                  <Text>Ingresar</Text>
                </Box>
              )}
              <Link to="/map" onClick={handleNavLinkClick}>
                <Box fontSize="lg" cursor="pointer" display="flex" alignItems="center" mb={4}>
                  <Image src={IcoHeladera} alt="" width="30px" height="30px" mr={2} />
                  <Text>Heladeras</Text>
                </Box>
              </Link>
              {isAuthenticated && (
                <Box
                  fontSize="lg"
                  onClick={logout}
                  cursor="pointer"
                  display="flex"
                  alignItems="center"
                >
                  <Image src={Logout} alt="" width="30px" height="30px" mr={2} />
                  <Text>Cerrar sesión</Text>
                </Box>
              )}
            </Flex>
          </DrawerBody>
        </DrawerContent>
      </Drawer>

      <UserProfileModal isOpen={isUserModalOpen} onClose={onUserModalClose} />
    </>
  );
}

export default NavApp;
