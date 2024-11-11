import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Outlet, Link } from 'react-router-dom';
import Navigation from "../components/nav/navigation.js"

class Layout extends Component {
    render() {
        return (
            <div id="layout-page-root">
                <Navigation />
                <Outlet />
            </div>
        );
    }
}

export default Layout;