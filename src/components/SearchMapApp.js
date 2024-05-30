import React, { useState } from 'react';
import { Button } from 'react-bootstrap';

function SearchMapApp({ onSearch }) {
  const [query, setQuery] = useState('');

  const handleInputChange = (e) => {
    setQuery(e.target.value);
  };

  const handleSearch = () => {
    onSearch(query);
  };

  return (
    <div style={{ display: 'flex', flexDirection: 'row', position: 'fixed', top: '100px', left: '200px', zIndex: 1000 }}>
      <input
        type="text"
        placeholder="Buscar una Heladera"
        value={query}
        onChange={handleInputChange}
      />
      <Button className='shadow-lg ms-3 ' variant="primary" onClick={handleSearch}>
        Buscar
      </Button>
    </div>
  );
}

export default SearchMapApp;
