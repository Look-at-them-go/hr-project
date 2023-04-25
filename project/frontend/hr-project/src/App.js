import React, { Component } from "react";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import { Home } from "./components/Home";
import { AddCandidate } from "./components/AddCandidate";
import { NavMenu } from "./components/NavMenu";

import "./custom.css";

export default class App extends Component {
  static displayName = App.name;

  render() {
    return (
      <BrowserRouter>
      <NavMenu/>
        <Routes>
          <Route exact path="/" element={<Home/>} />
          <Route path="/add-candidate" element={<AddCandidate/>} />
        </Routes>
      </BrowserRouter>
    );
  }
}
