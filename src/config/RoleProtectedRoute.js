import React from 'react';
import { useAuth } from '../config/authContext';
import { Navigate } from 'react-router-dom';

const RoleProtectedRoute = ({ children, allowedRoles }) => {
  const { isAuthenticated, roles, login } = useAuth();

  if (!isAuthenticated) {
    login(); // Redirige al login si no está autenticado
    return null;
  }

  // Verifica si el usuario tiene uno de los roles permitidos
  const hasRequiredRole = roles.some(role => allowedRoles.includes(role));

  if (!hasRequiredRole) {
    // Redirige a una página de "acceso denegado" o cualquier otro lugar
    return <Navigate to="/acceso-denegado" replace />;
  }

  return children; // Permite el acceso si tiene el rol adecuado
};

export default RoleProtectedRoute;
