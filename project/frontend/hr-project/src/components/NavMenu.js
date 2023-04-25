import React, { Component } from "react";
import {
  Collapse,
  Container,
  Navbar,
  NavbarBrand,
  NavbarToggler,
  NavItem,
  NavLink,
} from "reactstrap";
import { Link } from "react-router-dom";
import "./NavMenu.css";

export class NavMenu extends Component {
  static displayName = NavMenu.name;

  constructor(props) {
    super(props);

    this.toggleNavbar = this.toggleNavbar.bind(this);
    this.state = {
      collapsed: true,
    };
  }

  toggleNavbar() {
    this.setState({
      collapsed: !this.state.collapsed,
    });
  }

  render() {
    return (
      <header>
        <Navbar className="navbar-container">
          <Container>
            <NavbarBrand tag={Link} to="/">
              <span className="navbar-title">
                HR Platform
              </span>
            </NavbarBrand>
            <Collapse
              className="d-sm-inline-flex flex-sm-row-reverse"
              isOpen={!this.state.collapsed}
              navbar
            >
              <div className="nav-item-container">
                <NavItem>
                  <NavLink tag={Link} className="navlink" to="/">
                    Home
                  </NavLink>
                </NavItem>
                <NavItem>
                  <NavLink tag={Link} className="navlink" to="/add-candidate">
                    Add Candidate
                  </NavLink>
                </NavItem>
              </div>
            </Collapse>
          </Container>
        </Navbar>
      </header>
    );
  }
}
