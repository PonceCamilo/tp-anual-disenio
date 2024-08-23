import { useEffect } from 'react';
import { success } from '@pnotify/core';
import "@pnotify/core/dist/PNotify.css";
import "@pnotify/core/dist/BrightTheme.css";
import "@pnotify/confirm/dist/PNotifyConfirm.css";

const NotifyAlert = ({ message,title }) => {
    useEffect(() => {
      if (message) {
        success({
          title: title,
          text: message,
        });
      }
    }, [message]);

  return null;
};

export default NotifyAlert;