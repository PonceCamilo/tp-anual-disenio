// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyADxnYrm0bgBiNqJU5eRE51buQzRX9ZiIc",
  authDomain: "heladerasdds-217d5.firebaseapp.com",
  projectId: "heladerasdds-217d5",
  storageBucket: "heladerasdds-217d5.firebasestorage.app",
  messagingSenderId: "434421638324",
  appId: "1:434421638324:web:a922fe05589d9fea8ca3e1",
  measurementId: "G-7W521C88DP"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);