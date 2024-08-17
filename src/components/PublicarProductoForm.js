import React, { useState } from 'react';
import '../assets/styles/PublicarProductoForm.css';

function PublicarProductoForm() {
    const [product, setProduct] = useState({
        name: '',
        description: '',
        points: 0,
        image: null
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setProduct({
            ...product,
            [name]: value
        });
    };

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setProduct({
                ...product,
                image: file
            });
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        // Crear un FormData para enviar la imagen junto con el resto de los datos
        const formData = new FormData();
        formData.append('name', product.name);
        formData.append('description', product.description);
        formData.append('points', product.points);
        if (product.image) {
            formData.append('image', product.image);
        }

        // Aquí puedes hacer una petición para enviar los datos al servidor
        fetch('/api/publicar-producto', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                console.log('Producto publicado:', data);
                // Manejar la respuesta del servidor
            })
            .catch(error => {
                console.error('Error al publicar el producto:', error);
            });
    };

    return (
        <div className="publicar-producto-form-container">
            <h2>Publicar Producto/Servicio</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="name">Nombre</label>
                    <input
                        type="text"
                        id="name"
                        name="name"
                        value={product.name}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="description">Descripción</label>
                    <textarea
                        id="description"
                        name="description"
                        value={product.description}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="points">Puntos necesarios</label>
                    <input
                        type="number"
                        id="points"
                        name="points"
                        value={product.points}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="image">Selecciona una imagen</label>
                    <input
                        type="file"
                        id="image"
                        name="image"
                        accept="image/*"
                        onChange={handleImageChange}
                    />
                </div>
                <button type="submit">Publicar</button>
            </form>
        </div>
    );
}

export default PublicarProductoForm;