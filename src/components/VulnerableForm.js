import React, { useState } from 'react';
import {
    FormControl,
    FormLabel,
    Input,
    Button,
    Checkbox,
    Stack,
    Select,
    NumberInput,
    NumberInputField,
    Text
} from '@chakra-ui/react';

const VulnerableForm = () => {
    const [formData, setFormData] = useState({
        name: '',
        LastName: '',
        birthDate: '',
        registrationDate: '',
        isHomeless: false,
        address: '',
        documentType: '',
        documentNumber: '',
        hasMinors: false,
        minorsCount: ''
    });

    const handleInputChange = (event) => {
        const { name, value, type, checked } = event.target;
        setFormData({
            ...formData,
            [name]: type === 'checkbox' ? checked : value
        });

        
        setTimeout(() => window.dispatchEvent(new Event('resize')), 0);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        
        const currentTimestamp = new Date().toISOString();

        
        const dataToSubmit = {
            ...formData,
            registrationDate: currentTimestamp
        };

        console.log("Datos a enviar:", dataToSubmit);
        
    };

    return (
        <form onSubmit={handleSubmit}>
            <Stack spacing={4}>
                <FormControl id="name" isRequired>
                    <FormLabel>Nombre</FormLabel>
                    <Input
                        type="text"
                        placeholder="Ingrese su nombre"
                        name="name"
                        value={formData.name}
                        onChange={handleInputChange}
                    />
                </FormControl>

                <FormControl id="lastName" isRequired>
                    <FormLabel>Apellido</FormLabel>
                    <Input
                        type="text"
                        placeholder="Ingrese su Apellido"
                        name="LastName"
                        value={formData.LastName}
                        onChange={handleInputChange}
                    />
                </FormControl>

                <FormControl id="birthDate" isRequired>
                    <FormLabel>Fecha de Nacimiento</FormLabel>
                    <Input
                        type="date"
                        name="birthDate"
                        value={formData.birthDate}
                        onChange={handleInputChange}
                    />
                </FormControl>

                <FormControl id="isHomeless">
                    <Checkbox
                        name="isHomeless"
                        isChecked={formData.isHomeless}
                        onChange={handleInputChange}
                    >
                        ¿En situación de calle?
                    </Checkbox>
                </FormControl>

                {!formData.isHomeless && (
                    <FormControl id="address">
                        <FormLabel>Domicilio</FormLabel>
                        <Input
                            type="text"
                            placeholder="Ingrese su domicilio"
                            name="address"
                            value={formData.address}
                            onChange={handleInputChange}
                        />
                    </FormControl>
                )}

                <FormControl id="documentType" isRequired>
                    <FormLabel>Tipo de Documento</FormLabel>
                    <Select
                        name="documentType"
                        value={formData.documentType}
                        onChange={handleInputChange}
                    >
                        <option value="">Seleccione...</option>
                        <option value="DNI">DNI</option>
                        <option value="Pasaporte">Pasaporte</option>
                        <option value="Otro">Otro</option>
                    </Select>
                </FormControl>

                <FormControl id="documentNumber" isRequired>
                    <FormLabel>Número de Documento</FormLabel>
                    <Input
                        type="text"
                        placeholder="Ingrese su número de documento"
                        name="documentNumber"
                        value={formData.documentNumber}
                        onChange={handleInputChange}
                    />
                </FormControl>

                <FormControl id="hasMinors">
                    <Checkbox
                        name="hasMinors"
                        isChecked={formData.hasMinors}
                        onChange={handleInputChange}
                    >
                        ¿Posee menores a cargo?
                    </Checkbox>
                </FormControl>

                {formData.hasMinors && (
                    <FormControl id="minorsCount">
                        <FormLabel>Cantidad de menores a cargo</FormLabel>
                        <NumberInput
                            name="minorsCount"
                            value={formData.minorsCount}
                            onChange={(valueString) =>
                                setFormData({ ...formData, minorsCount: valueString })
                            }
                        >
                            <NumberInputField placeholder="Ingrese la cantidad de menores" />
                        </NumberInput>
                    </FormControl>
                )}

                <Button colorScheme="green" type="submit">
                    Registrar
                </Button>
            </Stack>
        </form>
    );
};

export default VulnerableForm;
