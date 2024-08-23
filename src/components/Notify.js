import { useEffect } from 'react';
import { success } from '@pnotify/core';
import "@pnotify/core/dist/PNotify.css";
import "@pnotify/core/dist/BrightTheme.css";
import "@pnotify/confirm/dist/PNotifyConfirm.css";

const Notify = ({ message }) => {
  useEffect(() => {
    if (message) {
      const notification = success({
        title: 'Success!',
        text: message,
        delay: 3000, 
      })
      return; 
    }
  }, [message]);

  return null;
};

export default Notify;
