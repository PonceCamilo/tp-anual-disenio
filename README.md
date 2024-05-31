# tp-anual-diseño

Tecnologías Utilizadas

    Backend:
        Spring Boot
        Java 17

    Frontend:
        React
        React Bootstrap
        React Google Maps API

    Gestor de Paquetes:
        Yarn

Variables de Entorno

Para mantener segura nuestra API key, sigue estos pasos:

    En la raíz del proyecto(Front), crea un archivo llamado .env.local.

    Dentro, añade la siguiente línea:

    REACT_APP_GOOGLE_MAPS_API_KEY=(Key dentro del docs , ingresar con un mail .frba.utn.edu.ar , sino les va a pedir permisos)

https://docs.google.com/document/d/1q6Z1KfbiKVK4Epo6AxTKiZKfk3pDTCAnS13loOJOi_U/edit?usp=sharing

Prerrequisitos

    Java 17
    Node.js (incluye npm) 
    Yarn
Dependencias 
    Yarn add react-bootstrap
    yarn add @react-google-maps/api 

Clonar el Repositorio

    Clona el repositorio para el backend:

    Clona el repositorio para el front :


git clone https://github.com/PonceCamilo/tp-anual-disenio.git

Este contiene el backend en su raíz.

Clona el repositorio para el frontend:

En otra carpeta, vuelve a clonar el repositorio, ingresa en la carpeta tp-anual-disenio, abre una terminal y ejecuta:

    git checkout front-end

    Esto te permitirá tener una separación clara y un mejor control del backend y frontend a la hora de revisar.

Instalación de Dependencias

    Front:

    En la carpeta donde clonaste el front, instala las dependencias y ejecuta la aplicación utilizando:
    yarn add react-bootstrap
    yarn add @react-google-maps/api


En la carpeta donde clonaste el frontend (tp-anual-disenio con la rama front-end), abre una terminal y ejecuta:

    yarn start

Orden de Inicio

Para asegurar que el API esté corriendo antes de que el frontend lo solicite, inicia primero el backend y luego el frontend.
PD: El workspace del back tarda en cargar
