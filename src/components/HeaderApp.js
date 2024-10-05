import React, { useRef } from 'react';
import { Box, Flex, Heading, Text, Button, Image, useBreakpointValue } from '@chakra-ui/react';
import HeaderImage from '../assets/imgs/Header.png';

function HeaderApp() {
  const headerRef = useRef(null);
  const isMobile = useBreakpointValue({ base: true, md: false });

  return (
    <Flex
      ref={headerRef}
      className='header-container'
      direction={{ base: 'column', md: 'row' }}
      align='center'
      justify='center'
      minH='100vh'
      bg='transparent'
      p={8}
    >
      <Box
        className='title-container'
        textAlign={isMobile ? 'center' : 'left'}
        mb={isMobile ? 6 : 0}
      >
        <Heading as='h1' size='2xl' mb={4} color='gray.800'>
          Heladeras <br /> Comunitarias
        </Heading>
        <Text fontSize='xl' color='gray.600' maxW='lg' mb={6}>
          Un espacio solidario para compartir alimentos con quienes más lo necesitan.
        </Text>
        <Flex direction={isMobile ? 'column' : 'row'} gap={3} width='100%' justify='center'>
          <Button
            colorScheme='gray'
            bg='gray.800'
            size='lg'
            color='white'
            _hover={{ bg: 'gray.700' }}
            mb={isMobile ? 4 : 0}
          >
            Doná
          </Button>
          <Button
            colorScheme='gray'
            bg='gray.800'
            size='lg'
            color='white'
            _hover={{ bg: 'gray.700' }}
          >
            Sé Voluntario
          </Button>
        </Flex>
      </Box>

      <Box
        className='image-container'
        borderRadius='full'
        border='6px solid #15ffcc'
        display={isMobile ? 'none' : 'block'} 
        maxW='500px' 
        width='100%' 
        justifySelf='center' 
      >
        <Image
          src={HeaderImage}
          alt='Descripción'
          boxSize='100%' // Ajustar al contenedor
          objectFit='cover'
          borderRadius='full'
        />
      </Box>
    </Flex>
  );
}

export default HeaderApp;
