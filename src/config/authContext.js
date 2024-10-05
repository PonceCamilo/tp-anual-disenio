import React, { createContext, useContext, useState, useEffect } from 'react';
import { jwtDecode } from 'jwt-decode';

const AuthContext = createContext();

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(null);
  const [user, setUser] = useState(null);
  const [roles, setRoles] = useState([]);
  const [accessToken, setAccessToken] = useState(null);

  useEffect(() => {
    const storedAccessToken = localStorage.getItem('access_token');
    const userProfile = localStorage.getItem('user_profile');
    const userRoles = localStorage.getItem('user_roles');

    if (storedAccessToken && userProfile) {
      setIsAuthenticated(true);
      setAccessToken(storedAccessToken);
      setUser(JSON.parse(userProfile));
      setRoles(JSON.parse(userRoles));

      // Check token expiration
      try {
        const { exp } = jwtDecode(storedAccessToken);
        const currentTime = Date.now() / 1000;

        if (exp < currentTime) {
          // Token has expired, log out
          logout();
        }
      } catch (error) {
        console.error('Error decoding token:', error);
        logout();
      }
    } else {
      setIsAuthenticated(false);
      setUser(null);
    }
  }, []);

  const login = () => {
    const auth0Domain = process.env.REACT_APP_AUTH0_DOMAIN;
    const clientId = process.env.REACT_APP_AUTH0_CLIENT_ID;
    const redirectUri = process.env.REACT_APP_AUTH0_REDIRECT_URI; 
    const audience = process.env.REACT_APP_AUTH0_AUDIENCE;
    const scope = 'openid profile email ROLE_ADMIN ROLE_COLLABORATOR ROLE_VULNERABLE ROLE_TECHNICAL';

    const loginUrl = `${auth0Domain}/authorize?response_type=code&client_id=${clientId}&redirect_uri=${encodeURIComponent(redirectUri)}&scope=${encodeURIComponent(scope)}&audience=${encodeURIComponent(audience)}&prompt=login`;
    window.location.href = loginUrl;
  };

  const logout = () => {
    localStorage.removeItem('access_token');
    localStorage.removeItem('user_profile');
    localStorage.removeItem('user_roles');
    setIsAuthenticated(false);
    setUser(null);
    setAccessToken(null);
    setRoles([]);
  };

  return (
    <AuthContext.Provider value={{ accessToken, isAuthenticated, user, roles, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
