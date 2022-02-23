import React, { Component } from 'react';

/**
 * This component represents the NavDropDownItem. It allows us to add links to 
 * a navigation drop down.
 */
export default class NavDropDownItem extends Component {
    constructor(props) {
        super(props);
    }

    /**
     * Takes in two properties. The name that will show for the link. The second is the
     * url for the link.
     * 
     * @returns JSX
     */
    render() {
        return (
            <li><a class="dropdown-item" href={this.props.url}>{this.props.name}</a></li>
        );
    }
}

