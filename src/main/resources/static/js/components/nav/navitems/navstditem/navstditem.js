import React, { Component } from 'react';
import ReactDOM from 'react-dom';

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

/* 
    This seems to be important from my findings. If the page doesn't include that component,
    then it will still try to render without the EventListener. This will cause React to throw
    an error about not finding the DOM item. This method prevents the render function from
    loading if the component isn't on the page.

    I think there might be a better solution to this. I think that solution might include
    not including the js for smaller components in Main.js
*/
window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('navStdItem'))) {
        // Used to render the component on the page. The default refresh is when state is changed.
        ReactDOM.render(<NavStdItem />, document.getElementById('navStdItem'));
    }
});
