import React, { Component } from 'react';
import NavDropDownItem from './navdropdownitem/navdropdownitem';

/**
 * This component represents the NavDropDown item. It will allow us to create a sub navigation
 * on the top nav.
 */
export default class NavDropDown extends Component {
    constructor(props) {
        super(props);
    }

    /**
     * This will take in one property for the name. This name will show to the user on the page 
     * on the top level navigation. You could add NavDropDownItems to this method as well.
     * 
     * @returns JSX for NavDropDown
     */
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

