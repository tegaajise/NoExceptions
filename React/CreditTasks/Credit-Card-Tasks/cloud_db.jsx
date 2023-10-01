// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries
import { collection, getFirestore } from "firebase/firestore"

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyCVcPUnH225izoROuqeaG05yIfZBITvhnU",
  authDomain: "task-app-72096.firebaseapp.com",
  projectId: "task-app-72096",
  storageBucket: "task-app-72096.appspot.com",
  messagingSenderId: "153402349356",
  appId: "1:153402349356:web:7dbe919c94d60771dd7e89"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
// get database
const data = getFirestore(app)

export const usersCollection = collection(data, "users")