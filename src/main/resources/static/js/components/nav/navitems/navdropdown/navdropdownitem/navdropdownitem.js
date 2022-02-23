import React, { Component } from 'react';
import ReactDOM from 'react-dom';

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

/* 
    This seems to be important from my findings. If the page doesn't include that component,
    then it will still try to render without the EventListener. This will cause React to throw
    an error about not finding the DOM item. This method prevents the render function from
    loading if the component isn't on the page.

    I think there might be a better solution to this. I think that solution might include
    not including the js for smaller components in Main.js
*/
window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('navDropDownItem'))) {
        // Used to render the component on the page. The default refresh is when state is changed.
        ReactDOM.render(<NavDropDownItem />, document.getElementById('navDropDownItem'));
    }
});
