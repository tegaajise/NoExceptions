import React from "react";
import ReactDOM from "react-dom/client";
// import App from "./App.jsx";
import App from "./Home.jsx";
import Spirituality from "./Spirituality.jsx";
import Learning from "./Learning.jsx";
import Food from "./Food.jsx";
import Water from "./Water.jsx";
import Sleep from "./Sleep.jsx";
import SetupForm from "./SetupForm.jsx";
import "./index.css";
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import "./index.css";

const router = createBrowserRouter([
  {
    path: "/",
    element: <SetupForm/>
  },
  {
    path: "/sleep",
    element: <Sleep/>
  },
  {
    path: "/food",
    element: <Food/>
  },
  {
    path: "/water",
    element: <Water/>
  },
  {
    path: "/learning",
    element: <Learning/>
  },
  {
    path: "/spirituality",
    element: <Spirituality/>
  }
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
