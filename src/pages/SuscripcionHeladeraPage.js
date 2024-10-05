import React from 'react';
import SuscripcionHeladeraContent from '../components/SuscripcionHeladeraContent';

import { Box } from '@chakra-ui/react';
function SuscripcionHeladeraPage() {
    return (
        <Box 
            paddingTop="70px" 
            minHeight="100vh" 
            display="flex" 
            flexDirection="column" 
            justifyContent="center" 
            alignItems="center"
        >
            <SuscripcionHeladeraContent />
        </Box>
    );
}

export default SuscripcionHeladeraPage;
