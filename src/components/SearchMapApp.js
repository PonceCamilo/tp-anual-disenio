import React, { useState } from 'react';
import { Box, Input, Button } from '@chakra-ui/react';

function SearchMapApp({ onSearch }) {
  const [query, setQuery] = useState('');

  const handleInputChange = (e) => {
    setQuery(e.target.value);
  };

  const handleSearch = () => {
    onSearch(query);
  };

  return (
    <Box 
      position="fixed" 
      display="flex"
      top="70px" // Ajusta según el espaciado que necesites
      left="50%" 
      transform="translateX(-50%)" 
      zIndex="10" // Asegúrate de que esté sobre el mapa
      width="80%" // Ajusta el ancho como necesites
      maxWidth="600px" // Tamaño máximo opcional
    >
      <Input
        placeholder="Buscar una Heladera"
        value={query}
        onChange={handleInputChange}
        bg="white"
        mb={2}
        mr={2}
      />
      <Button variant="solid" colorScheme="green" onClick={handleSearch}>
        Buscar
      </Button>
    </Box>
  );
}

export default SearchMapApp;
