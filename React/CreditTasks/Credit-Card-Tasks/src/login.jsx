import React, { useState, useEffect } from "react";
import "./Login.css";
import { usersCollection } from "../cloud_db";
import { addDoc, onSnapshot } from "firebase/firestore";

// Main problems
/* Login features only properly cooperate when you submit button a second time */
/* Logic for checking if login worked (i.e. the if-else statement) */

const Login = () => {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });
  const [errorMessage, setErrorMessage] = useState("");
  // Holds the id
  const [loggedInUser, setLoggedInUser] = useState();
  const [storedUsers, setStoredUsers] = useState();
  const [userExists, setUserExists] = useState(false);

  useEffect(() => {
    const disconnect = onSnapshot(usersCollection, (snapshot) => {
      setStoredUsers(snapshot.docs);
    });
    return disconnect;
  }, []);

  const handleLogin = (event) => {
    event.preventDefault();
    setUserExists(false);

    const currentUser = checkUser();

    const selectedButton = document.activeElement;
    if (selectedButton.innerHTML === "Login") {
      credentialsCheck(currentUser);
    } else {
      console.log("test");
      createNewUser();
    }
  };

  function checkUser() {
    for (const doc of storedUsers) {
      if (doc.data().username === credentials.username) {
        setUserExists(true);
        return doc;
      }
    }
  }

  function appEntrance() {
    window.location.href = "/payment";
  }

  function credentialsCheck(account) {
    if (!userExists) {
      setErrorMessage("Invalid username or password");
      return;
    }
    if (account.data().password === credentials.password) {
      appEntrance;
      setLoggedInUser(account.id);
    } else {
      setErrorMessage("Invalid username or password");
    }
  }

  async function createNewUser() {
    setErrorMessage(`${userExists ? "User already taken" : ""}`);
    if (!userExists) {
      const newNoteRef = await addDoc(usersCollection, credentials);
      appEntrance;
      setLoggedInUser(newNoteRef.id);
    }
  }

  return (
    <div className="login-container">
      <h1>Login</h1>
      <form onSubmit={handleLogin}>
        <input
          type="text"
          placeholder="Username"
          value={credentials.username}
          onChange={(e) =>
            setCredentials({ ...credentials, username: e.target.value })
          }
        />
        <input
          type="password"
          placeholder="Password"
          value={credentials.password}
          onChange={(e) =>
            setCredentials({ ...credentials, password: e.target.value })
          }
        />
        <button type="submit" name="login">
          Login
        </button>
        <button type="submit" name="create-account">
          Create Account
        </button>
        <p className="error-message">{errorMessage}</p>
      </form>
    </div>
  );
};

export default Login;
