import React, { Component } from 'react';

export default class NavDropDownItem extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <li><a class="dropdown-item" href={this.props.url}>{this.props.name}</a></li>
        );
    }
}

