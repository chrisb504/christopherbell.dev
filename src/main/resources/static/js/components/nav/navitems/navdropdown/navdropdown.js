import React, { Component } from 'react';
import ReactDOM from 'react-dom';
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

/* 
    This seems to be important from my findings. If the page doesn't include that component,
    then it will still try to render without the EventListener. This will cause React to throw
    an error about not finding the DOM item. This method prevents the render function from
    loading if the component isn't on the page.

    I think there might be a better solution to this. I think that solution might include
    not including the js for smaller components in Main.js
*/
window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('navDropDown'))) {
        // Used to render the component on the page. The default refresh is when state is changed.
        ReactDOM.render(<NavDropDown />, document.getElementById('navDropDown'));
    }
});
