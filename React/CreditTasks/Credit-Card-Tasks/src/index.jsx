import React from "react";
import ReactDOM from "react-dom/client";
import PaymentApp from "./App";
import CardInfo from "./CreditCard";
import DailyTasks from "./AddTask";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "./login";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    path: "/payment",
    element: <PaymentApp />,
  },
  {
    path: "cardinfo",
    element: <CardInfo />,
  },
  {
    path: "tasks",
    element: <DailyTasks />,
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
