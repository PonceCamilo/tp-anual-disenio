import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';

function SearchMapApp({ onSearch }) {
  const [query, setQuery] = useState('');

  const handleInputChange = (e) => {
    setQuery(e.target.value);
  };

  const handleSearch = () => {
    onSearch(query);
  };

  return (
    <Container fluid className="d-flex justify-content-center fixed-top" style={{ top: '100px' }}>
      <div className="d-flex align-items-center">
        <input
          type="text"
          placeholder="Buscar una Heladera"
          value={query}
          onChange={handleInputChange}
          className="form-control me-2"
        />
        <Button variant="dark" onClick={handleSearch}>Buscar</Button>
        
      </div>
    </Container>
  );
}

export default SearchMapApp;
