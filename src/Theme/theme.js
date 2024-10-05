import { extendTheme } from '@chakra-ui/react';
import Bg from '../assets/imgs/bg.jpg';
import '@fontsource/manrope';

const theme = extendTheme({
  styles: {
    global: {
      'html, body': {
        height: '100%',
        margin: 0,
        padding: 0,
        backgroundImage: `url('${Bg}')`, // Cambia esto a la ruta correcta
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat',
      },
    },
  },
  fonts: {
    heading: 'Manrope, sans-serif',
    body: 'Manrope, sans-serif',
  },
});

export default theme;
