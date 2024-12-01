import React from 'react';
import { useAuth } from '../config/authContext';
import { Navigate } from 'react-router-dom';
import { Center, Spinner } from '@chakra-ui/react';
import { useState } from 'react';

const RoleProtectedRoute = ({ children, allowedRoles }) => {
  const { isAuthenticated, roles, login,user } = useAuth();
  const [isLoading] = useState(true);
  const data = localStorage.getItem('cached_roles');
  if (isAuthenticated === null) {
    return (
      <Center height="100vh">
          {isLoading ? (
              <Spinner size="xl" />
          ) : (
              <h1></h1>
          )}
      </Center>
  ); //  spinner de carga
  }
  
  if (isAuthenticated && !data) {  
    return <Navigate to="/persona-form" replace />;
  }
  if (!isAuthenticated) {
    login(); // Redirige al usuario al inicio de sesión si no está autenticado
    return null
  }
  
  if (user.email_verified === false) {  
    return <Navigate to="/Verificar-email" replace />;
  }
  // Verifica si el usuario tiene uno de los roles permitidos
  const hasRequiredRole = roles.some(role => allowedRoles.includes(role));
  if (!hasRequiredRole) {
    return <Navigate to="/acceso-denegado" replace />;
  }
  
  return children; // Permite el acceso si tiene el rol adecuado
};


export default RoleProtectedRoute;
