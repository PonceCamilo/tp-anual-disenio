import React from 'react';
import ReactDOM from 'react-dom/client';
import './assets/styles/styles.scss';
import App from './App';
import { Auth0Provider } from '@auth0/auth0-react';
import { authConfig } from '../src/config/auth-config';
import reportWebVitals from './reportWebVitals';

// Función para manejar el redireccionamiento después de la autenticación
const onRedirectCallback = (appState) => {
  window.history.replaceState(
    {},
    document.title,
    appState?.returnTo || window.location.pathname
  );
};

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Auth0Provider
    domain={authConfig.domain}
    clientId={authConfig.clientId}
    redirectUri={window.location.origin}
    onRedirectCallback={onRedirectCallback} // Agrega la función de callback aquí
  >
    <App />
  </Auth0Provider>
);

// Medir rendimiento
reportWebVitals();
