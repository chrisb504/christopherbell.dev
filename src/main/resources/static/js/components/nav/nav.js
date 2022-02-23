import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import NavDropDown from './navitems/navdropdown/navdropdown.js';
import NavStdItem from "./navitems/navstditem/navstditem.js";

/*
    This component represents the top navigation for the site. It includes the top level
    Nav component and two components to represent NavItems. One being a NavStdItem,
    which is the standard navigation item. The second being the NavDropItem, which
    represents a navigation item that can include a sub navigation.
*/
export default class Nav extends Component {
    // Below is a set of JSX that will be rendered into html.
    render() {
        return (
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/">Home</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            {/* Usage of a NavStdItem */}
                            <NavStdItem name="Blog" url="/blog" />
                            {/* Usage of a NavDropDown */}
                            <NavDropDown name="Photography" />
                            <NavStdItem name="SMP" url="/smp" />
                            <NavStdItem name="Survive" url="/survive" />
                        </ul>
                    </div>
                </div>
            </nav>
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
    if (document.body.contains(document.getElementById('nav'))) {
        // Used to render the component on the page. The default refresh is when state is changed.
        ReactDOM.render(<Nav />, document.getElementById('nav'));
    }
});
