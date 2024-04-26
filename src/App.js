import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import NavApp from './components/NavApp';
import HeaderApp from './components/HeaderApp';
import BodyApp from './components/BodyApp';
import MapApp from './components/MapApp';

function App() {
  return (
    <Router>
      <NavApp />
      <div>
        <Routes>
          <Route path="/" element={<HeaderApp/>}/>
          <Route path="/map" element={<MapApp />} />
        </Routes>
      </div>
      
    </Router>
  );
}

export default App;

