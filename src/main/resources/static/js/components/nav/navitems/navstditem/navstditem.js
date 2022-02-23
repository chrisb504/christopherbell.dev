import React, { Component } from 'react';

export default class NavStdItem extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <li class="nav-item">
                <a class="nav-link" href={this.props.url}>{this.props.name}</a>
            </li>
        );
    }
}

