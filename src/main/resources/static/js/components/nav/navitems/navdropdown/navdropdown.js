import React, { Component } from 'react';
import NavDropDownItem from './navdropdownitem/navdropdownitem';

export default class NavDropDown extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    {this.props.name}
                </a>
                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                    <NavDropDownItem name="Gallery" url="/photography"/>
                    <NavDropDownItem name="Usage Notice" url="/photography/usage"/>
                </ul>
            </li>
        );
    }
}

