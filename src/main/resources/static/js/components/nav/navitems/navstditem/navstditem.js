import React, { Component } from 'react';

/** 
 *   This component represents the standard NavItem. You would use this component if you want to
 *   have a simple link appear on the navigation.
 *
 *   For example, one might exist for the Blog page. That would allow us to link out to the Blog 
 *   page.
 */
export default class NavStdItem extends Component {
    constructor(props) {
        super(props);
    }

    /**
     * This is the JSX for a NavStdItem. It takes two properties. One for the URL and one for the 
     * name of the link that will show for the user.
     * 
     * @returns JSX for a NavStdItem
     */
    render() {
        return (
            <li class="nav-item">
                <a class="nav-link" href={this.props.url}>{this.props.name}</a>
            </li>
        );
    }
}

