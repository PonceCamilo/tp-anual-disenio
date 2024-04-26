import React, { useEffect } from 'react';
import './css/FoodAnimation.css';

const FoodAnimation = () => {
    useEffect(() => {
        const foods = document.querySelectorAll('.food');
        const box = document.querySelector('.box');
        let timeoutId;

        foods.forEach(food => {
            const observer = new IntersectionObserver(entries => {
                entries.forEach(entry => {
                    if (entry.isIntersecting) {
                        // Detener la animación del alimento al chocar con la caja
                        food.classList.add('paused-animation');
                        // Esperar 3 segundos antes de remover la clase 'box' y agregar la clase 'box-with-food' a la caja
                        timeoutId = setTimeout(() => {
                           box.classList.remove('box');
                           box.classList.remove('falling-food-container');
                           box.classList.add('box-with-food');
                        }, 2000);
                    }
                });
            });
            observer.observe(food);
        });

        return () => {
            clearTimeout(timeoutId);
        };
    }, []);

    return (
        <div className="vacio">
            <div className="falling-food-container">
                <div className="food food1"></div>
                <div className="food food2"></div>
                <div className="food food3"></div>
                <div className="food food4"></div>
            </div>
            <div className="cajita">
                <div className="box"></div>
            </div>
        </div>
    );
};

export default FoodAnimation;