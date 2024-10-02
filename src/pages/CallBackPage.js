import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const CallbackPage = () => {
    const navigate = useNavigate();  // Reemplaza useHistory con useNavigate

    useEffect(() => {
        const urlParams = new URLSearchParams(window.location.search);
        const code = urlParams.get('code');

        if (code) {
            // Hacer el fetch al backend con el código de autorización
            fetch('http://localhost:8080/callback?code=' + code, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            })
            .then(response => response.json())
            .then(data => {
                const accessToken = data.access_token;
                // Guardar el access token en localStorage
                localStorage.setItem('access_token', accessToken);

                // Decodificar el token para obtener los roles
                const tokenPayload = JSON.parse(atob(accessToken.split('.')[1]));
                const userRoles = tokenPayload['scope'] ? tokenPayload['scope'].split(' ').filter(role => role) : []; // Ajusta esta key según cómo estén definidos los roles en el token

                // Guardar los roles en localStorage
                localStorage.setItem('user_roles', JSON.stringify(userRoles));
                //añado un tiempo de espera 

                // Obtener la información del usuario utilizando el access token
                return fetch(process.env.REACT_APP_AUTH0_DOMAIN + '/userinfo', {
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                    }
                });
            })
            .then(userResponse => userResponse.json())
            .then(userProfile => {
                // Guardar el perfil del usuario en localStorage
                localStorage.setItem('user_profile', JSON.stringify(userProfile));

                // Redirigir a la página principal
                navigate('/');
                window.location.reload();
            })
            .catch((error) => {
                console.error('Error al intercambiar el código por un token:', error);
            });
        }
    }, [navigate]);

    return (
        <div>
            <h1>Callback</h1>
        </div>
    );
};

export default CallbackPage;
