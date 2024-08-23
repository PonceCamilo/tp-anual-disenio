import { useEffect } from 'react';
import { error } from '@pnotify/core';
import "@pnotify/core/dist/PNotify.css";
import "@pnotify/core/dist/BrightTheme.css";
import "@pnotify/confirm/dist/PNotifyConfirm.css";

const NotifyError = ({ message }) => {
  useEffect(() => {
    if (message) {
      const notification = error({
        title: 'Error!',
        text: message,
        delay: 3000, // Duración en milisegundos
      });
      return;
    }
  }, [message]);

  return null;
};

export default NotifyError;
