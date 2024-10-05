import React from 'react';
import { useAuth } from '../config/authContext';
import { Navigate } from 'react-router-dom';

const RoleProtectedRoute = ({ children, allowedRoles }) => {
  const { isAuthenticated, roles, login } = useAuth();

  // Mostrar un componente de carga mientras se verifica la autenticación
  if (isAuthenticated === null) {
    return <div>Cargando...</div>; // O un spinner de carga
  }

  if (!isAuthenticated) {
    return <Navigate to="/acceso-denegado" replace />;
  }

  // Verifica si el usuario tiene uno de los roles permitidos
  const hasRequiredRole = roles.some(role => allowedRoles.includes(role));

  if (!hasRequiredRole) {
    return <Navigate to="/acceso-denegado" replace />;
  }

  return children; // Permite el acceso si tiene el rol adecuado
};


export default RoleProtectedRoute;
