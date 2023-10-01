import React from "react";
import { Link } from "react-router-dom";
import { Container, Nav, Navbar } from "react-bootstrap";
import "./App.css";

const tabs = ["SLEEP", "FOOD", "WATER", "LEARNING", "SPIRITUALITY"];

function NavHeaders() {
  const theTabs = tabs.map((name) => (
    <>
      <Navbar className="bg-body-tertiary">
        <Container>
          <Navbar.Brand href={`/${name}`} className="menu-item">
            {name}
          </Navbar.Brand>
        </Container>
      </Navbar>
      <br />
    </>
  ));
  return theTabs;
}

function App() {
  return <NavHeaders />;
}

export default App;
